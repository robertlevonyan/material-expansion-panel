# Material Expansion Panel

Expansion panels contain creation flows and allow lightweight editing of an element.

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Material%20Expansion%20Panel-yellowgreen.svg?style=flat-square)](https://android-arsenal.com/details/1/6433)

## Setup

#### Gradle:

Add following line of code to your module(app) level gradle file

```java
    implementation 'com.robertlevonyan.view:MaterialExpansionPanel:1.0.8'
```

#### Maven:

```xml
<dependency>
  <groupId>com.robertlevonyan.view</groupId>
  <artifactId>MaterialExpansionPanel</artifactId>
  <version>1.0.8</version>
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
