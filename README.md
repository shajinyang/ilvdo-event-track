# 非侵入式埋点框架

### 介绍

基于 AOP 编程思想，使用AspectJ 在编译期进行埋点注入，不影响业务逻辑代码。

- 埋点代码完全脱离业务代码
- 注解的方式配置事件路径
- 注解的方式添加事件切点
- 对U盟统计进行封装，简化使用
- 底层三方统计框架可灵活替换

### 如何配置埋点框架

第一步：在项目的gradle里配置

```
      allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }

         dependencies {
                classpath "com.hujiang.aspectjx:gradle-android-plugin-aspectjx:$aspectX"
         }
      }
```

第二步：在module的gradle里配置
```
    //添加插件
     apply plugin: 'android-aspectjx'

     dependencies {
         implementation 'com.github.shajinyang:event_track_api:1.0.0'
         annotationProcessor 'com.github.shajinyang:event_track_compier:1.0.0'
     }
```

### 如何使用

#### 初始化，推荐在application里进行初始化

初始化
```
 IlvdoEventTrack
                .init()
                .withApplicatiuon(this)
                .withAppkey("54f6667efd98c53466000db0") //umeng  申请的appkey
                .withChannel("UMENG_CHANNEL_VALUE")  // 对应 umeng的channel
                .config();
```




#### 配置需要上报事件

新建任意java类，类型可自行定义 （暂不支持kotlin）
添加注解

```
@Events({
    @Path(path = {"01","02"} ,id="login_to_main"),
    @Path(path = {"03","06"} ,id="pay_vip")

        })
public class MyPath {

}
```


#### 添加事件切点

在对应的方法上添加注解 （支持kotlin）

```
public class LoginActivity extends Activity {

    @Override
    @EventAspect(pathId ="12")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    @EventAspect(pathId = "123")
    public void  test(){

    }
}

```

添加完上面的代码，框架会自动帮你进行事件上报。

#### 注解说明
- @Events @Path 嵌套注解
```
@Events({
    @Path(path = {"01","02"} ,id="login_to_main"),
    @Path(path = {"03","06"} ,id="pay_vip")
        })
```
path  为字符串数组，用来保存事件序列的标识，数组内容是各个切点的标识 。
id    为该事件的id，和Umeng设置的自定义事件id对应

- @EventAspect
```
  @EventAspect(pathId = "123")
    public void  test(){

    }
```
该注解可添加在任意需要统计的方法上，这里以自定义的方法test()为例。
pathId 为切点的标识，所有的pathId 组成一个完整的事件序列。














