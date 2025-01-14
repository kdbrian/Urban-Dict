package io.kdbrian.urbandict.data.remote.firebase

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.domain.firebase.WordRepository
import kotlinx.coroutines.tasks.await

private const val wordCollection: String = "urban-words-test"

class WordsRepo : WordRepository {

    private val wordsCollection = Firebase.firestore.collection(wordCollection)

    override suspend fun getWords(): Result<List<UrbanWord>> {
        return try {
            val snapshot = wordsCollection.get().await()
            val words = snapshot.toObjects(UrbanWord::class.java)
            Result.success(words)
        } catch (e: Exception) {
            Result.failure(e)

        }
    }

    override suspend fun addWord(word: UrbanWord): Result<UrbanWord> {
        return try {
            val docId = wordsCollection.document().id
            wordsCollection
                .document(docId)
                .set(word.copy(wordId = docId))
                .await()
            Result.success(word.copy(wordId = docId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getSimilarWords(word: UrbanWord): Result<List<UrbanWord>> {
        return try {
            val similarWords =
                wordsCollection.whereArrayContainsAny("acronyms", word.acronyms).get()
                    .await()
            if (!similarWords.isEmpty) {
                Result.success(similarWords.toObjects(UrbanWord::class.java))
            } else {
                Result.failure(Exception("No similar words found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun toggleLike(word: UrbanWord): Result<UrbanWord> {
        return try {

            if (word.likedBy.isNotEmpty()) {
                word.likedBy.remove(word.likedBy.first())
            }
            wordsCollection.document(word.wordId)
                .set(word)

            Result.success(word)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }


}