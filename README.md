# Material Expansion Panel

Expansion panels contain creation flows and allow lightweight editing of an element.

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Material%20Expansion%20Panel-yellowgreen.svg?style=flat-square)](https://android-arsenal.com/details/1/6433)

## Setup

#### Gradle:

Add following line of code to your module(app) level gradle file

```java
    implementation 'com.robertlevonyan.view:MaterialExpansionPanel:1.1.0'
```

#### Maven:

```xml
<dependency>
  <groupId>com.robertlevonyan.view</groupId>
  <artifactId>MaterialExpansionPanel</artifactId>
  <version>1.1.0</version>
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
|`app:exp_headerBackgroundColor` |Set a custom background color for layout header           |
|`app:exp_expandIndicator`       |Select custom drawable for expand indicator               |

### Setting Listeners

```java
    Expandable expandable = findViewById(R.id.expandable);
```

Set expand listener
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

### Customizing Expandable from Java

```java
    expandable.setIcon(); // Icon for Expandable Header
    expandable.setIconStyle(); // Set style for header icon: square, circle or roundedSquare
    expandable.setAnimateExpand(); // Animate layout expanding
    expandable.changeBackgroundColor(); // Set a custom background color for layout
    expandable.setHeaderBackgroundColor(); // Set a custom background color for layout header
    expandable.setExpandIndicatorDrawable(); // Select custom drawable for expand indicator
    expandable.setExpandIndicator(); // Select custom drawable resource for expand indicator
```

## Versions

#### 1.1.0

New version with bug fixes

#### 1.0.0

First version of library

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
