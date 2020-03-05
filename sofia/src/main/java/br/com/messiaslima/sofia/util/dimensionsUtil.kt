package br.com.messiaslima.sofia.util

import android.content.res.Resources

fun Int.toDp(): Float = this / Resources.getSystem().displayMetrics.density

fun Float.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()