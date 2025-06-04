package com.developersbreach.kotlindictionarymultiplatform.previews.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.developersbreach.kotlindictionarymultiplatform.previews.fakeTopicDetails
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.SyntaxSection
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@PreviewLightDark
@Composable
fun SyntaxSectionPreview() {
    KotlinDictionaryTheme {
        SyntaxSection(topic = fakeTopicDetails())
    }
}