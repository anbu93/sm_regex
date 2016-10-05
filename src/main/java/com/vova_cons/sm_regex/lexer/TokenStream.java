package com.vova_cons.sm_regex.lexer;

import java.util.LinkedList;
import java.util.List;

public class TokenStream {
    private List<Token> tokens = new LinkedList<>();
    private int current = 0;

    public TokenStream(String pattern){
        int number = 1;
        for(char c : pattern.toCharArray())
            tokens.add(new Token(c, TokenType.match(c), number++));
    }

    public Token next(){
        return tokens.get(current++);
    }

    public boolean hasNext(){
        return current < tokens.size();
    }

    public Token seek(){
        return tokens.get(current);
    }

    public int save(){
        return current;
    }

    public void reset(int save){
        this.current = save;
    }
}
