# JustToolc

## fork me
ahviplc/JustToolc 
>https://github.com/ahviplc/JustToolc

## slogan
```markdown
❤JustToolc > Java Tools For You ❤
```

## maven命令操作
maven清理命令 
> mvn clean

maven打包jar 
> mvn package

maven安装命令 
> mvn install

```markdown
模块安装命令 将打包的的jar/war文件复制到你的本地仓库中,供其他模块使用 -Dmaven.test.skip=true 跳过测试(同时会跳过test compile)
```

maven发布命令 
> mvn deploy

maven生成javadoc文档  
> mvn javadoc:javadoc

## maven项目使用
```xml
<!--全局使用-->
<dependency>
    <groupId>org.lc</groupId>
    <artifactId>\JustToolc</artifactId>
    <version>0.1</version>
</dependency>

<!--使用范围只在test-->
<dependency>
    <groupId>org.lc</groupId>
    <artifactId>\JustToolc</artifactId>
    <version>0.1</version>
    <scope>test</scope>
</dependency>
```
## about me
```markdown
By LC
寄语:一人一世界,一树一菩提!~LC
Version 0.1 From 20200610 
```

