package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import com.developersbreach.kotlindictionarymultiplatform.data.topic.repository.TopicRepository

class TopicPagingSource(
    private val repository: TopicRepository,
    private val query: String,
) : PagingSource<Int, ItemTopic>() {

    override suspend fun load(
        params: LoadParams<Int>,
    ): LoadResult<Int, ItemTopic> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        return try {
            val topics = repository.getTopicsPage(page, pageSize, query)
            LoadResult.Page(
                data = topics,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (topics.isEmpty()) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, ItemTopic>,
    ): Int {
        return 1
    }
}