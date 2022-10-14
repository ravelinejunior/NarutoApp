package com.raveline.borutoapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

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

val Colors.welcomeScreenBackgroundColor
@Composable
get() = if (isLight) Color.White else Color.Black
