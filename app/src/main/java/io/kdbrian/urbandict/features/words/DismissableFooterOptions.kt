package io.kdbrian.urbandict.features.words

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme
import io.kdbrian.urbandict.ui.theme.coral
import io.kdbrian.urbandict.ui.theme.gambarino
import io.kdbrian.urbandict.ui.theme.peachYellow
import io.kdbrian.urbandict.ui.theme.telma

@Composable
fun DismissableFooterOptions(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    onExpandAction: () -> Unit = {},
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        AnimatedVisibility(isExpanded, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(color = Color.LightGray, shape = RoundedCornerShape(12.dp))
                    .padding(6.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FooterAction()
                FooterAction()
                FooterAction()
            }
        }

        Spacer(Modifier.height(24.dp))

        if (isExpanded) {
            IconButton(
                onClick = onExpandAction,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .background(shape = CircleShape, color = Color.LightGray)
            ) {
                Icon(imageVector = Icons.Rounded.Clear, contentDescription = null)
            }
        } else
            Surface(
                tonalElevation = 16.dp,
                onClick = onExpandAction,
                color = Color.Transparent
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = peachYellow, shape = RoundedCornerShape(12.dp))
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Options",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = telma
                        )
                    )
                    val icon =
                        if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown
                    Icon(imageVector = icon, contentDescription = null)
                }
            }

    }

}

@Composable
private fun FooterAction(onClick: () -> Unit = {}) {
    Text(
        text = LoremIpsum(5).values.joinToString(),
        style = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            fontFamily = gambarino
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    )

    HorizontalDivider(
        modifier = Modifier
            .width(200.dp)
            .padding(horizontal = 16.dp, vertical = 4.dp),
        color = coral
    )
}

@Preview(showSystemUi = true)
@Composable
fun DismissableFooterOptionsPrev(modifier: Modifier = Modifier) {
    UrbanDictTheme {
        DismissableFooterOptions(isExpanded = true)
    }
}



