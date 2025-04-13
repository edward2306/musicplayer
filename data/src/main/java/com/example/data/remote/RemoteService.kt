package com.example.data.remote

import com.example.data.network.NetworkService.client
import com.example.domain.model.MusicResponse
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val baseUrl = "https://itunes.apple.com/search"
interface RemoteService {
    suspend fun music(artist: String) : MusicResponse
}

class MusicServiceImpl : RemoteService {
    private val json = Json { ignoreUnknownKeys = true }
    override suspend fun music(artist: String): MusicResponse {
        val response = client.get(baseUrl) {
            parameter("term", artist)
            parameter("entity", "musicTrack")
            parameter("limit", 20)
        }.bodyAsText()
        return json.decodeFromString<MusicResponse>(response)
    }

}