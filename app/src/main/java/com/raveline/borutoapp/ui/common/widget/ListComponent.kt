package com.raveline.borutoapp.ui.common.widget

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items

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
    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        items(
            items = heroes,
            key = { hero ->
                hero.id
            }
        ) { hero ->
            hero?.let {
                HeroItem(navController = navController, heroModel = it)
            }
        }
    }
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
        Surface(
            shape = RoundedCornerShape(
                bottomStart = MEDIUM_LARGE_PADDING,
                bottomEnd = MEDIUM_LARGE_PADDING,
            )
        ) {
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
                bottomStart = MEDIUM_LARGE_PADDING,
                bottomEnd = MEDIUM_LARGE_PADDING,
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
                        color = White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                //Hero Description
                Text(
                    text = heroModel.heroAbout,
                    style = TextStyle(
                        fontSize = HERO_DESCRIPTION_SIZE,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
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
                        text = heroModel.heroRating.toString(),
                        style = TextStyle(
                            fontSize = HERO_TITLE_SIZE,
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

@Preview
@Composable
fun HeroItemPreview() {
    HeroItem(
        navController = rememberNavController(), heroModel = HeroModel(
            id = 1,
            heroImage = "",
            heroName = "Namikaze Minato",
            heroAbout = "Lorem ipsum dolor sit amet, haksjhd , ashndlh asjdjaslkdjaslkdj jadlja s lkdjaskldj alksdjaklsj daslkdjalsjd jsalkdjasj dlkasjdçksdjiso jiasj iajkldsjlas",
            heroAbilities = listOf(),
            heroFamily = listOf(),
            heroPower = 100,
            heroRating = 4.5,
            month = "",
            day = "",
            heroNatureTypes = listOf()
        )
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HeroItemDarkPreview() {
    HeroItem(
        navController = rememberNavController(), heroModel = HeroModel(
            id = 1,
            heroImage = "",
            heroName = "Namikaze Minato",
            heroAbout = "Lorem ipsum dolor sit amet, haksjhd , ashndlh asjdjaslkdjaslkdj jadlja s lkdjaskldj alksdjaklsj daslkdjalsjd jsalkdjasj dlkasjdçksdjiso jiasj iajkldsjlas",
            heroAbilities = listOf(),
            heroFamily = listOf(),
            heroPower = 100,
            heroRating = 4.5,
            month = "",
            day = "",
            heroNatureTypes = listOf()
        )
    )
}













