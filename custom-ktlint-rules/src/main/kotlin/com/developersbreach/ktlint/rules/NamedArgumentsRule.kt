package com.developersbreach.ktlint.rules

import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import com.pinterest.ktlint.rule.engine.core.api.ElementType
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType

class NamedArgumentsRule : Rule(
    ruleId = RuleId("custom-ktlint-rules:named-arguments"),
    about = About(
        maintainer = "All call-site arguments must use explicit names",
    )
) {
    @Deprecated("Marked for removal in Ktlint 2.0. Please implement interface RuleAutocorrectApproveHandler.")
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        // only look at call-site argument lists
        if (node.elementType != ElementType.VALUE_ARGUMENT_LIST ||
            node.treeParent?.elementType != ElementType.CALL_EXPRESSION
        ) {
            return
        }

        // walk up to see if we're inside a @Composable function
        val containingFun = node.psi.getParentOfType<KtNamedFunction>(true) ?: return
        val isComposable = containingFun.annotationEntries
            .any { it.shortName?.asString() == "Composable" }
        if (!isComposable) return

        // enforce named args
        node.getChildren(null)
            .filter { it.elementType == ElementType.VALUE_ARGUMENT }
            .forEach { arg ->
                if (arg.findChildByType(ElementType.EQ) == null) {
                    emit(
                        arg.startOffset,
                        "Call arguments in @Composable functions must be named",
                        false,
                    )
                }
            }
    }
}
