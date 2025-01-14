package io.kdbrian.urbandict.domain.firebase

import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.presentation.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow

interface WordRepository {

    val liveWords: MutableSharedFlow<Resource<List<UrbanWord>>>
        get() = MutableSharedFlow()

    suspend fun getWords(): Result<List<UrbanWord>>
    suspend fun addWord(word: UrbanWord): Result<UrbanWord>
    suspend fun getSimilarWords(word: UrbanWord): Result<List<UrbanWord>>
    suspend fun toggleLike(word: UrbanWord): Result<UrbanWord>
    suspend fun observeWords()
}