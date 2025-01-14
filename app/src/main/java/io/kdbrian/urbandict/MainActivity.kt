package io.kdbrian.urbandict

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.kdbrian.urbandict.ui.nav.App
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme
import io.kdbrian.urbandict.ui.theme.indianRed


val LocalBackgroundColor = staticCompositionLocalOf { indianRed }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(
                LocalBackgroundColor provides indianRed
            ) {
                UrbanDictTheme {
                    App()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UrbanDictTheme {
    }
}