/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rodolfo.sisfinacias.util;

import br.com.rodolfo.sisfinacias.model.Contas;
import java.util.List;
import javafx.scene.control.DatePicker;

/**
 *
 * @author h-e-r
 */
public class Util {
    
    
    public static final String [] calcularTotais (List<Contas> contas) {
        
        double temp1 = 0;
        double temp2 = 0;
        
        for (Contas atual : contas) {
            
            temp1 += atual.getValorReal();
            temp2 += atual.getValorPago();
            
        }
        
        temp1 = temp2 - temp1;
        
        return (new String[]{ String.format("%.2f", temp1), String.format("%.2f", temp2)});
        
    }
    
    public static final boolean isEmpty (String src) {
        
        if (src == null) {
            return true;
        }
        
        return ((src.trim().length() == 0) || (src.equals("0")));
    }
    
    public static final boolean isEmpty (DatePicker picker) {
        
        if (picker.getValue() == null) {
            return true;
        }
        return false;
    }
    
    public static final String getNewLine () {
        return (System.getProperty("line.separator"));
    }
    
}
