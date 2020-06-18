package com.lc.lang;

import com.lc.utils.UClassLoaderUtil;
import com.lc.utils.UDateUtil;
import com.lc.utils.URandomUtil;

import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * MongoDB ID生成策略实现<br>
 * <p>
 * ObjectId由以下几部分组成：
 * <pre>
 * 1. Time 时间戳。
 * 2. Machine 所在主机的唯一标识符，一般是机器主机名的散列值。
 * 3. PID 进程ID。确保同一机器中不冲突
 * 4. INC 自增计数器。确保同一秒内产生objectId的唯一性。
 * </pre>
 * <p>
 * 参考：http://blog.csdn.net/qxc1281/article/details/54021882
 *
 * @author LC
 * @since 0.1
 */
public class ObjectId {
    /**
     * 线程安全的下一个随机数,每次生成自增+1
     */
    private static final AtomicInteger nextInc = new AtomicInteger(URandomUtil.randomInt());
    /**
     * 机器信息
     */
    private static final int machine = getMachinePiece() | getProcessPiece();

    /**
     * 获取一个objectId的bytes表现形式
     *
     * @return objectId
     * @since 4.1.15
     */
    public static byte[] nextBytes() {
        final ByteBuffer bb = ByteBuffer.wrap(new byte[12]);
        bb.putInt((int) UDateUtil.currentSeconds());// 4位
        bb.putInt(machine);// 4位
        bb.putInt(nextInc.getAndIncrement());// 4位

        return bb.array();
    }

    /**
     * 获取一个objectId
     *
     * @param withHyphen 是否包含分隔符
     * @return objectId
     */
    public static String next(boolean withHyphen) {
        byte[] array = nextBytes();
        final StringBuilder buf = new StringBuilder(withHyphen ? 26 : 24);
        int t;
        for (int i = 0; i < array.length; i++) {
            if (withHyphen && i % 4 == 0 && i != 0) {
                buf.append("-");
            }
            t = array[i] & 0xff;
            if (t < 16) {
                buf.append('0');
            }
            buf.append(Integer.toHexString(t));

        }
        return buf.toString();
    }

    /**
     * 获取一个objectId用下划线分割
     *
     * @return objectId
     */
    public static String next() {
        return next(false);
    }

    // ----------------------------------------------------------------------------------------- Private method start

    /**
     * 获取机器码片段
     *
     * @return 机器码片段
     */
    private static int getMachinePiece() {
        // 机器码
        int machinePiece;
        try {
            StringBuilder netSb = new StringBuilder();
            // 返回机器所有的网络接口
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            // 遍历网络接口
            while (e.hasMoreElements()) {
                NetworkInterface ni = e.nextElement();
                // 网络接口信息
                netSb.append(ni.toString());
            }
            // 保留后两位
            machinePiece = netSb.toString().hashCode() << 16;
        } catch (Throwable e) {
            // 出问题随机生成,保留后两位
            machinePiece = (URandomUtil.randomInt()) << 16;
        }
        return machinePiece;
    }

    /**
     * 获取进程码片段
     *
     * @return 进程码片段
     */
    private static int getProcessPiece() {
        // 进程码
        // 因为静态变量类加载可能相同,所以要获取进程ID + 加载对象的ID值
        final int processPiece;
        // 进程ID初始化
        int processId;
        try {
            // 获取进程ID
            final String processName = ManagementFactory.getRuntimeMXBean().getName();
            final int atIndex = processName.indexOf('@');
            if (atIndex > 0) {
                processId = Integer.parseInt(processName.substring(0, atIndex));
            } else {
                processId = processName.hashCode();
            }
        } catch (Throwable t) {
            processId = URandomUtil.randomInt();
        }

        final ClassLoader loader = UClassLoaderUtil.getClassLoader();
        // 返回对象哈希码,无论是否重写hashCode方法
        int loaderId = (loader != null) ? System.identityHashCode(loader) : 0;

        // 进程ID + 对象加载ID
        StringBuilder processSb = new StringBuilder();
        processSb.append(Integer.toHexString(processId));
        processSb.append(Integer.toHexString(loaderId));
        // 保留前2位
        processPiece = processSb.toString().hashCode() & 0xFFFF;

        return processPiece;
    }
    // ----------------------------------------------------------------------------------------- Private method end
}
