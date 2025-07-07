package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developersbreach.designsystem.components.KdScaffold
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.paging.TopicList

@Composable
fun TopicScreenUI(
    topics: List<Topic>,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onTopicClick: (String) -> Unit,
    onLoadMore: () -> Unit,
) {
    KdScaffold(
        modifier = Modifier,
        topBar = { TopicTopBar() },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = paddingValues.calculateTopPadding()),
        ) {
            SearchField(
                searchQuery = searchQuery,
                onQueryChange = onQueryChange,
            )
            Spacer(modifier = Modifier.height(8.dp))

            TopicList(
                topics = topics,
                onTopicClick = onTopicClick,
                onLoadMore = onLoadMore,
            )
        }
    }
}