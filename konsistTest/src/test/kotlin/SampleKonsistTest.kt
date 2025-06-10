import com.lemonappdev.konsist.api.Konsist
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SampleKonsistTest {
    @Test
    fun `no direct usage of androidx compose text should be allowed except designSystem`() {
        checkNoDirectUsageExceptAllowed(
            componentName = "androidx.compose.material.Text",
            excludePaths = arrayOf(),
        )
    }
}

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

    assertTrue(offendingFiles.isEmpty()) {
        "Found forbidden imports of $componentName in files:\n" +
                offendingFiles.joinToString("\n") { it.path }
    }
}