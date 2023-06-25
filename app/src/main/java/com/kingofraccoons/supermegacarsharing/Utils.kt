package com.kingofraccoons.supermegacarsharing

import android.content.Context
import android.content.res.Configuration

object Utils {

    /** Returns `true` if app has a dark theme, `false` is app has a light theme **/
    fun Context.isDarkThemeOn(): Boolean {
        return resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }
}