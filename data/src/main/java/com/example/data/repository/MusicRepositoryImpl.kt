package com.example.data.repository

import com.example.data.remote.RemoteService
import com.example.domain.model.MusicItem
import com.example.domain.repository.MusicRepository

class MusicRepositoryImpl(private val service: RemoteService) : MusicRepository {
    override suspend fun music(artist: String): List<MusicItem> {
        return service.music(artist).results
    }
}