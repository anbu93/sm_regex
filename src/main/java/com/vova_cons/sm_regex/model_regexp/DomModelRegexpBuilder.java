package com.vova_cons.sm_regex.model_regexp;

import com.vova_cons.sm_regex.RegularExpression;
import com.vova_cons.sm_regex.lexer.TokenStream;
import com.vova_cons.sm_regex.parser.syntax_tree.SyntaxTree;
import com.vova_cons.sm_regex.parser.syntax_tree.SyntaxTreeParser;
import com.vova_cons.sm_regex.semantic_model.SemanticModelBuilder;
import com.vova_cons.sm_regex.semantic_model.semantic_model.SemanticModel;

public class DomModelRegexpBuilder {
    public static RegularExpression create(String pattern) throws Exception {
        TokenStream tokenStream = new TokenStream(pattern);
        SyntaxTree syntaxTree = SyntaxTreeParser.parseSyntaxTree(tokenStream);
        SemanticModel semanticModel = SemanticModelBuilder.build(syntaxTree);
        return new DomModelRegexp(pattern, semanticModel);
    }
}
