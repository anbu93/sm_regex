package com.vova_cons.sm_regex.state_machine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringFiniteAutomateImplTest {
    private StringFiniteAutomate machine;
    private static final char triggerA = 'a';
    private static final char triggerB = 'b';
    private static final char triggerC = 'c';

    @Before
    public void setUp() throws Exception {
        StringFiniteAutomateBuilder builder = StringFiniteAutomateBuilder.createBuilder();
        builder.addWay("start", "A", triggerA);
        builder.addWay("start", "B", triggerB);
        builder.addWay("A", "C", triggerC);
        builder.addWay("B", "C", triggerC);
        builder.addWay("C", "A", triggerA);
        builder.setFinalState("C");
        machine = builder.create("start");
    }

    @Test
    public void testMoveToFinishFromWayAC() throws Exception {
        machine.move(triggerA);
        machine.move(triggerC);
        Assert.assertTrue(machine.isFinished());
    }

    @Test
    public void testMoveToFinishFromWayBC() throws Exception {
        machine.move(triggerB);
        machine.move(triggerC);
        Assert.assertTrue(machine.isFinished());
    }

    @Test
    public void testGetWayTraceForWayAC() throws Exception {
        machine.move(triggerA);
        machine.move(triggerC);
        Assert.assertEquals(
                String.valueOf(triggerA) + triggerC,
                machine.getWayTrace());
    }

    @Test
    public void testGetWayTraceForWayBC() throws Exception {
        machine.move(triggerB);
        machine.move(triggerC);
        Assert.assertEquals(
                String.valueOf(triggerB) + triggerC,
                machine.getWayTrace());
    }

    @Test
    public void testMoveForUndefinedWayStartToStateC() throws Exception {
        boolean exceptionTrowed = false;
        try {
            machine.move(triggerC);
        } catch (Exception excepted){
            exceptionTrowed = true;
        }
        Assert.assertTrue(exceptionTrowed);
    }

    @Test
    public void testMoveAfterReachedFinishState() throws Exception {
        String excepted = String.valueOf(triggerA) + triggerC;
        machine.move(triggerA);
        machine.move(triggerC);
        machine.move(triggerA);
        Assert.assertTrue(machine.isFinished());
        Assert.assertEquals(excepted, machine.getWayTrace());
    }

    @Test
    public void testReReachFinishState() throws Exception {
        String excepted = String.valueOf(triggerA) + triggerC + triggerA + triggerC;
        machine.move(triggerA);
        machine.move(triggerC);
        machine.move(triggerA);
        machine.move(triggerC);
        Assert.assertEquals(excepted, machine.getWayTrace());
    }

    @Test
    public void testReReachFinishStateAfterCrashMachine() throws Exception {
        String excepted = String.valueOf(triggerA) + triggerC;
        machine.move(triggerA);
        machine.move(triggerC);
        Assert.assertTrue(machine.isFinished());
        machine.move(triggerA);
        boolean isExceptionTrowed = false;
        try {
            machine.move(triggerB);
        } catch (Exception exceptedError){
            isExceptionTrowed = true;
        }
        Assert.assertTrue("Exception not trowed", isExceptionTrowed);
        machine.move(triggerC);
        Assert.assertEquals(excepted, machine.getWayTrace());
    }

    @Test
    public void testCurrentStateIsFinished() throws Exception {
        Assert.assertFalse(machine.isCurrentFinished());
        machine.move(triggerA);
        machine.move(triggerC);
        Assert.assertTrue(machine.isCurrentFinished());

    }
}