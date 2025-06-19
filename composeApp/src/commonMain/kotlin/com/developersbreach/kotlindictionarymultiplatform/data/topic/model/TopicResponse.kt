package com.developersbreach.kotlindictionarymultiplatform.data.topic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicResponse(
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

fun RawTopic.toTopic(): Topic {
    return Topic(
        name = fields.name.value,
        description = fields.description.value,
    )
}