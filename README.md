# JustToolc

## fork me
ahviplc/JustToolc 
>https://github.com/ahviplc/JustToolc

## slogan
```markdown
❤JustToolc > Java Tools For U (You)❤
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

maven生成source.jar 
> mvn source:jar

maven清理安装并生成source.jar 
> mvn clean install source:jar -Dmaven.test.skip=true -Dmaven.javadoc.skip=false

JustToolc的maven一行命令生成 
>  mvn -T 1C clean source:jar javadoc:javadoc install -Dmaven.test.skip=true -Dmaven.javadoc.skip=false

```markdown
上面的一行命令代表:清理安装并生成source.jar 生成javadoc 跳过test的junit单元测试 不跳过javadoc文档生成
-Dmaven.test.skip=true 跳过test的junit单元测试
-Dmaven.javadoc.skip=false 不跳过javadoc文档生成
```

## maven项目使用
```xml
<!--全局使用-->
<dependency>
    <groupId>com.lc</groupId>
    <artifactId>\JustToolc</artifactId>
    <version>0.1</version>
</dependency>

<!--使用范围只在test-->
<dependency>
    <groupId>com.lc</groupId>
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

