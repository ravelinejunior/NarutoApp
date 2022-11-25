package com.raveline.borutoapp.ui.screens.detailScreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.raveline.borutoapp.ui.viewmodel.DetailsViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {

    val selectedHero = detailsViewModel.selectedHero

}