package com.vova_cons.sm_regex.parser.syntax_tree;

import com.vova_cons.sm_regex.lexer.Token;

public interface Element {
    int NODE = 0;
    int LEAF = 1;

    Element getParent();
    int type();
    Token getValue();

    void setParent(NodeElement element);
}
