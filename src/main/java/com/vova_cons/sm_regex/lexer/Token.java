package com.vova_cons.sm_regex.lexer;

public class Token {
    public char value;
    public TokenType type;
    public int number;

    Token(char value, TokenType type){
        this.value = value;
        this.type = type;
        this.number = 0;
    }

    Token(char value, TokenType type, int number){
        this.value = value;
        this.type = type;
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Token &&
                value == ((Token) obj).value &&
                type == ((Token) obj).type;
    }

    @Override
    public String toString() {
        return "[type=" + type.toString() + ";value=" + value + ";number=" + number+ "]";
    }

    public static Token parse(char c) {
        return new Token(c, TokenType.match(c));
    }
}
