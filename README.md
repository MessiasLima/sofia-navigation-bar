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
