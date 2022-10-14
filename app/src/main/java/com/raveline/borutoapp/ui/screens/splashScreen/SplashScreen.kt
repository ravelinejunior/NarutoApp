package com.raveline.borutoapp.ui.screens.splashScreen

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.raveline.borutoapp.ui.theme.Blue60
import com.raveline.borutoapp.ui.theme.Blue80

@Composable
fun SplashScreen(navController: NavController) {
    val degrees = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 2000,
                delayMillis = 200,
                easing = LinearOutSlowInEasing
            )
        )
    }
    Splash(degrees = degrees.value)
}

@Composable
fun Splash(degrees: Float) {
    val retrySignal = rememberLottieRetrySignal()
    val composition: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(com.raveline.borutoapp.R.raw.japan_flow),
        onRetry = { failCount, exception ->
            retrySignal.awaitRetry()
            Log.e("TAGLottie", "SplashError: ${exception.localizedMessage}")
            Log.e("TAGLottie", "SplashError: $failCount")

            // Continue retrying. Return false to stop trying.
            true
        }
    )
    val progress by animateLottieCompositionAsState(
        composition = composition.value,
        isPlaying = true,
        speed = 1f,
        iterations = LottieConstants.IterateForever
    )
    if (isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .background(
                    Color.Black.copy(alpha = 0.8f)
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                LottieAnimation(
                    composition = composition.value,
                    progress = progress,
                    modifier = Modifier
                        .rotate(degrees = degrees)
                        .padding(4.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f),
                    maintainOriginalImageBounds = true
                )

            }
        }
    } else {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Blue80, Blue60
                        )
                    )
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {


            LottieAnimation(
                composition = composition.value,
                progress = progress,
                modifier = Modifier
                    .rotate(degrees = degrees)
                    .padding(4.dp)
                    .fillMaxSize(),
                maintainOriginalImageBounds = true
            )
        }

    }
}

@Composable
@Preview
fun PreviewSplashScreen() {
    Splash(degrees = 0f)
}