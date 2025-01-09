package io.kdbrian.urbandict.features.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import io.kdbrian.urbandict.BuildConfig
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme
import io.kdbrian.urbandict.ui.theme.gambarino
import io.kdbrian.urbandict.ui.theme.indianRed
import io.kdbrian.urbandict.ui.theme.telma

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5E9E2)),
    ) {
        val (dict, onary, footer, prompt) = createRefs()

        Text(
            text = "Join the crowd",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = telma,
                fontWeight = FontWeight.SemiBold,
                color = indianRed,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier
                .constrainAs(prompt) {
                    top.linkTo(parent.top, margin = 8.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                }
        )

        Text(
            text = "Dictio",
            style = TextStyle(
                fontSize = 130.sp,
                fontFamily = telma,
                fontWeight = FontWeight.Bold,
                color = indianRed
            ),
            modifier = Modifier
                .offset(x = 29.dp)
                .constrainAs(dict) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )


        Text(
            text = "onary",
            style = TextStyle(
                fontSize = 130.sp,
                fontFamily = telma,
                fontWeight = FontWeight.Bold,
                color = indianRed
            ),
            modifier = Modifier
                .offset(x = (-23).dp)
                .constrainAs(onary) {
                    top.linkTo(dict.bottom)
                    start.linkTo(parent.start)
                }
        )


        Column(
            modifier = Modifier
                .padding(12.dp)
                .constrainAs(footer) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 12.dp),
                color = indianRed
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Urban Dict ${BuildConfig.VERSION_NAME}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = gambarino,
                color = indianRed
            )

        }

    }


}


@Preview
@Composable
fun OnBoardingScreenPrev(modifier: Modifier = Modifier) {
    UrbanDictTheme {
        OnBoardingScreen()
    }
}



