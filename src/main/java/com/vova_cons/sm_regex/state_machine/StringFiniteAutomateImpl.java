package com.vova_cons.sm_regex.state_machine;

class StringFiniteAutomateImpl implements StringFiniteAutomate {
    private State currentState;
    private State startState;
    private boolean isFinished;
    private boolean isCrashed;
    private StringBuilder wayStack;
    private String lastWayToFinishState;

    StringFiniteAutomateImpl(State startState){
        this.startState = startState;
        reset();
    }

    @Override
    public void reset(){
        this.currentState = startState;
        this.isFinished = false;
        this.wayStack = new StringBuilder();
        this.lastWayToFinishState = null;
        this.isCrashed = false;
    }

    @Override
    public void move(char trigger) throws Exception {
        if(isCrashed) return; // после краша, машина перестает обрабатывать вводы
        State new_state = currentState.move(trigger);
        if (new_state == null) { // такого пути нет из этого состояния
            isCrashed = true;
            throw new Exception("ERROR: not valid way for trigger: " + trigger);
        }
        currentState = new_state;
        wayStack.append(trigger);
        if (currentState.isFinishState()) {
            lastWayToFinishState = wayStack.toString();
            isFinished = true;
        }
    }

    @Override
    public String getWayTrace() {
        return lastWayToFinishState;
    }

    @Override
    public boolean isFinished() {
        return this.isFinished;
    }

    @Override
    public boolean isCurrentFinished() {
        return currentState.isFinishState();
    }
}
