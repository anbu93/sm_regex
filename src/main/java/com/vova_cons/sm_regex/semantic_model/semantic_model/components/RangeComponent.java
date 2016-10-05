package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import com.vova_cons.sm_regex.parser.syntax_tree.Element;
import com.vova_cons.sm_regex.parser.syntax_tree.NodeElement;
import com.vova_cons.sm_regex.semantic_model.semantic_model.InputCharStream;

import java.util.Iterator;

class RangeComponent implements Component {
    private char left;
    private char right;

    public RangeComponent(NodeElement node) throws Exception {
        Iterator<Element> childs = node.getChilds();
        try {
            left = childs.next().getValue().value;
            right = childs.next().getValue().value;
            if (childs.hasNext()) throw new Exception();
        } catch (Exception error){
            throw new Exception("Range component have uncorrected count of child elements: " + node.getValue().toString());
        }
    }

    @Override
    public boolean apply(InputCharStream inputCharStream) {
        int save = inputCharStream.save();
        if (inputCharStream.hasNext()) {
            char character = inputCharStream.next();
            if (left <= character && character <= right)
                return true;
            inputCharStream.reset(save);
        }
        return false;
    }
}
