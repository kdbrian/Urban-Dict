package io.kdbrian.urbandict.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UrbanWord(
    val wordId: String = "",
    val word: String = "",
    val author: String = "",
    val definition: String = "",
    val acronyms: List<String> = emptyList(),
    val usedBy: List<String> = emptyList(),
    val banned: Boolean = false,
) {
    constructor(word: String, definition: String, author: String) : this(wordId = "", word = word, definition = definition, author = author)

}
