package com.raveline.borutoapp.ui.screens.homeScreen.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.raveline.borutoapp.R
import com.raveline.borutoapp.ui.theme.topBarBackgroundColorLightDarkMode
import com.raveline.borutoapp.ui.theme.topBarContentColorLightDarkMode

@Composable
fun TopAppBarHome(
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Explore",
                color = MaterialTheme.colors.topBarContentColorLightDarkMode
            )
        },
        backgroundColor = MaterialTheme.colors.topBarBackgroundColorLightDarkMode,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_icon)
                )
            }
        }
    )
}