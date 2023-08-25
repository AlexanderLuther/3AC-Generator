package com.hluther.compiler.tac;
/**
 *
 * @author helmuth
 */
public class Quadruple {
    
    private String operation;
    private String firstArgument;
    private String secondArgument;
    private String result;

    public Quadruple(String operation, String firstArgument, String secondArgument, String result) {
        this.operation = operation;
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
        this.result = result;
    }

    public void print(){
        if(secondArgument == null){
            System.out.println(result +"="+ operation + firstArgument);
        } else{
            System.out.println(result +"="+ firstArgument + operation + secondArgument);
        }
    }
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getFirstArgument() {
        return firstArgument;
    }

    public void setFirstArgument(String firstArgument) {
        this.firstArgument = firstArgument;
    }

    public String getSecondArgument() {
        return secondArgument;
    }

    public void setSecondArgument(String secondArgument) {
        this.secondArgument = secondArgument;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    
}
