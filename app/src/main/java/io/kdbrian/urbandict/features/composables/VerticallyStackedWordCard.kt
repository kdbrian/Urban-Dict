package io.kdbrian.urbandict.features.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme
import io.kdbrian.urbandict.ui.theme.gambarino
import io.kdbrian.urbandict.util.Shapes
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun VerticallyStackedWordCard(
    modifier: Modifier = Modifier,
    word: UrbanWord = UrbanWord(),
    onSelect: (UrbanWord) -> Unit = {}
) {

    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    var offsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .padding(6.dp)
            .fillMaxWidth()
            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
        ,
        shape = Shapes.rounded6Dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "${word.word} ${offsetX.value}",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = gambarino
                )
            )

            Spacer(Modifier.height(12.dp))

            Text(text = word.definition)
        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun VerticallyStackedWordCardPreview() {
    UrbanDictTheme {
        VerticallyStackedWordCard()
    }
}