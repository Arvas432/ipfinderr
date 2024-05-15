package com.example.ipfinderr.ui.settings

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.ipfinderr.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : AppCompatActivity() {
    private val viewModel by viewModel<SettingsViewModel>()
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getScreenStateLiveData().observe(this){
            when(it){
                SettingsState.switchOff -> binding.nightModeSwitch.isChecked = false
                SettingsState.switchOn -> binding.nightModeSwitch.isChecked = true
            }
        }
        binding.nightModeSwitch.setOnCheckedChangeListener { switcher, isChecked ->
            viewModel.switchTheme(isChecked)
        }
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.shareButton.setOnClickListener {
            val shareLink = getString(R.string.share_link)
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareLink)
            shareIntent.type = "text/plain"
            startActivity(shareIntent)
        }
        binding.techSupportButton.setOnClickListener {
            val techSupportIntent = Intent(Intent.ACTION_SENDTO)
            techSupportIntent.data = Uri.parse("mailto:")
            techSupportIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.my_email)))
            techSupportIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
            techSupportIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_contents))
            startActivity(Intent.createChooser(techSupportIntent, "Send Email"))
        }
        binding.termsOfServiceButton.setOnClickListener {
            val termsOfServiceLink = getString(R.string.terms_of_service_link)
            val termsOfServiceIntent = Intent(Intent.ACTION_VIEW)
            termsOfServiceIntent.data = Uri.parse(termsOfServiceLink)
            startActivity(termsOfServiceIntent)
        }
    }
}