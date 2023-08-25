package com.hluther.compiler.lexer;

import java.util.LinkedList;
import com.hluther.entity.MError;
import java_cup.runtime.*;
import com.hluther.compiler.parser.sym;

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
    
    private Symbol createToken(int type, Object lexeme){
        symbol = new Symbol(type, yyline+1, yycolumn, lexeme);
        verifyLexicalError();
        return symbol;
    }

    private Symbol createToken(int type){
        symbol = new Symbol(type, yyline+1, yycolumn);
        verifyLexicalError();
        return symbol;
    }

    public LinkedList<MError> getLexicalErrors(){
        return lexicalErrors;
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

    0 | {NoZeroNumber}[:digit:]*                    { return createToken(sym.INTEGER, Integer.parseInt(yytext())); }
    ([:letter:]|"_")([:letter:]|[:digit:]|"_")*     { return createToken(sym.ID, yytext()); } 
    "+"                                             { return createToken(sym.PLUS); }                         
    "-"                                             { return createToken(sym.MINUS); }                         
    "*"                                             { return createToken(sym.ASTERISK); }                            
    "/"                                             { return createToken(sym.SLASH); }                            
    "("                                             { return createToken(sym.PARENTHESISO); }
    ")"                                             { return createToken(sym.PARENTHESISC); }
    "="                                             { return createToken(sym.EQUAL); }
    ";"                                             { return createToken(sym.SEMICOLON); }
    {WhiteSpace}                                    { verifyLexicalError(); }
    [^]                                             { errorLexeme.append(yytext()); errorLine = yyline+1; errorColumn = yycolumn+1; }
    <<EOF>>                                         { verifyLexicalError(); return new Symbol(sym.EOF); }
                                                      
}
