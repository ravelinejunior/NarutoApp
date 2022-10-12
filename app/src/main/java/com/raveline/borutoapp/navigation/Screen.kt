package com.raveline.borutoapp.navigation

import com.raveline.borutoapp.utils.Constants.detailsScreen
import com.raveline.borutoapp.utils.Constants.heroId
import com.raveline.borutoapp.utils.Constants.homeScreen
import com.raveline.borutoapp.utils.Constants.searchScreen
import com.raveline.borutoapp.utils.Constants.splashScreen
import com.raveline.borutoapp.utils.Constants.welcomeScreen

sealed class Screen(val route: String) {
    object Splash : Screen(splashScreen)
    object Welcome : Screen(welcomeScreen)
    object Home : Screen(homeScreen)
    object Details : Screen("$detailsScreen/{$heroId}") {
        fun passHeroId(heroId: Int): String {
            return "$detailsScreen/$heroId"
        }
    }

    object Search : Screen(searchScreen)
}
