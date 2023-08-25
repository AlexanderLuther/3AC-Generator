package com.hluther.interpreter.ast;

import java.util.LinkedList;
/**
 * Clase que alberga todas los metodos definidos y las variables globales
 * declaradas.
 * @author helmuth
 */
public class AbstractSyntaxTree implements Instruction{

    private LinkedList<Instruction> instructions;
    
    /**
     * Constructor de la clase AbstractSintaxTree
     * @param instructions  Lista de instrucciones que conforman al AST
     */
    public AbstractSyntaxTree(LinkedList<Instruction> instructions) {
        this.instructions = instructions;
    }
        
         
    /**
     * Método que ejecuta el codigo general de una pista.Es una sobreescritura del 
 método execute que se debe programar por la implementación de la interfaz
 instruction
     * @param symbolTable Tabla de símbolos del ámbito padre de la sentencia
     * @param  ast
     * @return null
     * @throws com.hluther.entity_classes.ExecutionException
     *   
    @Override
    public Object execute(SymbolTable symbolTable, AbstractSyntaxTree ast) throws ExecutionException{            
        //Crear una nueva instancia de la clase track.
        track = new Track();   
        
        //Ejecutar todas las sentencias correspondientes a declaraciones y asignaciones de valores.
        for(Instruction instruction:instructions){
            if(instruction instanceof Declaration){
                if(instruction instanceof ArrayDeclaration){
                    ((ArrayDeclaration)instruction).execute(symbolTable, ast);
                } else{
                    ((Declaration)instruction).execute(symbolTable, ast);
                }
            } else if(instruction instanceof Assignment){
                if(instruction instanceof ArrayAssignment){
                    ((ArrayAssignment)instruction).execute(symbolTable, ast);
                } else{
                    ((Assignment)instruction).analyze(symbolTable, ast);
                }
            } else if(instruction instanceof DeclarationAndAssignment){
                ((DeclarationAndAssignment)instruction).execute(symbolTable, ast);
            }
        }
        
        //Ejecutar el metodo Principal
        for(Instruction instruction:instructions){
            if(instruction instanceof Method){
                if(((Method)instruction).getId().equals("_Principal()")){
                    ((Method)instruction).setParamsValues(new LinkedList<>());
                    ((Method)instruction).execute(symbolTable, ast);
                }
            }
        }       
        return null;
  
    }*/
    
}
