package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.introduction
import org.jetbrains.compose.resources.stringResource

@Composable
fun IntroductionSection(
    topic: KotlinTopicDetails,
) {
    Text(stringResource(Res.string.introduction), style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onPrimary)
    Spacer(modifier = Modifier.height(4.dp))
    Text(text = topic.intro, style = MaterialTheme.typography.bodyMedium)
}