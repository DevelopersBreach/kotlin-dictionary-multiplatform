package com.developersbreach.kotlindictionarymultiplatform.data.topic.repository

import arrow.core.Either
import arrow.core.getOrElse
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.TopicResponse
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.toTopic
import com.developersbreach.kotlindictionarymultiplatform.core.network.topicSource.FirestoreConstants
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.ItemTopic
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

    suspend fun getTopicsPage(
        page: Int,
        pageSize: Int,
        query: String,
    ): List<ItemTopic> {
        val allTopics = getTopics().getOrElse { emptyList() }
        val filtered = allTopics
            .filter { it.name?.contains(query, ignoreCase = true) == true }
            .sortedBy { it.name?.lowercase() ?: "" }
            .map { topic ->
                ItemTopic(
                    name = topic.name ?: "",
                    initial = topic.name?.firstOrNull()?.uppercase() ?: "",
                    description = topic.description ?: "",
                )
            }
        val fromIndex = (page - 1) * pageSize
        val toIndex = (fromIndex + pageSize).coerceAtMost(filtered.size)
        return if (fromIndex < filtered.size) filtered.subList(fromIndex, toIndex) else emptyList()
    }
}