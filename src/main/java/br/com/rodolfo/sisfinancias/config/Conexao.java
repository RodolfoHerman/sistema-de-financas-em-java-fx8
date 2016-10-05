/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rodolfo.sisfinancias.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author h-e-r
 */
public class Conexao {
    
    private static Conexao conexao;
    private static EntityManagerFactory emf;
    
    private Conexao () {
        emf = Persistence.createEntityManagerFactory("sisFinaciasPU");
    }
    
    public static EntityManager getEntityManager () {
        if (conexao == null) {            
            conexao = new Conexao();            
        }      
        return (emf.createEntityManager());
    }
    
}
