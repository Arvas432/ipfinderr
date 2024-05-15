package com.example.ipfinderr.ui.settings

sealed class SettingsState{
    object switchOn: SettingsState()
    object switchOff: SettingsState()
}
