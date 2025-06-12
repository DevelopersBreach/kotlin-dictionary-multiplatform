package com.developersbreach.kotlindictionarymultiplatform

import kotlin.test.Test

class DesignSystemTest {

    @Test
    fun `all material3 components have design-system wrappers`() {
        val ignoreComponents = setOf(
            "androidx.compose.material3.Typography",
            "androidx.compose.material3.darkColorScheme",
            "androidx.compose.material3.lightColorScheme",
            "androidx.compose.material3.MaterialTheme",
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.compose.material3.TopAppBarDefaults",
            "androidx.compose.material3.TextFieldDefaults",
        )

        checkAllMaterial3ComponentsMoved(
            allowedComponents = ignoreComponents,
            excludePaths = arrayOf(DESIGN_SYSTEM_PATH),
        )
    }

    @Test
    fun `no direct usage of androidx compose Surface should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.Surface",
            excludePaths = arrayOf("$DESIGN_SYSTEM_PATH/Surface.kt"),
        )
    }

    @Test
    fun `no direct usage of androidx compose scaffold should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.Scaffold",
            excludePaths = arrayOf("$DESIGN_SYSTEM_PATH/Scaffold.kt"),
        )
    }

    @Test
    fun `no direct usage of androidx compose icon should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.Icon",
            excludePaths = arrayOf("$DESIGN_SYSTEM_PATH/Icon.kt"),
        )
    }

    @Test
    fun `no direct usage of androidx compose iconButton should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.IconButton",
            excludePaths = arrayOf("$DESIGN_SYSTEM_PATH/IconButton.kt"),
        )
    }

    @Test
    fun `no direct usage of androidx compose textField should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.TextField",
            excludePaths = arrayOf("$DESIGN_SYSTEM_PATH/TextField.kt"),
        )
    }

    @Test
    fun `no direct usage of androidx compose alertDialog should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.AlertDialog",
            excludePaths = arrayOf("$DESIGN_SYSTEM_PATH/AlertDialog.kt"),
        )
    }

    @Test
    fun `no direct usage of androidx compose textButton should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.TextButton",
            excludePaths = arrayOf("$DESIGN_SYSTEM_PATH/TextButton.kt"),
        )
    }

    @Test
    fun `no direct usage of androidx compose circularProgressIndicator should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.CircularProgressIndicator",
            excludePaths = arrayOf("$DESIGN_SYSTEM_PATH/CircularProgressIndicator.kt"),
        )
    }

    @Test
    fun `no direct usage of androidx compose topAppBar should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.TopAppBar",
            excludePaths = arrayOf("$DESIGN_SYSTEM_PATH/TopAppBar.kt"),
        )
    }

    companion object {
        private const val DESIGN_SYSTEM_PATH =
            "design-system/src/commonMain/kotlin/com/developersbreach/designsystem/components"
    }
}