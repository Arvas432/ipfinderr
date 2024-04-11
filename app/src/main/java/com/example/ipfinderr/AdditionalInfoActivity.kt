package com.example.ipfinderr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AdditionalInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_additional_info)
        val backBtn = findViewById<ImageButton>(R.id.back_btn)
        backBtn.setOnClickListener {
            finish()
        }
    }
}