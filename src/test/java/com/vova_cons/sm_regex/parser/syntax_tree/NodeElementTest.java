package com.vova_cons.sm_regex.parser.syntax_tree;

import com.vova_cons.sm_regex.lexer.Token;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NodeElementTest {
    private NodeElement node;
    private LeafElement first;
    private LeafElement last;
    private LeafElement middle;


    @Before
    public void setUp() throws Exception {
        node = new NodeElement();
        first = new LeafElement(Token.parse('a'));
        middle = new LeafElement(Token.parse('b'));
        last = new LeafElement(Token.parse('c'));
        node.appendChild(first);
        node.appendChild(middle);
        node.appendChild(last);
    }

    @Test
    public void testGetLeftElementForMiddle() throws Exception {
        Element element = node.getLeftChild(middle);
        Assert.assertEquals(first, element);
    }

    @Test
    public void testGetRightElementForMiddle() throws Exception {
        Element element = node.getRightChild(middle);
        Assert.assertEquals(last, element);
    }

    @Test
    public void testGetLeftElementForFirst() throws Exception {
        Element element = node.getLeftChild(first);
        Assert.assertNull(element);
    }

    @Test
    public void testGetRightElementForLast() throws Exception {
        Element element = node.getRightChild(last);
        Assert.assertNull(element);
    }

    @Test
    public void testGetLeftElementForLast() throws Exception {
        Element element = node.getLeftChild(last);
        Assert.assertEquals(middle, element);
    }

    @Test
    public void testGetRightElementForFirst() throws Exception {
        Element element = node.getRightChild(first);
        Assert.assertEquals(middle, element);
    }

    @Test
    public void testReplaceMiddle() throws Exception {
        Element excepted = new LeafElement(Token.parse('d'));
        node.replaceChild(middle, excepted);
        Assert.assertEquals(excepted, node.getRightChild(first));
        Assert.assertEquals(excepted, node.getLeftChild(last));
    }

    @Test
    public void testRemoveMiddle() throws Exception {
        node.removeChild(middle);
        Assert.assertEquals(last, node.getRightChild(first));
        Assert.assertEquals(first, node.getLeftChild(last));
    }
}