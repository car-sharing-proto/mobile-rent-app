package com.kingofraccoons.supermegacarsharing.ui.main.settings

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dolatkia.animatedThemeManager.AppTheme
import com.dolatkia.animatedThemeManager.ThemeFragment
import com.dolatkia.animatedThemeManager.ThemeManager
import com.kingofraccoons.supermegacarsharing.R
import com.kingofraccoons.supermegacarsharing.Utils.isDarkThemeOn
import com.kingofraccoons.supermegacarsharing.databinding.FragmentSettingsBinding
import com.kingofraccoons.supermegacarsharing.ui.themes.DarkTheme
import com.kingofraccoons.supermegacarsharing.ui.themes.LightTheme
import com.kingofraccoons.supermegacarsharing.ui.themes.MyAppTheme

class SettingsFragment : ThemeFragment() {
    var binding: FragmentSettingsBinding? = null
    override fun syncTheme(appTheme: AppTheme) {
        if (appTheme is MyAppTheme) {
            binding?.root?.background = ColorDrawable(
                appTheme.firstActivityBackgroundColor(requireContext())
            )

            binding?.titleThemes?.setTextColor(appTheme.firstActivityTextColor(requireContext()))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (binding == null)
            binding = FragmentSettingsBinding.bind(view)
        val adapter = this.getThemeManager()?.getCurrentLiveTheme()?.let {
            ThemesAdapter(it, getThemePosition()) { nameTheme, view ->
                setTheme(nameTheme)
                updateTheme(nameTheme, view)
            }
        }

        binding?.let {
            it.recyclerThemes.adapter = adapter
            adapter?.submitList(resources.getStringArray(R.array.entries_theme).toList())
        }
    }

    private fun updateTheme(currentTheme: String, view: View) {
        val themes = resources.getStringArray(R.array.entries_theme)
        println(themes)
        println(currentTheme)
        when (currentTheme) {
            // update to system theme
            themes[0] -> {
                ThemeManager.instance.changeTheme(
                    if (requireContext().isDarkThemeOn())
                        DarkTheme()
                    else
                        LightTheme(),
                    view
                )
            }

            // update to light theme
            themes[1] -> {
                ThemeManager.instance.changeTheme(LightTheme(), view)
            }

            // update to dark theme
            themes[2] -> {
                ThemeManager.instance.changeTheme(DarkTheme(), view)
            }
        }
    }


    private fun getThemePosition(): Int {
        val themes = resources.getStringArray(R.array.entries_theme)
        val sharedPreferences = requireContext().getSharedPreferences("theme", Context.MODE_PRIVATE)

        return themes.indexOf(
            sharedPreferences.getString(
                resources.getString(R.string.theme_key),
                themes[0]
            )
        )
    }

    private fun setTheme(nameTheme: String) {
        val sharedPreferences = requireContext().getSharedPreferences("theme", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString(resources.getString(R.string.theme_key), nameTheme)
            .apply()
    }
}