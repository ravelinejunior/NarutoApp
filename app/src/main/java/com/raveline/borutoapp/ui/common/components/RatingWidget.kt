package com.raveline.borutoapp.ui.common.components

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import com.raveline.borutoapp.R
import com.raveline.borutoapp.ui.theme.LightGray
import com.raveline.borutoapp.ui.theme.StarColor

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double,
    scaleFactor: Float = 2f
) {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }

    FilledStar(starPath = starPath, starPathBounds = starPathBounds,scaleFactor = 3f)
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
fun EmptyFilledStar(
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
    EmptyFilledStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 3f)
}

















