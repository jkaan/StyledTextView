package com.jf.styledtextview

import android.content.Context
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class SpannableStringBuilder(private val context: Context) {
    fun spannableString(
        text: String,
        spans: SpannableStringBuilderDsl.() -> Unit
    ): SpannableString {
        val builder = SpannableStringBuilder(text)

        spans(SpannableStringBuilderDsl(builder, context))

        return SpannableString(builder)
    }

    class SpannableStringBuilderDsl(val builder: SpannableStringBuilder, val context: Context) {
        fun color(tag: String, @ColorRes color: Int) {
            setSpanForTag(
                builder, tag, ForegroundColorSpan(
                    ContextCompat.getColor(context, color)
                )
            )
        }

        fun typeface(tag: String, @FontRes font: Int) {
            ResourcesCompat.getFont(context, font)?.let {
                setSpanForTag(
                    builder, tag, CustomTypefaceSpan(it)
                )
            }
        }

        private fun setSpanForTag(builder: SpannableStringBuilder, tag: String, span: Any) {
            val openingTag = "{$tag}"
            val closingTag = "{/$tag}"
            while (builder.indexOf(openingTag) != -1) {
                val startIndex = builder.indexOf(openingTag)
                val endIndex = builder.indexOf(closingTag)
                builder.setSpan(span, startIndex, endIndex, 0)
                val startIndexTag = builder.indexOf(openingTag)
                builder.delete(startIndexTag, startIndexTag + openingTag.length)
                val endIndexTag = builder.indexOf(closingTag)
                builder.delete(endIndexTag, endIndexTag + closingTag.length)
            }
        }
    }
}
