package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import com.vova_cons.sm_regex.semantic_model.semantic_model.InputCharStream;

class SelectorComponent extends MultipleChildComponent {
    @Override
    public boolean apply(InputCharStream inputCharStream) {
        for(Component component : getChilds())
            if(component.apply(inputCharStream))
                return true;
        return false;
    }
}
