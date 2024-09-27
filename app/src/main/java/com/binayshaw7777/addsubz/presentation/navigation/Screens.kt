package com.binayshaw7777.addsubz.presentation.navigation

import com.binayshaw7777.addsubz.utils.Constants

sealed class Screens(val name: String) {
    data object HomeScreen : Screens(Constants.HOME_SCREEN)
    data object SettingScreen : Screens(Constants.SETTING_SCREEN)
}