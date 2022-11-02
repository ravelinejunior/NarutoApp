package com.raveline.borutoapp.ui.common.components

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.raveline.borutoapp.R
import com.raveline.borutoapp.ui.theme.LightGray
import com.raveline.borutoapp.ui.theme.StarColor

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double,
    scaleFactor: Float = 4f,
    spaceBetween: Dp = 8.dp,
) {

    val result = calculateStars(rating = rating)
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(spaceBetween)) {
        result["filledStars"]?.let {
            //This function makes the row repeat the amount what is passed plus times of creation
            repeat(it) {
                FilledStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
        result["halfFilledStars"]?.let {
            //This function makes the row repeat the amount what is passed plus times of creation
            repeat(it) {
                HalfFilledStar(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
        result["emptyStars"]?.let {
            //This function makes the row repeat the amount what is passed plus times of creation
            repeat(it) {
                EmptyStars(
                    starPath = starPath,
                    starPathBounds = starPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }

    }
}

@Composable
fun FilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = this.size

        scale(scale = scaleFactor) {

            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width / 2) - (pathWidth / 1.64f)
            val top = (canvasSize.height / 2) - (pathHeight / 1.67f)

            translate(
                left = left,
                top = top
            ) {
                drawPath(
                    path = starPath,
                    color = StarColor
                )
            }
        }

    }
}

@Composable
fun HalfFilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = this.size

        scale(scale = scaleFactor) {

            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width / 2) - (pathWidth / 1.64f)
            val top = (canvasSize.height / 2) - (pathHeight / 1.67f)

            translate(
                left = left,
                top = top
            ) {
                drawPath(
                    path = starPath,
                    color = LightGray.copy(alpha = 0.6f)
                )
                clipPath(path = starPath) {
                    drawRect(
                        color = StarColor,
                        size = Size(
                            width = starPathBounds.maxDimension / 1.67f,
                            height = starPathBounds.maxDimension * scaleFactor
                        )
                    )
                }
            }
        }

    }
}

@Composable
fun EmptyStars(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
) {

    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = this.size

        scale(scale = scaleFactor) {

            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width / 2) - (pathWidth / 1.64f)
            val top = (canvasSize.height / 2) - (pathHeight / 1.67f)

            translate(
                left = left,
                top = top
            ) {
                drawPath(
                    path = starPath,
                    color = LightGray.copy(alpha = 0.6f)
                )

            }
        }

    }
}

@Composable
fun calculateStars(
    rating: Double
): Map<String, Int> {
    val maxStars by remember { mutableStateOf(5) }
    var filledStars by remember { mutableStateOf(0) }
    var halfFilledStars by remember { mutableStateOf(0) }
    var emptyStars by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = rating) {
        val (firstNumber, lastNumber) = rating.toString()
            .split(".")
            .map { it.toInt() }

        if (firstNumber in 0..5 && lastNumber in 0..9) {
            filledStars = firstNumber
            if (lastNumber in 1..5) {
                halfFilledStars++
            }
            if (lastNumber in 6..9) {
                filledStars++
            }
            if (firstNumber == 5 && lastNumber > 0) {
                emptyStars = 5
                filledStars = 5
                halfFilledStars = 5
            }
        } else {
            Log.e("TAGCalculateStars", "Invalid rating number!")
        }

    }

    emptyStars = maxStars - (filledStars + halfFilledStars)
    return mapOf(
        "filledStars" to filledStars,
        "halfFilledStars" to halfFilledStars,
        "emptyStars" to emptyStars
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_MASK)
@Composable
fun FilledStarPreview() {
    RatingWidget(modifier = Modifier, rating = 2.0)
}

@Preview(showBackground = true)
@Composable
fun HalfFilledStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }
    HalfFilledStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 3f)
}

@Preview(showBackground = true)
@Composable
fun EmptyFilledStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }
    EmptyStars(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 3f)
}

















