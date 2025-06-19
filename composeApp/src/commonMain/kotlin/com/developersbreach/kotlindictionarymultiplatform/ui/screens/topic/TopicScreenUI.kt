package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developersbreach.designsystem.components.KdScaffold

@Composable
fun TopicScreenUI(
    topics: List<ItemTopic>,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onTopicClick: (String) -> Unit,
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
            )
        }
    }
}