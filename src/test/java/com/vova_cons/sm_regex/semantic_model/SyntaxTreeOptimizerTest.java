package com.vova_cons.sm_regex.semantic_model;

import com.vova_cons.sm_regex.lexer.Lexer;
import com.vova_cons.sm_regex.lexer.TokenStream;
import com.vova_cons.sm_regex.parser.syntax_tree.SyntaxTree;
import com.vova_cons.sm_regex.parser.syntax_tree.SyntaxTreeParser;
import org.junit.Assert;
import org.junit.Test;

public class SyntaxTreeOptimizerTest {
    SyntaxTree createSyntaxTree(String pattern){
        TokenStream tokenStream = Lexer.parse(pattern);
        return SyntaxTreeParser.parseSyntaxTree(tokenStream);
    }

    private void testOptimize(String pattern, String excepted) throws Exception {
        SyntaxTree tree = createSyntaxTree(pattern);
        try {
            SyntaxTreeOptimizer.optimize(tree);
        } catch(Exception e){
            throw new Exception(e.getMessage() + "\nTree: " + tree.toString());
        }
        Assert.assertEquals(excepted, tree.toString());
    }

    @Test
    public void testMultipleOr() throws Exception {
        SyntaxTree tree = createSyntaxTree("a|b|c|d");
        SyntaxTreeOptimizer.optimize(tree);
        Assert.assertEquals("{|{abcd}}", tree.toString());
    }

    @Test
    public void testSelectorConstruction() throws Exception {
        testOptimize("a[bcd]", "{a[{bcd}}");
    }

    @Test
    public void testSkobsConstruction() throws Exception {
        testOptimize("a(bcd)", "{a({bcd}}");
    }

    @Test
    public void testMultiIncludeSkobs() throws Exception {
        testOptimize("a((b(c))d)", "{a({({b({c}}d}}");
    }


    @Test
    public void testMultipleConstruction() throws Exception {
        testOptimize("ab*", "{a*{b}}");
    }

    @Test
    public void testMultiIncludeMultipleConstruction() throws Exception {
        testOptimize("(a*b)*", "{*{({*{a}b}}}");
    }

    @Test
    public void testMultipleMoreConstruction() throws Exception {
        testOptimize("ab+", "{a+{b}}");
        testOptimize("ab+c", "{a+{b}c}");
    }

    @Test
    public void testRangeConstruction() throws Exception {
        testOptimize("a-z", "{-{az}}");
    }

    @Test
    public void testRangeInSelector() throws Exception {
        testOptimize("[A-Za-z]", "{[{-{AZ}-{az}}}");
    }

    @Test
    public void testOptionalConstruction() throws Exception {
        testOptimize("ab?c", "{a?{b}c}");
    }

    @Test
    public void testMultipleSkobsConstruction() throws Exception {
        testOptimize("a(bcd)*a", "{a*{({bcd}}a}");
    }

    @Test
    public void testMultipleSelectorConstruction() throws Exception {
        testOptimize("a[bcd]*e", "{a*{[{bcd}}e}");
    }

    @Test
    public void testHardCombinedConstruction() throws Exception {
        testOptimize("a[bcd]*(abc)|(cba)", "{a*{[{bcd}}|{({abc}({cba}}}");
    }

    @Test
    public void testEmailRegexp() throws Exception {
        String pattern = "[A-Za-z0-9]+@([a-z]+.)+[a-z]+";
        String exceptedTree = "{[{A-Za-z0-9}+@({[{a-z}+.}+[{a-z}+}";
        String exceptedOptimisedTree = "{+{[{-{AZ}-{az}-{09}}}@+{({+{[{-{az}}}.}}+{[{-{az}}}}";
        testOptimize(pattern, exceptedOptimisedTree);
    }
}