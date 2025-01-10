package io.kdbrian.urbandict.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UrbanWord(
    val wordId: String,
    val word: String,
    val author: String,
    val definition: String,
    val acronyms: List<String>,
    val usedBy: List<String>,
    val banned: Boolean
)
