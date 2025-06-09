package com.developersbreach.ktlint.rules

import com.pinterest.ktlint.rule.engine.core.api.ElementType
import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.psi.psiUtil.getParentOfType

class MultiParameterPerLineRule : Rule(
    ruleId = RuleId("custom-ktlint-rules:multi-parameter-per-line"),
    about = About(
        maintainer = "Each value argument must be on its own line if there is more than one (only in @Composable functions)",
    )
) {
    @Deprecated("Marked for removal in Ktlint 2.0. Please implement interface RuleAutocorrectApproveHandler.")
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        if (node.elementType != ElementType.VALUE_ARGUMENT_LIST) return

        // limit to @Composable
        val containingFun = node.psi.getParentOfType<KtNamedFunction>(true) ?: return
        if (containingFun.annotationEntries.none { it.shortName?.asString() == "Composable" }) return

        val args = node.getChildren(null)
            .filter { it.elementType == ElementType.VALUE_ARGUMENT }
        if (args.size <= 1) return

        val lines = args.map { it.lineNumber() }.toSet()
        if (lines.size == 1) {
            emit(node.startOffset, "Each parameter should be on its own line", true)

            if (autoCorrect) {
                val psiFactory = KtPsiFactory(node.psi.project)
                args.forEachIndexed { index, argNode ->
                    if (index > 0) {
                        // before each argument except the first, insert a newline + indentation
                        val whiteSpace = psiFactory.createWhiteSpace("\n    ")
                        node.addChild(whiteSpace.node, argNode)
                    }
                }
            }
        }
    }

    private fun ASTNode.lineNumber(): Int {
        val doc = psi.containingFile.viewProvider.document
        return doc?.getLineNumber(startOffset) ?: -1
    }
}