package com.raveline.borutoapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val White = Color(0xFFFFFFFF)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Red80 = Color(0xFF880E4F)
val Red70 = Color(0xFFC2185B)
val Red = Color(0xFFD50000)
val Blue80 = Color(0xFF283593)
val Blue60 = Color(0xFF3949AB)
val Blue40 = Color(0xFF1976D2)
val Orange80 = Color(0xFFC62828)
val Orange40 = Color(0xFFF44336)

val Black = Color(0xFF000000)
val Black80 = Color(0xCC000000)
val Teal80 = Color(0xFF00838F)
val Teal70 = Color(0xFF0097A7)
val Teal40 = Color(0xFF26C6DA)
val Green70 = Color(0xFF00BFA5)
val Green60 = Color(0xFF00897B)
val Green40 = Color(0xFF1DE9B6)

val LightGray = Color(0xFF9B9B9B)
val DarkGray = Color(0xFF3A3A3A)
val StarColor = Color(0xFFFAD371)

val Colors.welcomeScreenBackgroundColor
    @Composable
    get() = if (isLight) Color.White else Color.Black

val Colors.titleWelcomeColor
    @Composable
    get() = if (isLight) DarkGray else LightGray

val Colors.descriptionWelcomeColor
    @Composable
    get() = if (isLight) DarkGray.copy(alpha = 0.5f) else LightGray.copy(alpha = 0.5f)

val Colors.activeColorIndicator
    @Composable
    get() = if (isLight) Blue60 else Blue40

val Colors.inactiveColorIndicator
    @Composable
    get() = if (isLight) LightGray else DarkGray

val Colors.topBarBackgroundColorLightDarkMode
    @Composable
    get() = if (isLight) White else LightGray

val Colors.topBarContentColorLightDarkMode
    @Composable
    get() = if (isLight) Black else Color.Blue










