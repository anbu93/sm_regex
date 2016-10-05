package com.vova_cons.sm_regex.state_machine;

/**
 * Finite automate creator (Pattern "Builder" GoF)
 */
public interface StringFiniteAutomateBuilder {
    /** Create string finite automate builder object.
     * @return StringFiniteAutomateBuilder interface implemented object */
    static StringFiniteAutomateBuilder createBuilder(){
        return new StringFiniteAutomateBuilderImpl();
    }

    /** Add way in state machine (fromState -> targetState for trigger)
     * @param fromStateID source state identifier
     * @param targetStateID target state identifier
     * @param wayTrigger trigger for way.
     * WARNING: If one of states identifier does not found in state map, this state has been created (not throwing exception) */
    void addWay(String fromStateID, String targetStateID, char wayTrigger);

    /** Mark state as finished
     * @param stateID finished marked state identifier
     * WARINING: don't thrown exception if this state doesn't exists! State has been created automatically!
     */
    void setFinalState(String stateID);

    /** Create finite automate for this start state
     * @param startStateID finite automate start state identifier
     * @return created FiniteAutomate object
     * WARNING: not thrown exceptions!
     * If you set uncorrected start state, automate has been created, and his have been only one state! */
    StringFiniteAutomate create(String startStateID);
}
