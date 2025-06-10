package com.developersbreach.kotlindictionarymultiplatform.data.detail.model

import kotlinx.serialization.Serializable

@Serializable
data class Syntax(
    val signature: String,
    val notes: String? = null,
)