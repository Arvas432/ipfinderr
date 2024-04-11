package com.example.ipfinderr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private var searchData: String = SEARCH_DEF
    private val handler = Handler(Looper.getMainLooper())
    private var ipInteractor = Creator.provideIpInteractor()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            searchData = savedInstanceState.getString(SEARCH_VALUE, SEARCH_DEF)
        }
        ipInteractor.searchIp("8.8.8.8", object: IpInteractor.IpConsumer{
            override fun consume(searchResult: IpResult) {
                Log.i("NETWORK", "IM HERE")
                Log.i("NETWORK", "IM HERE")
                Log.i("NETWORK", "IM HERE")
                Log.i("NETWORK", "IM HERE")
                Log.i("NETWORK", "IM HERE")

                handler.post{
                    Log.i("RESPONSE", searchResult.city)
                }
            }
        })

        val moreInfoButton = findViewById<FrameLayout>(R.id.additional_info_button)
        val mapButton = findViewById<FrameLayout>(R.id.map_button)
        val settingsButton = findViewById<ImageButton>(R.id.settings_button)
        val searchHistoryButton = findViewById<Button>(R.id.search_history_btn)
        val searchTextField = findViewById<EditText>(R.id.search_field_et)
        val clearButton = findViewById<ImageView>(R.id.clear_button)
        moreInfoButton.setOnClickListener {
            val navigateToAdditionalInfoIntent = Intent(this, AdditionalInfoActivity::class.java)
            startActivity(navigateToAdditionalInfoIntent)
        }
        mapButton.setOnClickListener {
            val navigateToMapIntent = Intent(this, MapActivity::class.java)
            startActivity(navigateToMapIntent)
        }
        settingsButton.setOnClickListener {
            val navigateToSettingsIntent = Intent(this, SettingsActivity::class.java)
            startActivity(navigateToSettingsIntent)
        }
        searchHistoryButton.setOnClickListener {
            val navigateToSearchHistoryIntent = Intent(this, SearchHistoryActivity::class.java)
            startActivity(navigateToSearchHistoryIntent)
        }
        val searchFieldTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()){
                    clearButton.visibility = View.GONE
                }
                else{
                    clearButton.visibility = View.VISIBLE
                }
            }
            override fun afterTextChanged(s: Editable?) = Unit
        }
        searchTextField.addTextChangedListener(searchFieldTextWatcher)
        searchTextField.setText(searchData)
        clearButton.setOnClickListener {
            currentFocus?.let {
                val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
                inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
            }
            searchTextField.setText("")
        }
    }
    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState)
        outState.putString(SEARCH_VALUE, searchData)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        searchData = savedInstanceState.getString(SEARCH_VALUE, SEARCH_DEF)
    }
    companion object {
        const val SEARCH_VALUE = "SEARCH_VALUE"
        const val SEARCH_DEF = ""
    }
}