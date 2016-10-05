package com.vova_cons.sm_regex;

import org.junit.Assert;
import org.junit.Test;

public class RegularExpressionTest {
    private RegularExpression regex;
    private StringBuilder notification;
    private boolean isCorrect;

    private RegularExpressionTest forRegex(String pattern) throws Exception {
        regex = RegularExpressionBuilder.create(pattern);
        notification = new StringBuilder();
        notification.append("pattern: ").append(pattern).append("\n");
        isCorrect = true;
        return this;
    }

    private RegularExpressionTest accept(String input){
        if (!regex.isMatch(input)) {
            notification.append("\t- not accept: ").append(input).append("\n");
            isCorrect = false;
        } else
            notification.append("\t+ accept: ").append(input).append("\n");
        return this;
    }

    private RegularExpressionTest fullAccept(String input){
        if (!regex.isFullMatch(input)) {
            notification.append("\t- not full accept: ").append(input).append("\n");
            isCorrect = false;
        } else
            notification.append("\t+ full accept: ").append(input).append("\n");
        return this;
    }

    private RegularExpressionTest fullReject(String input){
        if(regex.isFullMatch(input)) {
            notification.append("\t- not full reject: ").append(input).append("\n");
            isCorrect = false;
        } else
            notification.append("\t+ full reject: ").append(input).append("\n");
        return this;
    }

    private RegularExpressionTest reject(String input){
        if(regex.isMatch(input)) {
            notification.append("\t- not reject: ").append(input).append("\n");
            isCorrect = false;
        } else
            notification.append("\t+ reject: ").append(input).append("\n");
        return this;
    }

    private RegularExpressionTest test(){
        Assert.assertTrue(notification.toString(), isCorrect);
        return this;
    }

    @Test
    public void simpleRegexName() throws Exception {
        forRegex("vova")
                .accept("vova")
                .accept("vova kons")
                .reject("ganya")
                .reject("algys")
                .test();
    }

    @Test
    public void simpleRegexABC() throws Exception {
        forRegex("abc")
                .accept("abc")
                .accept("abcdef")
                .reject("ac")
                .reject("bcdefg")
                .reject("bac")
                .test();
    }

    @Test
    public void optional() throws Exception {
        forRegex("ab?c")
                .accept("abc")
                .accept("ac")
                .accept("abcdef")
                .reject("abbc")
                .reject("bc")
                .test();
    }

    @Test
    public void skobs() throws Exception {
        forRegex("a(bcd)ef")
                .accept("abcdef")
                .accept("abcdefg")
                .reject("aef")
                .reject("abcef")
                .test();
    }

    @Test
    public void selector() throws Exception {
        forRegex("a[bc]d")
                .accept("abd")
                .accept("acd")
                .reject("ad")
                .reject("abcd")
                .test();
    }

    @Test
    public void multiple() throws Exception {
        forRegex("ab*c")
                .accept("ac")
                .accept("abc")
                .accept("abbc")
                .accept("abbbbbbbc")
                .reject("adc")
                .test();
    }

    @Test
    public void multipleMore() throws Exception {
        forRegex("ab+c")
                .accept("abc")
                .accept("abbc")
                .accept("abbbbbbbbc")
                .reject("ac")
                .reject("abbb")
                .test();
    }

    @Test
    public void optionalSkobs() throws Exception {
        forRegex("a(bc)?d")
                .accept("ad")
                .accept("abcd")
                .reject("abc")
                .reject("abd")
                .test();
    }

    @Test
    public void optionalSelector() throws Exception {
        forRegex("a[bc]?d")
                .accept("abd")
                .accept("acd")
                .accept("ad")
                .reject("abcd")
                .test();
    }

    @Test
    public void multipleSkobs() throws Exception {
        forRegex("a(bc)*d")
                .accept("abcd")
                .accept("abcbcd")
                .accept("ad")
                .reject("a")
                .reject("abc")
                .test();
    }

    @Test
    public void multipleSelector() throws Exception {
        forRegex("a[bc]*d")
                .accept("ad")
                .accept("abd")
                .accept("acd")
                .accept("abcd")
                .accept("abcbbcd")
                .accept("acccccccd")
                .fullAccept("ad")
                .reject("a")
                .reject("d")
                .reject("abcad")
                .fullReject("abcccbde")
                .test();
    }

    @Test
    public void range() throws Exception {
        forRegex("a-z")
                .accept("a")
                .accept("b")
                .accept("g")
                .accept("z")
                .reject("A")
                .reject("0")
                .reject("1")
                .test();
    }

    @Test
    public void multipleMoreRangeSelector() throws Exception {
        forRegex("[a-z]+")
                .accept("vova")
                .accept("vova93")
                .accept("algys")
                .fullReject("algysRak")
                .reject("Vova")
                .fullReject("vova93")
                .test();
    }

    @Test
    public void multipleMoreTwoRangeSeletor() throws Exception {
        forRegex("[A-Za-z]+")
                .fullAccept("VovaKons")
                .fullAccept("AlgysBuldakovRakamakafo")
                .fullReject("vova93")
                .reject("9")
                .reject("")
                .fullReject("algys rak")
                .test();
    }

    @Test
    public void emailRegexTest() throws Exception {
        forRegex("[A-Za-z0-9_.]+@([a-z]+.)+[a-z]+")
                .fullAccept("vova93@gmail.com")
                .fullAccept("anbu23477@gmail.com")
                .fullAccept("vova_cons@mail.ru")
                .fullAccept("v.a.konstantinov@stud.svfu.ru")
                .fullReject("invalid-mail@box")
                .test();
    }
}