# 我的仓库

#### 介绍

springboot开发的文件上传项目，用于上传文件

#### 软件架构

软件架构说明

#### windows安装教程

1. 导入idea
2. 配置maven为阿里云
3. JDK必须是17及以上spring boot支持的版本

#### Linux安装教程

1. 安装JavaJDK17及以上spring boot支持的版本
2. 在application.yml里面修改想要的上传目录（演示的为Windows。Linux使用方法一致）

#### 使用说明

## Windows

1. 导入idea
2. 可以在有jdk17及以上spring boot支持的前提下直接java -jar 运行jar包
3. 运行后端口为82端口，直接访问即可
4. Windows需要将yml的内容
5. @Value("${Updata.url}")
   private String url; 修改成自己的目录，即可上传(修改application.yml的Updata中的url)
   ![效果图](https://foruda.gitee.com/images/1708508021560631195/755868fc_13504388.png)

## Linux
Linux可以直接使用root用户运行java -jar File-0.0.1.jar 即可（前提是JavaJDK17及以上）

1. 安装JDK17
2. 运行jar包
3. 运行后端口为82端口，直接访问即可，记得打开防火墙端口

# 效果图

![效果图](https://foruda.gitee.com/images/1708485819786481550/6acfc99a_13504388.png "Snipaste_2024-02-21_11-15-57.png")

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### 开发者

1.徐朴芬
