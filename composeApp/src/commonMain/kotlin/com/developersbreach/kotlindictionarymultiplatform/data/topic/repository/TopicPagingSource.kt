package com.developersbreach.kotlindictionarymultiplatform.data.topic.repository

import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.Topic

class TopicPagingSource(
    private val repository: TopicRepository,
    private val query: String,
) : PagingSource<Int, Topic>() {

    override suspend fun load(
        params: LoadParams<Int>,
    ): LoadResult<Int, Topic> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        return try {
            val pageItems = repository.getTopicsPage(page, pageSize, query)
            LoadResult.Page(
                data = pageItems,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (pageItems.isEmpty()) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, Topic>,
    ): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}