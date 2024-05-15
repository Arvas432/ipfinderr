package com.example.ipfinderr.ui.map

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.example.ipfinderr.R
import com.example.ipfinderr.databinding.ActivityMainBinding
import com.example.ipfinderr.databinding.ActivityMapBinding
import com.example.ipfinderr.databinding.ActivitySettingsBinding
import com.example.ipfinderr.domain.IpResult
import com.example.ipfinderr.ui.additionalData.AdditionalInfoActivity
import com.google.gson.Gson

class MapActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ipInput = Gson().fromJson(intent.getStringExtra(MAP_KEY), IpResult::class.java)
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
            supportFragmentManager.beginTransaction()
                .replace(R.id.map, MapsFragment.newInstance(ipInput))
                .commit()
        }
        val backBtn = findViewById<ImageButton>(R.id.back_btn)
        backBtn.setOnClickListener {
            finish()
        }
    }
    companion object{
        const val MAP_KEY = "MAP_KEY"
    }

}