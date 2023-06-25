package com.kingofraccoons.supermegacarsharing.ui.themes

import android.content.Context
import androidx.core.content.ContextCompat
import com.kingofraccoons.supermegacarsharing.R

class DarkTheme: MyAppTheme {
    override fun id(): Int {
        return 1
    }

    override fun firstActivityBackgroundColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.background_dark)
    }

    override fun firstActivityTextColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.text_dark)
    }

    override fun firstActivityIconColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.icon_dark)
    }

    override fun backgroundBNVColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.bnv_dark)
    }

    override fun rippleItemColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.ripple_color_dark)
    }
}