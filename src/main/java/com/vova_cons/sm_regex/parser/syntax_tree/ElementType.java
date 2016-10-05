package com.vova_cons.sm_regex.parser.syntax_tree;

public enum ElementType {
    SEQUENCE,
    SELECTOR,
    MULTIPLE_MODIFICATOR,
    MULTIPLE_MORE_MODIFICATOR,
    OPTIONAL_MODIFICATOR,
    TOKEN;

    //public abstract Element create(TokenStream stream);
}
