package com.example.ipfinderr.data.settings

import android.app.Application
import android.content.SharedPreferences
import com.example.ipfinderr.App
class ThemeSwitcherImpl(private val application: Application, private val sharedPrefs: SharedPreferences): ThemeSwitcher {
    override fun switchTheme(dark: Boolean) {
        (application as App).switchTheme(dark)
        if (dark){
            sharedPrefs
                .edit()
                .putString(THEME_MODE_KEY, DARK_MODE_VALUE)
                .apply()
        }
        else{
            sharedPrefs
                .edit()
                .putString(THEME_MODE_KEY, LIGHT_MODE_VALUE)
                .apply()
        }
    }

    override fun getSavedTheme() = (application as App).darkTheme

    companion object {

        const val THEME_MODE_KEY = "key_for_theme_mode"
        const val DARK_MODE_VALUE = "dark"
        const val LIGHT_MODE_VALUE = "light"
    }

}