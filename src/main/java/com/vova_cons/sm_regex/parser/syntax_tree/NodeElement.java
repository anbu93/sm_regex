package com.vova_cons.sm_regex.parser.syntax_tree;

import com.vova_cons.sm_regex.lexer.Token;

import java.util.ArrayList;
import java.util.Iterator;

public class NodeElement implements Element {
    private NodeElement parentNode = null;
    private Token token = Token.parse('[');
    private ArrayList<Element> childElements = new ArrayList<>();

    public NodeElement() { }

    public NodeElement(Token token) {
        this.token = token;
    }

    @Override
    public Element getParent() {
        return parentNode;
    }

    @Override
    public int type() {
        return Element.NODE;
    }

    @Override
    public Token getValue() {
        return token;
    }

    @Override
    public void setParent(NodeElement element) {
        this.parentNode = element;
    }

    public void appendChild(Element element){
        element.setParent(this);
        childElements.add(element);
    }

    public Element getLeftChild(Element getterChild){
        Element last = null;
        Iterator<Element> iterator = childElements.iterator();
        while(iterator.hasNext()){
            Element child = iterator.next();
            if (child.equals(getterChild))
                return last;
            last = child;
        }
        return null;
    }

    public Element getRightChild(Element getterChild){
        Element last = null;
        Iterator<Element> iterator = childElements.iterator();
        while(iterator.hasNext()){
            Element child = iterator.next();
            if (getterChild.equals(last))
                return child;
            last = child;
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NodeElement){
            NodeElement other = (NodeElement) obj;
            Iterator<Element> thisIterator = this.childElements.iterator();
            Iterator<Element> otherIterator = other.childElements.iterator();
            while(thisIterator.hasNext() && otherIterator.hasNext())
                if (!thisIterator.next().equals(otherIterator.next()))
                    return false;
            return thisIterator.hasNext() == otherIterator.hasNext();
        }
        return false;
    }

    public void removeChild(Element child){
        child.setParent(null);
        childElements.remove(child);
    }

    public void replaceChild(Element old, Element newChild){
        childElements.set(childElements.indexOf(old), newChild);
        newChild.setParent(this);
        old.setParent(null);
    }

    public Iterator<Element> getChilds(){
        return childElements.iterator();
    }
}
