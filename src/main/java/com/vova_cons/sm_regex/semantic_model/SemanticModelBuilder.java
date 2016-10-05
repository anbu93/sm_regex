package com.vova_cons.sm_regex.semantic_model;

import com.vova_cons.sm_regex.semantic_model.semantic_model.SemanticModel;
import com.vova_cons.sm_regex.semantic_model.semantic_model.SemanticModelTranslator;
import com.vova_cons.sm_regex.parser.syntax_tree.SyntaxTree;

/**
 * Semantic model builder.
 * Input: Syntax tree
 * Output: Semantic model
 * Steps:
 *  - Optimise syntax tree
 *  - Translate syntax tree to semantic model
 */
public class SemanticModelBuilder {
    /**
     * Building semantic model from syntax tree (not optimised syntax tree)
     * @param syntaxTree syntax tree
     * @return semantic model
     * @throws Exception if building fail
     */
    public static SemanticModel build(SyntaxTree syntaxTree) throws Exception {
        SyntaxTree optimisedSyntaxTree = SyntaxTreeOptimizer.optimize(syntaxTree);
        return new SemanticModelTranslator(optimisedSyntaxTree).translate();
    }
}
