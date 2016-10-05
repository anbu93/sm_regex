package com.vova_cons.sm_regex;

public class RegularExpressionBuildingException extends Exception {
    public RegularExpressionBuildingException(String pattern, String error){
        super("ERROR on building regexp \"" + pattern + "\": " + error);
    }
}
