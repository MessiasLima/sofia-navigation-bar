# Sofia
An elegant navigation bar

## Demo
![Demostration](./demo.gif)

## Implementation 
1. Add dependency on build.gradle

1. Crate an menu xml file
    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <menu xmlns:android="http://schemas.android.com/apk/res/android">

        <item
            android:id="@+id/menu_item_all"
            android:title="@string/all"/>

        <item
            android:id="@+id/menu_item_charts"
            android:title="@string/charts"/>

        <item
            android:id="@+id/menu_item_learn"
            android:title="@string/learn"/>

        <item
            android:id="@+id/menu_item_articles"
            android:title="@string/articles"/>

        <item
            android:id="@+id/menu_item_about"
            android:title="@string/about"/>
    
    </menu>
    ```
1. Add View on your xml file
    ```xml
    <br.com.messiaslima.sofia.Sofia
        android:id="@+id/sofiaNavigationBar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:menu="@menu/example_menu" />

    ```
1. Add a listener to get menu interactions
    ```kotlin
        sofiaNavigationBar.onItemSelected = { item ->
            itemSelected.text = item.title
        }
    ```
## License

MIT License

Copyright (c) 2020 Messias Junior

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
