/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TParametro;
import com.sigaf.pojo.TParametroseguimiento;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IParametroSeguimientoBo {
    
    
    public void create(TParametroseguimiento paramentro);

    public TParametro getParametroSeguimiento(Integer id);
    
    public List<TParametroseguimiento> listParametroSeguimiento();

    public void delete(Integer id);

    public void update(TParametroseguimiento parametro);
    
    public Boolean getParametroRepetido(String nombre);
    
}
