package com.developersbreach.kotlindictionarymultiplatform.previews.topic

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicTopBar
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@PreviewLightDark
@Composable
fun TopicTopBarPreview() {
    KotlinDictionaryTheme {
        TopicTopBar()
    }
}