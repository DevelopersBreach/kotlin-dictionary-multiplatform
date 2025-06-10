package com.developersbreach.kotlindictionarymultiplatform.data.detail.model

import kotlinx.serialization.Serializable
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
    val metadata: Map<String, JsonElement> = emptyMap(),
)

@Serializable
data class CodeExample(
    val description: String? = null,
    val code: String,
    val language: String = "kotlin",
)

@Serializable
data class Section(
    val heading: String? = null,
    val content: String? = null,
    val codeExamples: List<CodeExample> = emptyList(),
)

@Serializable
data class Syntax(
    val signature: String,
    val notes: String? = null,
)