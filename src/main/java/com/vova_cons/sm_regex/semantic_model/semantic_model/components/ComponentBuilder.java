package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import com.vova_cons.sm_regex.lexer.Token;
import com.vova_cons.sm_regex.parser.syntax_tree.Element;
import com.vova_cons.sm_regex.parser.syntax_tree.NodeElement;

import java.util.Iterator;

public class ComponentBuilder {
    public static Component create(Element element) throws Exception {
        return create(element.getValue(), element);
    }

    private static Component create(Token token, Element element) throws Exception {
        switch(token.type){
            case CHAR:
                return new SymbolComponent(element.getValue().value);
            case OPEN_SCOBS:
                return createMultipleChildrenHaveComponent(new SequenceComponent(), element);
            case OPEN_QUADRO:
                return createMultipleChildrenHaveComponent(new SelectorComponent(), element);
            case MULTIPLE:
                return createOneChildrenComponent(new MultipleComponent(), (NodeElement) element);
            case MULTIPLE_MORE:
                return createOneChildrenComponent(new MultipleMoreComponent(), (NodeElement) element);
            case OPTIONAL:
                return createOneChildrenComponent(new OptionalComponent(), (NodeElement) element);
            case RANGE:
                return new RangeComponent((NodeElement) element);
            case OR:
                return createMultipleChildrenHaveComponent(new SelectorComponent(), element);
            default:
                throw new Exception("Uncorrected token type: " + token.type);
        }
    }

    private static Component createOneChildrenComponent(OneChildrenComponent component, NodeElement element) throws Exception {
        Iterator<Element> elementIterator = element.getChilds();
        if (!elementIterator.hasNext())
            throw new Exception("Component builder, error on create one children have component - " +
                    "element not have a child element: "
                    + element.getValue().toString());
        Element childElement = elementIterator.next();
        if (elementIterator.hasNext())
            throw new Exception("Component builder, error on create one children have component - " +
                    "element have more then one child elements: "
                    + element.getValue().toString());
        component.setComponent(ComponentBuilder.create(childElement));
        return component;
    }

    private static Component createMultipleChildrenHaveComponent(MultipleChildComponent component, Element element) throws Exception {
        Iterator<Element> iterator = ((NodeElement)element).getChilds();
        while(iterator.hasNext())
            component.add(ComponentBuilder.create(iterator.next()));
        return component;
    }
}
