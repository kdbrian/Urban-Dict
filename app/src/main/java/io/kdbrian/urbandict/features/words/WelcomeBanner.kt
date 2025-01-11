package io.kdbrian.urbandict.features.words

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme
import io.kdbrian.urbandict.ui.theme.gambarino
import io.kdbrian.urbandict.ui.theme.peachYellow
import io.kdbrian.urbandict.ui.theme.telma

@Composable
fun WelcomeBanner(
    modifier: Modifier = Modifier,
    backgroundColor: androidx.compose.ui.graphics.Color = peachYellow
) {

    val veryLargeTextStyle = TextStyle(
        fontSize = 55.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = telma,
        lineHeight = 70.sp
    )

    Surface(
        modifier = modifier
            .fillMaxWidth(),
        tonalElevation = 12.dp,
        color = backgroundColor,
        shadowElevation = 12.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
        ) {

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                Text(
                    text = "Urban Dictionary",
                    style = veryLargeTextStyle,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = null)
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = LoremIpsum(6).values.joinToString(),
                style = veryLargeTextStyle.copy(
                    fontSize = 30.sp,
                    fontFamily = gambarino,
                    lineHeight = 35.sp
                )
            )

        }

    }
}


@Preview(showSystemUi = true)
@Composable
fun WelcomeBannerPrev(modifier: Modifier = Modifier) {
    UrbanDictTheme {
        WelcomeBanner()
    }
}







