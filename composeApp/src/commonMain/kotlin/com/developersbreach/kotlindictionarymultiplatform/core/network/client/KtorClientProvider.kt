package com.developersbreach.kotlindictionarymultiplatform.core.network.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClientProvider {

    val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }
}