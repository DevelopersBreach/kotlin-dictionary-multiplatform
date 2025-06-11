package com.developersbreach.kotlindictionarymultiplatform

import kotlin.test.Test

class DesignSystemTest {

    @Test
    fun `no direct usage of androidx compose Surface should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.Surface",
            excludePaths = arrayOf(
                "design-system/src/commonMain/kotlin/com/developersbreach/designsystem/components/Surface.kt",
            ),
        )
    }

    @Test
    fun `no direct usage of androidx compose scaffold should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.Scaffold",
            excludePaths = arrayOf(
                "design-system/src/commonMain/kotlin/com/developersbreach/designsystem/components/Scaffold.kt",
            ),
        )
    }

    @Test
    fun `no direct usage of androidx compose icon should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.Icon",
            excludePaths = arrayOf(
                "design-system/src/commonMain/kotlin/com/developersbreach/designsystem/components/Icon.kt",
            ),
        )
    }

    @Test
    fun `no direct usage of androidx compose iconButton should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.IconButton",
            excludePaths = arrayOf(
                "design-system/src/commonMain/kotlin/com/developersbreach/designsystem/components/IconButton.kt",
            ),
        )
    }

    @Test
    fun `no direct usage of androidx compose textField should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material3.TextField",
            excludePaths = arrayOf(
                "design-system/src/commonMain/kotlin/com/developersbreach/designsystem/components/TextField.kt",
            ),
        )
    }
}