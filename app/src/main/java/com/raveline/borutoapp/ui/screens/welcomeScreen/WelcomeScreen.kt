package com.raveline.borutoapp.ui.screens.welcomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.raveline.borutoapp.data.model.OnBoardingPage
import com.raveline.borutoapp.ui.theme.welcomeScreenBackgroundColor

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScree(navController: NavController) {

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
    ) {
        HorizontalPager(count = pages.size, state = pagerState, verticalAlignment = Alignment.Top) {page ->
            PagerScreen(onBoardingPage = pages[page])
        }
    }

}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    TODO("Not yet implemented")
}
