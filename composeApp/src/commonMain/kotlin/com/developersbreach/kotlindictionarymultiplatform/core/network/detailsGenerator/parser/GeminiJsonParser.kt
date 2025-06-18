package com.developersbreach.kotlindictionarymultiplatform.core.network.detailsGenerator.parser

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive

object GeminiJsonParser {

    fun extractCleanJson(
        rawResponse: String,
        json: Json,
    ): String {
        val root = json.parseToJsonElement(rawResponse).jsonObject
        val text = root["candidates"]
            ?.jsonArray?.firstOrNull()
            ?.jsonObject?.get("content")
            ?.jsonObject?.get("parts")
            ?.jsonArray?.firstOrNull()
            ?.jsonObject?.get("text")
            ?.jsonPrimitive?.content
            ?: error("Malformed Gemini response")

        return text
            .removePrefix("```json\n")
            .removePrefix("```json")
            .removePrefix("json\n")
            .removePrefix("json")
            .removeSuffix("```")
            .trim()
    }
}