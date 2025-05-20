package com.developersbreach.kotlindictionarymultiplatform.data.topic.repository

import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic

object TopicRepository {
    fun getTopics(): List<Topic> = listOf(
        Topic("Variables"),
        Topic("Strings"),
        Topic("Functions"),
        Topic("Coroutines"),
        Topic("Classes"),
        Topic("Interfaces"),
        Topic("Objects"),
        Topic("Collections"),
        Topic("Null Safety"),
        Topic("Lambdas"),
        Topic("Higher-Order Functions"),
        Topic("Delegation"),
        Topic("Sealed Classes"),
        Topic("Generics"),
        Topic("Annotations")
    )
}