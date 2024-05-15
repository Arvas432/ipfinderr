package com.example.playlistmaker.domain.settings.impl

import com.example.ipfinderr.domain.settings.SettingsInteractor
import com.example.ipfinderr.domain.settings.SettingsRepository
import com.example.playlistmaker.domain.settings.model.ThemeSettings


class SettingsInteractorImpl(private val repository: SettingsRepository): SettingsInteractor {
    override fun getThemeSettings(): ThemeSettings {
        return repository.getThemeSettings()
    }

    override fun updateThemeSetting(settings: ThemeSettings) {
        repository.updateThemeSetting(settings)
    }
}