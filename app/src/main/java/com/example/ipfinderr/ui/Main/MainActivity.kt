package com.example.ipfinderr.ui.Main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.ipfinderr.R
import com.example.ipfinderr.databinding.ActivityMainBinding
import com.example.ipfinderr.domain.IpResult
import com.example.ipfinderr.ui.AdditionalInfoActivity
import com.example.ipfinderr.ui.MapActivity
import com.example.ipfinderr.ui.SettingsActivity
import com.example.ipfinderr.ui.searchHistory.SearchHistoryActivity
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var searchData: String = SEARCH_DEF
    private val viewModel by viewModel<MainActivityViewModel>()
    private lateinit var countryDataTv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val savedIp = Gson().fromJson(intent.getStringExtra(IP_RESULT_KEY), IpResult::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        countryDataTv = binding.headerEnd5
        setContentView(binding.root)
        if (savedInstanceState != null) {
            searchData = savedInstanceState.getString(SEARCH_VALUE, SEARCH_DEF)
        }
        viewModel.getScreenStateLiveData().observe(this){
            renderState(it)
        }
        //viewModel.searchDebounce()
        if(savedIp!=null){
            setContentScreenState(savedIp)
        }
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
                    viewModel.setSearchData(s.toString())
                    viewModel.searchDebounce()

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
        binding.searchFieldEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                viewModel.immediateSearch()
            }
            false
        }
    }

    private fun setContentScreenState(ipResult: IpResult) {
        binding.progressBar.isVisible = false
        binding.searchHistoryBtn.isVisible = true
        binding.headerEnd5.text = ipResult.country
        binding.headerEnd4.text = ipResult.city
        binding.headerEnd3.text = ipResult.isp
        binding.headerEnd2.text = ipResult.ip
        binding.headerEnd1.text = ipResult.longitude
        binding.headerEnd.text = ipResult.latitude
        Glide.with(this)
            .load(ipResult.imageUrl)
            .placeholder(R.drawable.placeholder)
            .centerInside()
            .into(binding.flag)
    }

    private fun setEmptyResultsScreenState() {

    }

    private fun setNetworkErrorScreenState() {

    }

    private fun setLoadingScreenState() {
        binding.searchHistoryBtn.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun setDefaultScreenState() {
        binding.searchHistoryBtn.isVisible = true
        binding.progressBar.isVisible = false
    }

    private fun renderState(state: MainState){
        when(state){
            is MainState.Default ->{setDefaultScreenState()}
            is MainState.Loading ->{setLoadingScreenState()}
            is MainState.NetworkError ->{setNetworkErrorScreenState()}
            is MainState.EmptyResults ->{setEmptyResultsScreenState()}
            is MainState.Content ->{setContentScreenState(state.ipResult)}
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
        const val IP_RESULT_KEY = "IP_RESULT_KEY"
    }
}
