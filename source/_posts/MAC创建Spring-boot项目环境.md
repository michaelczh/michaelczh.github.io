
---
title: MAC创建Spring-boot项目环境
date: 2019-06-25 00:40:59
tags:
- Java
- Spring
categories:
- Java
---

使用**brew**

### 更新brew
`brew update && brew upgrade && brew cleanup`


### 安装Java
`brew cask install java`

安装完成后，执行`java -version`. 有输出信息表示安装成功。

### 设置JAVA环境变量
`vim ~/.bash_profile` 进入环境变量
输入
`export JAVA_HOME=$(/usr/libexec/java_home)`
保存退出，并执行
`source ~/.bash_profile`
执行`echo JAVA_HOME`，应该可以看得到路径

### 安装 Maven
`brew install maven`

### 设置环境变量
> export M2_HOME=/usr/local/Cellar/maven/{版本号}/
> export M2=$M2_HOME/bin
> export PATH=\$M2:\$JAVA_HOME:$PATH

