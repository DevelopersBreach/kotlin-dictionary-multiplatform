package com.developersbreach.kotlindictionarymultiplatform.data.detail.repository

import arrow.core.Either
import com.developersbreach.kotlindictionarymultiplatform.core.network.API_KEY
import com.developersbreach.kotlindictionarymultiplatform.core.network.KtorHttpClient
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails

class DetailRepository {

    suspend fun fetchTopic(topicId: String): Either<Throwable, KotlinTopicDetails> =
        Either.catch {
            KtorHttpClient.generateTopicDetails(topicId, API_KEY)
        }
}