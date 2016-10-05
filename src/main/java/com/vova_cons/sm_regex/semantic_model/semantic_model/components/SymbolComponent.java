package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import com.vova_cons.sm_regex.semantic_model.semantic_model.InputCharStream;

class SymbolComponent implements Component {
    private char character;

    public SymbolComponent(char value) {
        this.character = value;
    }

    @Override
    public boolean apply(InputCharStream inputCharStream) {
        int save = inputCharStream.save();
        if (!inputCharStream.hasNext())
            return false;
        char input = inputCharStream.next();
        if (input == character)
            return true;
        inputCharStream.reset(save);
        return false;
    }
}
