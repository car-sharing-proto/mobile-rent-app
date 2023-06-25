package com.kingofraccoons.supermegacarsharing

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dolatkia.animatedThemeManager.AppTheme
import com.dolatkia.animatedThemeManager.ThemeActivity
import com.dolatkia.animatedThemeManager.ThemeManager
import com.kingofraccoons.supermegacarsharing.Utils.isDarkThemeOn
import com.kingofraccoons.supermegacarsharing.databinding.ActivityMainBinding
import com.kingofraccoons.supermegacarsharing.ui.themes.DarkTheme
import com.kingofraccoons.supermegacarsharing.ui.themes.LightTheme
import com.kingofraccoons.supermegacarsharing.ui.themes.MyAppTheme

class MainActivity : ThemeActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun getStartTheme(): AppTheme {
        return when (getThemePosition()) {
            0 -> {
                if (isDarkThemeOn())
                    DarkTheme()
                else
                    LightTheme()
            }

            1 -> {
                LightTheme()
            }

            2 -> {
                DarkTheme()
            }

            else -> {
                if (isDarkThemeOn())
                    DarkTheme()
                else
                    LightTheme()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navHostController =
            supportFragmentManager.findFragmentById(R.id.container)?.findNavController()!!

        setupActionBarWithNavController(
            navHostController,
            AppBarConfiguration(navHostController.graph)
        )
        binding.bottomNavigationView.setupWithNavController(navHostController)
    }

    override fun syncTheme(appTheme: AppTheme) {
        if (appTheme is MyAppTheme) {
            binding.bottomNavigationView.itemTextColor =
                ColorStateList.valueOf(appTheme.firstActivityTextColor(this))
            binding.bottomNavigationView.itemIconTintList =
                ColorStateList.valueOf(appTheme.firstActivityIconColor(this))
            binding.root.background = ColorDrawable(appTheme.firstActivityBackgroundColor(this))
            binding.bottomNavigationView.backgroundTintList = ColorStateList.valueOf(
                appTheme.backgroundBNVColor(this)
            )
            binding.bottomNavigationView.itemActiveIndicatorColor = ColorStateList.valueOf(
                appTheme.rippleItemColor(this)
            )

            binding.toolbar.backgroundTintList = ColorStateList.valueOf(
                appTheme.firstActivityBackgroundColor(
                    this
                )
            )
            binding.toolbar.setTitleTextColor(appTheme.firstActivityTextColor(this))


            ThemeManager.instance.syncStatusBarIconsColorWithBackground(
                this,
                appTheme.firstActivityBackgroundColor(this)
            )
        }
    }

    private fun getThemePosition(): Int {
        val themes = resources.getStringArray(R.array.entries_theme)
        val sharedPreferences = getSharedPreferences("theme", Context.MODE_PRIVATE)

        return themes.indexOf(
            sharedPreferences.getString(
                resources.getString(R.string.theme_key),
                themes[0]
            )
        )
    }
}