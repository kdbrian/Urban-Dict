package io.kdbrian.urbandict.features.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme
import io.kdbrian.urbandict.ui.theme.gambarino
import io.kdbrian.urbandict.ui.theme.telma

@Composable
fun WordCard(
    modifier: Modifier = Modifier, word: UrbanWord,
    divider: (@Composable () -> Unit)? = null,
    onSelect: (UrbanWord) -> Unit = {}
) {

    Column(
        modifier = modifier
            .width(200.dp)
            .clickable { onSelect(word) }
            .padding(6.dp)

    ) {

        Text(
            text = word.word, style = TextStyle(
                fontSize = 24.sp, fontWeight = FontWeight.Bold, fontFamily = gambarino
            )
        )

        divider?.apply { invoke() }

        Text(
            text = word.definition,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Light, fontFamily = telma),
            modifier = Modifier.padding(8.dp)
        )

    }

}

@Preview
@Composable
fun WordCardPrev(modifier: Modifier = Modifier) {
    UrbanDictTheme {
        WordCard(word = UrbanWord())
    }
}