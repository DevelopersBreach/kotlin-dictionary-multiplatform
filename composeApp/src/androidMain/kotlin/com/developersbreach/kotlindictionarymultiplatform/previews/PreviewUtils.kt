package com.developersbreach.kotlindictionarymultiplatform.previews

import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.CodeExample
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.Section
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.Syntax
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic

fun sampleCodeSnippet(): String = """
    fun greet(name: String): String {
        return "Hello, Sam!"
    }
    """.trimIndent()

fun topic() = "Smart Casts"

fun subtitle() = "Automatic casting of immutable values"

fun fakeTopicDetails() = KotlinTopicDetails(
    topicId = "smart-cast",
    topicName = "Smart Cast",
    intro = "Smart casting allows the compiler to automatically cast types.",
    syntax = Syntax(
        signature = "if (x is String) println(x.length)",
        notes = "Works only with immutable vars",
    ),
    sections = listOf(
        Section(
            heading = "Why Use It?",
            content = "Because it’s safer and reduces boilerplate.",
            codeExamples = listOf(
                CodeExample(
                    description = "Basic usage of smart cast",
                    code = """
                        fun printLength(obj: Any) {
                            if (obj is String) {
                                println(obj.length) // Smart cast
                            }
                        }
                    """.trimIndent(),
                ),
            ),
        ),
    ),
    pitfalls = listOf("Doesn't work with mutable vars."),
    relatedTopics = listOf("Type Checking", "Safe Casts"),
)

fun sampleTopicList(): List<Topic> = listOf(
    Topic("Smart Casts"),
    Topic("Null Safety"),
    Topic("Coroutines"),
    Topic("Lambdas"),
    Topic("Sealed Classes"),
)

val faketopiclist = listOf(
    "Introduction" to 0,
    "Examples" to 1,
    "Use Cases" to 2,
    "Best Practices" to 3,
)