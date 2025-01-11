package io.kdbrian.urbandict.features.words

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbDown
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.ui.composables.WordCard
import io.kdbrian.urbandict.ui.theme.UrbanDictTheme
import io.kdbrian.urbandict.ui.theme.gambarino
import io.kdbrian.urbandict.ui.theme.indianRed
import io.kdbrian.urbandict.ui.theme.peachYellow
import io.kdbrian.urbandict.util.Shapes

@Composable
fun FullScreenWord(
    modifier: Modifier = Modifier,
    word: UrbanWord,
    similarWordsInCriteria: List<UrbanWord>,
    onClose: () -> Unit = {}
){

    val verticalScrollState = rememberScrollState()
    var isLiked by remember { mutableStateOf(false) }
    val thumbsUp = if (isLiked) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUp
    val thumbsDown = if (isLiked) Icons.Outlined.ThumbDown else Icons.Filled.ThumbDown

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = peachYellow)
            .padding(12.dp)
            .verticalScroll(verticalScrollState),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        IconButton(
            onClick = onClose,
            modifier = Modifier
        ) {
            Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
        }


        Text(
            text = word.word,
            modifier = Modifier.padding(horizontal = 12.dp),
            style = TextStyle(
                fontFamily = gambarino,
                fontSize = 50.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        Text(
            text = word.definition,
            modifier = Modifier.padding(horizontal = 12.dp),
            style = TextStyle(
                fontFamily = gambarino,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "People also looked up....",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = gambarino,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )

        LazyRow {
            items(items = similarWordsInCriteria) {
                WordCard(
                    word = it,
                    modifier = Modifier
                        .padding(4.dp)
                        .border(
                            width = 1.dp,
                            color = indianRed,
                            shape = Shapes.rounded6Dp
                        ),
                    divider = { }
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {

            IconButton(onClick = {isLiked = true}) {
                Icon(
                    imageVector = thumbsUp,
                    contentDescription = null,
                )
            }

            Spacer(Modifier.width(8.dp))

            IconButton(onClick = { isLiked = false}) {
                Icon(
                    imageVector = thumbsDown,
                    contentDescription = null,
                )
            }

            Spacer(Modifier.width(8.dp))

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.Share,
                    contentDescription = null,
                )
            }
        }

        Spacer(Modifier.weight(1f))


    }


}


@Preview
@Composable
fun FullScreenWordPreview(modifier: Modifier = Modifier) {
    UrbanDictTheme {
        FullScreenWord(
            word = UrbanWord(),
            similarWordsInCriteria = listOf(UrbanWord(), UrbanWord())
        )
    }
}
