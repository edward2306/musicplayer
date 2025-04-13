package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MusicResponse (
    val resultCount: Int,
    val results: List<MusicItem>
)

@Serializable
data class MusicItem(
    val trackId: Long,
    val trackName: String,
    val artistName: String,
    val collectionName: String? = null,
    val artworkUrl100: String? = null,
    val previewUrl: String? = null
)