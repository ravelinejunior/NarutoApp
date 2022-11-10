package com.raveline.borutoapp.ui.common.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.raveline.borutoapp.ui.theme.*

@Composable
fun ShimmerEffect() {

}

@Composable
fun AnimatedShimmerItem() {
    val transaction = rememberInfiniteTransition()
    val alphaAnimation by transaction.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    ShimmerItem(alpha = alphaAnimation)
}

@Composable
fun ShimmerItem(alpha: Float) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(HERO_BOX_HEIGHT_SIZE),
        color = if (isSystemInDarkTheme()) Color.Black else LighterShimmer,
        shape = RoundedCornerShape(size = LARGE_PADDING)
    ) {
        Column(
            modifier = Modifier.padding(all = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .alpha(alpha)
                    .height(HERO_NAME_SHIMMER_SIZE),
                color = if (isSystemInDarkTheme()) DarkShimmer else LightShimmer,
                shape = RoundedCornerShape(size = MEDIUM_PADDING)
            ) {}
            Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
            repeat(3) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(alpha)
                        .height(ABOUT_PLACEHOLDER_HEIGHT),
                    color = if (isSystemInDarkTheme()) DarkShimmer else LightShimmer,
                    shape = RoundedCornerShape(size = SMALL_PADDING)
                ) {}
                Spacer(modifier = Modifier.padding(all = BIG_SMALL_PADDING))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(5) {
                    Surface(
                        modifier = Modifier
                            .alpha(alpha)
                            .padding(end = BIG_SMALL_PADDING)
                            .size(SMALL_PADDING),
                        color = if (isSystemInDarkTheme()) DarkShimmer else LightShimmer,
                        shape = RoundedCornerShape(size = SMALL_PADDING)
                    ) {}
                }
            }
            Spacer(modifier = Modifier.padding(all = BIG_SMALL_PADDING))
        }
    }
}

@Preview
@Composable
fun PreviewShimmerItem() {
    AnimatedShimmerItem()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewDarkThemeShimmerItem() {
    AnimatedShimmerItem()
}