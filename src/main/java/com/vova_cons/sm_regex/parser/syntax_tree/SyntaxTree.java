package com.vova_cons.sm_regex.parser.syntax_tree;

import com.vova_cons.sm_regex.common.Stack;
import com.vova_cons.sm_regex.lexer.Token;
import com.vova_cons.sm_regex.lexer.TokenType;

import java.util.Iterator;

public class SyntaxTree {
    private NodeElement rootNode;
    private Stack<NodeElement> currentNodeStack = new Stack<>();

    public SyntaxTree(){
        rootNode = new NodeElement();
        currentNodeStack.push(rootNode);
    }

    public void parse(Token token){
        if (token.type == TokenType.OPEN_SCOBS ||
                token.type == TokenType.OPEN_QUADRO){
            NodeElement node = new NodeElement(token);
            currentNodeStack.seek().appendChild(node);
            currentNodeStack.push(node);
        } else if (token.type == TokenType.CLOSE_QUADRO || token.type == TokenType.CLOSE_SCOBS){
            currentNodeStack.pop();
        } else {
            LeafElement leaf = new LeafElement(token);
            currentNodeStack.seek().appendChild(leaf);
        }
    }

    public String getPattern(){
        Stack<Iterator<Element>> stack = new Stack<>();
        Iterator<Element> iterator = rootNode.getChilds();
        stack.push(iterator);
        StringBuilder buffer = new StringBuilder();
        while(stack.size() > 0){
            while(stack.seek().hasNext()){
                Element element = stack.seek().next();
                buffer.append(element.getValue().value);
                if (element.type() == Element.NODE) {
                    stack.push(((NodeElement) element).getChilds());
                }
            }
            buffer.append('}');
            stack.pop();
        }
        return buffer.toString();
    }

    public NodeElement getRootElement(){
        return rootNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Stack<Iterator<Element>> stack = new Stack<>();
        stack.push(rootNode.getChilds());
        sb.append("{");
        while(stack.size() > 0){
            Iterator<Element> iter = stack.seek();
            while(iter.hasNext()){
                Element element = iter.next();
                if (element.type() == Element.NODE){
                    stack.push(((NodeElement)element).getChilds());
                    sb.append(element.getValue().value).append("{");
                    iter = stack.seek();
                } else {
                    sb.append(element.getValue().value);
                }
            }
            sb.append("}");
            stack.pop();
        }
        return sb.toString();
    }

    public void setRootElement(NodeElement node) {
        this.rootNode = node;
    }
}
