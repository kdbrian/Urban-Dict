package io.kdbrian.urbandict.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme

@Composable
fun SwippableWordCards(
    modifier: Modifier = Modifier,
    words: List<UrbanWord> = emptyList(),
    onSelect: (UrbanWord) -> Unit = {}
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Space between cards
    ) {
        itemsIndexed(words) { index, word ->
            WordCard(
                modifier = Modifier
                    .offset(y = (index * 8).dp) // Create a stacked effect
                    .zIndex(-index.toFloat()), // Optional: Order stack appearance
                word = word,
                onSelect = onSelect
            )
        }
    }

}

@Preview
@Composable
fun SwippableWordCardsPrev(modifier: Modifier = Modifier) {
    UrbanDictTheme {
        SwippableWordCards(
            words = listOf(UrbanWord(),UrbanWord(),UrbanWord(),)
        )
    }
}