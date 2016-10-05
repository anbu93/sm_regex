package com.vova_cons.sm_regex.semantic_model.semantic_model;

public class InputCharStream {
    private char[] stream;
    private int iterator;

    public InputCharStream(String string){
        this.stream = string.toCharArray();
        this.iterator = 0;
    }

    public char next(){
        checkIterator();
        return stream[iterator++];
    }

    public boolean hasNext(){
        return iterator < stream.length;
    }

    public int save(){
        return iterator;
    }

    public void reset(int savePoint){
        this.iterator = savePoint;
    }

    private void checkIterator(){
        if (iterator == stream.length)
            throw new RuntimeException("InputCharStream out of range: " + iterator);
    }
}
