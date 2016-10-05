package com.vova_cons.sm_regex.semantic_model.optimisation_rules;

import com.vova_cons.sm_regex.common.Stack;
import com.vova_cons.sm_regex.lexer.TokenType;
import com.vova_cons.sm_regex.parser.syntax_tree.Element;
import com.vova_cons.sm_regex.parser.syntax_tree.NodeElement;

import java.util.Iterator;

public abstract class OptimisationRule {
    public static OptimisationRule modifierRule = new ModifierOptimisationRule();
    public static OptimisationRule rangeRule = new RangeTokenOptimisationRule();
    public static OptimisationRule orRule = new OrTokenOptimisationRule();

    private Stack<Iterator<Element>> stack = new Stack<>();

    public void apply(Iterator<Element> elementIterator) throws Exception {
        stack.push(elementIterator);
        while(stack.size() > 0){
            while(stack.seek().hasNext()){
                Element element = stack.seek().next();
                if (element.type() == Element.NODE)
                    stack.push(((NodeElement) element).getChilds());
                if (isNeedTokenType(element.getValue().type)) {
                    NodeElement newNode = applyRuleForElement(element);
                    refreshCurrentIterator(newNode);
                }
            }
            stack.pop();
        }
    }

    private void refreshCurrentIterator(NodeElement newNode) throws Exception {
        Iterator<Element> newIterator = ((NodeElement)newNode.getParent()).getChilds();
        while(newIterator.hasNext() && newIterator.next() != newNode);
        stack.pop();
        stack.push(newIterator);
    }

    protected abstract boolean isNeedTokenType(TokenType type);
    protected abstract NodeElement applyRuleForElement(Element element) throws Exception;
}
