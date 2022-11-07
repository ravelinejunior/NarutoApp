package com.raveline.borutoapp.ui.screens.homeScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.raveline.borutoapp.ui.common.components.RatingWidget
import com.raveline.borutoapp.ui.common.widget.ListComponent
import com.raveline.borutoapp.ui.screens.homeScreen.components.TopAppBarHome
import com.raveline.borutoapp.ui.theme.LARGE_PADDING
import com.raveline.borutoapp.ui.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBarHome(onSearchClicked = {})
        }
    ) {
        ListComponent(heroes = allHeroes, navController = navController)
    }
}