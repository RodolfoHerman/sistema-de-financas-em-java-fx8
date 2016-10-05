/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rodolfo.sisfinancias.util;

import br.com.rodolfo.sisfinancias.dao.DAOContas;
import br.com.rodolfo.sisfinancias.dao.DAOContasImpl;

/**
 *
 * @author h-e-r
 */
public class ServiceLocator {
    
    public static DAOContas getDAOContas () {
        return (new DAOContasImpl());
    }
    
    
}
