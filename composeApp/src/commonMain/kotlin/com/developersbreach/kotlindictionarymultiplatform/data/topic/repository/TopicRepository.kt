package com.developersbreach.kotlindictionarymultiplatform.data.topic.repository

import arrow.core.Either
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.TopicResponse
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.toTopic
import com.developersbreach.kotlindictionarymultiplatform.core.network.topicSource.FirestoreConstants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class TopicRepository(
    private val httpClient: HttpClient,
) {
    suspend fun getTopics(): Either<Throwable, List<Topic>> {
        return Either.catch {
            val topicResponse: TopicResponse = httpClient.get(FirestoreConstants.TOPICS_URL).body()
            topicResponse.topics.map { it.toTopic() }
        }
    }
}