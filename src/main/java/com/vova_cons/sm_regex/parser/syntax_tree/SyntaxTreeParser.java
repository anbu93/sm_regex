package com.vova_cons.sm_regex.parser.syntax_tree;

import com.vova_cons.sm_regex.lexer.TokenStream;

/**
 * Syntax tree parser from token stream.
 */
public class SyntaxTreeParser {
    /**
     * Parsing method. Main method
     * @param tokenStream input token stream object
     * @return parsed syntax tree
     */
    public static SyntaxTree parseSyntaxTree(TokenStream tokenStream){
        SyntaxTree syntaxTree = new SyntaxTree();
        while(tokenStream.hasNext())
            syntaxTree.parse(tokenStream.next());
        return syntaxTree;
    }
}
