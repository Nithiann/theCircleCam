package com.nithiann.thecircle.presentation

sealed class Screen(val route: String) {
    object AboutScreen: Screen("about")
    object LiveScreen: Screen("live")
    object ProfileScreen: Screen("profile")
}