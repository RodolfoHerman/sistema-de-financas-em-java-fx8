/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rodolfo.sisfinacias.dao;

import br.com.rodolfo.sisfinacias.config.Conexao;
import br.com.rodolfo.sisfinacias.model.Contas;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author h-e-r
 */
public abstract class CRUDDaoImpl <E, I> implements CRUDDao <E>{

    protected abstract String getSql (String ano);
    
    protected abstract I getId (E e);
    
    protected abstract Map <String, Object> getParam (String ano);
    
    public void salvar(E e) {
        
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        
        if (getId (e) != null) {
            
            e = em.merge(e);
            
        }
        
        em.persist(e);
        em.getTransaction().commit();
        em.close();
        
    }

    public void excluir(E e) {
        
        EntityManager em = Conexao.getEntityManager();
        
        em.getTransaction().begin();
        e = em.merge(e);
        em.remove(e);
        em.getTransaction().commit();
        em.close();
        
        
    }

    public List<Contas> pesquisar(String ano) {
        
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery(getSql(ano));

        Map <String, Object> mapa = getParam(ano);
        
        for (String param : mapa.keySet()) {
            
            query.setParameter(param, mapa.get(param));

        }
        
        return (query.getResultList());
    }
    
}
