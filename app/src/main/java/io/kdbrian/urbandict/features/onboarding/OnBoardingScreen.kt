package io.kdbrian.urbandict.features.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kdbrian.urbandict.BuildConfig
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme
import io.kdbrian.urbandict.ui.theme.gambarino
import io.kdbrian.urbandict.ui.theme.telma

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier) {

    Contraint(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Dictio",
            style = TextStyle(
                fontSize = 100.sp,
                fontFamily = telma,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.offset(x = 70.dp)
        )


        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalDivider(modifier = Modifier.padding(horizontal = 12.dp))

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Urban Dict ${BuildConfig.VERSION_NAME}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = gambarino
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



