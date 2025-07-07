package com.developersbreach.kotlindictionarymultiplatform.paging

import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import kotlinx.coroutines.flow.Flow

interface TopicPager {
    fun pages(): Flow<Page<Topic>>
}

expect fun createTopicPager(): TopicPager