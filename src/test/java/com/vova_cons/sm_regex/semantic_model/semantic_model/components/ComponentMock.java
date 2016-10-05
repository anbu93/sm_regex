package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import com.vova_cons.sm_regex.semantic_model.semantic_model.InputCharStream;

public class ComponentMock implements Component {
    private boolean result;
    private int charEatingCounter;

    public ComponentMock(boolean result, int charEatingCounter) {
        this.result = result;
        this.charEatingCounter = charEatingCounter;
    }


    @Override
    public boolean apply(InputCharStream inputCharStream) {
        for(int i = 0; i < charEatingCounter; i++) {
            if (inputCharStream.hasNext())
                inputCharStream.next();
        }
        return result;
    }
}
