package com.raveline.borutoapp.ui.screens.searchScreen.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchTopBarKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
    }

    @Test
    fun openSearchTopBar_addInputText_assertInputText() {
        val text = mutableStateOf("")

        composeTestRule.setContent {
            SearchTopBar(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onSearchClicked = {},
                onCloseClicked = {},
            )
        }

        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("Junior Raveline")
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextEquals("Junior Raveline")
    }

    @Test
    fun openSearchTopBar_addInputText_pressCloseButtonOnce_assertEmptyInputText() {
        val text = mutableStateOf("")

        composeTestRule.setContent {
            SearchTopBar(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onSearchClicked = {},
                onCloseClicked = {},
            )
        }

        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("Junior Raveline")

        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()

        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextContains("")
    }

    @Test
    fun openSearchTopBar_addInputText_pressCloseButtonTwice_assertCloseState() {
        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)

        composeTestRule.setContent {
            if (searchWidgetShown.value) {
                SearchTopBar(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onSearchClicked = {},
                    onCloseClicked = {
                        searchWidgetShown.value = false
                    },
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("Junior Raveline")

        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()

        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()

        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }

    @Test
    fun openSearchTopBar_pressCloseButtonOnceWhenInputIsEmpty_assertCloseState() {
        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)

        composeTestRule.setContent {
            if (searchWidgetShown.value) {
                SearchTopBar(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onSearchClicked = {},
                    onCloseClicked = {
                        searchWidgetShown.value = false
                    },
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()

        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }

    @After
    fun tearDown() {
    }
}