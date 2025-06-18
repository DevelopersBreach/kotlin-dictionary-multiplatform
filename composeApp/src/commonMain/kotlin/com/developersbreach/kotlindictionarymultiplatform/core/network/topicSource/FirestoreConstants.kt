package com.developersbreach.kotlindictionarymultiplatform.core.network.topicSource

object FirestoreConstants {
    private const val ROOT_URL = "https://firestore.googleapis.com/v1/projects/kotlin-dictionary/databases/(default)/documents"
    const val TOPICS_URL = "$ROOT_URL/topics"
}