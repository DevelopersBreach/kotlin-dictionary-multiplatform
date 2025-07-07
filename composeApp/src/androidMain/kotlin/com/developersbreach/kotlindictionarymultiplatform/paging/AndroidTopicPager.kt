package com.developersbreach.kotlindictionarymultiplatform.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

actual fun createTopicPager(): TopicPager = AndroidPagingTopicPager()

private class AndroidPagingTopicPager : TopicPager {
    private val firestore = FirebaseFirestore.getInstance()
    private val baseQuery = firestore
        .collection("documents")
        .orderBy("title", Query.Direction.ASCENDING)

    override fun pages(): Flow<Page<Topic>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { FirestoreTopicPagingSource(baseQuery) },
        ).flow.flatMapConcat { pagingData ->
            kotlinx.coroutines.flow.flow {
                val items = mutableListOf<Topic>()
                pagingData.collect { item ->
                    items.add(item)
                }
                emit(Page(items = items, isEndReached = items.size < 10))
            }
        }
    }
}
