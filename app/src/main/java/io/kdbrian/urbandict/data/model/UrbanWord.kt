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
    val thumbsUp: Int = 0,
    val thumbsDown: Int = 0,
    val likedBy: MutableList<String> = mutableListOf(),
    val dislikedBy: MutableList<String> = mutableListOf(),
    val banned: Boolean = false,
) {
    constructor(word: String, definition: String, author: String) : this(wordId = "", word = word, definition = definition, author = author)

}
