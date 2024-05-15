package com.example.ipfinderr.di

import android.content.Context
import android.content.SharedPreferences
import com.example.ipfinderr.data.NetworkClient
import com.example.ipfinderr.data.RetrofitNetworkClient
import com.example.ipfinderr.data.localstorage.LocalIpStorageHandler
import com.example.ipfinderr.data.localstorage.SharedPreferencesLocalIpStorageHandler
import com.example.ipfinderr.domain.IpFindApi
import com.google.gson.Gson
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.android.ext.koin.androidContext

val dataModule = module{
    val iTunesBaseUrl = "https://api.ipgeolocation.io/getip/"
    val IP_FINDERR_PREFERENCES = "ip_finder_preferences"
    single<IpFindApi>{
        Retrofit.Builder()
            .baseUrl(iTunesBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IpFindApi::class.java)
    }
    single{
        Gson()
    }
    single<NetworkClient>{
        RetrofitNetworkClient(get())
    }
    single {
        androidContext().getSharedPreferences(IP_FINDERR_PREFERENCES, Context.MODE_PRIVATE)
    }
    single<LocalIpStorageHandler>{
        SharedPreferencesLocalIpStorageHandler(get(), get())
    }

}