package com.vova_cons.sm_regex;

import com.vova_cons.sm_regex.model_regexp.DomModelRegexpBuilder;
import com.vova_cons.sm_regex.finite_automate_regexp.FiniteAutomateRegexpBuilder;

/**
 * Regular expression builder
 */
public class RegularExpressionBuilder {
    public static final int FINITE_AUTOMATE_TYPE = 0;
    public static final int DOM_MODEL_TYPE = 1;

    private static int type = DOM_MODEL_TYPE;

    /**
     * Create regular expression object
     * Used pattern fabric method [GoF]
     * @param pattern regex pattern
     * @return RegularExpression interface implement object.
     */
    public static RegularExpression create(String pattern) throws Exception {
        switch(type) {
            case FINITE_AUTOMATE_TYPE:
                return FiniteAutomateRegexpBuilder.create(pattern);
            case DOM_MODEL_TYPE:
                return DomModelRegexpBuilder.create(pattern);
            default:
                throw new RegularExpressionBuildingException(
                        pattern,
                        "undefined regexp type for builder: " + type
                );
        }
    }

    /**
     * Set created regular expression type.
     * @param type new type for building
     */
    public static void setType(int type){
        RegularExpressionBuilder.type = type;
    }
}
