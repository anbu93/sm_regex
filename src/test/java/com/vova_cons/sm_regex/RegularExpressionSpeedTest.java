package com.vova_cons.sm_regex;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionSpeedTest {
    private String pattern;
    private long startTime;
    private long standartJavaRegexp;
    private long mySmRegexp;
    private Map<String, Boolean> testSet = new HashMap<>();
    private boolean standartPass = true;
    private boolean myPass = true;

    private StringBuilder notification = new StringBuilder();

    RegularExpressionSpeedTest forRegexp(String pattern){
        this.pattern = pattern;
        return this;
    }
    RegularExpressionSpeedTest accept(String input){
        testSet.put(input, true);
        return this;
    }
    RegularExpressionSpeedTest reject(String input){
        testSet.put(input, false);
        return this;
    }
    RegularExpressionSpeedTest test() throws Exception {
        start();
        testStandart();
        standartJavaRegexp = stop();
        start();
        testMyRegex();
        mySmRegexp = stop();
        System.out.println("My regex: " + mySmRegexp + "\nStandart Java: " + standartJavaRegexp);
        Assert.assertTrue(notification.toString(), myPass && standartPass);
        return this;
    }

    private void start(){
        this.startTime = System.currentTimeMillis();
    }
    private long stop(){
        return System.currentTimeMillis() - this.startTime;
    }

    private void testStandart(){
        notification.append("Standard Java Regex:\n");
        standartPass = true;
        Pattern p = Pattern.compile(pattern);
        for(String inputString : testSet.keySet()) {
            Matcher m = p.matcher(inputString);
            if (m.matches() != testSet.get(inputString)) {
                notification.append("\tTest fail: ")
                        .append(inputString).append(" excepted: ")
                        .append(testSet.get(inputString)).append("\n");
                standartPass = false;
            }
        }
    }
    private void testMyRegex() throws Exception {
        notification.append("My SM-Regex Regex:\n");
        myPass = true;
        RegularExpression regex = RegularExpressionBuilder.create(pattern);
        for(String inputString : testSet.keySet()) {
            if (regex.isMatch(inputString) != testSet.get(inputString)) {
                notification.append("\tTest fail: ")
                        .append(inputString).append(" excepted: ")
                        .append(testSet.get(inputString)).append("\n");
                myPass = false;
            }
        }
    }


    @Test
    public void onceUseSpeedTest() throws Exception {
        forRegexp("1+(01)*1")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .accept("1011")
                .accept("10101011")
                .accept("11110101011")
                .reject("1001")
                .reject("110")
                .reject("011")
                .reject("10101")
                .reject("101")
                .test();
    }
}
