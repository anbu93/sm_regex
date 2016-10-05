package com.vova_cons.sm_regex.semantic_model.optimisation_rules;

import com.vova_cons.sm_regex.lexer.TokenType;
import com.vova_cons.sm_regex.parser.syntax_tree.Element;
import com.vova_cons.sm_regex.parser.syntax_tree.NodeElement;

public class RangeTokenOptimisationRule extends OptimisationRule {

    @Override
    protected boolean isNeedTokenType(TokenType type) {
        return type == TokenType.RANGE;
    }

    @Override
    protected NodeElement applyRuleForElement(Element element) throws Exception {
        NodeElement parentNode = (NodeElement) element.getParent();
        Element leftHandElement = parentNode.getLeftChild(element);
        Element rightHandElement = parentNode.getRightChild(element);
        NodeElement rangeNode = new NodeElement(element.getValue());
        if (leftHandElement == null || rightHandElement == null)
            throw new Exception("Syntax tree optimisation phase: range node error of: " + element.getValue().toString());
        parentNode.removeChild(leftHandElement);
        rangeNode.appendChild(leftHandElement);
        parentNode.removeChild(rightHandElement);
        rangeNode.appendChild(rightHandElement);
        parentNode.replaceChild(element, rangeNode);
        return rangeNode;
    }
}
