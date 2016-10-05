package com.vova_cons.sm_regex.semantic_model.semantic_model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InputCharStreamTest {

    private InputCharStream stream;
    private String chars = "0123456789";

    @Before
    public void setUp(){
        stream = new InputCharStream(chars);
    }

    @Test
    public void testHasNextOnBegin() throws Exception {
        Assert.assertTrue(stream.hasNext());
    }

    @Test
    public void testHasNextOnLastItem() throws Exception {
        stream.reset(chars.length()-1);
        stream.next();
        Assert.assertFalse(stream.hasNext());
    }

    @Test
    public void testHasNextOnAllItemsWithout() throws Exception {
        for(int i = 0; i < chars.length(); i++){
            stream.reset(i);
            Assert.assertTrue("On item: " + i, stream.hasNext());
        }
    }

    @Test
    public void testNext() throws Exception {
        char first = stream.next();
        char second = stream.next();
        Assert.assertEquals(first, chars.charAt(0));
        Assert.assertEquals(second, chars.charAt(1));
    }

    @Test
    public void testIterateAllItems() {
        for(int i = 0; i < chars.length(); i++)
            Assert.assertEquals(chars.charAt(i), stream.next());
        Assert.assertFalse(stream.hasNext());
    }

    @Test
    public void testSaveReset() throws Exception {
        int save = stream.save();
        while(stream.hasNext())
            stream.next();
        stream.reset(save);
        int count = 0;
        while(stream.hasNext()) {
            stream.next();
            count++;
        }
        Assert.assertEquals(chars.length(), count);
    }
}