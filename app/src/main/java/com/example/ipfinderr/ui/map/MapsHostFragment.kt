package com.example.ipfinderr.ui.map

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.ipfinderr.R
import com.example.ipfinderr.databinding.FragmentMapsHostBinding
import com.example.ipfinderr.domain.search.IpResult
import com.example.ipfinderr.ui.BindingFragment
import com.example.ipfinderr.ui.Main.fragment.MainFragment
import com.google.gson.Gson


class MapsHostFragment : BindingFragment<FragmentMapsHostBinding>(){
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMapsHostBinding {
        return FragmentMapsHostBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ipInput = Gson().fromJson(requireArguments().getString(IP_RESULT_KEY), IpResult::class.java)
        binding.ipTv.text = ipInput.ip
        Glide.with(this)
            .load(ipInput.imageUrl)
            .placeholder(R.drawable.placeholder)
            .centerInside()
            .into(binding.flagView)
        binding.headerEnd5.text = ipInput.country
        binding.headerEnd4.text = ipInput.city
        binding.headerEnd1.text = ipInput.longitude
        binding.headerEnd.text = ipInput.latitude
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.map, MapsFragment.newInstance(ipInput))
                .commit()
        }
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.mapButton.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:${ipInput.latitude},${ipInput.longitude}?q=${ipInput.zipcode}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(requireActivity().packageManager)?.let {
                startActivity(mapIntent)
            }

        }
    }
    companion object {
        private const val ARG_IP_RESULT = "argIpResult"
        const val IP_RESULT_KEY = "IP_RESULT_KEY"
        fun newInstance(ipResult: IpResult): MainFragment = MainFragment().apply {
            arguments = bundleOf(ARG_IP_RESULT to Gson().toJson(ipResult))
        }
    }
}