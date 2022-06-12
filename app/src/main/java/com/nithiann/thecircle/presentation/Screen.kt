package com.nithiann.thecircle.presentation

sealed class Screen(val route: String) {
    object AboutScreen: Screen("about")
    object NavigationBar: Screen("navigation")
    object LiveScreen: Screen("live")
}