package com.example.ipfinderr.ui.additionalData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.example.ipfinderr.R
import com.example.ipfinderr.databinding.ActivityAdditionalInfoBinding
import com.example.ipfinderr.domain.IpResult
import com.google.gson.Gson

class AdditionalInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdditionalInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdditionalInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ipInput = Gson().fromJson(intent.getStringExtra(IP_ADDITIONAL_KEY), IpResult::class.java)
        binding.backBtn.setOnClickListener {
            finish()
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
    companion object{
        const val IP_ADDITIONAL_KEY = "IP_ADDITIONAL_KEY"
    }
}