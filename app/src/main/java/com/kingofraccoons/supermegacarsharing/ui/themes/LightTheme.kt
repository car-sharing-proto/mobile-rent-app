package com.kingofraccoons.supermegacarsharing.ui.themes

import android.content.Context
import androidx.core.content.ContextCompat
import com.kingofraccoons.supermegacarsharing.R

class LightTheme: MyAppTheme {
    override fun id(): Int {
        return 0
    }

    override fun firstActivityBackgroundColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.background_light)
    }

    override fun firstActivityTextColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.text_light)
    }

    override fun firstActivityIconColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.icon_light)
    }

    override fun backgroundBNVColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.bnv_light)
    }

    override fun rippleItemColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.ripple_color_light)
    }
}