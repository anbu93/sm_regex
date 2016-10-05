package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import com.vova_cons.sm_regex.semantic_model.semantic_model.InputCharStream;

class MultipleMoreComponent extends OneChildrenComponent {

    @Override
    public boolean apply(InputCharStream inputCharStream) {
        if (getChild() == null) return false;
        if (getChild().apply(inputCharStream)){
            while(getChild().apply(inputCharStream));
            return true;
        }
        return false;
    }
}
