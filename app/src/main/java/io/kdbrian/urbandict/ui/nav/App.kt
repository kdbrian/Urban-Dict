package io.kdbrian.urbandict.ui.nav

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.features.auth.AuthEvent
import io.kdbrian.urbandict.features.auth.MyProfile
import io.kdbrian.urbandict.features.onboarding.OnBoardingScreen
import io.kdbrian.urbandict.features.words.FullScreenWordPreview
import io.kdbrian.urbandict.features.words.GridWordFeed
import io.kdbrian.urbandict.features.words.MySaves
import io.kdbrian.urbandict.presentation.util.Resource
import io.kdbrian.urbandict.presentation.viewmodel.WordViewModel
import java.util.UUID

@Composable
fun App(
    modifier: Modifier = Modifier,
    wordsViewModel : WordViewModel
) {

    val allWords by wordsViewModel.words.collectAsState(initial = Resource.Loading())
    val liveWords by wordsViewModel.livewords.collectAsState(initial = Resource.Loading())

    val navController = rememberNavController()
    val viewWord: (String) -> Unit = { wordId ->
        navController.navigate(Route.ViewWord(wordId))
    }
    val openProfile: () -> Unit = {
        navController.navigate(Route.Account(UUID.randomUUID().toString()))
    }
    val openSaves: () -> Unit = {
        navController.navigate(Route.Saves(UUID.randomUUID().toString()))
    }
    val onSaveWord: (UrbanWord) -> Unit = {
    }

    val onWordLikeChange: (UrbanWord) -> Unit = {
    }


    NavHost(
        modifier = modifier.systemBarsPadding(),
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

        composable<Route.Home> {
            GridWordFeed(
                words = liveWords,
                onOpenWord = { viewWord(it.wordId) },
                onOpenProfile = openProfile,
                onOpenSaves = openSaves
            )
        }

        composable<Route.ViewWord> { backStackEntry ->
            val wordId = backStackEntry.toRoute<Route.ViewWord>()
                FullScreenWordPreview(
                    word = UrbanWord(),
                    similarWordsInCriteria = emptyList(),
                    onClose = { navController.popBackStack() },
                    isSaved = false,
                    onSave = onSaveWord,
                    isLiked = false,
                    onLiked = onWordLikeChange
                )
        }

        composable<Route.Account> { backStackEntry ->
            val userId = backStackEntry.toRoute<Route.Account>()
            MyProfile()
        }

        composable<Route.Saves> { _->
            MySaves(
                saves = emptyList(),
                onOpenWord = { viewWord(it.wordId) },
                onClose = { navController.popBackStack() },
                onToggleSaved = onSaveWord,
                onClearSaved = { }
            )
        }

    }

}