package io.kdbrian.urbandict.features.words

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kdbrian.urbandict.LocalBackgroundColor
import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.ui.composables.WordCard
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme
import kotlin.random.Random

@Composable
fun GridWordFeed(
    modifier: Modifier = Modifier,
    words: List<UrbanWord>
) {

    WordFeed(
        modifier = modifier.background(color = LocalBackgroundColor.current),
        banner = {
            WelcomeBanner(modifier = it, backgroundColor = LocalBackgroundColor.current)
        },
        body = {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(words) { word ->
                    WordCard(
                        word = word,
                        divider = {

                            val dividerColor = Color(
                                Random.nextFloat(),
                                Random.nextFloat(),
                                Random.nextFloat(),
                            )

                            DividerWithTextInMiddle(
                                dividerColor = dividerColor,
                                text = "Trending"
                            )
                        },
                        modifier = Modifier
                            .padding(6.dp)
                            .background(
                                shape = RoundedCornerShape(12.dp),
                                color = Color(
                                    Random.nextFloat(),
                                    Random.nextFloat(),
                                    Random.nextFloat(),
                                )
                            )
                            .padding(4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(70.dp))
        },
        footer = { footerModifier ->
            var isExpanded by remember { mutableStateOf(false) }
            DismissableFooterOptions(
                isExpanded = isExpanded,
                onExpandAction = { isExpanded = !isExpanded },
                modifier = footerModifier
                    .padding(8.dp)
            )
        }
    )

}

@Composable
private fun DividerWithTextInMiddle(
    dividerColor: Color,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(
            color = dividerColor,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp),
            style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Light)
        )
        HorizontalDivider(
            color = dividerColor,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun GridWordFeedPrev(modifier: Modifier = Modifier) {
    UrbanDictTheme {
    }
}
