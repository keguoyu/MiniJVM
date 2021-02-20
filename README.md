# MiniJVM
MiniJVM是一款使用python编写的简易JVM，能够从本地加载class文件并且执行绝大多数指令。

## 支持的功能
1.从本地磁盘加载class并解析

2.支持绝大多数指令集的执行

3.支持虚拟机内存分区以及对象的创建

4.支持方法的调用和参数传递

5.支持静态代码块的初始化

## 不支持的功能
GC，类文件校验

## 使用的方式

git clone https://github.com/keguoyu/MiniJVM.git

git checkout main

命令行启动：

python3 main.py -classname ~/CLASS_NAME CLASS_NAME是你需要加载的目标class文件

main.py支持传递更多的参数，如下：

[-h] ==> 查看帮助

[-v] ==> 查看版本信息

[-d] ==> 开启指令执行debug，开启之后可以看到类加载和指令执行的过程

[-cp] ==> class文件的目录路径

[-cn] ==> 目标类的类名

[-jre] ==> jdk安装的路径（在某些Mac上通过python获取JAVA_HOME的环境变量无法获取，可以通过这种手动指定的方式去指定）

在MiniJVM工程的跟路径下，又一个HelloJava的class文件，可以使用如下指令去执行该类：

python3 Main.py -d true -cp ~/AllFiles/WorkSpace/Python/MiniJVM -cn HelloJava -jre /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/contents/home/jre

可以看到指令运行的过程。

# 后续计划

增加GC，类校验以及更多指令
