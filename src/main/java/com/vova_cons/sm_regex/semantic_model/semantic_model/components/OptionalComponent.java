package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import com.vova_cons.sm_regex.semantic_model.semantic_model.InputCharStream;

class OptionalComponent extends OneChildrenComponent {
    @Override
    public boolean apply(InputCharStream inputCharStream) {
        getChild().apply(inputCharStream);
        return true;
    }
}
