package com.developersbreach.kotlindictionarymultiplatform.data.topic.repository

import arrow.core.Either
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic

object TopicRepository {
    fun getTopics(): Either<Throwable, List<Topic>> =
        Either.catch {
            listOf(
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
                Topic("Annotations"),
            )
        }
}