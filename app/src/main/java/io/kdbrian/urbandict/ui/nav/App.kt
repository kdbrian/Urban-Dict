package io.kdbrian.urbandict.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.kdbrian.urbandict.features.auth.AuthEvent
import io.kdbrian.urbandict.features.onboarding.OnBoardingScreen

@Composable
fun App(
    modifier: Modifier = Modifier,
) {

    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.OnBoarding
    ) {

        composable<Route.OnBoarding> {
            OnBoardingScreen(
                onAuthEvent = { auth ->
                    when (auth) {

                        AuthEvent.GetStarted -> {
                            navController.navigate(Route.Home)
                        }

                        is AuthEvent.SignIn -> {
                            //get fields sign in

                        }

                        AuthEvent.SignOut -> {

                            //clear login info
                        }

                        is AuthEvent.SignUp -> {
                            //get fields sign up

                        }

                        AuthEvent.PasswordLess -> {
                            //perform password less -> move to home
                        }
                    }
                }
            )
        }

        composable<Route.Home> { }

        composable<Route.ViewWord> { backStackEntry ->
            val wordId = backStackEntry.toRoute<Route.ViewWord>()
        }

        composable<Route.Account> { backStackEntry ->
            val userId = backStackEntry.toRoute<Route.Account>()
        }

    }

}