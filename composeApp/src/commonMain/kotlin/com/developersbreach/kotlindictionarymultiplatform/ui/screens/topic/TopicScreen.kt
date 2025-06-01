package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiStateHandler
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.add_bookmark
import kotlindictionarymultiplatform.composeapp.generated.resources.back
import kotlindictionarymultiplatform.composeapp.generated.resources.description_subtitle
import kotlindictionarymultiplatform.composeapp.generated.resources.icon
import kotlindictionarymultiplatform.composeapp.generated.resources.remove_bookmark
import kotlindictionarymultiplatform.composeapp.generated.resources.search
import kotlindictionarymultiplatform.composeapp.generated.resources.search_kotlin_terms
import kotlindictionarymultiplatform.composeapp.generated.resources.topics
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopicScreen(
    onTopicClick: (String) -> Unit,
    viewModel: TopicViewModel,
) {
    val topicState by viewModel.topics.collectAsState()
    val filteredTopics by viewModel.filteredTopics.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val bookmarkedStates by viewModel.bookmarkedStates.collectAsState()

    UiStateHandler(uiState = topicState) {
        TopicScreenLayout(
            topics = filteredTopics,
            bookmarkedStates = bookmarkedStates,
            searchQuery = searchQuery,
            onQueryChange = viewModel::updateSearchQuery,
            onBookmarkClick = viewModel::toggleBookmark,
            onTopicClick = onTopicClick,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicScreenLayout(
    topics: List<Topic>,
    bookmarkedStates: List<Boolean>,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onBookmarkClick: (Int) -> Unit,
    onTopicClick: (String) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.topics),
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Back action */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(Res.string.back))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = paddingValues.calculateTopPadding()),
        ) {
            SearchField(searchQuery = searchQuery, onQueryChange = onQueryChange)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 40.dp),
            ) {
                itemsIndexed(topics) { index, topic ->
                    val isBookmarked = bookmarkedStates.getOrNull(index) ?: true
                    TopicCard(
                        topic = topic.name,
                        subtitle = stringResource(Res.string.description_subtitle),
                        isBookmarked = isBookmarked,
                        onBookmarkClick = { onBookmarkClick(index) },
                        onCardClick = { onTopicClick(topic.name) },
                    )
                }
            }
        }
    }
}

@Composable
fun TopicCard(
    topic: String,
    subtitle: String,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    onCardClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(6.dp, RoundedCornerShape(16.dp), clip = true)
            .clickable { onCardClick() },
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Filled.Search, contentDescription = stringResource(Res.string.icon))
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = topic,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            IconButton(onClick = onBookmarkClick) {
                Icon(
                    imageVector = if (isBookmarked) Icons.Outlined.Bookmark else Icons.Filled.Bookmark,
                    contentDescription = if (isBookmarked) stringResource(Res.string.remove_bookmark) else stringResource(Res.string.add_bookmark),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Composable
fun SearchField(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
) {
    TextField(
        value = searchQuery,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .padding(4.dp),
        placeholder = {
            Text(
                stringResource(Res.string.search_kotlin_terms),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground,
            )
        },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = stringResource(Res.string.search), tint = MaterialTheme.colorScheme.onBackground)
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onBackground,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(25.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                // Optional: handle enter key logic
            },
        ),
        textStyle = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.onBackground,
            lineHeight = 24.sp,
            letterSpacing = .5.sp,
        ),
    )
}