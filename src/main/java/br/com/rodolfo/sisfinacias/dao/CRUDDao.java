/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rodolfo.sisfinacias.dao;

import br.com.rodolfo.sisfinacias.model.Contas;
import java.util.List;

/**
 *
 * @author h-e-r
 */
public interface CRUDDao <E>{
    
    public abstract void salvar (E e);
    
    public abstract void excluir (E e);
    
    public abstract List<Contas> pesquisar (String ano);
    
}
