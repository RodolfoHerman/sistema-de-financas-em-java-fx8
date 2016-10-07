/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rodolfo.sisfinancias.dao;

import java.util.List;

/**
 *
 * @author h-e-r
 */
public interface CRUD <E> {
    
    public void salvar (E e);
    
    public void excluir (E e);
    
    public List<E> pesquisar (String e);
    
}
