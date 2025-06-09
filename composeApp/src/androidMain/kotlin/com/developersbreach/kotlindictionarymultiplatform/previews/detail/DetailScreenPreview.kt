package com.developersbreach.kotlindictionarymultiplatform.previews.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.developersbreach.kotlindictionarymultiplatform.previews.fakeTopicDetails
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.DetailScreenUI
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.toDetailUi
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@PreviewLightDark
@Composable
fun DetailScreenPreview() {
    KotlinDictionaryTheme {
        DetailScreenUI(
            detailUiState = fakeTopicDetails().toDetailUi(),
            navigateUp = {},
        )
    }
}