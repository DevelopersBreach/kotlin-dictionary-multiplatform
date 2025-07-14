package com.developersbreach.kotlindictionarymultiplatform.data.topic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicsResponse(
    @SerialName("documents") val topics: List<RawTopic>,
)

@Serializable
data class RawTopic(
    @SerialName("fields") val fields: TopicFields,
)

@Serializable
data class TopicFields(
    @SerialName("name") val name: RawField,
    @SerialName("description") val description: RawField,
)

@Serializable
data class RawField(
    @SerialName("stringValue") val value: String,
)

@Serializable
data class TopicResponse(
    val name: String?,
    val description: String?,
)

fun RawTopic.toTopic(): TopicResponse {
    return TopicResponse(
        name = fields.name.value,
        description = fields.description.value,
    )
}