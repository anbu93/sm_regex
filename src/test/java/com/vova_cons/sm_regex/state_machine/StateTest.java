package com.vova_cons.sm_regex.state_machine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StateTest {
    private State stateA;
    private State stateB;
    private State stateC;
    private char triggerB = 'b';
    private char triggerC = 'c';

    @Before
    public void setUp() throws Exception {
        stateA = new State();
        stateB = new State();
        stateC = new State();
        stateA.addWay(triggerB, stateB);
        stateA.addWay(triggerC, stateC);
        stateB.setFinished(true);
    }

    @Test
    public void testMoveFromAtoB() throws Exception {
        State actual = stateA.move(triggerB);
        Assert.assertEquals(stateB, actual);
    }

    @Test
    public void testMoveFromAtoC() throws Exception {
        State actual = stateA.move(triggerC);
        Assert.assertEquals(stateC, actual);
    }

    @Test
    public void testMoveToFinishState() throws Exception {
        State actual = stateA.move(triggerB);
        Assert.assertTrue(actual.isFinishState());
    }

    @Test
    public void testMoveToNotFinishState() throws Exception {
        State actual = stateA.move(triggerC);
        Assert.assertFalse(actual.isFinishState());
    }

    @Test
    public void testNoWayMove() throws Exception {
        char triggerA = 'a';
        State actual = stateA.move(triggerA);
        Assert.assertEquals(null, actual);
    }
}