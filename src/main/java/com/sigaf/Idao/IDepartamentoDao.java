/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TDepartamento;
import java.util.List;

/**
 *
 * @author Genoves
 */
public interface IDepartamentoDao {

    public void create(TDepartamento departamento);

    public TDepartamento getDepartamento(Integer id);

    public List<TDepartamento> listTDepartamento();

    public void delete(Integer id);

    public void update(TDepartamento departamento);
    
    public Boolean departaentoRepetido(String nombre);

}
