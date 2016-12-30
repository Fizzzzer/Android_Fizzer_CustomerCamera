# Android_Fizzer_CustomerCamera  

这里是一个自定义的相机功能，可以根据自己的项目需求，更改相机拍照的界面view，效果图如下
![image](https://github.com/Fizzzzer/Android_Fizzer_CustomerCamera/blob/master/test.gif)

###使用
在`OnActivityResult`中可以接受拍照返回来的图片
```java
String path = data.getStringExtra("data");
```
这里面的path就是拍照返回的图片路径，拿到这个图片路径，想干啥就可以干啥了    
后面的就不在多说了，在代码中都可以看，如果有写的不好的地方，望斧正
