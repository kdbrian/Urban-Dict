package io.kdbrian.urbandict.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.kdbrian.urbandict.data.model.UrbanWord
import io.kdbrian.urbandict.domain.firebase.WordRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class WordViewModel(
    private val wordRepository: WordRepository
) : ViewModel(){

    private val _words : MutableSharedFlow<List<UrbanWord>> = MutableSharedFlow()
    val words = _words.asSharedFlow()

    init {
        viewModelScope.launch {
            wordRepository.getWords().fold(
                onSuccess = {
                    _words.emit(it)
                },
                onFailure = {
                    _words.emit(listOf(UrbanWord(word = "Failed to load", definition = it.message.toString())))
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