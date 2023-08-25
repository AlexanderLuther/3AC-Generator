package com.hluther.interpreter.ast;
/**
 *
 * @author helmuth
 */
public class Symbol {
    
}

    private final Tipo tipo;
    private final String id;
    private Object valor;    
    /**
     * Constructor de la clase Símbolo.
     * @param id identificador de la variable que se desea almacenar
     * @param tipo tipo de la variable que se desea almacenar
     */
    public Simbolo(String id, Tipo tipo) {
        this.tipo = tipo;
        this.id = id;
    }

    /**
     * Método que devuelve el identificador de la variable almacenada en el símbolo.
     * @return Identificador de la variable
     */
    public String getId() {
        return id;
    }
    /**
     * Método que devuelve el valor que almacena la variable.
     * @return Valor de la variable
     */
    public Object getValor() {
        return valor;
    }
    /**
     * Método que asigna un nuevo valor a la variable.
     * @param v Nuevo valor para la variable.
     */
    void setValor(Object v) {
        valor=v;
    }
    /**
     * Enumeración que lista todos los tipos de variable reconocidos en el lenguaje.
     */
    public static enum Tipo{
        NUMERO
    }