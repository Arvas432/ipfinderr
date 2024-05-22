package com.example.ipfinderr.ui.settings.state

sealed class SettingsState{
    object switchOn: SettingsState()
    object switchOff: SettingsState()
}
