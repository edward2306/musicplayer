package com.example.domain.repository

import com.example.domain.model.MusicItem

interface MusicRepository {
    suspend fun music(artist: String): List<MusicItem>
}