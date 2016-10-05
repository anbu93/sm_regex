package com.vova_cons.sm_regex.lexer;

import org.junit.Assert;
import org.junit.Test;

public class LexerTest {
    private TokenStream tokenStream;
    private Token[] exceptedTokens;


    private LexerTest where(String pattern){
        tokenStream = Lexer.parse(pattern);
        return this;
    }
    private LexerTest excepted(Token[] excepted){
        this.exceptedTokens = excepted;
        return this;
    }
    private void test() throws Exception {
        for(int i = 0; i < exceptedTokens.length; i++)
            exceptedTokens[i].number = i+1;
        StringBuilder excepted = new StringBuilder();
        StringBuilder actual = new StringBuilder();
        while(tokenStream.hasNext())
            actual.append(tokenStream.next().toString());
        for(Token token : exceptedTokens)
            excepted.append(token);
        Assert.assertEquals(excepted.toString(), actual.toString());
    }

    @Test
    public void testParse() throws Exception {
        where("x[abc]*")
                .excepted(new Token[]{
                new Token('x', TokenType.CHAR, 1),
                new Token('[', TokenType.OPEN_QUADRO, 2),
                new Token('a', TokenType.CHAR, 3),
                new Token('b', TokenType.CHAR, 4),
                new Token('c', TokenType.CHAR, 5),
                new Token(']', TokenType.CLOSE_QUADRO, 6),
                new Token('*', TokenType.MULTIPLE, 7)
                }).test();
    }

    @Test
    public void testSimpleRegexp() throws Exception {
        where("abc").excepted(new Token[]{
                new Token('a', TokenType.CHAR, 1),
                new Token('b', TokenType.CHAR, 2),
                new Token('c', TokenType.CHAR, 3)
        }).test();
    }

    @Test
    public void testMultipleSkobsRegexp() throws Exception {
        where("(ab)*").excepted(new Token[]{
                new Token('(', TokenType.OPEN_SCOBS, 1),
                new Token('a', TokenType.CHAR, 2),
                new Token('b', TokenType.CHAR, 3),
                new Token(')', TokenType.CLOSE_SCOBS, 4),
                new Token('*', TokenType.MULTIPLE, 5)
        }).test();
    }

    @Test
    public void testOptional() throws Exception {
        where("ab?").excepted(new Token[]{
                new Token('a', TokenType.CHAR),
                new Token('b', TokenType.CHAR),
                new Token('?', TokenType.OPTIONAL)
        }).test();
    }

    @Test
    public void testQuadroSkobs() throws Exception {
        where("[ab]").excepted(new Token[] {
                new Token('[', TokenType.OPEN_QUADRO),
                new Token('a', TokenType.CHAR),
                new Token('b', TokenType.CHAR),
                new Token(']', TokenType.CLOSE_QUADRO)
        }).test();
    }
}