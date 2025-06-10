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