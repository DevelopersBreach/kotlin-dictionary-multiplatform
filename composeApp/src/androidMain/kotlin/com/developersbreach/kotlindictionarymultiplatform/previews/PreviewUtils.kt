package com.developersbreach.kotlindictionarymultiplatform.previews

import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.CodeExample
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.Section
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.Syntax
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.ItemTopic

internal fun sampleCodeSnippet(): String {
    return """
        fun greet(name: String): String {
            return "Hello, Sam!"
        }
        """.trimIndent()
}

internal fun fakeTopicDetails(): KotlinTopicDetails {
    return KotlinTopicDetails(
        topicId = "smart-cast",
        topicName = "Smart Cast",
        intro = "Smart casting allows the compiler to automatically cast types.",
        syntax =
            Syntax(
                signature = "if (x is String) println(x.length)",
                notes = "Works only with immutable vars",
            ),
        sections =
            listOf(
                Section(
                    heading = "Why Use It?",
                    content = "Because it’s safer and reduces boilerplate.",
                    codeExamples =
                        listOf(
                            CodeExample(
                                description = "Basic usage of smart cast",
                                code =
                                    """
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
}

private fun sampleTopicList(): List<Topic> {
    return listOf(
        Topic("Smart Casts"),
        Topic("Null Safety"),
        Topic("Coroutines"),
        Topic("Lambdas"),
        Topic("Sealed Classes"),
    )
}

internal fun sampleTopicUiList(): List<ItemTopic> {
    return sampleTopicList().map { topic ->
        ItemTopic(
            name = topic.name,
            initial = topic.name.firstOrNull()?.uppercase() ?: "",
            isBookmarked = true,
        )
    }
}