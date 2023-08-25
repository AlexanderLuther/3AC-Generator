package com.hluther.compiler.ast;

import com.hluther.compiler.tac.Quadruple;
import java.util.LinkedList;

/**
 *
 * @author helmuth
 */
public interface Instruction {
        
    public Object generate3AC(LinkedList<Quadruple> quadruples, int tCounter);
}
