package com.developersbreach.kotlindictionarymultiplatform.data.topic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FirestoreDocument(
    @SerialName("fields") val fields: Map<String, FirestoreField>,
)

@Serializable
data class FirestoreField(
    @SerialName("stringValue") val stringValue: String,
)

@Serializable
data class FirestoreResponse(
    @SerialName("documents") val documents: List<FirestoreDocument>,
)