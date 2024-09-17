package com.example.lasttask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AlbumViewModel(private val repository: AlbumRepository) : ViewModel() {
    val albums = MutableLiveData<List<Album>>()
    val errorMessage = MutableLiveData<String>()

    fun fetchAlbums() {
        viewModelScope.launch {
            try {
                val result = repository.getAlbums()
                albums.postValue(result)
            } catch (e: Exception) {
                errorMessage.postValue(e.message)
            }
        }
    }
}
