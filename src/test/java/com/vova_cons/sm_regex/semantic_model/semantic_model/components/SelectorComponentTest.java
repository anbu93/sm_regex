package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import com.vova_cons.sm_regex.lexer.Token;
import com.vova_cons.sm_regex.parser.syntax_tree.LeafElement;
import com.vova_cons.sm_regex.parser.syntax_tree.NodeElement;
import com.vova_cons.sm_regex.parser.syntax_tree.SyntaxTree;
import com.vova_cons.sm_regex.semantic_model.semantic_model.InputCharStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SelectorComponentTest {
    private SelectorComponent selectorComponent;
    private InputCharStream stream;

    @Before
    public void setUp() throws Exception {
        stream = new InputCharStream("0123456789");
        selectorComponent = new SelectorComponent();
    }

    @Test
    public void testApply() throws Exception {
        selectorComponent.add(new SymbolComponent('0'));
        selectorComponent.add(new SymbolComponent('1'));
        selectorComponent.add(new SymbolComponent('2'));
        Assert.assertTrue(selectorComponent.apply(stream));
    }

    @Test
    public void testReject() throws Exception {
        selectorComponent.add(new SymbolComponent('3'));
        selectorComponent.add(new SymbolComponent('1'));
        selectorComponent.add(new SymbolComponent('2'));
        Assert.assertFalse(selectorComponent.apply(stream));
    }

    @Test
    public void multipleSelectorAccept() throws Exception {
        selectorComponent.add(new SymbolComponent('3'));
        selectorComponent.add(new SymbolComponent('1'));
        selectorComponent.add(new SymbolComponent('2'));
        MultipleComponent multipleComponent = new MultipleComponent();
        multipleComponent.setComponent(selectorComponent);
        Assert.assertTrue(multipleComponent.apply(stream));
    }

    @Test
    public void createOnBuilder() throws Exception {
        NodeElement node = new NodeElement(Token.parse('['));
        node.appendChild(new LeafElement(Token.parse('0')));
        node.appendChild(new LeafElement(Token.parse('2')));
        node.appendChild(new LeafElement(Token.parse('1')));
        Component component = ComponentBuilder.create(node);
        SyntaxTree tree = new SyntaxTree();
        tree.setRootElement(node);
        Assert.assertTrue(tree.toString(), component.apply(stream));
    }
}