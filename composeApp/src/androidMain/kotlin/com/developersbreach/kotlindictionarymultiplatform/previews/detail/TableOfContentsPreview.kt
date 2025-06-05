package com.developersbreach.kotlindictionarymultiplatform.previews.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.developersbreach.kotlindictionarymultiplatform.previews.faketopiclist
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.TableOfContents
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@PreviewLightDark
@Composable
fun TableOfContentsPreview() {
    KotlinDictionaryTheme {
        TableOfContents(
            items = faketopiclist,
            onClick = {},
        )
    }
}