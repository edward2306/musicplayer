package com.example.data.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkService {
    val client: HttpClient by lazy{
        HttpClient(CIO){
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }

            install(Logging){
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("Ktor Logging", message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
}