package io.kdbrian.urbandict

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import io.kdbrian.urbandict.data.remote.firebase.WordsRepo
import io.kdbrian.urbandict.presentation.viewmodel.WordViewModel
import io.kdbrian.urbandict.ui.nav.App
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme
import io.kdbrian.urbandict.ui.theme.indianRed

val LocalBackgroundColor = staticCompositionLocalOf { indianRed }

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(
                LocalBackgroundColor provides indianRed
            ) {
                UrbanDictTheme {
                    LocalViewModelStoreOwner.current?.let { owner ->

                        val wordsViewModel = viewModel<WordViewModel>(
                            viewModelStoreOwner = owner,
                            factory = WordViewModel.Factory(wordRepository = WordsRepo()),
                            key = "WordViewModel"
                        )

                        App()



                    }
                }
            }
        }
    }
}

