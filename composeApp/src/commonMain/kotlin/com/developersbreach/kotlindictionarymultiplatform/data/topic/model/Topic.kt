package com.developersbreach.kotlindictionarymultiplatform.data.topic.model

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    val name: String?,
    val description: String?,
)