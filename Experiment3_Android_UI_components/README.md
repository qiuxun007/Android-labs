# 实验名称：Android 界面组件

## 一、实验目的
1、掌握 Android 中 ListView 与 SimpleAdapter 的用法，能够显示带图片的列表。

2、掌握自定义布局 AlertDialog 的创建方法，会用 setView() 加载自定义布局。

3、掌握使用 XML 定义选项菜单，并响应菜单项点击事件。

4、掌握 ActionMode 上下文操作模式的创建，实现长按多选与删除。

5、掌握使用 RecyclerView 配合 Adapter 与 ViewHolder 实现高效列表，并动态更新数据。


## 二、实验原理
1、SimpleAdapter 是 Android 提供的简单适配器，能将 List 中的 Map 数据按 key 与布局文件里的控件 id 一一对应绑定，适合图片加文本的简单列表。ListView 通过 setOnItemClickListener 响应列表项点击事件。

2、AlertDialog.Builder 提供 setView() 方法，可将任意 XML 布局作为对话框内容，比系统默认样式更灵活。可以设置 PositiveButton 和 NegativeButton 响应按钮点击。

3、XML 菜单在 res/menu 目录下定义，通过 onCreateOptionsMenu 加载、onOptionsItemSelected 响应。菜单支持用 menu 标签嵌套形成子菜单。选项菜单通常挂在 ActionBar 上，在无 ActionBar 的主题下需要用 PopupMenu 弹出。

4、ActionMode 是上下文操作模式。通过 startSupportActionMode 启动后顶部出现操作栏，onCreateActionMode 加载菜单，onActionItemClicked 响应按钮。列表项用 selector 实现选中变色，ListView 需要设置 choiceMode 为 singleChoice。

5、RecyclerView 比 ListView 更强大，强制使用 ViewHolder 复用机制，性能更好。需要设置 LayoutManager 和 Adapter，支持 notifyItemInserted 等精确更新方法。

## 三、实验内容与步骤

### 任务一：ListView 的用法
1、创建列表项布局 list_item.xml，左侧放 TextView 显示动物名称，右侧放 ImageView 显示动物图片。

2、把 6 张动物图片放入 res/drawable 目录，命名为小写英文。

3、在主布局 activity_main.xml 中添加 ListView。

4、在 MainActivity 中把名称和图片装入 List 中的 Map 结构，用 SimpleAdapter 绑定数据。

5、给列表项设置点击监听，点击时弹出 Toast，并发送通知。通知使用应用图标作为 Icon，列表项名称作为标题。

### 任务二：自定义布局的 AlertDialog
1、创建对话框布局 dialog_login.xml，包含橙色标题栏、Username 输入框、Password 输入框。

2、在 MainActivity 中添加“打开自定义对话框”按钮。

3、点击按钮时用 AlertDialog.Builder 的 setView() 方法加载 dialog_login 布局，设置 Cancel 和 Sign in 两个按钮。

4、点击 Sign in 时弹出 Toast 显示输入的用户名和密码。

### 任务三：使用 XML 定义菜单
1、在 res/menu 目录下新建 menu_main.xml，包含三个顶层菜单项：字体大小（子菜单为小、中、大）、普通菜单项、字体颜色（子菜单为红色、黑色）。

2、在 MainActivity 中重写 onCreateOptionsMenu 加载菜单，重写 onOptionsItemSelected 响应菜单项。

3、点击字体大小子项时改变测试文本 tv_test 的字体大小，点击字体颜色子项时改变文字颜色，点击普通菜单项时弹出 Toast。

4、由于项目主题为 NoActionBar，选项菜单无法挂在 ActionBar 上，改为在界面上添加“打开菜单”按钮，通过 PopupMenu 弹出菜单。

### 任务四：ActionMode 上下文菜单
1、创建 ActionModeActivity，主布局 activity_action_mode.xml 中放 ListView，并设置 choiceMode 为 singleChoice。

2、在 res/color 目录下创建 item_action_bg.xml，用 selector 定义选中时的蓝色背景、按下时的灰色背景、默认的白色背景。

3、列表项布局 list_item_action.xml 使用该 selector 作为背景。

4、在 res/menu 目录下创建 menu_context.xml，定义删除菜单项，图标用系统自带的垃圾桶。

5、在 ActionModeActivity 中设置长按监听，长按时让列表项进入选中状态，并调用 startSupportActionMode 启动 ActionMode。

6、在 ActionMode 的回调中：onCreateActionMode 加载菜单并设置标题为 1 selected；onActionItemClicked 响应删除，移除对应数据并结束 ActionMode。

### 任务五：使用 RecyclerView 实现列表视图
1、在模块的 build.gradle.kts 中添加 RecyclerView 的依赖。

2、创建数据模型 Animal，包含名称和图片资源 id。

3、创建列表项布局 item_recycler.xml，横向排列图片和名称。

4、创建适配器 AnimalAdapter，继承 RecyclerView.Adapter，用 ViewHolder 复用布局，并在 onBindViewHolder 中绑定数据和点击事件。

5、创建 RecyclerActivity，加载动物数据，设置 LinearLayoutManager 和适配器。

6、点击某个列表项时弹出 Toast 显示名称，同时新增一条“xxx Copy”记录，调用 notifyItemInserted 让列表立即更新。

## 四、实验结果

### 任务一：ListView 显示动物列表
<img width="313" height="707" alt="image" src="https://github.com/user-attachments/assets/93767b85-fe07-4613-8649-b984df0c17f9" />


### 任务二：自定义 AlertDialog
<img width="308" height="297" alt="image" src="https://github.com/user-attachments/assets/54fd811f-48c9-498f-8ded-500b4c983abf" />

### 任务三：XML 菜单弹出
<img width="307" height="347" alt="image" src="https://github.com/user-attachments/assets/2f667129-2479-43ff-8c1f-5e0f66f9f27c" />
<img width="305" height="252" alt="image" src="https://github.com/user-attachments/assets/470ff871-31a7-46ca-a04e-012ee3f6cd35" />

### 任务四：ActionMode 长按选中
<img width="312" height="358" alt="image" src="https://github.com/user-attachments/assets/9af43ea8-1246-4667-9b02-f060be884228" />

### 任务五：RecyclerView 列表
<img width="317" height="711" alt="image" src="https://github.com/user-attachments/assets/13c06ff8-74f1-43c2-8440-dc9af9da60ba" />

## 五、实验总结
1、掌握了 ListView 与 SimpleAdapter 的用法，理解了适配器如何把数据源绑定到列表项布局，通过 Map 的 key 与 SimpleAdapter 的数组参数实现数据与控件的对应。

2、掌握了自定义 AlertDialog 的创建，了解了 setView() 的用法，能够灵活设计对话框布局而不局限于系统默认样式。

3、掌握了 XML 菜单的定义与响应，理解了选项菜单依赖 ActionBar 的特性。当项目使用 NoActionBar 主题时，需要改用 PopupMenu 才能正常弹出。

4、掌握了 ActionMode 上下文菜单。通过 startSupportActionMode 启动、setTitle 显示选中数量，配合自定义 selector 实现列表项选中变色。还学会了使用 androidx 版本的 ActionMode，而不是系统原生的 ActionMode。

5、掌握了 RecyclerView 的核心用法。相比 ListView，RecyclerView 强制使用 ViewHolder 模式，必须手动设置 LayoutManager，支持 notifyItemInserted 等精细更新 API，性能更优。
