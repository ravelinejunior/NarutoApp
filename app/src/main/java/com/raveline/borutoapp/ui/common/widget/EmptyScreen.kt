package com.raveline.borutoapp.ui.common.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.paging.compose.LazyPagingItems
import com.airbnb.lottie.compose.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.raveline.borutoapp.R
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.ui.theme.DarkGray
import com.raveline.borutoapp.ui.theme.LightGray
import com.raveline.borutoapp.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<HeroModel>? = null
) {
    var message by remember {
        mutableStateOf("Find your hero!")
    }

    var resRaw by remember {
        mutableStateOf(R.raw.searching)
    }

    if (error != null) {
        message = parseErrorMessage(error)
        resRaw = R.raw.internet_error
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

    EmptyContent(
        alphaAnim = alphaAnim,
        message = message,
        resRaw = resRaw,
        heroes = heroes,
        error = error
    )

}


@Composable
fun EmptyContent(
    alphaAnim: Float,
    message: String,
    resRaw: Int,
    heroes: LazyPagingItems<HeroModel>? = null,
    error: LoadState.Error? = null,
) {


    var isRefreshing by remember {
        mutableStateOf(false)
    }
    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            isRefreshing = !isRefreshing
            heroes?.refresh()
            isRefreshing = !isRefreshing
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
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
}

fun parseErrorMessage(mError: LoadState.Error?): String {
    return when (mError?.error) {
        is SocketTimeoutException -> {
            "Server Unavailable!"
        }
        is ConnectException -> {
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
