package com.hluther.compiler.ast;

import static com.hluther.compiler.ast.OperationType.SUBTRACTION;
import static com.hluther.compiler.ast.OperationType.SUM;
import com.hluther.compiler.tac.Quadruple;
import java.util.LinkedList;

public class Operation implements Instruction{
    
    private final OperationType type;
    private Instruction leftOperator;
    private Instruction rightOperator;
    private Object value;
    
    /**
     * Operaciones binarias
     * @param leftOperator  Operador izquierdo de la operación
     * @param rightOperator Opeardor derecho de la operación
     * @param type Tipo de operación 
     */
    public Operation(Instruction leftOperator, Instruction rightOperator, OperationType type) {
        this.type = type;
        this.leftOperator = leftOperator;
        this.rightOperator = rightOperator;
    }
    
    /**
     * Operaciones unarias
     * @param leftOperator  Único operador de la operación
     * @param type Tipo de operación
     */
    public Operation(Instruction leftOperator, OperationType type) {
        this.type = type;
        this.leftOperator = leftOperator;
    }
    
    /**
     * ID
     * @param value Identificador de una variable declarada.
     */
    public Operation(String value) {
        this.value= value;
        this.type = OperationType.ID;
    }
    
    /**
     * Integer
     * @param value Valor de tipo int que representa la operación a realizar.
     */
    public Operation(Integer value) {
        this.value = value;
        this.type = OperationType.INTEGER;
    }
    
    public OperationType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
    
    @Override
    public Object generate3AC(LinkedList<Quadruple> quadruples, int tCounter){
        String value1 = null;
        String value2 = null;
        
        
        if(leftOperator != null) value1 =  leftOperator.generate3AC(quadruples, tCounter).toString();
        if(rightOperator != null) value2 =  rightOperator.generate3AC(quadruples, tCounter).toString();
        String temp = "t"+(quadruples.size()-tCounter);
        
        switch(type){
            case INTEGER -> {
                return value.toString();
            }
            
            case ID -> {
                return value.toString();
            }
            
            case SUM ->{ 
                quadruples.add(new Quadruple("+", value1, value2, temp));
                return temp;
            }
            
            case SUBTRACTION ->{
                quadruples.add(new Quadruple("-", value1, value2, temp));
                return temp;
            }
            
            case MULTIPLICATION ->{
                quadruples.add(new Quadruple("*", value1, value2, temp));
                return temp;
            }
            
            case DIVISION ->{
                quadruples.add(new Quadruple("/", value1, value2, temp));
                return temp;
            }
            
            case PLUS ->{
                quadruples.add(new Quadruple("+", value1, null, temp));
                return temp;
            }
            
            case MINUS ->{
                quadruples.add(new Quadruple("-", value1, null, temp));
                return temp;
            }
               
        }
        return null;
    }
    
}