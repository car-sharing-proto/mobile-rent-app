package com.kingofraccoons.supermegacarsharing.ui.themes

import android.content.Context
import com.dolatkia.animatedThemeManager.AppTheme

interface MyAppTheme : AppTheme {
    fun firstActivityBackgroundColor(context: Context): Int
    fun firstActivityTextColor(context: Context): Int
    fun firstActivityIconColor(context: Context): Int
    fun backgroundBNVColor(context: Context): Int
    fun rippleItemColor(context: Context): Int
}