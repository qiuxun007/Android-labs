# 实验名称：Android开发基础

## 一、实验目的
1、掌握 Android Studio 的下载、安装与配置方法。

2、熟悉 Android 项目的目录结构与基本组成。

3、掌握创建第一个 Android 工程的方法。

4、掌握使用 Git 将 Android 工程同步至 GitHub/Gitee 的操作流程。

5、了解 Android 工程中 .gitignore 忽略文件的配置。

## 二、实验原理
1、Android Studio 是 Google 官方推出的 Android 集成开发环境（IDE），基于 IntelliJ IDEA 构建，提供了代码编辑、调试、性能分析、模拟器等完整开发工具链。新建项目时会自动同步 Gradle 依赖文件和支持库。

2、一个标准的 Android 工程主要包含：
  app/：应用模块，包含源代码、资源文件等
  gradle/：Gradle 包装器配置
  build.gradle：项目/模块构建配置
  settings.gradle：项目模块设置
  .gitignore：Git 忽略文件配置  
  
3、Git 是分布式版本控制系统，通过 git init、git add、git commit、git push 等命令可将本地工程同步至远程仓库（GitHub/Gitee）。Android 工程需配置 .gitignore 以忽略编译产物（如 build/、.gradle/、local.properties 等）。

## 三、实验内容与步骤
1. 安装 Android Studio
访问官网下载安装包：
  https://developer.android.com/studio
  https://developer.android.google.cn/studio/
根据电脑硬件配置选择合适的版本（版本越高对硬件要求越高）。
运行安装程序，按向导完成安装。
首次启动，完成 SDK 等组件的下载与配置。
注意事项：安装完成后新建第一个项目时，会同步项目的依赖文件和支持库，需耐心等待。

2. 创建第一个 Android 工程
打开 Android Studio，选择 New Project。
选择模板（如 Empty Activity），设置：
  项目名称（Name）
  包名（Package name）
  保存路径（Save location）
  语言（Language）：Java / Kotlin
  最低 SDK 版本（Minimum SDK）
  点击 Finish，等待 Gradle 同步完成。
  运行项目，在模拟器或真机上查看效果。

3. 注册 GitHub 并安装 Git
注册 GitHub 账号，下载安装 Git，配置 Git 用户信息：

4. 将工程同步至 GitHub

5. 配置 .gitignore 忽略文件

## 四、实验结果
<img width="1917" height="1135" alt="image" src="https://github.com/user-attachments/assets/ba38aa60-14ed-4f31-bb02-b0cb3a545243" />

## 五、实验总结
1、成功安装并配置了 Android Studio 开发环境。

2、掌握了创建第一个 Android 工程的方法，熟悉了 Android 项目的目录结构。

3、学会了安装 Git、配置用户信息，并将工程同步至 GitHub。
