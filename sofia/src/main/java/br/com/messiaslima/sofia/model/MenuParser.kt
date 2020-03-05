package br.com.messiaslima.sofia.model

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.XmlResourceParser
import android.util.AttributeSet
import android.util.Xml
import androidx.annotation.MenuRes
import br.com.messiaslima.sofia.R
import org.xmlpull.v1.XmlPullParser.*

class MenuParser(private val context: Context) {

    private companion object {
        const val XML_MENU_TAG = "menu"
        const val XML_MENU_ITEM_TAG = "item"
    }

    @SuppressLint("ResourceType")
    fun parse(@MenuRes menuRes: Int): SofiaMenu {
        val parser = context.resources.getLayout(menuRes)
        val attrs = Xml.asAttributeSet(parser)
        skipMenuTagStart(parser)
        return parseMenu(parser, attrs)
    }

    private fun skipMenuTagStart(parser: XmlResourceParser) {
        var currentEvent = parser.eventType
        do {
            if (currentEvent == START_TAG) {
                val name = parser.name
                require(name == XML_MENU_TAG) { "Expecting menu, got $name" }
                break
            }
            currentEvent = parser.next()
        } while (currentEvent != END_DOCUMENT)
    }

    private fun parseMenu(parser: XmlResourceParser, attrs: AttributeSet): SofiaMenu {
        val items = mutableListOf<SofiaMenuItem>()
        var eventType = parser.eventType
        var isEndOfMenu = false

        while (!isEndOfMenu) {
            val name = parser.name
            when {
                eventType == START_TAG && name == XML_MENU_TAG -> { }
                eventType == START_TAG && name == XML_MENU_ITEM_TAG -> items.add(parseMenuItem(attrs))
                eventType == END_TAG && name == XML_MENU_TAG -> isEndOfMenu = true
                eventType == END_DOCUMENT -> throw RuntimeException("Unexpected end of document")

            }
            eventType = parser.next()
        }

        return SofiaMenu(items)
    }

    private fun parseMenuItem(attrs: AttributeSet): SofiaMenuItem {
        val sAttr = context.obtainStyledAttributes(attrs, R.styleable.SofiaMenuItem)

        val item = SofiaMenuItem(
            id = sAttr.getResourceId(R.styleable.SofiaMenuItem_android_id, 0),
            title = sAttr.getText(R.styleable.SofiaMenuItem_android_title)
        )

        sAttr.recycle()
        return item
    }
}