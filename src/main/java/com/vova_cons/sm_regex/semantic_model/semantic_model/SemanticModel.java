package com.vova_cons.sm_regex.semantic_model.semantic_model;

import com.vova_cons.sm_regex.semantic_model.semantic_model.components.Component;

public class SemanticModel {
    private Component rootComponent;

    SemanticModel(Component component){
        this.rootComponent = component;
    }

    public boolean isApply(String str){
        InputCharStream stream = new InputCharStream(str);
        return rootComponent.apply(stream);
    }

    public boolean isFullApply(String str){
        InputCharStream stream = new InputCharStream(str);
        return rootComponent.apply(stream) && !stream.hasNext();
    }

    public InputCharStream applyAndGiveStream(String line){
        InputCharStream stream = new InputCharStream(line);
        if (!rootComponent.apply(stream))
            return null;
        return  stream;
    }
}
