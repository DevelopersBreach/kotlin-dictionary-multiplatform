package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.TopicUi

@Composable
fun TopicScreenUI(
    topics: List<TopicUi>,
    bookmarkedStates: List<Boolean>,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onBookmarkClick: (Int) -> Unit,
    onTopicClick: (String) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { TopicTopBar() },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = paddingValues.calculateTopPadding()),
        ) {
            SearchField(searchQuery = searchQuery, onQueryChange = onQueryChange)
            Spacer(modifier = Modifier.height(8.dp))
            TopicList(
                topics = topics,
                bookmarkedStates = bookmarkedStates,
                onBookmarkClick = onBookmarkClick,
                onTopicClick = onTopicClick,
            )
        }
    }
}