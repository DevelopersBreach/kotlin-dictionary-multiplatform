package com.developersbreach.kotlindictionarymultiplatform.data.topic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RawTopic(
    @SerialName("fields") val fields: Map<String, RawField>,
)

@Serializable
data class RawField(
    @SerialName("stringValue") val value: String,
)

@Serializable
data class Response(
    @SerialName("documents") val topics: List<RawTopic>,
)

fun RawTopic.toTopic(): Topic {
    return Topic(
        name = fields["name"]?.value.orEmpty(),
        description = fields["description"]?.value.orEmpty(),
    )
}