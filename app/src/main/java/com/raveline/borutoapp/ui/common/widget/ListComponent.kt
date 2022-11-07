package com.raveline.borutoapp.ui.common.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.raveline.borutoapp.R
import com.raveline.borutoapp.data.model.HeroModel
import com.raveline.borutoapp.navigation.Screen
import com.raveline.borutoapp.ui.common.components.RatingWidget
import com.raveline.borutoapp.ui.theme.*
import com.raveline.borutoapp.utils.Constants.BASE_URL

@Composable
fun ListComponent(
    heroes: LazyPagingItems<HeroModel>,
    navController: NavController
) {

}

@Composable
fun HeroItem(
    navController: NavController,
    heroModel: HeroModel
) {

    val painter = rememberAsyncImagePainter(
        model = "$BASE_URL/${heroModel.heroImage}",
        placeholder = painterResource(
            id = R.drawable.energy_savings_leaf_24dp,
        ),
        error = painterResource(
            id = R.drawable.energy_savings_leaf_24dp
        )
    )

    Box(
        modifier = Modifier
            .height(HERO_BOX_HEIGHT_SIZE)
            .clickable {
                navController.navigate(Screen.Details.passHeroId(heroId = heroModel.id))
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(shape = Shapes().large) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(id = R.string.hero_image),
                contentScale = ContentScale.Crop,
            )
        }
        //Description tape to the users info, an overlay function
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MEDIUM_LARGE_PADDING)
            ) {

                //Hero Name
                Text(
                    text = heroModel.heroName,
                    style = TextStyle(
                        color = Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                //Hero Description
                Text(
                    text = heroModel.heroAbout,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )

                //Hero Rating 
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = heroModel.heroRating
                    )
                    Text(
                        text = heroModel.heroAbout,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            color = Color.White.copy(alpha = ContentAlpha.medium)
                        ),
                    )
                }
            }
        }
    }
}













