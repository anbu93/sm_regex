package com.vova_cons.sm_regex.semantic_model.semantic_model;

import com.vova_cons.sm_regex.lexer.TokenType;
import com.vova_cons.sm_regex.parser.syntax_tree.SyntaxTree;
import com.vova_cons.sm_regex.semantic_model.semantic_model.components.Component;
import com.vova_cons.sm_regex.semantic_model.semantic_model.components.ComponentBuilder;

public class SemanticModelTranslator {
    private SyntaxTree syntaxTree;
    private Component rootComponent;

    public SemanticModelTranslator(SyntaxTree syntaxTree) throws Exception {
        if(syntaxTree == null) throw new Exception("Semantic model translator: Syntax tree are null!");
        this.syntaxTree = syntaxTree;
    }

    public SemanticModel translate() throws Exception {
        syntaxTree.getRootElement().getValue().type = TokenType.OPEN_SCOBS;
        rootComponent = ComponentBuilder.create(syntaxTree.getRootElement());
        return new SemanticModel(rootComponent);
    }
}
