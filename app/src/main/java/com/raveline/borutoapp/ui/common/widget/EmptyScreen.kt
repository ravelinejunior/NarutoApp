package com.raveline.borutoapp.ui.common.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import com.airbnb.lottie.compose.*
import com.raveline.borutoapp.R
import com.raveline.borutoapp.ui.theme.DarkGray
import com.raveline.borutoapp.ui.theme.LightGray
import com.raveline.borutoapp.ui.theme.SMALL_PADDING
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error
) {
    val message by remember {
        mutableStateOf(parseErrorMessage(message = error.toString()))
    }

    val resRaw by remember {
        mutableStateOf(R.raw.internet_error)
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f, animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(alphaAnim = alphaAnim, message = message, resRaw = resRaw)

}


@Composable
fun EmptyContent(alphaAnim: Float, message: String, resRaw: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val composition: LottieCompositionResult = rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(resRaw)
        )

        val progress by animateLottieCompositionAsState(
            composition = composition.value,
            isPlaying = true,
            speed = 1f,
            iterations = LottieConstants.IterateForever,
        )

        LottieAnimation(
            modifier = Modifier
                .fillMaxHeight(0.68f)
                .fillMaxWidth(0.9f),
            composition = composition.value,
            progress = progress,
            maintainOriginalImageBounds = true
        )

        Text(
            modifier = Modifier
                .padding(top = SMALL_PADDING)
                .alpha(alphaAnim),
            text = message,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}

fun parseErrorMessage(message: String): String {
    return when {
        message.contains(ignoreCase = true, other = "SocketTimeoutException") -> {
            "Server Unavailable!"
        }
        message.contains(ignoreCase = true, other = "ConnectException") -> {
            "Internet Unavailable!"
        }
        else -> {
            "Unknown Error."
        }

    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    EmptyScreen(error = LoadState.Error(SocketTimeoutException()))
}
