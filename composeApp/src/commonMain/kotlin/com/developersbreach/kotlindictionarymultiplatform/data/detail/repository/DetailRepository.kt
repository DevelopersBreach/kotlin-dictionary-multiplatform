package com.developersbreach.kotlindictionarymultiplatform.data.detail.repository

import arrow.core.Either
import com.developersbreach.kotlindictionarymultiplatform.core.network.api.GeminiApiService
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import com.developersbreach.kotlindictionarymultiplatform.getOpenApiKey

class DetailRepository(
    private val service: GeminiApiService,
) {

    suspend fun fetchTopic(
        topicId: String,
    ): Either<Throwable, KotlinTopicDetails> {
        return Either.catch {
            service.generateTopicDetails(
                topicId = topicId,
                apiKey = getOpenApiKey(),
            )
        }
    }
}