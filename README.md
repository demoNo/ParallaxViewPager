# ParallaxViewPager

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

README: [English](https://github.com/demoNo/ParallaxViewPager/blob/master/README.md) | [中文](https://github.com/demoNo/AutoScrollViewPager/blob/master/README-zh.md)

![](https://raw.githubusercontent.com/demoNo/ParallaxViewPager/master/art/rate_0.5.gif)
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


## Configuration

> TODO


## Licence

> Apache License, Version 2.0