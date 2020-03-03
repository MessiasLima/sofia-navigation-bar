package br.com.messiaslima.sofia

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

class Sofia @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {
    init {
        orientation = HORIZONTAL
        LayoutInflater.from(context).inflate(R.layout.sofia, this, true)
    }
}