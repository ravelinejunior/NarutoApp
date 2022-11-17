package com.raveline.borutoapp.ui.screens.searchScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.raveline.borutoapp.R
import com.raveline.borutoapp.ui.theme.TOP_APP_BAR_HEIGHT
import com.raveline.borutoapp.ui.theme.topBarBackgroundColorLightDarkMode
import com.raveline.borutoapp.ui.theme.topBarContentColorLightDarkMode

@Composable
fun SearchTopBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit,
) {
    SearchWidget(text = text, onTextChange = onTextChange, onSearchClicked = onSearchClicked,onCloseClicked=onCloseClicked)
}

@Composable
fun SearchWidget(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topBarContentColorLightDarkMode
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    text = "Search Your Hero...",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topBarBackgroundColorLightDarkMode
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = stringResource(
                            R.string.search_icon_content
                        ),
                        tint = MaterialTheme.colors.topBarBackgroundColorLightDarkMode
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Close, contentDescription = stringResource(
                            R.string.search_icon_content
                        ),
                        tint = MaterialTheme.colors.topBarBackgroundColorLightDarkMode
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.topBarContentColorLightDarkMode
            )
        )
    }
}

@Preview
@Composable
fun SearchTopBarPreview() {
    SearchTopBar(text = "", onTextChange = {}, onSearchClicked = {}, onCloseClicked = {})
}



















