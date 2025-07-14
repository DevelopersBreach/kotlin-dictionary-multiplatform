package com.developersbreach.kotlindictionarymultiplatform.data.topic.repository

import arrow.core.Either
import arrow.core.getOrElse
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.TopicsResponse
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.TopicResponse
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.toTopic
import com.developersbreach.kotlindictionarymultiplatform.core.network.topicSource.FirestoreConstants
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.Topic
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class TopicRepository(
    private val httpClient: HttpClient,
) {
    private suspend fun getTopics(): Either<Throwable, List<TopicResponse>> {
        return Either.catch {
            val topicsResponse: TopicsResponse = httpClient.get(FirestoreConstants.TOPICS_URL).body()
            topicsResponse.topics.map { it.toTopic() }
        }
    }

    suspend fun getTopicsPage(
        page: Int,
        pageSize: Int,
        query: String,
    ): List<Topic> {
        val allTopics = getTopics().getOrElse { emptyList() }
        val filteredTopics = allTopics
            .filter { it.name?.contains(query, ignoreCase = true) == true }
            .sortedBy { it.name?.lowercase() ?: "" }
            .map { topic ->
                Topic(
                    name = topic.name ?: "",
                    initial = topic.name?.firstOrNull()?.uppercase() ?: "",
                    description = topic.description ?: "",
                )
            }
        val fromIndex = (page - 1) * pageSize
        val toIndex = (fromIndex + pageSize).coerceAtMost(filteredTopics.size)
        return if (fromIndex < filteredTopics.size) filteredTopics.subList(fromIndex, toIndex) else emptyList()
    }
}