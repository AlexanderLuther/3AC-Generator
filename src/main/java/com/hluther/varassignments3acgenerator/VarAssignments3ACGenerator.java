package com.hluther.varassignments3acgenerator;

import com.hluther.controller.AnalysisController;
import com.hluther.controller.FileController;

/**
 *
 * @author helmuth
 */
public class VarAssignments3ACGenerator {

    
    public static void main(String[] args) {
        FileController fileController = new FileController();
        AnalysisController analysisController = new AnalysisController();
        
        
        String data = fileController.readFile("input.txt");
        System.out.println("\033[34m" + "Codigo en archivo input.txt:" + "\u001B[0m");
        System.out.println(data);
        System.out.println("\033[34m" + "Codigo Tres Direcciones:" + "\u001B[0m");
        analysisController.analyse(data);
    }
}
