package com.raveline.borutoapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.raveline.borutoapp.ui.screens.homeScreen.HomeScreen
import com.raveline.borutoapp.ui.screens.searchScreen.SearchScreen
import com.raveline.borutoapp.ui.screens.splashScreen.SplashScreen
import com.raveline.borutoapp.ui.screens.welcomeScreen.WelcomeScree
import com.raveline.borutoapp.utils.Constants

@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScree(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(Constants.heroId) {
                type = NavType.IntType
            })
        ) {

        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
    }
}