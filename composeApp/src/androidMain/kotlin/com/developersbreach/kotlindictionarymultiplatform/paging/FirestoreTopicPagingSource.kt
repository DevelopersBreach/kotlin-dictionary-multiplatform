package com.developersbreach.kotlindictionarymultiplatform.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class FirestoreTopicPagingSource(
    private val query: Query,
) : PagingSource<DocumentSnapshot, Topic>() {

    override suspend fun load(
        params: LoadParams<DocumentSnapshot>,
    ): LoadResult<DocumentSnapshot, Topic> {
        return try {
            val pageQuery = params.key?.let {
                query.startAfter(it).limit(params.loadSize.toLong())
            } ?: query.limit(params.loadSize.toLong())

            val snapshot = pageQuery.get().await()

            val topics = snapshot.documents.map { doc ->
                Topic(
                    name = doc.getString("title"),
                    description = doc.getString("description"),
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
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.nextKey
        }
    }
}