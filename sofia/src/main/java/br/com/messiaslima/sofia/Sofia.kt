package br.com.messiaslima.sofia

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.MenuRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.updatePadding
import androidx.core.view.updatePaddingRelative
import br.com.messiaslima.sofia.model.MenuParser
import br.com.messiaslima.sofia.model.SofiaMenuItem
import br.com.messiaslima.sofia.util.toPx

class Sofia @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet,
    private val defStyle: Int = 0,
    private val defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var itemHorizontalPadding: Float = 0f
    private var itemVerticalPadding: Float = 0f
    private var menu: Int = 0
    private var menuPaddingStart: Float = 0f
    private var menuPaddingEnd: Float = 0f

    init {
        orientation = HORIZONTAL
        extractAttributes()
    }

    private fun extractAttributes() {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.Sofia)
        itemHorizontalPadding = attributes.getDimension(R.styleable.Sofia_itemHorizontalPadding, 10f)
        itemVerticalPadding = attributes.getDimension(R.styleable.Sofia_itemVerticalPadding, 10f)
        menu = attributes.getResourceId(R.styleable.Sofia_menu, -1)
        menuPaddingStart = attributes.getDimension(R.styleable.Sofia_android_paddingStart, 10f)
        menuPaddingEnd = attributes.getDimension(R.styleable.Sofia_android_paddingEnd, 10f)
        attributes.recycle()

        setupMenuItems()
        setupMenuLayoutParameters()
    }

    private fun setupMenuLayoutParameters() {
        updatePaddingRelative(
            start = menuPaddingStart.toPx(),
            end = menuPaddingEnd.toPx()
        )
    }

    private fun setupMenuItems() {
        val sofiaMenu = MenuParser(context).parse(menu)
        sofiaMenu.menuItems.forEach(this::renderMenuItem)
    }

    private fun renderMenuItem(menuItem: SofiaMenuItem) {
        val textView = AppCompatTextView(context)
        textView.id = menuItem.id
        textView.text = menuItem.title
        textView.updatePaddingRelative(
            start = itemHorizontalPadding.toPx(),
            end = itemHorizontalPadding.toPx(),
            top = itemHorizontalPadding.toPx(),
            bottom = itemHorizontalPadding.toPx()
        )
        addView(textView)
    }
}