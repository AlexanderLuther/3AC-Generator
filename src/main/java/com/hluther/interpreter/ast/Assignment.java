package com.hluther.interpreter.ast;

/**
 * @author helmuth
 */

public class Assignment implements Instruction{
    
    private String id;
    private Instruction value;
    
    /**
     * Constructor de la clase Assignment
     * @param id  identificador de la variable.
     * @param value  valor que se le va a asignar.
     */
    public Assignment(String id, Instruction value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public Instruction getValue() {
        return value;
    }
  
    /**
     * Método que ejecuta la accion de asignar un valor, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable  tabla de símbolos del ámbito padre de la sentencia asignación
     * @param ast
     * @return En este caso retorna nulo porque no es una sentencia que genere un valor.
     * @throws com.hluther.entity_classes.ExecutionException
     *
    @Override
    public Object execute(SymbolTable symbolTable, AbstractSyntaxTree ast) throws ExecutionException{
        try{
            symbolTable.setValue(id, value.execute(symbolTable, ast));
        } catch(ExecutionException e){
            if(!e.isCompleteData()){
                e.setRow(row);
                e.setColumn(column);
                e.setCompleteData(true);
            }
            throw e;
        }
        return null;
    } */
}
