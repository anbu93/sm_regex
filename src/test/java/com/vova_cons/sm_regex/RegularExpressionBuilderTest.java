package com.vova_cons.sm_regex;

import com.vova_cons.sm_regex.RegularExpression;
import com.vova_cons.sm_regex.RegularExpressionBuilder;
import com.vova_cons.sm_regex.RegularExpressionBuildingException;
import com.vova_cons.sm_regex.model_regexp.DomModelRegexp;
import com.vova_cons.sm_regex.finite_automate_regexp.FiniteAutomateRegexp;
import org.junit.Test;

public class RegularExpressionBuilderTest {
    private static final String pattern = "abc";

    @Test
    public void testCreateDefault() throws Exception {
       testCreateDomModelRegexp();
    }

    @Test
    public void testCreateDomModelRegexp() throws Exception {
        RegularExpressionBuilder.setType(RegularExpressionBuilder.DOM_MODEL_TYPE);
        RegularExpression regexp = RegularExpressionBuilder.create(pattern);
        if (!(regexp instanceof DomModelRegexp))
            throw new Exception("Fail test! Type: " + regexp.getClass().getName());
    }

    @Test
    public void testCreateFiniteAutomateRegexp() throws Exception {
        RegularExpressionBuilder.setType(RegularExpressionBuilder.FINITE_AUTOMATE_TYPE);
        RegularExpression regexp = RegularExpressionBuilder.create(pattern);
        if (!(regexp instanceof FiniteAutomateRegexp))
            throw new Exception("Test fail! Type: " + regexp.getClass().getName());
    }

    @Test
    public void testUncorrectedTypeSet() throws Exception {
        RegularExpressionBuilder.setType(-1);
        try {
            RegularExpressionBuilder.create(pattern);
            throw new Exception("Test fail! not thrown exception for uncorrected type set");
        } catch(RegularExpressionBuildingException exceptedError) {}
    }
}