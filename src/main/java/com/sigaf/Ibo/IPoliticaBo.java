/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TPolitica;
import java.util.List;

/**
 *
 * @author Genovés
 */
public interface IPoliticaBo {

    public void create(TPolitica politica);

    public TPolitica getPolitica(Integer id);
    
    public TPolitica getPoliticaHistorica(Integer id);

    public List<TPolitica> listPolitica(Integer id);

    public void delete(Integer id);

    public void update(Integer id);

}
