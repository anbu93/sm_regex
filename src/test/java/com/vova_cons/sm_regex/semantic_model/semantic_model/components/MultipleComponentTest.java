package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import com.vova_cons.sm_regex.semantic_model.semantic_model.InputCharStream;
import org.junit.Before;
import org.junit.Test;

public class MultipleComponentTest {

    private MultipleComponent component;
    private InputCharStream stream;

    @Before
    public void setUp(){
        component = new MultipleComponent();
        stream = new InputCharStream("01234567890");
    }

    @Test
    public void acceptTrueComponentMockTest() throws Exception {
        component.setComponent(new ComponentMock(true, 8));
        //Assert.assertTrue(component.isApply(stream)); // TODO: 05.10.2016 it's not testable
    }

    @Test
    public void acceptFalseComponentMockTest() throws Exception {
        component.setComponent(new ComponentMock(false, 8));
        //Assert.assertTrue(component.isApply(stream));
    }
}