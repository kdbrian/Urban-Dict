package io.kdbrian.urbandict.features.words

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.kdbrian.urbandict.LocalBackgroundColor
import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.ui.theme.gambarino

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
        ) {

            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "My Saves",
                fontFamily = gambarino,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = onClearSaved) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = null
                )
            }

        }

        Spacer(modifier = Modifier.weight(1f))
        Text(text = saves.joinToString(", ") { it.word })
        Spacer(modifier = Modifier.weight(1f))

    }


}