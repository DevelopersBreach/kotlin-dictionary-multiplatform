package com.developersbreach.kotlindictionarymultiplatform.core.network.api

import com.developersbreach.kotlindictionarymultiplatform.Log
import com.developersbreach.kotlindictionarymultiplatform.core.network.client.KtorClientProvider
import com.developersbreach.kotlindictionarymultiplatform.core.network.parser.GeminiJsonParser
import com.developersbreach.kotlindictionarymultiplatform.core.network.request.GeminiPromptBuilder
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

object GeminiApiService {

    suspend fun generateTopicDetails(
        topicId: String,
        apiKey: String,
    ): KotlinTopicDetails {
        val prompt = GeminiPromptBuilder.buildRequest(topicId)
        val requestBody = GeminiPromptBuilder.buildRequestBody(prompt)

        val response = KtorClientProvider.client.post(
            "https://generativelanguage.googleapis.com/v1/models/gemini-2.0-flash:generateContent?key=$apiKey",
        ) {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }

        val responseBody = response.bodyAsText()
        Log.i("GeminiRawResponse", responseBody)

        val cleanJson = GeminiJsonParser.extractCleanJson(responseBody, KtorClientProvider.json)
        Log.i("CleanJson", cleanJson)

        return KtorClientProvider.json.decodeFromString(KotlinTopicDetails.serializer(), cleanJson)
    }
}