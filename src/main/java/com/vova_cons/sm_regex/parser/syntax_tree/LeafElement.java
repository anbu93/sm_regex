package com.vova_cons.sm_regex.parser.syntax_tree;

import com.vova_cons.sm_regex.lexer.Token;

public class LeafElement implements Element {
    private NodeElement parent = null;
    private Token token = null;

    public LeafElement(Token token){
        this.token = token;
    }

    @Override
    public Element getParent() {
        return parent;
    }

    @Override
    public int type() {
        return Element.LEAF;
    }

    @Override
    public Token getValue() {
        return token;
    }

    @Override
    public void setParent(NodeElement element) {
        this.parent = element;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LeafElement)
            return ((LeafElement) obj).token.equals(token);
        return false;
    }

    @Override
    public String toString() {
        return token.toString();
    }
}
