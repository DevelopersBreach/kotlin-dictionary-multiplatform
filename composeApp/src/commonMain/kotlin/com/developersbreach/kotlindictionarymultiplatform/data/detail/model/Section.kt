package com.developersbreach.kotlindictionarymultiplatform.data.detail.model

import kotlinx.serialization.Serializable

@Serializable
data class Section(
    val heading: String? = null,
    val content: String? = null,
    val codeExamples: List<CodeExample> = emptyList(),
)