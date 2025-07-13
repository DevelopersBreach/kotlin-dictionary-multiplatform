package com.developersbreach.kotlindictionarymultiplatform.previews

import app.cash.paging.PagingData
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.CodeExample
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.Section
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.Syntax
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.TopicResponse
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.Topic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

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

private fun sampleTopicList(): List<TopicResponse> {
    return listOf(
        TopicResponse(
            name = "Smart Casts",
            description = "Automatic casting by the compiler after type checks.",
        ),
        TopicResponse(
            name = "Null Safety",
            description = "Kotlin's system to eliminate null pointer exceptions at compile time.",
        ),
        TopicResponse(
            name = "Coroutines",
            description = "Lightweight threads for asynchronous and non-blocking programming.",
        ),
        TopicResponse(
            name = "Lambdas",
            description = "Anonymous functions used to pass behavior as data.",
        ),
        TopicResponse(
            name = "Sealed Classes",
            description = "Classes used to represent restricted class hierarchies for type safety.",
        ),
    )
}

internal fun sampleTopicUiList(): List<Topic> {
    return sampleTopicList().map { topic ->
        Topic(
            name = topic.name ?: "",
            initial = topic.name?.firstOrNull()?.uppercase() ?: "",
            description = topic.description ?: "",
        )
    }
}

internal fun samplePagingData(): Flow<PagingData<Topic>> {
    return flowOf(PagingData.from(sampleTopicUiList()))
}