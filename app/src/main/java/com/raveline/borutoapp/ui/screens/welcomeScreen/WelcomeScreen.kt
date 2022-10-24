package com.raveline.borutoapp.ui.screens.welcomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.raveline.borutoapp.data.model.OnBoardingPage
import com.raveline.borutoapp.ui.theme.*

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
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { page ->
            PagerScreen(onBoardingPage = pages[page])
        }
    }

}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val composition: LottieCompositionResult = rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(onBoardingPage.sourceJsonLottie)
        )

        val progress by animateLottieCompositionAsState(
            composition = composition.value,
            isPlaying = true,
            speed = 1f,
            iterations = LottieConstants.IterateForever,
        )

        Box(
            modifier = Modifier
                .fillMaxHeight(0.68f)
                .fillMaxWidth()
        ) {
            LottieAnimation(

                composition = composition.value,
                progress = progress,
                maintainOriginalImageBounds = true
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.title,
            color = MaterialTheme.colors.titleWelcomeColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.description,
            color = MaterialTheme.colors.descriptionWelcomeColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}
