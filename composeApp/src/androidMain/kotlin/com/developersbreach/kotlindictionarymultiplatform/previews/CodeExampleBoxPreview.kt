package com.developersbreach.kotlindictionarymultiplatform.previews

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.CodeExampleBox
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@PreviewLightDark
@Composable
fun CodeExampleBoxLightPreview() {
    KotlinDictionaryTheme {
        CodeExampleBox(
            code = sampleCodeSnippet(),
            modifier = Modifier.padding(16.dp),
        )
    }
}
