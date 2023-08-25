package com.hluther.interpreter.lexer;

import java.util.LinkedList;
import com.hluther.entity.MError;
import java_cup.runtime.*;
import com.hluther.interpreter.parser.sym;

%%
//----------------------------------- OPCIONES Y DECLARACIONES -----------------------------------//
%class Lexer
%cup
%public
%unicode
%line
%column

//-------------------------------------------- MACROS --------------------------------------------//
LineTerminator =    \r|\n|\r\n
WhiteSpace =        {LineTerminator} | [ \t\f]
NoZeroNumber =      [1-9]

//------------------------------------------ CODIGO JAVA -----------------------------------------//
%{
    private LinkedList<MError> lexicalErrors = new LinkedList();
    private StringBuffer errorLexeme = new StringBuffer();
    private int errorLine = 0;
    private int errorColumn = 0;
    private Symbol symbol;
    
    private Symbol createToken(String name, int type, Object lexeme){
        symbol = new Symbol(type, yyline+1, yycolumn, lexeme);
        printToken(name);
        verifyLexicalError();
        return symbol;
    }

    private Symbol createToken(String name, int type){
        symbol = new Symbol(type, yyline+1, yycolumn);
        printToken(name);
        verifyLexicalError();
        return symbol;
    }

    public LinkedList<MError> getLexicalErrors(){
        return lexicalErrors;
    }
    
    private void printToken(String token){
        System.out.println(token);
    }

    private void addLexicalError(MError error){
        lexicalErrors.add(error);
    }

    private void verifyLexicalError(){
        if(errorLexeme.length() != 0){
            addLexicalError(new MError("Lexema no reconocido.", errorLexeme.toString(), errorLine, errorColumn-errorLexeme.length()));
            errorLexeme.setLength(0);
        }
    }

%}

%%
//------------------------------------------ REGLAS LEXICAS -----------------------------------------//
<YYINITIAL>{

    0 | {NoZeroNumber}[:digit:]*                    { return createToken("INTEGER", sym.INTEGER, Integer.parseInt(yytext())); }
    ([:letter:]|"_")([:letter:]|[:digit:]|"_")*     { return createToken("ID", sym.ID, yytext()); } 
    "+"                                             { return createToken("PLUS", sym.PLUS); }                         
    "-"                                             { return createToken("MINUS", sym.MINUS); }                         
    "*"                                             { return createToken("ASTERISK", sym.ASTERISK); }                            
    "/"                                             { return createToken("SLASH", sym.SLASH); }                            
    "("                                             { return createToken("PARENTHESISO", sym.PARENTHESISO); }
    ")"                                             { return createToken("PARENTHESISC", sym.PARENTHESISC); }
    "="                                             { return createToken("EQUAL", sym.EQUAL); }
    ";"                                             { return createToken("SEMICOLON", sym.SEMICOLON); }
    {WhiteSpace}                                    { verifyLexicalError(); }
    [^]                                             { errorLexeme.append(yytext()); errorLine = yyline+1; errorColumn = yycolumn+1; }
    <<EOF>>                                         { verifyLexicalError(); return new Symbol(sym.EOF); }
                                                      
}
