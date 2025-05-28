package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersbreach.kotlindictionarymultiplatform.Log
import com.developersbreach.kotlindictionarymultiplatform.theme.Cream
import com.developersbreach.kotlindictionarymultiplatform.theme.Grey
import com.developersbreach.kotlindictionarymultiplatform.theme.White
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.ic_bookmark
import kotlindictionarymultiplatform.composeapp.generated.resources.ic_bookmark_filled
import org.jetbrains.compose.resources.painterResource

@Composable
fun TopicScreen(
    onTopicClick: (String) -> Unit,
    viewModel: TopicViewModel,
) {
    val allTopics by viewModel.topics.collectAsState()
    val searchQuery = remember { mutableStateOf("") }

    val filteredTopics = remember(searchQuery.value, allTopics) {
        if (searchQuery.value.isBlank()) {
            allTopics
        } else {
            allTopics.filter { it.name.contains(searchQuery.value, ignoreCase = true) }
        }
    }

    val bookmarkedStates = remember { mutableStateListOf<Boolean>().apply { addAll(List(allTopics.size) { true }) } }

    Scaffold(
        backgroundColor = White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Topics",
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                    )
                },
                backgroundColor = White,
                navigationIcon = {
                    IconButton(onClick = { /* Handle back */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                elevation = 0.dp,
                modifier = Modifier.statusBarsPadding(),
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
        ) {
            SearchField(searchQuery = searchQuery)

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 40.dp),
            ) {
                items(filteredTopics) { topic ->
                    val index = allTopics.indexOf(topic)
                    val isBookmarked = bookmarkedStates.getOrNull(index) ?: true

                    TopicCard(
                        topic = topic.name,
                        // subtitle = topic.description ?: "",
                        subtitle = "Description need to be added",
                        isBookmarked = isBookmarked,
                        onBookmarkClick = {
                            if (index != -1) bookmarkedStates[index] = !bookmarkedStates[index]
                        },
                        onCardClick = {
                            Log.i("TopicScreen", "Topic clicked: ${topic.name}")
                            onTopicClick(topic.name)
                        },
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
        color = Grey,
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
                    .background(Color(0xFFFFE0B2), CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Filled.Search, contentDescription = "Icon")
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = topic,
                    style = MaterialTheme.typography.h4.copy(
                        color = Color(0xFF0F2851),
                        lineHeight = (18 * 1.3).sp,
                        letterSpacing = (18 * 0.012).sp,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.caption.copy(
                        lineHeight = (18 * 1.3).sp,
                        letterSpacing = (18 * 0.012).sp,
                        color = Color.Black.copy(alpha = 0.67f),
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Image(
                painter = if (isBookmarked) {
                    painterResource(Res.drawable.ic_bookmark)
                } else {
                    painterResource(Res.drawable.ic_bookmark_filled)
                },
                contentDescription = "Bookmark icon",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBookmarkClick() },
                colorFilter = ColorFilter.tint(Cream),
            )
        }
    }
}

@Composable
fun SearchField(
    searchQuery: MutableState<String>,
) {
    TextField(
        value = searchQuery.value,
        onValueChange = { searchQuery.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .padding(4.dp),
        placeholder = {
            Text(
                "Search Kotlin terms...",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.DarkGray,
            )
        },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color.DarkGray)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFE0E0E0),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.DarkGray,
            textColor = Color.DarkGray,
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
        textStyle = MaterialTheme.typography.subtitle1.copy(
            color = Color.DarkGray,
            lineHeight = 24.sp,
            letterSpacing = .5.sp,
        ),
    )
}