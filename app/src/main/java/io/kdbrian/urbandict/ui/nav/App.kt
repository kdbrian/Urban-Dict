package io.kdbrian.urbandict.ui.nav

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.kdbrian.urbandict.data.model.DemoWordDao
import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.features.auth.AuthEvent
import io.kdbrian.urbandict.features.onboarding.OnBoardingScreen
import io.kdbrian.urbandict.features.words.FullScreenWord
import io.kdbrian.urbandict.features.words.GridWordFeed
import io.kdbrian.urbandict.features.words.MySaves
import java.util.UUID

@Composable
fun App(
    modifier: Modifier = Modifier,
) {

    val navController = rememberNavController()
    val words = DemoWordDao.getWords()
    val savedWords = DemoWordDao.getSavedWords()
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
        DemoWordDao.onSaveWord(it)
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
                words = words,
                onOpenWord = { viewWord(it.wordId) },
                onOpenProfile = openProfile,
                onOpenSaves = openSaves
            )
        }

        composable<Route.ViewWord> { backStackEntry ->
            val wordId = backStackEntry.toRoute<Route.ViewWord>()
            val word = words.find { it.wordId == wordId.wordId }
            //load words and related data
            println("Word $wordId -> $word")
            if (word != null) {
                FullScreenWord(
                    word = word,
                    similarWordsInCriteria = words,
                    onClose = { navController.popBackStack() },
                    isSaved = savedWords.contains(word),
                    onSave = onSaveWord
                )
            }
        }

        composable<Route.Account> { backStackEntry ->
            val userId = backStackEntry.toRoute<Route.Account>()
            MyProfile(userId)
        }

        composable<Route.Saves> { _->
            MySaves(
                saves = savedWords,
                onOpenWord = { viewWord(it.wordId) },
                onClose = { navController.popBackStack() },
                onToggleSaved = onSaveWord,
                onClearSaved = { DemoWordDao.clearSavedWords() }
            )
        }

    }

}