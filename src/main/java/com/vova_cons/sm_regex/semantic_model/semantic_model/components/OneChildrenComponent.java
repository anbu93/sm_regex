package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

abstract class OneChildrenComponent implements Component {
    private Component component;

    public void setComponent(Component component){
        this.component = component;
    }

    protected Component getChild(){
        return this.component;
    }
}
