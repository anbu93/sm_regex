package com.vova_cons.sm_regex.common;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StackTest {
    @Test
    public void testPushAndPopOneItem() throws Exception {
        Stack<Integer> stack = new Stack<>();
        Integer value = 13;
        stack.push(value);
        Assert.assertEquals(value, stack.pop());
    }

    @Test
    public void testPushAndPopTenItems() throws Exception {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < 10; i++)
            stack.push(i);
        for(Integer i = 9; i >= 0; i--)
            Assert.assertEquals(i, stack.pop());
    }

    @Test
    public void testSeekMethod() throws Exception {
        Stack<Integer> stack = new Stack<>();
        Integer value = 13;
        stack.push(10);
        stack.push(value);
        Assert.assertEquals(value, stack.seek());
        Assert.assertEquals(2, stack.size());
    }

    @Test
    public void testSizeMethod() throws Exception {
        Stack<Integer> stack = new Stack<>();
        int size = 18;
        for(int i = 0; i < 18; i++)
            stack.add(i);
        Assert.assertEquals(size, stack.size());
    }

    @Test
    public void testPopOnEMptyStack() throws Exception {
        Stack<Integer> stack = new Stack<>();
        Assert.assertEquals(null, stack.pop());
        Assert.assertEquals(null, stack.seek());
        Assert.assertEquals(0, stack.size());
    }

    @Test
    public void testGetItems() throws Exception {
        Stack<Integer> stack = new Stack<>();
        int size = 10;
        for(int i = 0; i < size; i++)
            stack.push(i);
        List<Integer> items = stack.getItems();
        for(Integer i = 0; i < size; i++)
            Assert.assertEquals(i, items.get(i));
    }

    @Test
    public void testGetItemsHasClonedList() throws Exception {
        Stack<Integer> stack = new Stack<>();
        int size = 10;
        for(int i = 0; i < size; i++)
            stack.push(i);
        List<Integer> items = stack.getItems();
        stack.pop();
        for(Integer i = 0; i < size; i++)
            Assert.assertEquals(i, items.get(i));
    }
}