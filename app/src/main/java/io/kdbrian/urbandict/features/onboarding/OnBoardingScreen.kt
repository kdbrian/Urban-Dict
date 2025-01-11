package io.kdbrian.urbandict.features.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import io.kdbrian.urbandict.features.AuthScreen
import io.kdbrian.urbandict.features.auth.AuthEvent
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme
import io.kdbrian.urbandict.ui.theme.gambarino
import io.kdbrian.urbandict.ui.theme.indianRed
import io.kdbrian.urbandict.ui.theme.telma

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    onAuthEvent: (AuthEvent) -> Unit = {}
) {

    var showSignInPrompt by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFF5E9E2))//, shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
        ) {
            val (dict, onary, footer, prompt, getStartedBtn) = createRefs()

            AnimatedVisibility(!showSignInPrompt) {
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
                            top.linkTo(parent.top)
                            start.linkTo(parent.start, margin = 8.dp)
                        }
                        .clickable { showSignInPrompt = !showSignInPrompt }
                )
            }

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

            Button(
                onClick = { onAuthEvent(AuthEvent.GetStarted) },
                modifier = Modifier.constrainAs(getStartedBtn) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(onary.bottom)
                    bottom.linkTo(footer.top)
                }
            ) {
                Text(
                    text = "Explore", style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = telma
                    )
                )
            }


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
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = gambarino,
                    color = indianRed
                )

            }
        }


        AnimatedVisibility(showSignInPrompt) {
            AuthScreen(
                modifier = Modifier
                    .padding(top = 70.dp, start = 8.dp, end = 8.dp, bottom = 30.dp)
                    .align(Alignment.BottomCenter)
                    .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(12.dp)),
                onClose = { showSignInPrompt = false },
                onAuth = { email, password -> onAuthEvent(AuthEvent.SignIn(email, password)) },
                onPasswordLess = { onAuthEvent(AuthEvent.PasswordLess) }
            )
        }
    }

}


@Preview
@Composable
fun OnBoardingScreenPrev(modifier: Modifier = Modifier) {
    UrbanDictTheme {
        OnBoardingScreen(
            Modifier.systemBarsPadding()
        )
    }
}



