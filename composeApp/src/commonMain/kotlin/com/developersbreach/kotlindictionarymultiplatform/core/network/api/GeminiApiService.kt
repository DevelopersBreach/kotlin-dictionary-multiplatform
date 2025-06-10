package com.developersbreach.kotlindictionarymultiplatform.core.network.api

import com.developersbreach.kotlindictionarymultiplatform.Log
import com.developersbreach.kotlindictionarymultiplatform.core.network.parser.GeminiJsonParser
import com.developersbreach.kotlindictionarymultiplatform.core.network.request.GeminiPromptBuilder
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class GeminiApiService(
    private val client: HttpClient,
    private val json: Json,
) {
    suspend fun generateTopicDetails(
        topicId: String,
        apiKey: String,
    ): KotlinTopicDetails {
        val prompt = GeminiPromptBuilder.buildRequest(topicId)
        val requestBody = GeminiPromptBuilder.buildRequestBody(prompt)

        val response = client.post(
            "https://generativelanguage.googleapis.com/v1/models/gemini-2.0-flash:generateContent?key=$apiKey",
        ) {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }

        val responseBody = response.bodyAsText()
        Log.i("GeminiRawResponse", responseBody)

        val cleanJson = GeminiJsonParser.extractCleanJson(responseBody, json)
        Log.i("CleanJson", cleanJson)

        return json.decodeFromString(KotlinTopicDetails.serializer(), cleanJson)
    }
}