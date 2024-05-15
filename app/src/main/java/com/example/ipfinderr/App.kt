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
    override fun onCreate() {
        startKoin{
            androidContext(this@App)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule)
        }
        super.onCreate()
    }
}