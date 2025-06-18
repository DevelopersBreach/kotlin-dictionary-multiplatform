package com.developersbreach.kotlindictionarymultiplatform.data.topic.repository

import arrow.core.Either
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Response
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.toTopic
import com.developersbreach.kotlindictionarymultiplatform.core.network.topicSource.FirestoreConstants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

//object TopicRepository {
//
//    private val client = HttpClient {
//        install(ContentNegotiation) {
//            json(Json { ignoreUnknownKeys = true })
//        }
//    }
//
//    suspend fun getTopics(): Either<Throwable, List<Topic>> {
//        return Either.catch {
//            val response: Response = client.get(FirestoreConstants.TOPICS_URL).body()
//            response.topics.map { it.toTopic() }
//        }
//    }
//}

class TopicRepository(
    private val httpClient: HttpClient
){
    suspend fun getTopics(): Either<Throwable, List<Topic>> = Either.catch {
            val response: Response = httpClient.get(FirestoreConstants.TOPICS_URL).body()
            response.topics.map { it.toTopic() }
    }
}