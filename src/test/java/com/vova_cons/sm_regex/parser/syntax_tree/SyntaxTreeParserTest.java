package com.vova_cons.sm_regex.parser.syntax_tree;

import com.vova_cons.sm_regex.lexer.Lexer;
import org.junit.Assert;
import org.junit.Test;

public class SyntaxTreeParserTest {

    private SyntaxTree createSyntaxTree(String pattern){
        SyntaxTree syntaxTree = SyntaxTreeParser.parseSyntaxTree(Lexer.parse(pattern));
        return syntaxTree;
    }

    private void testPattern(String where, String excepted) throws Exception {
        SyntaxTree syntaxTree = createSyntaxTree(where);
        Assert.assertEquals(excepted, syntaxTree.getPattern());
    }

    private void testToString(String where, String excepted) throws Exception {
        SyntaxTree syntaxTree = createSyntaxTree(where);
        Assert.assertEquals(excepted, syntaxTree.toString());
    }

    @Test
    public void testParseSimpleConstruction() throws Exception {
        testPattern("abc", "abc}");
    }

    @Test
    public void testParseSelectorConstruction() throws Exception {
        testPattern("a[bc]+", "a[bc}+}");
    }

    @Test
    public void testParseSkobsConstruction() throws Exception {
        testPattern("a(bc)(df)", "a(bc}(df}}");
    }

    @Test
    public void testParseCombinedPattern() throws Exception {
        testPattern("a[bcd]*(ef)g", "a[bcd}*(ef}g}");
    }

    @Test
    public void testParseCombinedHardPattern() throws Exception {
        testPattern("a[bcd]*(abc)|(cba)", "a[bcd}*(abc}|(cba}}");
    }

    @Test
    public void testParseEmailPattern() throws Exception {
        testPattern("[A-Za-z0-9]+@[a-z](+\\.[a-z]+)+",
                "[A-Za-z0-9}+@[a-z}(+\\.[a-z}+}+}");
    }


    @Test
    public void testToStringSimpleRegexp() throws Exception {
        testToString("abc", "{abc}");
    }

    @Test
    public void testToStringSelectorConstruction() throws Exception {
        testToString("a[bc]+", "{a[{bc}+}");
    }

    @Test
    public void testToStringSkobsConstruction() throws Exception {
        testToString("a(bc)(def)", "{a({bc}({def}}");
    }

    @Test
    public void testToStringCombinedHardConstruction() throws Exception {
        testToString("a[bcd]*(abc)|(cba)", "{a[{bcd}*({abc}|({cba}}");
    }
}