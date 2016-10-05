package com.vova_cons.sm_regex.semantic_model.optimisation_rules;

import com.vova_cons.sm_regex.lexer.TokenType;
import com.vova_cons.sm_regex.parser.syntax_tree.Element;
import com.vova_cons.sm_regex.parser.syntax_tree.NodeElement;

import java.util.Iterator;

public class OrTokenOptimisationRule extends OptimisationRule {
    @Override
    protected boolean isNeedTokenType(TokenType type) {
        return type == TokenType.OR;
    }

    @Override
    protected NodeElement applyRuleForElement(Element element) throws Exception {
        NodeElement parentNode = (NodeElement) element.getParent();
        Element leftHandElement = parentNode.getLeftChild(element);
        Element rightHandElement = parentNode.getRightChild(element);
        NodeElement orNode = new NodeElement(element.getValue());
        if (leftHandElement == null || rightHandElement == null)
            throw new Exception("Syntax tree optimisation phase: or node error of: " + element.getValue().toString());
        parentNode.removeChild(leftHandElement);
        if (leftHandElement.getValue().type == TokenType.OR){
            Iterator<Element> iter = ((NodeElement) leftHandElement).getChilds();
            while(iter.hasNext())
                orNode.appendChild(iter.next());
        } else {
            orNode.appendChild(leftHandElement);
        }
        parentNode.removeChild(rightHandElement);
        if (rightHandElement.getValue().type == TokenType.OR)
            throw new Exception("Syntax tree optimisation phase: double or node error of: "
                    + rightHandElement.getValue().toString());
        else
            orNode.appendChild(rightHandElement);
        parentNode.replaceChild(element, orNode);
        return orNode;
    }
}