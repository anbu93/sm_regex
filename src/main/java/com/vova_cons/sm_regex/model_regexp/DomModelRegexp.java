package com.vova_cons.sm_regex.model_regexp;

import com.vova_cons.sm_regex.RegularExpression;
import com.vova_cons.sm_regex.semantic_model.semantic_model.InputCharStream;
import com.vova_cons.sm_regex.semantic_model.semantic_model.SemanticModel;

public class DomModelRegexp implements RegularExpression {
    private String pattern;
    private SemanticModel semanticModel;

    DomModelRegexp(String pattern, SemanticModel semanticModel){
        this.pattern = pattern;
        this.semanticModel = semanticModel;
    }

    @Override
    public boolean isFullMatch(String line) {
        return semanticModel.isFullApply(line);
    }

    @Override
    public boolean isMatch(String line) {
        return semanticModel.isApply(line);
    }

    @Override
    public String mathedString(String line) {
        InputCharStream stream = semanticModel.applyAndGiveStream(line);
        if (stream == null)
            return line;
        int pos = stream.save();
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < pos; i++)
            result.append(line.charAt(i));
        return result.toString();
    }

    @Override
    public String unmatchedString(String line) {
        InputCharStream stream = semanticModel.applyAndGiveStream(line);
        if (stream == null)
            return line;
        int pos = stream.save();
        StringBuilder result = new StringBuilder();
        for(int i = pos; i < line.length(); i++)
            result.append(line.charAt(i));
        return result.toString();
    }

    @Override
    public String getPattern() {
        return pattern;
    }
}
