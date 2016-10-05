package com.vova_cons.sm_regex.semantic_model;

import com.vova_cons.sm_regex.semantic_model.optimisation_rules.OptimisationRule;
import com.vova_cons.sm_regex.parser.syntax_tree.Element;
import com.vova_cons.sm_regex.parser.syntax_tree.SyntaxTree;

import java.util.Iterator;


/**
 * Syntax tree optimiser class
 * Apply Three rules for transform from syntax tree to semantic model.
 */
public class SyntaxTreeOptimizer {
    /**
     * Syntax tree optimise method.
     * @param syntaxTree
     * @return optimised syntax tree
     * @throws Exception if optimisation process fail.
     */
    public static SyntaxTree optimize(SyntaxTree syntaxTree) throws Exception {
        return new SyntaxTreeOptimizer(syntaxTree).parse();
    }

    private SyntaxTree syntaxTree;

    private SyntaxTreeOptimizer(SyntaxTree syntaxTree) {
        this.syntaxTree = syntaxTree;
    }

    SyntaxTree parse() throws Exception {
        OptimisationRule.modifierRule.apply(getRootElementIterator());
        OptimisationRule.rangeRule.apply(getRootElementIterator());
        OptimisationRule.orRule.apply(getRootElementIterator());
        return syntaxTree;
    }

    private Iterator<Element> getRootElementIterator(){
        return syntaxTree.getRootElement().getChilds();
    }
}
