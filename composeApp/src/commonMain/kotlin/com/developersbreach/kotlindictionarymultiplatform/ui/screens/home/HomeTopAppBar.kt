package com.developersbreach.kotlindictionarymultiplatform.ui.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import kotlindictionarymultiplatform.composeapp.generated.resources.menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.developersbreach.designsystem.components.KdIconButton
import com.developersbreach.designsystem.components.KdText
import com.developersbreach.designsystem.components.KdTopAppBar
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar() {
    KdTopAppBar(
        title = {
            KdText(
                text = "Kotlin Dictionary",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        navigationIcon = {
            KdIconButton(
                onClick = {},
                modifier = Modifier,
                contentDescription = stringResource(Res.string.menu),
                iconModifier = Modifier,
                imageVector = Icons.Default.Menu,
            )
        },
    )
}