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
        
        analysisController.analyse(fileController.readFile("input.txt"));
    }
}
