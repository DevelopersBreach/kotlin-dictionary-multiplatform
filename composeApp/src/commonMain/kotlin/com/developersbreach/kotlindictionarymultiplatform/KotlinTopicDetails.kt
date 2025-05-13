package com.developersbreach.kotlindictionarymultiplatform

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonElement

@Serializable
data class KotlinTopicDetails(
    val topicId: String,
    val topicName: String,
    val intro: String,
    val syntax: Syntax,
    val sections: List<Section>,
    val pitfalls: List<String> = emptyList(),
    val relatedTopics: List<String> = emptyList(),
    val metadata: Map<String, JsonElement> = emptyMap()
)

@Serializable
data class Syntax(
    val signature: String,
    val notes: String? = null
)

@Serializable
data class Section(
    val heading: String? = null,
    val content: String? = null,
    val codeExamples: List<CodeExample> = emptyList()
)

@Serializable
data class CodeExample(
    val description: String? = null,
    val code: String,
    val language: String = "kotlin"
)

// --- Request/Response schema for OpenAI Chat Completion ---
@Serializable
data class ChatMessage(
    val role: String,
    val content: String
)

@Serializable
data class FunctionDefinition(
    val name: String,
    val description: String,
    val parameters: JsonElement
)

@Serializable
data class FunctionCall(
    val name: String,
    val arguments: String
)

@Serializable
data class ChatCompletionChoice(
    val message: ChatCompletionResponseMessage
)

@Serializable
data class ChatCompletionResponseMessage(
    val role: String,
    val content: String? = null,
    @SerialName("function_call")
    val functionCall: FunctionCall? = null
)

@Serializable
data class ChatCompletionResponse(
    val choices: List<ChatCompletionChoice>?
)

@Serializable
data class ChatCompletionRequest(
    val model: String,
    val messages: List<ChatMessage>,
    val functions: List<FunctionDefinition>,
    @SerialName("function_call")
    val functionCall: Map<String, String>
)