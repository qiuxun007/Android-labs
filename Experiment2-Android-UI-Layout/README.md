# 实验名称：Android 界面布局

## 一、实验目的
1、掌握 LinearLayout、TableLayout、ConstraintLayout 三种布局容器的使用方法。

2、熟悉 Android 中 XML 布局文件的编写方式，理解 layout_weight、约束链、stretchColumns 等核心属性。

3、掌握 Jetpack Compose 声明式 UI 的基本用法，理解状态管理机制。

## 二、实验原理
1、LinearLayout 是线性布局容器，子 View 按水平或垂直方向依次排列。通过 android:orientation 指定方向，通过 android:layout_weight 分配剩余空间。嵌套使用时，外层控制行，内层控制列，可实现网格布局。

2、TableLayout 是表格布局容器，子 View 用 TableRow 包裹，每行可包含多个单元格。通过 android:stretchColumns 指定自动拉伸的列，通过 android:layout_span 让单元格横跨多列。

3、ConstraintLayout 是约束布局容器，通过给每个 View 添加水平和垂直约束来确定位置和大小。用 0dp 配合左右约束可实现按比例分配宽度，多个 View 互相约束可形成约束链，后写的 View 图层在上，可实现叠放效果。

4、Jetpack Compose 是 Android 的声明式 UI 框架，界面由可组合函数（@Composable）构成。状态通过 mutableStateOf 和 mutableStateListOf 管理，状态改变时界面自动重组（Recomposition），不需要手动刷新 UI。

## 三、实验内容与步骤
### 实验一：LinearLayout 实现 4x4 网格
1、新建布局文件 activity_linear.xml，根标签为 LinearLayout，方向 vertical。

2、外层放 4 个水平 LinearLayout，每个高度设为 0dp，layout_weight 设为 1，等分屏幕高度。

3、每个水平 LinearLayout 内放 4 个 TextView，用 layout_weight 分配宽度。

4、每个 TextView 设置 margin、background、gravity、text、textColor、textSize，实现黑底白边、文字居中的格子效果。

### 实验二：TableLayout 实现菜单表格
1、新建布局文件 activity_table.xml，根标签为 TableLayout。

2、设置 android:stretchColumns="1"，让第 2 列自动拉伸。

3、每行用 TableRow 包裹，左列放菜单项名称，右列放快捷键。

4、标题行和分隔行用 android:layout_span="2" 横跨两列。

### 实验三：ConstraintLayout 实现计算器界面
1、新建布局文件 activity_constraint1.xml，根标签为 androidx.constraintlayout.widget.ConstraintLayout。

2、顶部标题栏 TextView 左右约束到 parent，铺满宽度。

3、Input 标签约束到标题栏下方，显示框左右约束到 parent。

4、下方 4x4 按钮，每行第一个约束 start 到 parent，中间的互相约束，最后一个约束 end 到 parent，形成闭合约束链，4 列等宽。

5、每行按钮 top 约束到上一行按钮的 bottom，上下对齐。

### 实验四：ConstraintLayout 实现旅行界面
1、新建布局文件 activity_constraint2.xml，根标签为 ConstraintLayout。

2、顶部标签栏用 LinearLayout，3 个标签等分，每个标签内用 ConstraintLayout 把图标叠在文字上方。

3、DCA ↔ MARS 区块用 ConstraintLayout，两个方块紧挨，双箭头图片叠在中间，图层在上。

4、One Way 和 1 Traveller 用橙色背景的 LinearLayout / TextView，约束到父布局左边。

5、中间星系图 + 左边小图用 LinearLayout 横排，整体居中约束。

6、底部 DEPART 按钮左右约束到 parent，固定在底部。

### 实验五：Jetpack Compose 实现课程学习任务列表
1、新建 Task.kt，定义 Task 数据类，isCompleted 用 mutableStateOf 包装，让 Compose 能观察到它的变化。

2、新建 TaskScreen.kt，用 mutableStateListOf 存放任务列表。

3、用 OutlinedTextField 输入任务名，Button 添加新任务。

4、用 Text 显示“已完成：X / Y”，X 由 tasks.count { it.isCompleted } 实时计算。

5、用 LazyColumn 渲染任务列表，每个任务一个 TaskItem。

6、TaskItem 里 Checkbox 控制 isCompleted，Text 根据 isCompleted 决定是否加删除线，TextButton 删除任务。

## 四、实验结果
### 实验一：LinearLayout 4x4 网格
<img width="332" height="748" alt="02bb791531ec9546ce82f0a8117c4ba3" src="https://github.com/user-attachments/assets/d0227a22-af2b-402e-8967-b33b5de47208" />

### 实验二：TableLayout 菜单表格
<img width="337" height="671" alt="ce6c667e2ae65e0eedff6e009b693007" src="https://github.com/user-attachments/assets/c08d467d-6157-4f1d-b662-32c2bc30fa38" />

### 实验三：ConstraintLayout 计算器界面
<img width="335" height="727" alt="2e3463815f7cd8d92fccac1b2b9433df" src="https://github.com/user-attachments/assets/f07d8601-aea2-483e-a66e-325669eafac6" />

### 实验四：ConstraintLayout 旅行界面
<img width="333" height="748" alt="d462251098f4f7045fa93843959f56c5" src="https://github.com/user-attachments/assets/6b99d23a-eb7e-43ab-8c36-87802fa1ae55" />

### 实验五：Jetpack Compose 课程学习任务列表
<img width="331" height="633" alt="7e22e22e3612595d43c2030dbbcc8084" src="https://github.com/user-attachments/assets/3f7c9ec5-0a5e-4187-860c-30050e2305d4" />
<img width="328" height="606" alt="3778edf86e9eed5f7025c4c12ea035a1" src="https://github.com/user-attachments/assets/cff11062-37e9-4dcb-a49c-537e01dae04f" />
<img width="322" height="716" alt="1253a48e0b0cef580b9b768b2a785b46" src="https://github.com/user-attachments/assets/71770b16-1ea4-45e1-9ebd-ffa1cb2bf073" />

## 五、实验总结
1、掌握了 LinearLayout 的嵌套用法，理解了 layout_weight 在水平和垂直方向上的分配机制。

2、掌握了 TableLayout 的表格布局方式，理解了 stretchColumns 和 layout_span 的作用。

3、掌握了 ConstraintLayout 的约束链和叠放技巧，理解了 0dp 配合约束确定宽度的原理。

4、掌握了 Jetpack Compose 的声明式 UI 写法，理解了 mutableStateOf 和 mutableStateListOf 触发的自动重组机制。

5、学会了用 Git 将 Android 工程同步至 GitHub，并配置 .gitignore 忽略编译产物。
