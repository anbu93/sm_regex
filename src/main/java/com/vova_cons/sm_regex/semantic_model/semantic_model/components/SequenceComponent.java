package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import com.vova_cons.sm_regex.semantic_model.semantic_model.InputCharStream;

class SequenceComponent extends MultipleChildComponent {
    @Override
    public boolean apply(InputCharStream inputCharStream) {
        int savePoint = inputCharStream.save();
        for(Component component : getChilds())
            if(!component.apply(inputCharStream)) {
                inputCharStream.reset(savePoint);
                return false;
            }
        return true;
    }
}
