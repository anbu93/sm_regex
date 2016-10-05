package com.vova_cons.sm_regex.semantic_model.optimisation_rules;

import com.vova_cons.sm_regex.lexer.TokenType;
import com.vova_cons.sm_regex.parser.syntax_tree.Element;
import com.vova_cons.sm_regex.parser.syntax_tree.NodeElement;

public class ModifierOptimisationRule extends OptimisationRule {

    @Override
    protected boolean isNeedTokenType(TokenType type) {
        return type == TokenType.MULTIPLE ||
                type == TokenType.MULTIPLE_MORE ||
                type == TokenType.OPTIONAL;
    }

    @Override
    protected NodeElement applyRuleForElement(Element modifierElement) throws Exception {
        NodeElement parentNode = (NodeElement) modifierElement.getParent();
        Element leftHandElement = parentNode.getLeftChild(modifierElement);
        if (leftHandElement == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Syntax tree optimisation: not left hand element for modifier: ")
                    .append(modifierElement.getValue().toString())
                    .append("\n");
            throw new Exception(sb.toString());
        }
        parentNode.removeChild(leftHandElement);
        NodeElement modifierNode = new NodeElement(modifierElement.getValue());
        parentNode.replaceChild(modifierElement, modifierNode);
        modifierNode.appendChild(leftHandElement);
        return modifierNode;
    }
}
