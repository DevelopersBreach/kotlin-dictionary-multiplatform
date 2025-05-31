package com.developersbreach.kotlindictionarymultiplatform.previews

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.CodeExampleBox
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@Preview(name = "Code Example Box - Light", showBackground = true)
@Composable
fun CodeExampleBoxLightPreview() {
    KotlinDictionaryTheme(useDarkTheme = false) {
        CodeExampleBox(
            code = sampleCodeSnippet(),
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview(name = "Code Example Box - Dark", showBackground = true)
@Composable
fun CodeExampleBoxDarkPreview() {
    KotlinDictionaryTheme(useDarkTheme = true) {
        CodeExampleBox(
            code = sampleCodeSnippet(),
            modifier = Modifier.padding(16.dp),
        )
    }
}