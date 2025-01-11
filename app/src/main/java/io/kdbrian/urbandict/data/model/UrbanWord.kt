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

object DemoWordDao {
    private val words = listOf(
        UrbanWord(word = "Hello"),
        UrbanWord(word = "World"),
        UrbanWord(word = "Supa"),
        UrbanWord(word = "Mario"),
        UrbanWord(word = "Luigi"),
    )

    private val savedWords = mutableListOf<UrbanWord>()

    fun getWords() = words
    fun getSavedWords() = savedWords

    fun onSaveWord(word: UrbanWord) {
        if (savedWords.contains(word))
            savedWords.remove(word)
        else
            savedWords.add(word)
    }

    fun clearSavedWords() {
        savedWords.clear()
    }

}
