package com.developersbreach.ktlint.rules

import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import com.pinterest.ktlint.rule.engine.core.api.ElementType
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class FunctionBlockBodyRule : Rule(
    ruleId = RuleId("custom-ktlint-rules:function-block-body"),
    about = About(
        maintainer = "Functions must use a block body with explicit return and braces",
    ),
) {
    @Deprecated("Marked for removal in Ktlint 2.0. Please implement interface RuleAutocorrectApproveHandler.")
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        if (node.elementType == ElementType.FUN) {
            val hasEq = node.findChildByType(ElementType.EQ) != null
            val hasBlock = node.findChildByType(ElementType.BLOCK) != null
            if (hasEq && !hasBlock) {
                // report at the '=' sign
                node.findChildByType(ElementType.EQ)?.let { eq ->
                    emit(
                        eq.startOffset,
                        "Functions must use a block body with explicit return and braces",
                        false,
                    )
                }
            }
        }
    }
}