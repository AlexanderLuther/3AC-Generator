package com.hluther.interpreter.ast;
/**
 *
 * @author helmuth
 */
public enum OperationType {
    //Variables
    ID("Acceso a Variable"),
    
    //Tipos de Datos
    INTEGER("Entero"),
    
    //Operaciones Aritmeticas
    SUM("Suma"),
    SUBTRACTION("Resta"),
    MULTIPLICATION("Multiplicacion"),
    DIVISION("Division"),
    PLUS("Suma Unaria"),
    MINUS("Resta Unaria");
    
    private String name;
    
    OperationType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}