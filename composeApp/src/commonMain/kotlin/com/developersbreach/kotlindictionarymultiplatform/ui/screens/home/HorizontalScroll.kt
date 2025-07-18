package com.developersbreach.kotlindictionarymultiplatform.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.developersbreach.designsystem.components.KdText
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.Topic
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.view_all
import org.jetbrains.compose.resources.stringResource

@Composable
fun HorizontalScroll(
    title: String,
    topics: List<Topic>,
    onViewAllClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        KdText(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier,
        )
        Spacer(modifier = Modifier.weight(1f))
        KdText(
            text = stringResource(Res.string.view_all),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .clickable { onViewAllClick() },
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow {
        items(topics) { topics ->
            HomeCard(label = topics.name)
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}