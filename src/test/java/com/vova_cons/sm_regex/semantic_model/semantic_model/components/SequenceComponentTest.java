package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import com.vova_cons.sm_regex.semantic_model.semantic_model.InputCharStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SequenceComponentTest {

    private InputCharStream stream;

    @Before
    public void setUp() {
        stream = new InputCharStream("0123456789");
    }

    @Test
    public void testCorrectApply() throws Exception {
        SequenceComponent sequenceComponent = new SequenceComponent();
        sequenceComponent.add(new ComponentMock(true, 1));
        sequenceComponent.add(new ComponentMock(true, 1));
        sequenceComponent.add(new ComponentMock(true, 1));
        sequenceComponent.add(new ComponentMock(true, 1));
        Assert.assertTrue(sequenceComponent.apply(stream));
        Assert.assertEquals(4, stream.save());
    }

    @Test
    public void testUncorrectApply() throws Exception {
        SequenceComponent sequenceComponent = new SequenceComponent();
        sequenceComponent.add(new ComponentMock(true, 1));
        sequenceComponent.add(new ComponentMock(true, 3));
        sequenceComponent.add(new ComponentMock(false, 1));
        sequenceComponent.add(new ComponentMock(true, 1));
        Assert.assertFalse(sequenceComponent.apply(stream));
        Assert.assertEquals(0, stream.save());
    }
}