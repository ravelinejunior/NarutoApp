package com.raveline.borutoapp.data.model

import androidx.annotation.RawRes
import com.raveline.borutoapp.R

sealed class OnBoardingPage(
    @RawRes
    val sourceJsonLottie: Int,
    val title: String,
    val description: String
) {

    object First : OnBoardingPage(
        sourceJsonLottie = R.raw.greetings,
        title = "Greetings",
        description = "Are you a Naruto fan? Because if you are then we have a great news for you!"
    )

    object Second : OnBoardingPage(
        sourceJsonLottie = R.raw.explore,
        title = "Explore",
        description = "Find your favorite heroes and learn some of the things that you didn't know about."
    )

    object Third : OnBoardingPage(
        sourceJsonLottie = R.raw.power,
        title = "Power",
        description = "Check out your hero's power and  see how much are they strong comparing to others."
    )
}
