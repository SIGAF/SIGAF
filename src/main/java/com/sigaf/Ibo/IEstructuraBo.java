/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TActivoFijo;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEstructura;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public interface IEstructuraBo {
    
    public void create(TEstructura estructura);

    public TEstructura getEstructura(Integer id);

    public List<TEstructura> listEstructura(Integer idEjercicio,Integer idTipo);
    
    public void delete(Integer id,Integer tipoRep);

    public void update(TEstructura estructura);
    
}
