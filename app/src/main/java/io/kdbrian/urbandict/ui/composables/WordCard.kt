package io.kdbrian.urbandict.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme

@Composable
fun WordCard(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .padding(4.dp)
            .width(170.dp)
            .height(200.dp)
            .background(shape = RoundedCornerShape(12.dp), color = Color(0xFFF7F7FF))
            .padding(6.dp)
    ) {

        Text(text = LoremIpsum(5).values.joinToString())

        Spacer(Modifier.height(12.dp))

        Text(text = LoremIpsum(5).values.joinToString())


    }

}

@Preview
@Composable
fun WordCardPrev(modifier: Modifier = Modifier) {
    UrbanDictTheme {
        WordCard()
    }
}