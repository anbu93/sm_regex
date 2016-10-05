package com.vova_cons.sm_regex.finite_automate_regexp;

import com.vova_cons.sm_regex.RegularExpression;
import com.vova_cons.sm_regex.lexer.Token;
import com.vova_cons.sm_regex.lexer.TokenStream;
import com.vova_cons.sm_regex.lexer.TokenType;
import com.vova_cons.sm_regex.state_machine.StringFiniteAutomateBuilder;

public class FiniteAutomateRegexpBuilder {
    public static RegularExpression create(String pattern){
        return new FiniteAutomateRegexpBuilder(pattern).create();
    }

    private StringFiniteAutomateBuilder builder = StringFiniteAutomateBuilder.createBuilder();
    private String pattern;
    private String startStateID = "0";
    private int currentState = 0;

    private TokenStream stream;

    FiniteAutomateRegexpBuilder(String pattern){
        this.pattern = pattern;
        this.stream = new TokenStream(pattern);
        parse();
    }

    void parse(){
        while(stream.hasNext()){
            Token token = stream.next();
            if (token.type.equals(TokenType.CHAR))
                builder.addWay(String.valueOf(currentState), String.valueOf(currentState+1), token.value);
            currentState++;
        }
    }

    RegularExpression create(){
        builder.setFinalState(String.valueOf(currentState));
        return new FiniteAutomateRegexp(pattern, builder.create(startStateID));
    }
}
