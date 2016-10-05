package com.vova_cons.sm_regex.semantic_model.semantic_model.components;

import com.vova_cons.sm_regex.semantic_model.semantic_model.InputCharStream;

public interface Component {
    boolean apply(InputCharStream inputCharStream);
}
