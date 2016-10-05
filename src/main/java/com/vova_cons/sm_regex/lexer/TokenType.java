package com.vova_cons.sm_regex.lexer;

public enum TokenType {
    //WS(" \t\n"),
    CHAR("a"),
    OPEN_SCOBS("("),
    CLOSE_SCOBS(")"),
    OPEN_QUADRO("["),
    CLOSE_QUADRO("]"),
    MULTIPLE("*"),
    MULTIPLE_MORE("+"),
    OR("|"),
    //NOT("^"),
    RANGE("-"),
    OPTIONAL("?");

    String patterns;

    TokenType(String pattern){
        this.patterns = pattern;
    }

    static TokenType match(char c){
        for(TokenType type : TokenType.values())
            for (char pattern : type.patterns.toCharArray())
                if (pattern == c)
                    return type;
        return CHAR;
    }


    @Override
    public String toString() {
        return patterns;
    }
}
