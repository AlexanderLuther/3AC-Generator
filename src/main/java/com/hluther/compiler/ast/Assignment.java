package com.hluther.compiler.ast;

import com.hluther.compiler.tac.Quadruple;
import java.util.LinkedList;

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

    @Override
    public Object generate3AC(LinkedList<Quadruple> quadruples, int tCounter){
        quadruples.add(new Quadruple("", value.generate3AC(quadruples, tCounter).toString(),null, id));
        return "t"+(quadruples.size()-tCounter);
    }
}
