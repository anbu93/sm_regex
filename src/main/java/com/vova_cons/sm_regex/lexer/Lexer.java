package com.vova_cons.sm_regex.lexer;

/**
 * Lexer for regular expression.
 * Translation from String to TokenStream
 */
public class Lexer {
    /**
     * Lexer class.
     * Not thrown exceptions. (Ignore errors running)
     * @param pattern input string line (regular expression pattern)
     * @return token stream object
     */
    public static TokenStream parse(String pattern){
        return new TokenStream(pattern);
    }
}
