# SmartIconPack
一个更简单的图标包模版

## 概述
本项目致力于制作更简单的图标包框架，进一步降低图标包制作门槛。
目前图标包有以下几个痛点：
1. 图标包规范杂乱（不同的桌面程序对于图标包的检测方式不同）
2. 开发成本高（很多图标包制作者甚至不懂开发）
3. 图标包的内部代码功能简单但是量大、重复、繁杂

而本项目的功能和目的主要2个
1. 启动器适配
2. 图标展示工具

框架负责启动器的规则适配，让设计师更专注于设计，而不用考虑重复而枯燥的启动器适配工作。
同时，提供必要的基础工具，简化应用内的UI呈现工作，为具有一定开发能力的制作者提供更加通用简单的工具。

## 项目结构
### 结构
项目针对不同开发能力的设计师，提供了三种不同程度的框架。

|  类型   | 人群(开发能力)  | 框架能力 |
|  :----:  | :----:  | ---- |
| 模板(App) | 无基础 | Kit功能<br>成品应用<br>主题设置 |
| 套件(Kit) | 入门 | Core功能<br>交互框架<br>界面风格 |
| 核心(Core)  | 熟练 | 规则适配<br>解析工具<br>基础工具 |

### 核心(Core)
核心层是本项目最底层的模块，提供必要的图标包身份申请，图标包配置文件解析与生成，系统应用的检索与图标包适配信息检索。
它适合一定开发能力的同学，可以在它的基础上来开发新的`套件`或直接开发应用，以此来制作图标包。

### 套件(Kit)
套件的定位是固定的应用风格，但是提供一定程度的定制能力，可以通过使用或切换套件来实现快速更换应用界面风格的目的。
套件要求尽可能少的要求接入者书写代码，尽可能多的提供参数配置项，尽可能使用配置文件来提供能力。或者提供唯一的初始化方法，并且提供示例代码。
它适合能搭建应用开发环境并且完成打包的接入者。或者满意某一个预设套件但是希望扩展它的开发者。

### 模板(App)
模板的定位是成品的应用，但是提供一定的参数配置功能。要求能开箱即用，包含完整的配置信息，只需要修改模板中的部分开发者信息，即可完成全部开发工作。
`模板`一般由`套件`开发者一同提供。

### 定位选择
以上3个层次是一个包含的关系。其中开发制作成本最大的是`套件`，它需要提供完整的应用交互界面及功能。而`模板`是接入成本最低的，但是它只能使用已有的`套件`来接入。接入者可以选择适合自己的层次来开发。
**同时也非常欢迎各位开发者来加入套件的开发，一起来提供更多的套件风格。**

## 接入

### 核心(IconCore)

使用Gradle接入：
```groovy
implementation 'com.lollipop.smartIconPack:IconCore:1.0'
```

当然，如果对现有`核心`不满意的同学，也可以直接clone来修改它。

由于图标包需要在`Manifest.xml`中的`MainActivity`下声明相应的权限规则，因此，模块为了简化这个配置过程，在模块中内置了入口的`Activity`，并且提供交互界面的接口，让接入者定制主页的UI，这可能会和常规的应用开发有所区别，但是我们也提供了非常多的辅助方法，能让接入过程不会太过于复杂。

#### 主要API介绍
这里介绍一些你可能会需要的工具，当然，还有更多的一些工具和类可能需要你翻看源码。
在这里，我们更希望你能简单翻看以下源码，也许，这会对你的开发工作有所帮助。

* **IconPackCore**
    核心的初始化入口，主页的UI接口会在此处绑定。因此需要在Activity初始化之前绑定，所以建议在`Application`的`onCreate`中初始化

* **IconApplication**
    上下文的基础类，它包含很多模块的初始化操作，并且`IconPackCore`的初始化方法也需要它的实例，因此，应用的`Application`需要使用它或者使用它的子类。

* **IconHelper**
    图标解析的核心类，它提供系统应用的检索功能、图标包与应用的匹配功能、图标配置文件的解析功能。他能基本满足图标包应用的场景覆盖。（你会能需要它）

* **IconImageView**
    有了图标信息，那么展示它成了必要的操作，这里提供了简单的实现，它可以快速的加载图标包的信息，而不用考虑图标本身的解析问题。

* **SimpleActivityRenderer**
    主页的渲染基类，它实现了主页渲染接口，并且提供了必要的基础实现。你只需要继承它，然后像继承`Activity`一样去完成图形界面就好了。

* **ExternalLinkManager**
    如果你希望开发一个`套件`，那么你可能会使用到它，它用于解析`外部链接`的配置文件，我们建议`套件`都使用同样的配置文件格式，这样可以缩短接入者的学习成本。你可以通过它来更方便的读取已配置的外部链接，以此来进行符合预期的应用跳转功能。

* **IconSaveHelper**
    如果你希望为自己的应用提供`适配申请`或`图标保存`功能，那么你可能会需要它，它可以将一个或一组图标，保存到本地。如果配合`IconHelper`，还可以把应用的图标保存到本地，然后发送给开发者，这样可以使适配图标时，找到一些灵感。

* **MakerInfoManager**
    作为一个`套件`，制作者身份的定制与展示是很有必要的，对于应用制作者而言，个人信息的展示更是成就感的来源之一。那么就需要这个工具来，它可以解析`标准`的开发者信息配置，然后用于展示。

* **UpdateInfoManager**
    对于用户而言，了解应用的更新内容，迭代历程，还是很有意义的。因此，这里提供更新信息的解析工具，让开发者通过配置文件，记录每个版本的更新内容。它将会解析并且对信息进行整理，方便开发者直接使用。

* **XmlBuilder**
    对于图标包的申请，开发者可能更希望用户选择完成之后，就直接生成相应的配置文件，而不是人工机械的打字。它就可以完成这个工作，它提供了xml文件内容的生成功能，可以按照预先设定的格式，通过数据填充，直接生成相应的文本信息甚至文件。

* **ZipHelper**
    图标包的适配申请时，除了申请的包信息，可能还会包含应用的原始图标，这时候，压缩工具派上了用场，它不仅可以整理杂乱的文件，还可以减少不必要的流量消耗。

* **CommonUtil.kt**
    对于不同的业务层场景，可能会有个各种小需求。这里提供了一些通用的小工具。比如异步线程与线程同步，日志打印输出，长度单位的转换，资源文件的查找等等。

### 基础套件(LIconKit)
这是随`核心`一起产出的套件，也可以理解为`核心`的使用模板。
它提供相对完整的图标包功能，以及开放的参数设定，同时，也为每个工具类提供了用例。
如果你是第一次尝试接入`核心`，那么建议使用它来作为参考。

#### 主要API介绍
这里介绍一些主要的业务类，由于基本上完全使用`核心`库资源，因此没有更多的工具类产生。

#### 主要API介绍
* **LIconKit**
    这是套件本身的初始化入口，内部包含了`核心`的初始化工作，但是它也包含了更多配置文件的绑定功能。

* **MainActivity**
    它是主页的最终实现类，虽然名为`Activity`，但是它其实是继承自`SimpleActivityRenderer`的主页渲染实现类。

* **LApplication**
    由于功能的定制，它也需要绑定主页渲染的类。因此最终的接入者，将要使用它来作为应用的上下文。

* **HomeFragment**
    本套件中，采用的是Tab分页的结构，这是第一个主分页，提供了图标包的主要信息展示。

* **IconFragment**
    它是图标包展示的分页，它用于展示当前图标包已有的所有图标

* **RequestFragment**
    这是适配请求的页面，用于用户对未适配的应用发起适配申请。

* **AboutFragment**
    这是开发者个人信息展示的页面，用于展示一些个性化的开发者信息。