/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rodolfo.sisfinancias.dao;

import br.com.rodolfo.sisfinancias.config.Conexao;
import br.com.rodolfo.sisfinancias.model.Contas;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author h-e-r
 */
public abstract class CRUDDaoImpl <E, I> implements CRUDDao <E>{

    protected abstract String getSql (Object object);
    
    protected abstract I getId (E e);
    
    protected abstract Map <String, Object> getParam (Object object);
    
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

    public List<E> pesquisar(Object object) {
        
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery(getSql(object));

        Map <String, Object> mapa = getParam(object);
        
        for (String param : mapa.keySet()) {
            
            query.setParameter(param, mapa.get(param));

        }
        
        return (query.getResultList());
    }
    
}
