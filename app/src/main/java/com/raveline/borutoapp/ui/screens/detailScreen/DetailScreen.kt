package com.raveline.borutoapp.ui.screens.detailScreen

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.raveline.borutoapp.ui.screens.detailScreen.components.DetailsContent
import com.raveline.borutoapp.ui.viewmodel.DetailsViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {

    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    DetailsContent(navController = navController, selectedHero = selectedHero)

}