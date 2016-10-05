/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rodolfo.sisfinacias.dao;

import br.com.rodolfo.sisfinacias.model.Contas;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author h-e-r
 */
public class DAOContasImpl extends CRUDDaoImpl<Contas, Integer> implements DAOContas {

    private static final int DIA = 31;
    private static final int JAN = 1;
    private static final int DEZ = 12;
    
    
    @Override
    protected String getSql(String ano) {

        StringBuffer sql = new StringBuffer("from Contas c where 1 = 1 ");

        if (ano != null && !ano.equals("")) {

            sql.append("and c.datVct between :DatIni and :DatFim");

        }

        return (sql.toString());
    }

    @Override
    protected Integer getId(Contas conta) {
        return (conta.getCodCon());
    }

    @Override
    protected Map<String, Object> getParam(String ano) {

        Map<String, Object> mapa = new HashMap<String, Object>();

        if (ano != null && !ano.equals("")) {
            
            LocalDate datIni = LocalDate.of(Integer.parseInt(ano), JAN, DIA);
            LocalDate datFim = LocalDate.of(Integer.parseInt(ano), DEZ, DIA);

            mapa.put("DatIni", datIni);
            mapa.put("DatFim", datFim);
        }

        return mapa;
    }

}
