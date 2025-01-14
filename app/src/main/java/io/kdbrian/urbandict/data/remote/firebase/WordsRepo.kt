package io.kdbrian.urbandict.data.remote.firebase

import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.domain.firebase.WordRepository

class WordsRepo : WordRepository {
    override suspend fun getWords(): Result<List<UrbanWord>> {
        TODO("Not yet implemented")
    }

    override suspend fun addWords(): Result<UrbanWord> {
        TODO("Not yet implemented")
    }

    override suspend fun getSimilarWords(word: UrbanWord): Result<List<UrbanWord>> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleLike(word: UrbanWord): Result<UrbanWord> {
        TODO("Not yet implemented")
    }


}