# JustToolc

```markdown
       _           _ _______          _      
      | |         | |__   __|        | |     
      | |_   _ ___| |_ | | ___   ___ | | ___ 
  _   | | | | / __| __|| |/ _ \ / _ \| |/ __|
 | |__| | |_| \__ \ |_ | | (_) | (_) | | (__ 
  \____/ \__,_|___/\__||_|\___/ \___/|_|\___|
               Full Of ❤Love❤                                                           
```

## fork me
ahviplc/JustToolc: ❤JustToolc > Java Tools For U (You) ❤
> https://github.com/ahviplc/JustToolc

JustToolc: ❤JustToolc > Java Tools For U (You) ❤
> https://gitee.com/ahviplc/JustToolc

## who is who
```markdown
❤fork了 整理了 封装了 来自Hutool的一些工具类和在公司日常开发过程中,一些常用工具类,针对公司开发项目需求的工具类❤

about Hutool:

Hutool — A set of tools that keep Java sweet.
https://hutool.cn/

looly/hutool: A set of tools that keep Java sweet.
https://github.com/looly/hutool

hutool: Hutool是一个小而全的Java工具类库，使Java拥有函数式语言般的优雅，让Java语言也可以“甜甜的”。
https://gitee.com/loolly/hutool
```

## slogan
```markdown
❤JustToolc > Java Tools For U (You)❤
```

## changelog 
src/test/LCKu
> https://github.com/ahviplc/JustToolc/tree/master/src/test/LCKu

src/test/LCKu · ahviplc/JustToolc - 码云 - 开源中国
> https://gitee.com/ahviplc/JustToolc/tree/master/src/test/LCKu

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
> TODO 待完成 现在还不支持.
```xml
<!--全局使用-->
<dependency>
    <groupId>com.lc</groupId>
    <artifactId>\JustToolc</artifactId>
    <version>0.1.0</version>
</dependency>

<!--使用范围只在test-->
<dependency>
    <groupId>com.lc</groupId>
    <artifactId>\JustToolc</artifactId>
    <version>0.1.0</version>
    <scope>test</scope>
</dependency>
```
## about me
```markdown
By LC
寄语:一人一世界,一树一菩提!~LC
Version 0.1.0 From 20200610 
```

