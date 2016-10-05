package com.vova_cons.sm_regex.parser;

import com.vova_cons.sm_regex.lexer.Lexer;
import com.vova_cons.sm_regex.lexer.TokenStream;
import org.junit.Test;

public class ParserTest {
    private TokenStream tokenStream;
    private int elementsCount;

    private void where(String pattern){
        tokenStream = Lexer.parse(pattern);
    }
    private void excepted(int countOfElements){

    }

    @Test
    public void testParse() throws Exception {
        Parser.parse(tokenStream);

    }
}