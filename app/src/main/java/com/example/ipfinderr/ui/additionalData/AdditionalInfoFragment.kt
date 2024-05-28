package com.example.ipfinderr.ui.additionalData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.ipfinderr.R
import com.example.ipfinderr.databinding.FragmentAdditionalInfoBinding
import com.example.ipfinderr.domain.search.IpResult
import com.example.ipfinderr.ui.BindingFragment
import com.example.ipfinderr.ui.Main.fragment.MainFragment
import com.google.gson.Gson

class AdditionalInfoFragment : BindingFragment<FragmentAdditionalInfoBinding>() {


    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAdditionalInfoBinding {
        return FragmentAdditionalInfoBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ipInput = Gson().fromJson<IpResult>(requireArguments().getString(IP_RESULT_KEY),
            IpResult::class.java)
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.ipTv.text = ipInput.ip
        Glide.with(this)
            .load(ipInput.imageUrl)
            .placeholder(R.drawable.placeholder)
            .centerInside()
            .into(binding.flag)
        binding.headerEnd5.text = ipInput.country
        binding.headerEnd4.text = ipInput.city
        binding.headerEnd3.text = ipInput.isp
        binding.headerEnd2.text = ipInput.ip
        binding.headerEnd1.text = ipInput.latitude
        binding.headerEnd.text = ipInput.longitude
        binding.headerEnd11.text = ipInput.country
        binding.headerEnd10.text = ipInput.countryCode
        binding.headerEnd9.text = ipInput.district
        binding.headerEnd8.text = ipInput.zipcode
        binding.headerEnd7.text = ipInput.timeZone
        binding.headerEnd6.text = ipInput.currency
    }

    companion object {
        private const val ARG_IP_RESULT = "argIpResult"
        const val IP_RESULT_KEY = "IP_RESULT_KEY"
        fun newInstance(ipResult: IpResult): MainFragment = MainFragment().apply {
            arguments = bundleOf(ARG_IP_RESULT to Gson().toJson(ipResult))
        }
    }
}