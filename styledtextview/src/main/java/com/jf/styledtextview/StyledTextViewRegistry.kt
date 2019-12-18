package com.jf.styledtextview

import androidx.annotation.ColorRes
import androidx.annotation.FontRes

object StyledTextViewRegistry {
    internal val colorTags = mutableMapOf(
        "blue" to R.color.blue
    )
    internal val fontTags = mutableMapOf(
        "avenir" to R.font.avenir_book,
        "dobraslab" to R.font.dobraslab_medium
    )

    fun addFont(tag: String, @FontRes font: Int) {
        fontTags[tag] = font
    }

    fun addColor(tag: String, @ColorRes color: Int) {
        colorTags[tag] = color
    }
}
