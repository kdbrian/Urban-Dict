package io.kdbrian.urbandict.features.words

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.kdbrian.urbandict.LocalBackgroundColor
import io.kdbrian.urbandict.data.model.UrbanWord

@Composable
fun MySaves(
    modifier: Modifier = Modifier,
    saves: List<UrbanWord>,
    onClose: () -> Unit,
    onOpenWord: (UrbanWord) -> Unit = {},
    onToggleSaved: (UrbanWord) -> Unit = {},
    onClearSaved: () -> Unit = {},
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = LocalBackgroundColor.current)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null
                )
            }

            IconButton(onClick = onClearSaved) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = null
                )
            }

        }




    }


}