package com.test.pokemongo.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 * This is a custom text-view class with some custom font style
 */
class AvenirTextView : TextView {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    fun init() {
        val tf = Typeface.createFromAsset(context.assets, "AvenirLTStd-Book.ttf")
        setTypeface(tf)
    }
}
