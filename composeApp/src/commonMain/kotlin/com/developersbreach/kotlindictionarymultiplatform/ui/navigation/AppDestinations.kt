package com.developersbreach.kotlindictionarymultiplatform.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppDestinations {

    @Serializable
    data object TopicList : AppDestinations

    @Serializable
    data class Detail(val topicId: String) : AppDestinations
}
