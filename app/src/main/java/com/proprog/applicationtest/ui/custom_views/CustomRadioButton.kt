package com.proprog.applicationtest.ui.custom_views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.res.ResourcesCompat
import com.proprog.applicationtest.R


class CustomRadioButton : AppCompatRadioButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        buttonDrawable = ResourcesCompat.getDrawable(
                resources,
                R.drawable.rb_button_checked_state,
                null
            )
        setBackgroundColor(ResourcesCompat.getColor(resources,R.color.rb_check_color_state,null))
        setCompoundDrawablesRelative(
          null, null,  ResourcesCompat.getDrawable(
                resources,
                R.drawable.rb_button_checked_state,
                null
            ), null
        )
    }

}