package com.developersbreach.kotlindictionarymultiplatform.previews.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.developersbreach.kotlindictionarymultiplatform.previews.fakeTopicDetails
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.DetailTopBar
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@PreviewLightDark
@Composable
fun DetailTopBarPreview() {
    KotlinDictionaryTheme {
        DetailTopBar(title = fakeTopicDetails().topicName)
    }
}