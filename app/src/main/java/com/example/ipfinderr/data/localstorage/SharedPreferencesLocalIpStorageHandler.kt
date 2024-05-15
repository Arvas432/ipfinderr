package com.example.ipfinderr.data.localstorage

import android.content.SharedPreferences
import com.example.ipfinderr.domain.IpResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesLocalIpStorageHandler(private val sharedPreferences: SharedPreferences, private val gson: Gson):
    LocalIpStorageHandler {
    override fun write(input: IpResult){
        var currentSearchHistory = read()
        currentSearchHistory = currentSearchHistory.filter { it.ip!=input.ip}.toMutableList()
        if (currentSearchHistory.size==10){
            currentSearchHistory.removeAt(currentSearchHistory.lastIndex)
        }
        currentSearchHistory.add(0,input)
        clear()
        sharedPreferences
            .edit()
            .putString(SEARCH_HISTORY_KEY, gson.toJson(currentSearchHistory))
            .apply()
    }
    override fun clear(){
        sharedPreferences
            .edit()
            .remove(SEARCH_HISTORY_KEY)
            .apply()
    }
    override fun read(): List<IpResult> {
        val json = sharedPreferences.getString(SEARCH_HISTORY_KEY, null) ?: return emptyList()
        return gson.fromJson(json, object: TypeToken<List<IpResult>>() {}.type)
    }
    companion object{
        const val SEARCH_HISTORY_KEY = "search_history_key"
    }
}