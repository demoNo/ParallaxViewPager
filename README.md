# ParallaxViewPager

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/demoNo/ParallaxViewPager.svg)](https://jitpack.io/#demoNo/ParallaxViewPager)

[中文](https://github.com/demoNo/ParallaxViewPager/blob/master/README-zh.md)

* **The first gif** `foreground speed = 2*background speed` witch is `speedRatio = 0.5`

![](https://raw.githubusercontent.com/demoNo/ParallaxViewPager/master/art/rate_0.5.gif)

* **the second gif** `foreground speed = background speed` witch is `speedRatio = 1`

![](https://raw.githubusercontent.com/demoNo/ParallaxViewPager/master/art/rate_1.gif)

## Add to your project

Gradle

* Add it in your root build.gradle at the end of repositories:
```Gradle
allprojects {
    repositories {
    	...
    	maven { url 'https://jitpack.io' }
    }
}
```

* Add the dependency
```Gradle
dependencies {
    compile 'li.yohan.parallax:ParallaxViewPager:v1.0.0'
}
```


Maven

* Add the JitPack repository to your build file
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

* Add the dependency
```xml
<dependency>
    <groupId>li.yohan.parallax</groupId>
    <artifactId>ParallaxViewPager</artifactId>
    <version>v1.0.0</version>
</dependency>
```

## Usage

* Just like normal ViewPager

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

set background image just like ViewPager.

you can set in xml

`android:background="@drawable/background"`

or in code
```Java
setBackgroundResource(int resid)
setBackground(Drawable background)
```

## Configuration

* you can set speed ratio in xml
```diff
 <li.yohan.parallax.ParallaxViewPager
     android:id="@+id/viewPager"
     android:layout_width="match_parent"
     android:layout_height="match_parent"
+    app:speedRatio="0.5f" />
```
* also set in code
```diff
 ParallaxViewPager mPager = ((ParallaxViewPager) findViewById(R.id.pager));
 mPager.setAdapter(new Adapter());
+mPager.setSpeedRatio(0.5f);
```

> **Note: the default ratio is 0.5f**

## Restrictions

1. The background image's width should larger than height.
2. The background image's width should larger than ViewPager's width.
3. You can't set pager with padding.
4. If the ratio that user set is not proper, it will be recalculate and reset.

## Licence

> Apache License, Version 2.0
