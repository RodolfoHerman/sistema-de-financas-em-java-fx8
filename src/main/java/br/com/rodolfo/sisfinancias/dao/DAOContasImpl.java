/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rodolfo.sisfinancias.dao;

import br.com.rodolfo.sisfinancias.config.Conexao;
import br.com.rodolfo.sisfinancias.model.Contas;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author h-e-r
 */
public class DAOContasImpl implements CRUD <Contas>{

    private static final int DIA = 31;
    private static final int JAN = 1;
    private static final int DEZ = 12;    
    
    public void salvar(Contas e) {
        
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        
        if (e.getCodCon() != null) {
            
            e = em.merge(e);
            
        }
        
        em.persist(e);
        em.getTransaction().commit();
        em.close();
        
    }

    public void excluir(Contas e) {
        
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
        
        StringBuilder sql = new StringBuilder ("from Contas c where 1 = 1 ");
        
        if (ano != null && !ano.equals("")) {
            
            sql.append("and c.datVct between :DatIni and :DatFim ");
            
        }
        
        Query nomeSql = em.createQuery(sql.toString());
        
        Map <String,Object> mapa = getParam (ano);
        
        for (String param: mapa.keySet()) {
            
            nomeSql.setParameter(param, mapa.get(param));
            
        }
        
        return (nomeSql.getResultList());
    }
    
    private Map<String, Object> getParam (String ano) {
        
        Map<String,Object> mapa = new HashMap<String,Object>();
        
        if (ano != null && !ano.equals("")) {
            
            LocalDate datIni = LocalDate.of(Integer.parseInt(ano), JAN, DIA);
            LocalDate datFim = LocalDate.of(Integer.parseInt(ano), DEZ, DIA);
            
            mapa.put("DatIni", datIni);
            mapa.put("DatFim", datFim);
            
        }
        
        return (mapa);
    }
    
}
