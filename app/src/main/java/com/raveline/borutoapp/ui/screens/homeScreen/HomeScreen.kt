package com.raveline.borutoapp.ui.screens.homeScreen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.raveline.borutoapp.ui.screens.homeScreen.components.TopAppBarHome

@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBarHome(onSearchClicked = {})
        }
    ) {

    }
}