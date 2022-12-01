package com.raveline.borutoapp.ui.screens.homeScreen

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.raveline.borutoapp.navigation.Screen
import com.raveline.borutoapp.ui.common.widget.ListComponent
import com.raveline.borutoapp.ui.screens.homeScreen.components.TopAppBarHome
import com.raveline.borutoapp.ui.theme.statusBarBackgroundColor
import com.raveline.borutoapp.ui.viewmodel.HomeViewModel

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarBackgroundColor
    )

    Scaffold(
        topBar = {
            TopAppBarHome(onSearchClicked = {
                navController.navigate(Screen.Search.route)
            })
        }
    ) {
        ListComponent(heroes = allHeroes, navController = navController)
    }
}