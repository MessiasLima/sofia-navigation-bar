package br.com.messiaslima.sofia

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.core.view.updatePaddingRelative
import br.com.messiaslima.sofia.model.MenuParser
import br.com.messiaslima.sofia.model.SofiaMenu
import br.com.messiaslima.sofia.model.SofiaMenuItem
import br.com.messiaslima.sofia.util.toPx
import kotlinx.android.synthetic.main.sofia.view.*

class Sofia @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {


    companion object {
        const val DEFAULT_ITEM_HORIZONTAL_PADDING = 20f
        const val DEFAULT_ITEM_HORIZONTAL_MARGIN = 10f
        const val DEFAULT_ITEM_VERTICAL_PADDING = 0f
        const val DEFAULT_ITEM_VERTICAL_MARGIN = 10f
    }

    private lateinit var sofiaMenu: SofiaMenu
    private var itemHorizontalMargin: Float = DEFAULT_ITEM_HORIZONTAL_MARGIN
    private var itemHorizontalPadding: Float = DEFAULT_ITEM_HORIZONTAL_PADDING
    private var itemVerticalPadding: Float = DEFAULT_ITEM_VERTICAL_PADDING
    private var menu: Int = 0
    private var selectedTextColor: Int = 0
    private var unselectedTextColor: Int = 0

    var onItemSelected: ((item: SofiaMenuItem) -> Unit)? = null

    init {
        orientation = HORIZONTAL
        setupBaseViews()
        extractAttributes()
    }

    private fun setupBaseViews() {
        LayoutInflater.from(context).inflate(R.layout.sofia, this, true)
    }

    private fun extractAttributes() {

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.Sofia)

        itemHorizontalPadding = attributes.getDimension(
            R.styleable.Sofia_itemHorizontalPadding,
            DEFAULT_ITEM_HORIZONTAL_PADDING
        )

        itemHorizontalMargin = attributes.getDimension(
            R.styleable.Sofia_itemHorizontalMargin,
            DEFAULT_ITEM_HORIZONTAL_MARGIN
        )

        itemVerticalPadding = attributes.getDimension(
            R.styleable.Sofia_itemVerticalPadding,
            DEFAULT_ITEM_VERTICAL_PADDING
        )

        menu = attributes.getResourceId(R.styleable.Sofia_menu, -1)

        selectedTextColor = attributes.getColor(
            R.styleable.Sofia_selectedTextColor,
            getColor(R.color.white)
        )

        unselectedTextColor = attributes.getColor(
            R.styleable.Sofia_unselectedTextColor,
            getColor(R.color.purple)
        )

        attributes.recycle()

        setupMenuItems()
    }

    private fun getColor(color: Int) = ContextCompat.getColor(context, color)


    private fun setupMenuItems() {
        sofiaMenu = MenuParser(context).parse(menu)
        sofiaMenu.menuItems.forEachIndexed(this::renderMenuItem)
    }

    private fun renderMenuItem(index: Int, menuItem: SofiaMenuItem) {
        val radioButton = RadioButton(context)
        radioButton.id = menuItem.id
        radioButton.text = menuItem.title
        radioButton.buttonDrawable = null
        radioButton.background = ContextCompat.getDrawable(context, R.drawable.shape)
        radioButton.setTextColor(unselectedTextColor)
        radioButton.updatePaddingRelative(
            start = itemHorizontalPadding.toPx(),
            end = itemHorizontalPadding.toPx(),
            top = itemVerticalPadding.toPx(),
            bottom = itemVerticalPadding.toPx()
        )
        radioButton.textSize = 17f
        radioButton.elevation = 10f
        radioButton.setOnCheckedChangeListener(this::onChangeButtonChecked)
        radioButton.isChecked = index == 0
        sofiaButtonsWrapper.addView(radioButton)

        radioButton.post {
            val newParams = RadioGroup.LayoutParams(radioButton.width, radioButton.height)
            val marginStart = if (index == 0) {
                itemHorizontalMargin.toPx() * 2
            } else {
                itemHorizontalMargin.toPx()
            }
            newParams.setMargins(
                marginStart,
                DEFAULT_ITEM_VERTICAL_MARGIN.toPx(),
                itemHorizontalMargin.toPx(),
                DEFAULT_ITEM_VERTICAL_MARGIN.toPx()
            )
            radioButton.layoutParams = newParams
        }
    }

    private fun onChangeButtonChecked(buttonView: CompoundButton, isChecked: Boolean) {
        changeButtonColor(isChecked, buttonView)
        if (isChecked) {
            onItemSelected?.invoke(sofiaMenu.menuItems.find { it.id == buttonView.id }!!)
        }
    }

    private fun changeButtonColor(
        isChecked: Boolean,
        buttonView: CompoundButton
    ) {
        val color = if (isChecked) selectedTextColor else unselectedTextColor
        buttonView.setTextColor(color)
    }

    fun setSelectedItem(index: Int) {
        val radioButton = (sofiaButtonsWrapper.getChildAt(index) as RadioButton)
        scrollToItem(index, radioButton)
        radioButton.isChecked = true
    }

    private fun scrollToItem(index: Int, radioButton: RadioButton) {
        val x = if (index == 0) {
            0
        } else {
            radioButton.right
        }
        sofiaScrollView.smoothScrollTo(x, 0)
    }
}