package com.developersbreach.kotlindictionarymultiplatform.data.detail.model

import kotlinx.serialization.Serializable

@Serializable
data class CodeExample(
    val description: String? = null,
    val code: String,
    val language: String = "kotlin",
)