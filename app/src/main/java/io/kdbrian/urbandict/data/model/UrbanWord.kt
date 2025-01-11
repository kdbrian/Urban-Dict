package io.kdbrian.urbandict.data.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _savedWords = MutableLiveData<MutableList<UrbanWord>>(
        mutableListOf()
    )
    val savedWords: LiveData<MutableList<UrbanWord>> = _savedWords

    private val _likedWords = MutableLiveData<MutableList<UrbanWord>>()
    val likedWords: LiveData<MutableList<UrbanWord>> = _likedWords

    private val words = listOf(
        UrbanWord(word = "Hello"),
        UrbanWord(word = "World"),
        UrbanWord(word = "Supa"),
        UrbanWord(word = "Mario"),
        UrbanWord(word = "Luigi"),
    )

    fun getWords() = words

    fun onSaveWord(word: UrbanWord) {
        if (savedWords.value?.contains(word) == true)
            savedWords.value?.remove(word)
        else
            savedWords.value?.add(word)
    }

    fun clearSavedWords() {
        savedWords.value?.clear()
    }

    fun toggleLike(word: UrbanWord) {
        if (likedWords.value?.contains(word) == true)
            likedWords.value?.remove(word)
        else
            likedWords.value?.add(word)
    }

}
