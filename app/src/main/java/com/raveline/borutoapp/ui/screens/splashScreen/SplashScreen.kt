package com.raveline.borutoapp.ui.screens.splashScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieRetrySignal
import com.raveline.borutoapp.ui.theme.Blue60
import com.raveline.borutoapp.ui.theme.Blue80

@Composable
fun SplashScreen(navController: NavController) {
    Splash()
}

@Composable
fun Splash() {
    val retrySignal = rememberLottieRetrySignal()
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Url("https://assets2.lottiefiles.com/packages/lf20_RuPsBY.json"),
        onRetry = { failCount, exception ->
            retrySignal.awaitRetry()
            Log.e("TAGLottie", "SplashError: ${exception.localizedMessage}")
            // Continue retrying. Return false to stop trying.
            true
        }
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
            LottieAnimation(
                composition = composition,
            )
        }
    } else {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        listOf(Blue80, Blue60)
                    )
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition = composition,
                isPlaying = true
            )
        }
    }
}

@Composable
@Preview
fun PreviewSplashScreen() {
    Splash()
}