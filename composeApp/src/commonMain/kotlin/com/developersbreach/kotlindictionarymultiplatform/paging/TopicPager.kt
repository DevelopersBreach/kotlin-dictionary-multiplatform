package com.developersbreach.kotlindictionarymultiplatform.paging

import kotlinx.coroutines.flow.Flow

interface TopicPager {
    fun pages(): Flow<Page<Topic>>
}

expect fun createTopicPager(): TopicPager