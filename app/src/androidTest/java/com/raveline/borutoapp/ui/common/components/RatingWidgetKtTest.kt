package com.raveline.borutoapp.ui.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import com.raveline.borutoapp.ui.theme.SMALL_PADDING
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RatingWidgetKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
    }

    @Test
    fun passZeroPointZeroValue_Assert_FiveEmptyStars() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(all = SMALL_PADDING), rating = 0.0)
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar").assertCountEquals(5)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar").assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar").assertCountEquals(0)
    }

    @Test
    fun passZeroPointFiveValue_Assert_FourEmptyStars() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(all = SMALL_PADDING), rating = 0.5)
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar").assertCountEquals(4)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar").assertCountEquals(1)
        composeTestRule.onAllNodesWithContentDescription("FilledStar").assertCountEquals(0)
    }

    @Test
    fun passZeroPointFiveValue_Assert_FourEmptyStars_and_OneFilledStar() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(all = SMALL_PADDING), rating = 1.0)
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar").assertCountEquals(4)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar").assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar").assertCountEquals(1)
    }

    @Test
    fun passFivePointFiveValue_Assert_ZeroEmptyStars_and_FiveFilledStar() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(all = SMALL_PADDING), rating = 5.0)
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar").assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar").assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar").assertCountEquals(5)
    }

    @Test
    fun passFivePointFiveValue_Assert_ZeroEmptyStars_and_FourFilledStar_and_HalfFilledStar() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(all = SMALL_PADDING), rating = 4.5)
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar").assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar").assertCountEquals(1)
        composeTestRule.onAllNodesWithContentDescription("FilledStar").assertCountEquals(4)
    }

    @After
    fun tearDown() {
    }
}