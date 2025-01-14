package io.kdbrian.urbandict.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.domain.firebase.WordRepository
import io.kdbrian.urbandict.presentation.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class WordViewModel(
    private val wordRepository: WordRepository
) : ViewModel(){

    private val _words: MutableSharedFlow<Resource<List<UrbanWord>>> = MutableSharedFlow()
    val words = _words.asSharedFlow()

    val livewords = wordRepository.liveWords.asSharedFlow()

    init {
        viewModelScope.launch {
            wordRepository.observeWords()
            wordRepository.getWords().fold(
                onSuccess = {
                    _words.emit(Resource.Success(it))
                },
                onFailure = {
                    _words.emit(Resource.Error("Failed to load"))
                }
            )
        }
    }

    fun addWord(word: UrbanWord) {
        viewModelScope.launch {
            wordRepository.addWord(word)
        }
    }

    fun refreshWords() {
        viewModelScope.launch {
            _words.emit(Resource.Loading())
            wordRepository.getWords().fold(
                onSuccess = {
                    _words.emit(Resource.Success(it))
                },
                onFailure = {
                    _words.emit(Resource.Error("Failed to load"))
                }
            )

        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val wordRepository: WordRepository)  : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WordViewModel(wordRepository) as T
        }
    }

}