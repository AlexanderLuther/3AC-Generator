package com.hluther.compiler.ast;

import com.hluther.compiler.tac.Quadruple;
import java.util.LinkedList;
/**
 * Clase que alberga todas los metodos definidos y las variables globales
 * declaradas.
 * @author helmuth
 */
public class AbstractSyntaxTree implements Instruction{

    private LinkedList<Instruction> instructions;
    private LinkedList<Quadruple> quadruples;
    
    /**
     * Constructor de la clase AbstractSintaxTree
     * @param instructions  Lista de instrucciones que conforman al AST
     */
    public AbstractSyntaxTree(LinkedList<Instruction> instructions) {
        this.instructions = instructions;
        this.quadruples = new LinkedList<>();
    }
    
    @Override
    public Object generate3AC(LinkedList<Quadruple> quadruples, int tCounter){
        quadruples = this.quadruples;
        
        for(Instruction instruction : instructions){
            if(instruction instanceof Assignment){
                tCounter++;
            }
            instruction.generate3AC(quadruples, tCounter);
        }
        
        for(Quadruple quadruple : quadruples){
            quadruple.print();
        }
        return null;
    }
         
}
