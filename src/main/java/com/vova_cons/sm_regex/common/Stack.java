package com.vova_cons.sm_regex.common;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Stack<T> {
    private List<T> stack = new LinkedList<>();

    /**
     * append value to stack
     */
    public void push(T value){
        stack.add(value);
    }
    public void add(T value){
        push(value);
    }

    /**
     * remove last item in stack
     */
    public T pop(){
        int lastItemIndex = stack.size()-1;
        if(lastItemIndex == -1)
            return null;
        T result = stack.get(lastItemIndex);
        stack.remove(lastItemIndex);
        return result;
    }
    public T remove(){
        return pop();
    }

    /**
     * See last item in stack
     */
    public T seek(){
        int lastItemIndex = stack.size()-1;
        if(lastItemIndex == -1)
            return null;
        return stack.get(lastItemIndex);
    }
    public T upper(){
        return seek();
    }
    public T last(){
        return seek();
    }

    public int size(){
        return stack.size();
    }

    public List<T> getItems(){
        List<T> result = new LinkedList<>();
        Collections.addAll(result, (T[]) stack.toArray());
        return result;
    }
}
