package io.kdbrian.urbandict.data.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore
import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.domain.firebase.WordRepository
import io.kdbrian.urbandict.presentation.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

private const val wordCollection: String = "urban-words-test"

class WordsRepo : WordRepository {

    private val wordsCollection = FirebaseFirestore.getInstance().collection(wordCollection)

    override val liveWords: MutableSharedFlow<Resource<List<UrbanWord>>> =
        MutableSharedFlow()

    private val scope = CoroutineScope(Dispatchers.IO)

    override suspend fun observeWords() {

        wordsCollection.addSnapshotListener { value, error ->
            scope.launch {
                println("got val -> ${value?.size()} err -> ${error?.message}")

                if (value?.isEmpty == false) {
                    val words = value.toObjects(UrbanWord::class.java)
                    liveWords.emit(Resource.Success(words))
                }

                if (error != null) {
                    liveWords.emit(Resource.Error(error.message.toString()))
                }

            }
        }
    }

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