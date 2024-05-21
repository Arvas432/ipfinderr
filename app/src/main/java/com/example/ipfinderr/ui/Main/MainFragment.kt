package com.example.ipfinderr.ui.Main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.ipfinderr.R
import com.example.ipfinderr.databinding.ActivityMainBinding
import com.example.ipfinderr.databinding.FragmentMainBinding
import com.example.ipfinderr.domain.IpResult
import com.example.ipfinderr.ui.BindingFragment
import com.example.ipfinderr.ui.additionalData.AdditionalInfoActivity
import com.example.ipfinderr.ui.map.MapActivity
import com.example.ipfinderr.ui.map.MapsFragment
import com.example.ipfinderr.ui.searchHistory.SearchHistoryActivity
import com.example.ipfinderr.ui.settings.SettingsActivity
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BindingFragment<FragmentMainBinding>() {
    private var searchData: String = MainActivity.SEARCH_DEF
    private val viewModel by viewModel<MainActivityViewModel>()
    private lateinit var curIp: IpResult
    private lateinit var countryDataTv: TextView

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments!=null){
            curIp = Gson().fromJson(requireArguments().getString(ARG_IP_RESULT), IpResult::class.java)

        }
        else{
            curIp = IpResult("", "", "", "", "", "", "", "", "","", "", "")
        }
        countryDataTv = binding.headerEnd5
        viewModel.getScreenStateLiveData().observe(viewLifecycleOwner){
            renderState(it)
        }
        viewModel.getSearchTextLiveData().observe(viewLifecycleOwner){
            binding.searchFieldEt.setText(it)
        }
        if(curIp.ip!=""){
            setContentScreenState(curIp)
        }
        binding.additionalInfoButton.setOnClickListener {
            if(curIp.ip!=""){
//                val navigateToAdditionalInfoIntent = Intent(this, AdditionalInfoActivity::class.java)
//                navigateToAdditionalInfoIntent.putExtra(MainActivity.IP_ADDITIONAL_KEY, Gson().toJson(curIp))
//                startActivity(navigateToAdditionalInfoIntent)
            }

        }
        binding.mapButton.setOnClickListener {
            if(curIp.ip!=""){
//                val navigateToMapIntent = Intent(this, MapActivity::class.java)
//                navigateToMapIntent.putExtra(MainActivity.MAP_KEY, Gson().toJson(curIp))
//                startActivity(navigateToMapIntent)
            }
        }
//        settingsButton.setOnClickListener {
//            val navigateToSettingsIntent = Intent(this, SettingsActivity::class.java)
//            startActivity(navigateToSettingsIntent)
//        }
//        searchHistoryButton.setOnClickListener {
//            val navigateToSearchHistoryIntent = Intent(this, SearchHistoryActivity::class.java)
//            startActivity(navigateToSearchHistoryIntent)
//        }
        val searchFieldTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()){
                    binding.clearButton.visibility = View.GONE
                }
                else{
                    binding.clearButton.visibility = View.VISIBLE
                    viewModel.setSearchData(s.toString())
                    viewModel.searchDebounce()

                }
            }
            override fun afterTextChanged(s: Editable?) = Unit
        }
        binding.searchFieldEt.addTextChangedListener(searchFieldTextWatcher)
        binding.searchFieldEt.setText(searchData)
        binding.clearButton.setOnClickListener {
            requireActivity().currentFocus?.let {
                val inputMethodManager = ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)!!
                inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
            }
//            binding.searchFieldEt.setText("")
            viewModel.setSearchData("")
        }
        binding.searchFieldEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.immediateSearch()
            }
            false
        }
    }
    private fun setContentScreenState(ipResult: IpResult) {
        curIp = ipResult
        binding.networkErrorView.isVisible = false
        binding.noResultsView.isVisible = false
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
        binding.progressBar.isVisible = false
        binding.searchHistoryBtn.isVisible = false
        binding.networkErrorView.isVisible = false
        binding.noResultsView.isVisible = true
    }

    private fun setNetworkErrorScreenState() {
        binding.progressBar.isVisible = false
        binding.searchHistoryBtn.isVisible = false
        binding.networkErrorView.isVisible = true
        binding.noResultsView.isVisible = false
    }

    private fun setLoadingScreenState() {
        binding.searchHistoryBtn.isVisible = false
        binding.progressBar.isVisible = true
        binding.networkErrorView.isVisible = false
        binding.noResultsView.isVisible = false
    }

    private fun setDefaultScreenState() {
        binding.networkErrorView.isVisible = false
        binding.noResultsView.isVisible = false
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

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
    companion object {
        private const val ARG_IP_RESULT = "argIpResult"
        fun newInstance(ipResult: IpResult): MainFragment = MainFragment().apply {
            arguments = bundleOf(ARG_IP_RESULT to Gson().toJson(ipResult))
        }
    }
}