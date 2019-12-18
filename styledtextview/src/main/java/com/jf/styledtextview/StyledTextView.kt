package com.jf.styledtextview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class StyledTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    private var constructorCalled = false

    init {
        constructorCalled = true
    }
    override fun setText(text: CharSequence?, type: BufferType?) {
        if (!constructorCalled) {
            super.setText(text, type)

            post {
                setText(text, type)
            }
        } else {
            val spannable = SpannableStringBuilder(context)
                .spannableString(text.toString()) {
                    StyledTextViewRegistry.colorTags.forEach {
                        color(it.key, it.value)
                    }
                    StyledTextViewRegistry.fontTags.forEach {
                        typeface(it.key, it.value)
                    }
                }

            super.setText(spannable, BufferType.SPANNABLE)
        }
    }
}
