package com.hluther.interpreter.ast;

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
    public Operation(int value) {
        this.value = value;
        this.type = OperationType.INTEGER;
    }
    
    public OperationType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
    
    /**
     * Método que ejecuta la instrucción operación, es una sobreescritura del
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param symbolTable tabla de símbolos del ámbito padre de la sentencia.
     * @param ast
     * @return Esta instrucción retorna el value producido por la operación que se ejecutó.
     * @throws com.hluther.entity_classes.ExecutionException
     /    
    @Override
    public Object execute(SymbolTable symbolTable, AbstractSyntaxTree ast) throws ExecutionException{
        Object value1;
        Object value2;
        if(type == null){
            return null;
        } else{
            switch(type){
                //---------------------------   ID  ---------------------------//
                case ID:
                    Object temValue = symbolTable.getValue(value.toString());
                    if(temValue == null){
                        return SymbolType.NOT_INITIALIZED;
                    } else{
                        return temValue;
                    }
                    
                //---------------------------   INTGER, DOUBLE, STRING, CHAR, BOOLEAN  ---------------------------//
                case INTEGER: case DOUBLE: case STRING: case CHAR: case BOOLEAN:
                    return value;
                
                //---------------------------  ARRAYACCESS  ---------------------------//
                case ARRAYACCESS:
                    ((ArrayAccess)leftOperator).setNullComparation(nullComparation);
                    return leftOperator.execute(symbolTable, ast);
                
                //---------------------------  PLUS, MINUS  ---------------------------//    
                case PLUS: case MINUS:   
                    value1 = leftOperator.execute(symbolTable, ast);
                 
                    switch(TypeConversion.getType(value1)){  
                        case DOUBLE:
                            switch(type){
                                case PLUS:                      return (double)value1;
                                case MINUS:                     return (double)value1 * (double)-1;
                                default:                        return null;
                            }
                        case INTEGER:
                            switch(type){
                                case PLUS:                      return (int)value1;
                                case MINUS:                     return (int)value1 * (int)-1;
                                default:                        return null;
                            }
                        case CHARACTER:
                            switch(type){
                                case PLUS:                      return (char)value1;
                                case MINUS:                     return (int)(char)value1 * (int)-1;
                                default:                        return null;
                            }
                        case BOOLEAN:
                            value1 = (Boolean)value1 ? 1 : 0;
                            switch(type){
                                case PLUS:                      return (int)value1;
                                case MINUS:                     return (int)value1 * (int)-1;
                                default:                        return null;
                            }
                    }
                    
                //---------------------------   SUM, SUBSTRACTION, MULTIPLICACTION, DIVISION, MOD, POTENTIATION, IS_EQUAL, NOT_EQUAL, GREATER_THAN, LESS_THAN, GREATER_THAN_OR_EQUAL_TO, LESS_THAN_OR_EQUAL_TO  ---------------------------//    
                case SUM: case SUBTRACTION: case MULTIPLICATION: case DIVISION: case MOD: case POTENTIATION: case IS_EQUAL: case NOT_EQUAL: case GREATER_THAN: case LESS_THAN: case GREATER_THAN_OR_EQUAL_TO: case LESS_THAN_OR_EQUAL_TO:                
                    value1 = leftOperator.execute(symbolTable, ast);
                    value2 = rightOperator.execute(symbolTable, ast);
                     
                    //Verificar si ambos operandos son del mismo tipo
                    if(TypeConversion.isSameType(value1, TypeConversion.getType(value2))){
                        return getArithmeticOperationValue(value1, value2, TypeConversion.getType(value1), ast);
                    } 
                    //Realizar conversion de tipos
                    else{
                        switch(TypeConversion.getType(value1)){
                            case DOUBLE:
                                switch(TypeConversion.getType(value2)){
                                    case INTEGER:   return doubleToInteger(value1, value2);
                                    case CHARACTER: return doubleToCharacter(value1, value2);
                                    case BOOLEAN:   return doubleToBoolean(value1, value2);
                                    case STRING:    return allTypesToString(value1, value2);
                                    default:        return null;
                                }
                            case INTEGER:
                                switch(TypeConversion.getType(value2)){
                                    case DOUBLE:    return integerToDouble(value1, value2);
                                    case CHARACTER: return integerToCharacter(value1, value2);
                                    case BOOLEAN:   return integerToBoolean(value1, value2);
                                    case STRING:    return allTypesToString(value1, value2);                                       
                                    default:        return null;
                                }
                            case CHARACTER:
                                switch(TypeConversion.getType(value2)){
                                    case INTEGER:   return characterToInteger(value1, value2);
                                    case DOUBLE:    return characterToDouble(value1, value2);
                                    case BOOLEAN:   return characterToBoolean(value1, value2);
                                    case STRING:    return allTypesToString(value1, value2);
                                    default:        return null;
                                }
                            case BOOLEAN:
                                value1 = (Boolean)value1 ? 1 : 0;
                                switch(TypeConversion.getType(value2)){
                                    case INTEGER:   return booleanToInteger(value1, value2);                                      
                                    case CHARACTER: return booleanToCharacter(value1, value2);                                        
                                    case DOUBLE:    return booleanToDouble(value1, value2);                                        
                                    case STRING:    return allTypesToString(value1, value2);                                      
                                    default:        return null;
                                }       
                            case STRING:
                                return stringToAllTypes(value1, value2); 
                        }
                    }
                
                //---------------------------   AND, OR, NAND, NOR, XOR  ---------------------------//
                case AND: case OR: case NAND: case NOR: case XOR:
                    value1 = leftOperator.execute(symbolTable, ast);
                    value2 = rightOperator.execute(symbolTable, ast);
                    
                    if(TypeConversion.getType(value1) == SymbolType.INTEGER){
                        switch ((int)value1) {
                            case 0:
                                value1 = false;
                                break;
                            case 1:
                                value1 = true;
                                break;
                            default:
                                throw new ExecutionException(rowOperationLeft, columnOperationLeft, "Imposible realizar la conversion implicita de [Entero] a [Boolean]. Solo se permiten los valores 1 o 0.");
                        }
                    }
                    
                    if(TypeConversion.getType(value2) == SymbolType.INTEGER){
                        switch ((int)value2) {
                            case 0:
                                value2 = false;
                                break;
                            case 1:
                                value2 = true;
                                break;
                            default:
                                throw new ExecutionException(rowOperationRight, columnOperationRight, "Imposible realizar la conversion implicita de [Entero] a [Boolean]. Solo se permiten los valores 1 o 0.");
                        }
                    }
              
                    return getLogicalOperationValue(value1, value2); 
                
                //---------------------------   NOT  ---------------------------//    
                case NOT:
                    value1 = leftOperator.analyze(symbolTable, ast);
                    
                    if(TypeConversion.getType(value1) == SymbolType.INTEGER){
                       switch ((int)value1) {
                            case 0:
                                value1 = false;
                                break;
                            case 1:
                                value1 = true;
                                break;
                            default:
                                throw new ExecutionException(rowOperationRight, columnOperationRight, "Imposible realizar la conversion implicita de [Entero] a [Boolean]. Solo se permiten los valores 1 o 0.");
                        }
                    }
                    
                    return !(boolean)value1;
                    
                    
                //---------------------------   IS_NULL  ---------------------------//
                case IS_NULL:
                    ((Operation)leftOperator).setNullComparation(true);
                    return (boolean)(leftOperator.execute(symbolTable, ast) == SymbolType.NOT_INITIALIZED);
                              
                case CALL:
                    return leftOperator.execute(symbolTable, ast);    
                    
                case NOTE:
                    return SymbolType.NOTE;
                    
                default:    
                    return null;
            }
        }
    }*/
}