package io.kdbrian.urbandict.features.words

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WordFeed(
    modifier: Modifier = Modifier,
    banner: @Composable (Modifier) -> Unit = {},
    body: @Composable BoxScope.() -> Unit = {},
    footer: @Composable (Modifier) -> Unit = {}
) {

    Scaffold(
        topBar = { banner(Modifier) },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                body()
                footer(Modifier.align(Alignment.BottomCenter))
            }
        }
    }

}