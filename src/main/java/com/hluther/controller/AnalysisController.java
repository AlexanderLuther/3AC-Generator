package com.hluther.controller;

import com.hluther.interpreter.lexer.Lexer;
import com.hluther.interpreter.parser.Parser;
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
        } catch (Exception ex) {
            System.out.println("Error analizando");
        }
    }
}
