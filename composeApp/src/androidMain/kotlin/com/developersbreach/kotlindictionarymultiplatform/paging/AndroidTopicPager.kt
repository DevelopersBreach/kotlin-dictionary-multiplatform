package com.developersbreach.kotlindictionarymultiplatform.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.tasks.await

actual fun createTopicPager(): TopicPager {
    return AndroidPagingTopicPager()
}

private class AndroidPagingTopicPager : TopicPager {

    private val query = FirebaseFirestore.getInstance()
        .collection("topics")
        .orderBy("title", Query.Direction.ASCENDING)

    override fun pages(): Flow<Page<Topic>> {
        return channelFlow {
            Pager(
                config = PagingConfig(pageSize = 10, enablePlaceholders = false),
                pagingSourceFactory = { FirestoreSource(query) },
            ).flow.collect { pagingData ->
                val items = mutableListOf<Topic>()
                pagingData.collect { topic ->
                    items.add(topic)
                }
                send(Page(items = items))
            }
        }
    }
}

private class FirestoreSource(
    private val query: Query,
) : PagingSource<DocumentSnapshot, Topic>() {

    override suspend fun load(
        params: LoadParams<DocumentSnapshot>,
    ): LoadResult<DocumentSnapshot, Topic> {
        return try {
            val currentQuery = params.key?.let { query.startAfter(it) } ?: query
            val snapshot = currentQuery.limit(params.loadSize.toLong()).get().await()
            val topics = snapshot.documents.map {
                Topic(
                    id = it.id,
                    name = it.getString("title") ?: "",
                    description = it.getString("description") ?: "",
                )
            }
            LoadResult.Page(
                data = topics,
                prevKey = null,
                nextKey = snapshot.documents.lastOrNull(),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<DocumentSnapshot, Topic>,
    ): DocumentSnapshot? {
        return null
    }
}