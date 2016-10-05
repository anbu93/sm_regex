package com.vova_cons.sm_regex.finite_automate_regexp;

import com.vova_cons.sm_regex.RegularExpression;
import com.vova_cons.sm_regex.state_machine.StringFiniteAutomate;

public class FiniteAutomateRegexp implements RegularExpression {
    private String pattern;
    private StringFiniteAutomate finiteAutomate;

    FiniteAutomateRegexp(String pattern, StringFiniteAutomate finiteAutomate){
        this.pattern = pattern;
        this.finiteAutomate = finiteAutomate;
    }

    @Override
    public boolean isFullMatch(String line) {
        if (!moveThisLine(line)) return false;
        return finiteAutomate.isCurrentFinished();
    }

    @Override
    public boolean isMatch(String line) {
        moveThisLine(line);
        return finiteAutomate.isFinished();
    }

    @Override
    public String mathedString(String line) {
        moveThisLine(line);
        return finiteAutomate.getWayTrace();
    }

    @Override
    public String unmatchedString(String line) {
        String matched = mathedString(line);
        return line.substring(matched.length());
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    private boolean moveThisLine(String line){
        try {
            for (int i = 0; i < line.length(); i++)
                finiteAutomate.move(line.charAt(i));
        } catch(Exception error){
            return false;
        }
        return true;
    }
}
