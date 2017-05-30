/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TMunicipioEmpleado;
import java.util.List;

/**
 *
 * @author Genoves
 */
public interface IMunicipioEmpleadoDao {
    
    
   public void create(TMunicipioEmpleado municipioEmpleado);
 
   public TMunicipioEmpleado getTMunicipioEmpleado(Integer id);
 
   public List<TMunicipioEmpleado> listTMunicipioEmpleado(Integer id);

   public void delete(Integer id);

   public void update(TMunicipioEmpleado municipioEmpleado);
    
}
