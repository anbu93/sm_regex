package com.vova_cons.sm_regex.semantic_model.semantic_model;

import com.vova_cons.sm_regex.lexer.Lexer;
import com.vova_cons.sm_regex.parser.syntax_tree.SyntaxTree;
import com.vova_cons.sm_regex.parser.syntax_tree.SyntaxTreeParser;
import com.vova_cons.sm_regex.semantic_model.SyntaxTreeOptimizer;
import org.junit.Assert;
import org.junit.Test;

public class SemanticModelTranslatorTest {
    private SemanticModel semanticModel;
    private StringBuilder notification;
    private boolean isCorrect;

    private SemanticModelTranslatorTest forRegex(String pattern) throws Exception {
        SyntaxTree tree = SyntaxTreeParser.parseSyntaxTree(Lexer.parse(pattern));
        SyntaxTreeOptimizer.optimize(tree);
        semanticModel = new SemanticModelTranslator(tree).translate();
        notification = new StringBuilder();
        notification.append("pattern: ").append(pattern).append("\n");
        isCorrect = true;
        return this;
    }

    private SemanticModelTranslatorTest accept(String input){
        if (!semanticModel.isApply(input)) {
            notification.append("\t- ")
                    .append("not accepted: ")
                    .append(input).append("\n");
            isCorrect = false;
        } else
            notification.append("\t+ passed: ").append(input).append("\n");
        return this;
    }

    private SemanticModelTranslatorTest reject(String input){
        if(semanticModel.isApply(input)) {
            notification.append("\t- ")
                    .append("not rejected: ")
                    .append(input).append("\n");
            isCorrect = false;
        } else
            notification.append("\t+ passed: ").append(input).append("\n");
        return this;
    }

    private SemanticModelTranslatorTest test(){
        Assert.assertTrue(notification.toString(), isCorrect);
        return this;
    }


    @Test
    public void testTranslatorCreateModelNotNull() throws Exception {
        forRegex("abc");
        Assert.assertNotNull(semanticModel);
    }

    @Test
    public void testTranslateSimpleRegex() throws Exception {
        forRegex("abc")
                .accept("abc")
                .accept("abcdef")
                .reject("ac")
                .reject("bcdefg")
                .reject("bac")
                .test();
    }

    @Test
    public void testOptionalConstructionRegex() throws Exception {
        forRegex("ab?c")
                .accept("abc")
                .accept("ac")
                .accept("abcdef")
                .reject("abbc")
                .reject("bc")
                .test();
    }

    @Test
    public void testSkobsConstruction() throws Exception {
        forRegex("a(bcd)ef")
                .accept("abcdef")
                .accept("abcdefg")
                .reject("aef")
                .reject("abcef")
                .test();
    }

    @Test
    public void testOptionalSkobsConstruction() throws Exception {
        forRegex("a(bc)?d")
                .accept("ad")
                .accept("abcd")
                .reject("abc")
                .reject("abd")
                .test();
    }

    @Test
    public void testSelectorConstruction() throws Exception {
        forRegex("a[bc]d")
                .accept("abd")
                .accept("acd")
                .reject("ad")
                .reject("abcd")
                .test();
    }

    @Test
    public void testOptionalSelectorConstruction() throws Exception {
        forRegex("a[bc]?d")
                .accept("abd")
                .accept("acd")
                .accept("ad")
                .reject("abcd")
                .test();
    }

    @Test
    public void testMultiple() throws Exception {
        forRegex("ab*c")
                .accept("ac")
                .accept("abc")
                .accept("abbc")
                .accept("abbbbbbbc")
                .reject("adc")
                .test();
    }

    @Test
    public void testMultipleMore() throws Exception {
        forRegex("ab+c")
                .accept("abc")
                .accept("abbc")
                .accept("abbbbbbbbc")
                .reject("ac")
                .reject("abbb")
                .test();
    }
}