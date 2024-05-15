package com.example.ipfinderr

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.ipfinderr.di.dataModule
import com.example.ipfinderr.di.interactorModule
import com.example.ipfinderr.di.repositoryModule
import com.example.ipfinderr.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    var darkTheme = false
    override fun onCreate() {
        val savedThemeValue = getSharedPreferences(IP_FINDERR_PREFERENCES, MODE_PRIVATE).getString(
            THEME_MODE_KEY, "")
        if(savedThemeValue.isNullOrEmpty()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        else{
            when(savedThemeValue){
                "dark" -> {
                    darkTheme = true
                }
                "light" -> {
                    darkTheme = false
                }
            }
            switchTheme(darkTheme)
        }
        startKoin{
            androidContext(this@App)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule)
        }
        super.onCreate()
    }
    fun switchTheme(darkThemeEnabled: Boolean) {
        darkTheme = darkThemeEnabled
        AppCompatDelegate.setDefaultNightMode(
            if (darkThemeEnabled) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        )
    }
    companion object {

        const val THEME_MODE_KEY = "key_for_theme_mode"
        const val  IP_FINDERR_PREFERENCES = "ip_finder_preferences"
    }
}