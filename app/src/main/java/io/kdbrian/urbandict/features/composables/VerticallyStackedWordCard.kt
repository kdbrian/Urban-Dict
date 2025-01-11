package io.kdbrian.urbandict.features.composables

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme

@Composable
fun VerticallyStackedWordCard(modifier: Modifier = Modifier) {

    Card {

    }

}

@Preview
@Composable
fun VerticallyStackedWordCardPreview() {
    UrbanDictTheme {
        VerticallyStackedWordCard()
    }
}