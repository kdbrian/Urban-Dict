package io.kdbrian.urbandict.data.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UrbanWord(
    val wordId: String = UUID.randomUUID().toString(),
    val word: String = LoremIpsum(3).values.joinToString(),
    val author: String = LoremIpsum(2).values.joinToString(),
    val definition: String = LoremIpsum(10).values.joinToString(),
    val acronyms: List<String> = emptyList(),
    val usedBy: List<String> = emptyList(),
    val banned: Boolean = false
)
