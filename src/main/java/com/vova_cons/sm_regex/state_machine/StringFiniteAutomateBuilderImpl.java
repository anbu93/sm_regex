package com.vova_cons.sm_regex.state_machine;

import java.util.HashMap;
import java.util.Map;

class StringFiniteAutomateBuilderImpl implements StringFiniteAutomateBuilder {
    private Map<String, State> stateMap = new HashMap<>();

    @Override
    public void addWay(String fromStateID, String targetStateID, char wayTrigger){
        State fromState = getState(fromStateID);
        State target = getState(targetStateID);
        fromState.addWay(wayTrigger, target);
    }

    @Override
    public StringFiniteAutomate create(String startStateID){
        return new StringFiniteAutomateImpl(getState(startStateID));
    }

    @Override
    public void setFinalState(String stateID){
        getState(stateID).setFinished(true);
    }

    private State getState(String id){
        if (!stateMap.containsKey(id))
            stateMap.put(id, new State());
        return stateMap.get(id);
    }
}