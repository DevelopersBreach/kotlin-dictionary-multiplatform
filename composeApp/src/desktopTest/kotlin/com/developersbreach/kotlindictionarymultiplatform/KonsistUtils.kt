package com.developersbreach.kotlindictionarymultiplatform

import com.lemonappdev.konsist.api.Konsist
import kotlin.test.assertTrue

fun checkNoDirectUsageExceptAllowed(
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