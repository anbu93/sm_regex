package com.vova_cons.sm_regex.state_machine;

import java.util.HashMap;
import java.util.Map;

class State {
    private boolean isFinished;
    private Map<Character, State> ways = new HashMap<>();

    State(){
        this.isFinished = false;
    }

    void addWay(char c, State target){
        ways.put(c, target);
    }

    void setFinished(boolean condition){
        this.isFinished = condition;
    }

    State move(char c){
        return ways.get(c);
    }

    public boolean isFinishState(){
        return isFinished;
    }
}
