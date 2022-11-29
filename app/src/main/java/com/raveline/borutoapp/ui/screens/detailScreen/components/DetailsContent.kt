package com.raveline.borutoapp.ui.screens.detailScreen.components

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.raveline.borutoapp.R
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.ui.common.components.InfoBox
import com.raveline.borutoapp.ui.common.components.OrderedList
import com.raveline.borutoapp.ui.theme.*
import com.raveline.borutoapp.utils.Constants.BASE_URL
import com.raveline.borutoapp.utils.Constants.MINIMUM_BACKGROUND_IMAGE_HEIGHT

@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavController,
    selectedHero: HeroModel?
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue = if (currentSheetFraction == 1f) EXTRA_LARGE_PADDING else 0.dp
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        sheetContent = {
            selectedHero?.let {
                BottomSheetContent(selectedHero = it)
            }
        },
        content = {
            selectedHero?.heroImage?.let { heroImage ->
                BackgroundContent(
                    heroImage = heroImage,
                    imageFraction = currentSheetFraction,
                    onClosedClicked = {
                        navController.popBackStack()
                    },
                )
            }
        },
    )
}

@Composable
fun BottomSheetContent(
    selectedHero: HeroModel,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.activeColorIndicator,
    titleColor: Color = MaterialTheme.colors.titleWelcomeColor,
) {

    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(MIN_SHEET_HEIGHT / 2),
                painter = painterResource(id = R.drawable.ic_baseline_local_fire_department_24),
                contentDescription = stringResource(
                    id = R.string.app_name
                ),
                tint = contentColor,
            )
            Text(
                modifier = Modifier
                    .weight(8f)
                    .padding(start = BIG_SMALL_PADDING),
                text = selectedHero.heroName,
                color = contentColor,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            InfoBox(
                icon = painterResource(
                    id = R.drawable.ic_baseline_bolt_24
                ),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.heroPower}",
                smallText = stringResource(R.string.power_string),
                textColor = contentColor
            )

            InfoBox(
                icon = painterResource(
                    id = R.drawable.ic_baseline_calendar_month_24
                ),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.month,
                smallText = stringResource(R.string.month),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(
                    id = R.drawable.ic_round_cake_24
                ),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.day,
                smallText = stringResource(R.string.birthday),
                textColor = contentColor
            )

        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about_string),
            color = titleColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold,
        )

        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.heroAbout,
            color = titleColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 7,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            OrderedList(
                title = stringResource(R.string.family_string),
                items = selectedHero.heroFamily,
                textColor = titleColor
            )
            OrderedList(
                title = stringResource(R.string.abilities_string),
                items = selectedHero.heroAbilities,
                textColor = titleColor
            )
            OrderedList(
                title = stringResource(R.string.nature_types_string),
                items = selectedHero.heroNatureTypes,
                textColor = titleColor
            )
        }


    }

}

@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onClosedClicked: () -> Unit
) {

    val imageUrl = "$BASE_URL$heroImage"
    val painter = rememberAsyncImagePainter(
        model = imageUrl, placeholder = painterResource(
            id = R.drawable.ic_baseline_fireplace_24,
        ),
        error = painterResource(
            id = R.drawable.ic_baseline_fireplace_24
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MINIMUM_BACKGROUND_IMAGE_HEIGHT)
                .align(Alignment.TopStart),
            painter = painter, contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            IconButton(
                onClick = { onClosedClicked() },
                modifier = Modifier.padding(all = SMALL_PADDING),
            ) {
                Icon(
                    modifier = Modifier.size(ICON_SIZE_BOX),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close_string),
                    tint = Color.White,
                )
            }
        }
    }

}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val TAG = "TAG_DetailsContent"
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        Log.d(TAG, "Fraction: $fraction")
        Log.d(TAG, "Fraction Target: $targetValue")
        Log.d(TAG, "Fraction Current Value: $currentValue")

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Preview
@Composable
fun BottomSheetContentPreview() {
    BottomSheetContent(
        selectedHero = HeroModel(
            id = 1,
            heroImage = "",
            heroName = "Namikaze Minato",
            heroAbout = "Lorem ipsum dolor sit amet, haksjhd , ashndlh asjdjaslkdjaslkdj jadlja s lkdjaskldj alksdjaklsj daslkdjalsjd jsalkdjasj dlkasjdçksdjiso jiasj iajkldsjlas",
            heroAbilities = listOf(),
            heroFamily = listOf(),
            heroPower = 100,
            heroRating = 4.5,
            month = "March",
            day = "24",
            heroNatureTypes = listOf()
        )
    )
}