/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.advanced.queries;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Paulo Figueiras
 */
public interface QueryTreatment {
    
    public ArrayList<String> getQueryKeywords(String query) ;
    
    public HashMap<String, Double> createQueryStatisticVector(ArrayList<String> keywords) ;
}
