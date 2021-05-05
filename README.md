# Material Expansion Panel

|Expansion panels contain creation flows and allow lightweight editing of an element.|<img src="https://github.com/robertlevonyan/materialExpansionPanel/blob/master/Images/expandable.png"  width="500" />|
|----------------------------------------------------------------------------------------------|-----------|

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Material%20Expansion%20Panel-yellow.svg?style=flat-square)](https://android-arsenal.com/details/1/6433) [![API](https://img.shields.io/badge/API-14%2B-yellow.svg?style=flat-square)](https://android-arsenal.com/api?level=14) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.robertlevonyan.view/MaterialExpansionPanel/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.robertlevonyan.view/MaterialExpansionPanel)

## Setup

Add following line of code to your project level gradle file

```kotlin
  repositories {
    mavenCentral()
  }

#### Gradle:

Add following line of code to your module(app) level gradle file

```groovy
    implementation 'com.robertlevonyan.view:MaterialExpansionPanel:<LATEST_VERSION>'
```

#### Kotlin:

```kotlin
    implementation("com.robertlevonyan.view:MaterialExpansionPanel:$LATEST_VERSION")
```

#### Maven:

```xml
<dependency>
  <groupId>com.robertlevonyan.view</groupId>
  <artifactId>MaterialExpansionPanel</artifactId>
  <version>LATEST_VERSION</version>
  <type>pom</type>
</dependency>
```

## Usage

```xml
<com.robertlevonyan.views.expandable.Expandable
        android:id="@+id/expandable"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <include
            android:id="@+id/header"
            layout="@layout/header_view" />

        <include
            android:id="@+id/content"
            layout="@layout/content_view" />
</com.robertlevonyan.views.expandable.Expandable>
```

|![alt text](https://github.com/robertlevonyan/materialExpansionPanel/blob/master/Images/collapsed.jpg)|![alt text](https://github.com/robertlevonyan/materialExpansionPanel/blob/master/Images/expanded.jpg)|
|----------------------------------------------------------------------------------------------|-----------|

Material Expansion Panel in action

![alt text](https://github.com/robertlevonyan/materialExpansionPanel/blob/master/Images/process.gif)

### Attributes

|Custom Atributes                |Description                                               |
|--------------------------------|----------------------------------------------------------|
|`app:exp_icon`                  |Icon for Expandable Header                                |
|`app:exp_iconStyle`             |Set style for header icon: square, circle or roundedSquare|
|`app:exp_animateExpand`         |Animate expand of layout                                  |
|`app:exp_backgroundColor`       |Set a custom background color for layout                  |
|`app:exp_expandIndicator`       |Select custom drawable for expand indicator               |

### Setting Listeners

```kotlin
    val expandable = findViewById(R.id.expandable);
```

Expand/collapse programmatically

#### Kotlin
```kotlin
    expandable.expand()
```

#### Java
```java
    expandable.expand();
```


Set expand listener

#### Kotlin
```kotlin
    expandable.doOnExpand {
        //some stuff on expand
    }

    expandable.doOnCollapse {
        //some stuff on collapse
    }
```

#### Java
```java
    expandable.setExpandingListener(new ExpandingListener() {
            @Override
            public void onExpanded() {
                //some stuff on expand
            }

            @Override
            public void onCollapsed() {
                //some stuff on collapse
            }
        });
```

### Customizing Expandable from code

```java
    expandable.icon = myIconDrawable // Icon for Expandable Header
    expandable.iconStyle = ExpandableIconStyles.SQUARE // Set style for header icon: square, circle or roundedSquare
    expandable.animateExpand = true // Animate layout expanding
    expandable.backgroundColor = myBackgroundColor // Set a custom background color for layout
    expandable.expandIndicator = myExpandDrawable // Select custom drawable for expand indicator
```

## Versions

#### 2.1.0

Migration to mavenCentral

#### 2.0.1 - 2.0.10
Several bug fixes and small features

### 2.0.0
New version of the library. Fully rewritten with Kotlin and AndroidX ready 🤩

#### 1.2.0

RecyclerView issue fixed

#### 1.1.0

New version with bug fixes

### 1.0.0

First version of library

## Contact

- **Email**: me@robertlevonyan.com
- **Website**: https://robertlevonyan.com/
- **Medium**: https://medium.com/@RobertLevonyan
- **Twitter**: https://twitter.com/@RobertLevonyan
- **Facebook**: https://facebook.com/robert.levonyan
- **Google Play**: https://play.google.com/store/apps/dev?id=5477562049350283357

## Licence

```
    Material Expansion Panel©
    Copyright 2017 Robert Levonyan
    Url: https://github.com/robertlevonyan/materialExpansionPanel

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```
