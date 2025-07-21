package com.developersbreach.kotlindictionarymultiplatform.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developersbreach.designsystem.components.KdScaffold
import com.developersbreach.designsystem.components.KdText
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.Topic
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.topics
import kotlindictionarymultiplatform.composeapp.generated.resources.welcome
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreenUI(
    topics: List<Topic>,
    onViewAllClick: () -> Unit,
) {
    KdScaffold(
        topBar = { HomeTopAppBar() },
        modifier = Modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = innerPadding.calculateTopPadding()),
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            KdText(
                text = stringResource(Res.string.welcome),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier,
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalScroll(
                title = stringResource(Res.string.topics),
                topics = topics,
                onViewAllClick = onViewAllClick,
            )
        }
    }
}