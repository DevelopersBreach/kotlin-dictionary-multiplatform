package com.developersbreach.kotlindictionarymultiplatform.core

import com.developersbreach.kotlindictionarymultiplatform.data.detail.ChatCompletionRequest
import com.developersbreach.kotlindictionarymultiplatform.data.detail.ChatCompletionResponse
import com.developersbreach.kotlindictionarymultiplatform.data.detail.ChatMessage
import com.developersbreach.kotlindictionarymultiplatform.data.detail.FunctionDefinition
import com.developersbreach.kotlindictionarymultiplatform.data.detail.KotlinTopicDetails
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject


object KtorHttpClient {
    private val json = Json { ignoreUnknownKeys = true }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    private val functionSchema = json.parseToJsonElement(
        // Paste the full JSON schema for your function here
        """
        {
          "type": "object",
          "properties": {
            "topicId": { "type": "string" },
            "topicName": { "type": "string" },
            "intro": { "type": "string" },
            "syntax": {
              "type": "object",
              "properties": {
                "signature": { "type": "string" },
                "notes": { "type": "string" }
              },
              "required": ["signature"]
            },
            "sections": {
              "type": "array",
              "items": {
                "type": "object",
                "properties": {
                  "heading": { "type": "string" },
                  "content": { "type": "string" },
                  "codeExamples": {
                    "type": "array",
                    "items": {
                      "type": "object",
                      "properties": {
                        "description": { "type": "string" },
                        "code": { "type": "string" },
                        "language": { "type": "string" }
                      },
                      "required": ["code"]
                    }
                  }
                },
                "required": ["heading","content"]
              }
            },
            "pitfalls": { "type": "array", "items": { "type": "string" } },
            "relatedTopics": { "type": "array", "items": { "type": "string" } },
            "metadata": { "type": "object", "additionalProperties": true }
          },
          "required": ["topicId","topicName","intro","syntax","sections"]
        }
        """.trimIndent()
    )

    private val functionDef = FunctionDefinition(
        name = "generate_kotlin_topic_details",
        description = "Return a fully-featured Kotlin documentation object for a given topic",
        parameters = functionSchema
    )

    /**
     * Calls the OpenAI ChatCompletion with function-calling to get topic details.
     * @param topicId the topic identifier, e.g. "variables".
     * @param apiKey your OpenAI API key.
     */
    suspend fun generateTopicDetails(topicId: String, apiKey: String): KotlinTopicDetails {
        // Prepare messages
        val messages = listOf(
            ChatMessage("system", "You are a Kotlin documentation generator."),
            ChatMessage("user", "Generate full Kotlin documentation for topic \"$topicId\".")
        )

        // Build request body
        val request = ChatCompletionRequest(
            model = "gpt-4o-mini",
            messages = messages,
            functions = listOf(functionDef),
            functionCall = mapOf("name" to functionDef.name)
        )

        // Execute HTTP request
        val response: HttpResponse = client.post("https://api.openai.com/v1/chat/completions") {
            header(HttpHeaders.Authorization, "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            setBody(json.encodeToString(ChatCompletionRequest.serializer(), request))
        }

        val text = response.bodyAsText()
        println("RAW RESPONSE:\n$text")

        // Parse response
        val chatResp = json.decodeFromString(ChatCompletionResponse.serializer(), text)
        println(chatResp)
        val funcCall = chatResp.choices?.first()?.message?.functionCall ?: error("No function call in response")

        // The arguments field is a JSON string: parse and decode into our DTO
        val argsJson = json.parseToJsonElement(funcCall.arguments)
        return json.decodeFromJsonElement(argsJson.jsonObject)
    }
}