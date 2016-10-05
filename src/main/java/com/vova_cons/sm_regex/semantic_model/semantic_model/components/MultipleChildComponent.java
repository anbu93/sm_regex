package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import java.util.LinkedList;
import java.util.List;

abstract class MultipleChildComponent implements Component {
    private List<Component> childComponentsList = new LinkedList<>();

    public void add(Component component){
        this.childComponentsList.add(component);
    }

    protected List<Component> getChilds(){
        return this.childComponentsList;
    }
}
