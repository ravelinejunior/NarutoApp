package com.raveline.borutoapp.ui.screens.welcomeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.google.accompanist.pager.*
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
            modifier = Modifier.weight(10f),
            count = pages.size,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { page ->
            PagerScreen(onBoardingPage = pages[page])
        }

        // Dot Indicator
        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),

            pagerState = pagerState,
            activeColor = MaterialTheme.colors.activeColorIndicator,
            inactiveColor = MaterialTheme.colors.inactiveColorIndicator,
            indicatorWidth = MEDIUM_PADDING,
            spacing = SMALL_PADDING
        )

        //Finish Button
        FinishButton(pagerState = pagerState, modifier = Modifier.weight(1f)) {

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

@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(
            horizontal = LARGE_PADDING
        ), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                onClick = onClick, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Blue80,
                    contentColor = White,
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Finish", style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                ))
            }
        }
    }
}
