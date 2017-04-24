# ParallaxViewPager

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

README: [English](https://github.com/demoNo/ParallaxViewPager/blob/master/README.md)

* **图 1** 前景速度是背景速度的2倍 当前 `speedRatio = 0.5`

![](https://raw.githubusercontent.com/demoNo/ParallaxViewPager/master/art/rate_0.5.gif)

* **图2** 前后速度一致（会有些误差），当前 `speedRatio = 1`

![](https://raw.githubusercontent.com/demoNo/ParallaxViewPager/master/art/rate_1.gif)

## 添加依赖

Gradle

* 在工程的根目录`build.gralde`配置
```Gradle
allprojects {
    repositories {
    	...
    	maven { url 'https://jitpack.io' }
    }
}
```

* 在app目录的`build.gralde`配置
```Gradle
dependencies {
    compile 'li.yohan.parallax:ParallaxViewPager:v1.0.0'
}
```

## 使用

* 像正常使用ViewPager一样

```xml
<li.yohan.parallax.ParallaxViewPager
    android:id="@+id/viewPager"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

```Java
ParallaxViewPager mPager = ((ParallaxViewPager) findViewById(R.id.pager));
mPager.setAdapter(new Adapter());
```

设置背景的方式和普通的 `ViewPager` 基本一致

在xml中设置

`android:background="@drawable/background"`

或者在代码中设置
```Java
setBackgroundResource(int resid)
setBackground(Drawable background)
```


## 设置

* 在xml中设置速率比
```diff
<li.yohan.parallax.ParallaxViewPager
    android:id="@+id/viewPager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    +app:speedRatio="0.5f" />
```
* 在代码中设置速率比
```diff
ParallaxViewPager mPager = ((ParallaxViewPager) findViewById(R.id.pager));
mPager.setAdapter(new Adapter());
+mPager.setSpeedRatio(0.5f);
```

> **默认的速率比是0.5**

## 限制

1. 背景图片宽度应该大于高度
2. 背景图片的宽度应该大于 ViewPager 的宽度
3. ViewPager 不能设置padding
4. 如果速率比设置不对的话，会自动设置一个合适的值保证图片不会出现超出边界的问题

## Licence

> Apache License, Version 2.0
