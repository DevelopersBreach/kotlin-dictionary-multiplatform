package com.developersbreach.kotlindictionarymultiplatform.core.network

import com.developersbreach.kotlindictionarymultiplatform.Log
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray

object KtorHttpClient {

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun generateTopicDetails(
        topicId: String,
        apiKey: String,
    ): KotlinTopicDetails {
        // Gemini-style prompt
        val prompt = """
            You are a Kotlin documentation generator.
            Generate a JSON object for the topic "$topicId" with the following structure:
            
            {
              "topicId": "...",
              "topicName": "...",
              "intro": "...",
              "syntax": {
                "signature": "...",
                "notes": "..."
              },
              "sections": [
                {
                  "heading": "...",
                  "content": "...",
                  "codeExamples": [
                    {
                      "description": "...",
                      "code": "...",
                      "language": "kotlin"
                    }
                  ]
                }
              ],
              "pitfalls": ["..."],
              "relatedTopics": ["..."],
              "metadata": {}
            }

            Respond only with pure JSON. No explanation or markdown.
        """.trimIndent()

        val requestBody = buildJsonObject {
            putJsonArray("contents") {
                addJsonObject {
                    putJsonArray("parts") {
                        addJsonObject {
                            put("text", prompt)
                        }
                    }
                }
            }
        }

        val response = client.post("https://generativelanguage.googleapis.com/v1/models/gemini-2.0-flash:generateContent?key=$apiKey") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }

        val responseBody = response.bodyAsText()
        Log.i("GeminiRawResponse", responseBody)

        // Parse root object
        val root = json.parseToJsonElement(responseBody).jsonObject
        val candidates = root["candidates"]?.jsonArray ?: error("Missing candidates")
        val firstCandidate = candidates.first().jsonObject
        val content = firstCandidate["content"]?.jsonObject ?: error("Missing content")
        val partsArray = content["parts"]?.jsonArray ?: error("Missing parts array")
        val part = partsArray.first().jsonObject
        val rawJson = part["text"]?.jsonPrimitive?.content ?: error("Missing text in part")
        Log.i("RawJson", rawJson)

        // Trim whitespace, remove code fences if any
        val cleanJson = rawJson.trim()
            .removePrefix("```json\n")
            .removePrefix("```json")
            .removePrefix("json\n")
            .removePrefix("json")
            .removeSuffix("```")
            .trim()

        return json.decodeFromString(KotlinTopicDetails.serializer(), cleanJson)
    }
}