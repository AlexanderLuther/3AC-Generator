package com.hluther.controller;

import com.hluther.compiler.lexer.Lexer;
import com.hluther.compiler.parser.Parser;
import java.io.StringReader;
/**
 *
 * @author helmuth
 */
public class AnalysisController {
    
    private Lexer lexer;
    private Parser parser;
    
    public void analyse(String text){
        try {
            lexer = new Lexer(new StringReader(text));
            parser = new Parser(lexer);
            
            parser.parse();
            parser.getAst().generate3AC(null, -1);
        } catch (Exception ex) {
            System.out.println("Error analizando" + ex.getMessage());
        }
    }
}
