package com.example.musicplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.MusicItem
import com.example.domain.usecase.MusicUseCase
import kotlinx.coroutines.launch

class MusicViewModel(private val useCase: MusicUseCase) : ViewModel() {
    private val _music = MutableLiveData<List<MusicItem>>()
    private val _currentlyPlayingId = MutableLiveData<Long?>()
    private var currentArtist: String? = null
    val musics: LiveData<List<MusicItem>> = _music
    val currentlyPlayingId: LiveData<Long?> = _currentlyPlayingId

    fun music() {
        viewModelScope.launch {
            try {
                val result = useCase.invoke("music")
                _music.postValue(result)
            } catch (e: Exception) {
                println("error $e")
            }
        }
    }

    fun search(term: String) {
        if (term != currentArtist) {
            _music.value = emptyList()
            currentArtist = term
        }

        viewModelScope.launch {
            val result = useCase.invoke(currentArtist?:"")
            val currentList = _music.value.orEmpty() + result
            _music.postValue(currentList)
        }
    }

    fun setPlaying(trackId: Long?) {
        _currentlyPlayingId.value = trackId
    }
}