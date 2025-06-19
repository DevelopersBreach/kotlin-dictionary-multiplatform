package com.developersbreach.kotlindictionarymultiplatform.core.network.detailsGenerator.request

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray

object GeminiPromptBuilder {

    private val jsonSchema = """
        {
          "type": "object",
          "properties": {
            "topicId": { "type": "string" },
            "topicName": { "type": "string" },
            "intro": { "type": "string" },
            "syntax": {
              "type": "object",
              "properties": {
                "signature": { "type": "string" },
                "notes": { "type": "string" }
              },
              "required": ["signature"]
            },
            "sections": {
              "type": "array",
              "items": {
                "type": "object",
                "properties": {
                  "heading": { "type": "string" },
                  "content": { "type": "string" },
                  "codeExamples": {
                    "type": "array",
                    "items": {
                      "type": "object",
                      "properties": {
                        "description": { "type": "string" },
                        "code": { "type": "string" },
                        "language": { "type": "string" }
                      },
                      "required": ["code"]
                    }
                  }
                },
                "required": ["heading", "content"]
              }
            },
            "pitfalls": { "type": "array", "items": { "type": "string" } },
            "relatedTopics": { "type": "array", "items": { "type": "string" } },
            "metadata": { "type": "object", "additionalProperties": true }
          },
          "required": ["topicId", "topicName", "intro", "syntax", "sections"]
        }
    """.trimIndent()

    fun buildRequest(
        topicId: String,
    ): String {
        return """
        You are a Kotlin documentation generator.

        Generate a detailed JSON object for the topic "$topicId". 
        The output MUST strictly conform to the following JSON schema:

        $jsonSchema

        Requirements:
        - Respond only with pure JSON.
        - Do not include any Markdown formatting (no ```json).
        - Ensure all required fields are present.
        - Use valid Kotlin examples.
        - Include at least 3 sections with headings, content, and code examples.
        - Use the language "kotlin" in each code example.

        Make sure the entire response is a **valid JSON** object matching the schema above.
            """.trimIndent()
    }

    fun buildRequestBody(
        prompt: String,
    ): JsonObject {
        return buildJsonObject {
            putJsonArray("contents") {
                addJsonObject {
                    putJsonArray("parts") {
                        addJsonObject {
                            put("text", prompt)
                        }
                    }
                }
            }
        }
    }
}