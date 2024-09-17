package com.example.lasttask

import com.example.lasttask.ApiService

class AlbumRepository(private val apiService: ApiService) {
    suspend fun getAlbums(): List<Album> {
        return apiService.getAlbums()
    }
}
