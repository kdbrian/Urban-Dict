package io.kdbrian.urbandict.domain.firebase

import io.kdbrian.urbandict.data.model.UrbanWord

interface WordRepository {
    suspend fun getWords(): Result<List<UrbanWord>>
    suspend fun addWords(): Result<UrbanWord>
    suspend fun getSimilarWords(word: UrbanWord): Result<List<UrbanWord>>
    suspend fun toggleLike(word: UrbanWord): Result<UrbanWord>
}