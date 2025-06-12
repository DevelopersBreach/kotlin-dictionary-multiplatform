package com.developersbreach.kotlindictionarymultiplatform

import com.lemonappdev.konsist.api.Konsist
import kotlin.test.assertTrue

internal fun checkNoDirectUsageExceptAllowed(
    componentName: String,
    excludePaths: Array<String>,
) {
    val offendingFiles = Konsist.scopeFromProject()
        .files
        .filter { file ->
            val normalizedFilePath = file.path.replace("\\", "/").lowercase()
            excludePaths.none { normalizedFilePath.contains(it.replace("\\", "/").lowercase()) }
        }
        .filter { file ->
            file.imports.any { it.name == componentName }
        }

    assertTrue(
        actual = offendingFiles.isEmpty(),
        message = "Found forbidden imports of $componentName in files:\n" + offendingFiles.joinToString("\n") { it.path },
    )
}

internal fun checkAllMaterial3ComponentsMoved(
    allowedComponents: Set<String>,
    excludePaths: Array<String> = emptyArray(),
) {
    val files = Konsist
        .scopeFromProject()
        .files
        .filter { file ->
            val path = file.path.replace("\\", "/").lowercase()
            excludePaths.none { path.contains(it.replace("\\", "/").lowercase()) }
        }

    val allMaterial3Imports = files
        .flatMap { it.imports }
        .map { it.name }
        .filter { it.startsWith("androidx.compose.material3.") }
        .distinct()

    val pending = allMaterial3Imports - allowedComponents

    assertTrue(
        actual = pending.isEmpty(),
        message = "Found pending Material3 components not in design-system:\n" +
            pending.joinToString("\n") { "  • $it" },
    )
}