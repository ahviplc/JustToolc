# JustToolc项目日志和代码库

## 完善工具类日志和完善JustToolc项目时 摘出来的重复代码 代码示例

### UConsole 完善了 ok
> 完善时间: 2020年6月18日14:25:40

### UDict 完善了 ok
> 完善时间: 2020年6月18日14:01:50

### UStringUtil 完善了 ok
> 完善时间: 2020年6月18日13:40:36

### UReUtil 完善了 ok
> 完善时间: 2020年6月18日14:01:50

### UUnicodeUtil 完善了 ok
> 完善时间: 2020年6月18日14:05:01

### UCompareUtil 完善了 ok
> 完善时间: 2020年6月18日13:30:33

### UBooleanUtil 完善了 ok
> 完善时间: 2020年6月18日13:53:03

> 废弃方法(含@Deprecated注释示例)
```java
  /**
     * 对Boolean数组取与
     *
     * <pre>
     *   UBooleanUtil.and(Boolean.TRUE, Boolean.TRUE)                 = Boolean.TRUE
     *   UBooleanUtil.and(Boolean.FALSE, Boolean.FALSE)               = Boolean.FALSE
     *   UBooleanUtil.and(Boolean.TRUE, Boolean.FALSE)                = Boolean.FALSE
     *   UBooleanUtil.and(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE)   = Boolean.TRUE
     *   UBooleanUtil.and(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE) = Boolean.FALSE
     *   UBooleanUtil.and(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE)  = Boolean.FALSE
     * </pre>
     *
     * @param array {@code Boolean}数组
     * @return 取与为真返回{@code true}
     * @deprecated 请使用 {@link #andOfWrap(Boolean... array)}
     */
    @Deprecated
    public static Boolean and(final Boolean... array) {
        if (UArrayUtil.isEmpty(array)) {
            throw new IllegalArgumentException("The Array must not be empty !");
        }
        final boolean[] primitive = Convert.convert(boolean[].class, array);
        return Boolean.valueOf(and(primitive));
    }
```

### UNumberUtil 完善了 ok
> 完善时间: 2020年6月18日13:53:27

> 完善类路径: com.lc.utils.UNumberUtil

> 下面为摘出来的重复代码: 
```java
/**
 * 比较两个字符是否相同
 *
 * @param c1         字符1
 * @param c2         字符2
 * @param ignoreCase 是否忽略大小写
 * @return 是否相同
 * @see UCharUtil#equals(char, char, boolean)
 */
public static boolean equals(char c1, char c2, boolean ignoreCase) {
    return UCharUtil.equals(c1, c2, ignoreCase);
}
```

## 其他

## 未完待续
