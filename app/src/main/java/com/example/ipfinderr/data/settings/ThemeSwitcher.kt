package com.example.ipfinderr.data.settings

interface ThemeSwitcher {
    fun switchTheme(dark: Boolean)
    fun getSavedTheme(): Boolean
}