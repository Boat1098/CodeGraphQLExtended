# 参考链接
[[https://www.cnblogs.com/suanguade/p/16080015.html]]
整个的codeql本机的配置在上面说的挺全的，以下我会就我使用时遇到的一些问题给出解决方案

## 1.创建代码数据库时创建失败怎么办
首先检查是否能够使用codeql，是否会爆出未知命令的错，如果有，则需要检查安装过程中是否有遗漏步骤
其次，检查项目所用的语言版本，例如在log4j的挖掘中，log4j 2.14.1版本所需java版本为java9,因此，需要在1. C:\Users\用户名.m2文件夹下创建toolchains.xml文件，写入下面配置（jdkHome设置为自己jdk的路径）
    

```
<toolchains>
<toolchain>  
  <type>jdk</type>  
  <provides>  
    <version>9</version>  
    <vendor>sun</vendor>  
  </provides>  
  <configuration>  
    <jdkHome>D:\Java\jdk9.0.4</jdkHome>  
  </configuration>  
</toolchain>  
</toolchains>
```
如果命令中含有类似与
codeql database create log4jdb --language=java --overwrite --command="mvn clean install -Dmaven.test.skip=true"等的命令 ，需要检查mvn等的版本是否正确

## 2.点击从folder添加数据库后仍没有数据库显示怎么办？

这种情况下多半是vscode中并未添加相应语言的编译执行插件，添加即可。

## 3.引入相应语言后，编译器报错怎么办
当创建test.ql后，如果import java等指定语言的语句报错，说明缺少qlpack.yml文件。有两种解决方法：1.参照codeql文档中的说明创建相应文件
2.按[vscode-codeql-starter](https://github.com/github/vscode-codeql-starter)库中的步骤，在相应语言文件夹下创建.ql文件