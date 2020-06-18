# JustToolc项目日志和代码库

## 完善工具类日志和完善JustToolc项目时 摘出来的重复代码

### UNumberUtil完善了 
> 完善时间: 2020年6月16日15:25:23

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

### UStringUtil完善了 
> 完善时间: 2020年6月18日11:19:30

### UConsole完善了 
> 完善时间: 2020年6月18日11:20:07

### UDict完善了 
> 完善时间: 2020年6月18日11:20:49

## 其他

## 未完待续
