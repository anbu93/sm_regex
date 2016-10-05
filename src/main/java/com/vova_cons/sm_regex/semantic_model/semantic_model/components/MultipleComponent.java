package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import com.vova_cons.sm_regex.semantic_model.semantic_model.InputCharStream;

class MultipleComponent extends OneChildrenComponent {
    @Override
    public boolean apply(InputCharStream inputCharStream) {
        if(getChild() == null) return true; // may be return false or throw exception? хз
        while(getChild().apply(inputCharStream));
        return true;
    }
}
