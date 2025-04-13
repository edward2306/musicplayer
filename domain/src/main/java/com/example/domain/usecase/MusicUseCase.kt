package com.example.domain.usecase

import com.example.domain.model.MusicItem
import com.example.domain.repository.MusicRepository

class MusicUseCase(private val repository: MusicRepository) {
    suspend fun invoke(artist: String): List<MusicItem> = repository.music(artist)
}