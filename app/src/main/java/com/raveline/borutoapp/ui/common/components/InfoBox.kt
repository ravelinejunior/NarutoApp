package com.raveline.borutoapp.ui.common.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.raveline.borutoapp.R
import com.raveline.borutoapp.ui.theme.ICON_SIZE_BOX
import com.raveline.borutoapp.ui.theme.SMALL_PADDING
import com.raveline.borutoapp.ui.theme.titleWelcomeColor

@Composable
fun InfoBox(
    icon: Painter,
    iconColor: Color,
    bigText: String,
    smallText: String,
    textColor: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .padding(end = SMALL_PADDING)
                .size(ICON_SIZE_BOX),
            painter = icon,
            contentDescription = stringResource(id = R.string.info_icon),
            tint = iconColor
        )
        Column {
            Text(
                text = bigText,
                color = textColor,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = smallText,
                color = textColor,
                fontSize = MaterialTheme.typography.overline.fontSize,
                fontWeight = FontWeight.Bold
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoBoxPreview() {
    InfoBox(
        icon = painterResource(id = R.drawable.ic_baseline_flash_on_24),
        iconColor = MaterialTheme.colors.primary,
        bigText = "92",
        smallText = "Power",
        textColor = MaterialTheme.colors.titleWelcomeColor
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun InfoBoxDarkModePreview() {
    InfoBox(
        icon = painterResource(id = R.drawable.ic_baseline_flash_on_24),
        iconColor = MaterialTheme.colors.primary,
        bigText = "92",
        smallText = "Power",
        textColor = MaterialTheme.colors.titleWelcomeColor
    )
}