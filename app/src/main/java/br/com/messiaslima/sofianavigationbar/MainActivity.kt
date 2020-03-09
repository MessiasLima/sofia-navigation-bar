package br.com.messiaslima.sofianavigationbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.messiaslima.sofia.model.MenuParser
import br.com.messiaslima.sofia.model.SofiaMenu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sofiaMenu: SofiaMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sofiaMenu = MenuParser(this).parse(R.menu.example_menu)
        setupNavigationBar()
        setupNumberPicker()
    }

    private fun setupNumberPicker() {
        numberPicker.minValue = 0
        numberPicker.maxValue = (sofiaMenu.menuItems.size - 1)
        numberPicker.setOnValueChangedListener { _, _, newVal ->
            sofiaNavigationBar.setSelectedItem(newVal)
        }
    }

    private fun setupNavigationBar() {
        sofiaNavigationBar.onItemSelected = { item ->
            itemSelected.text = item.title
        }
    }
}
