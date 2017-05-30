/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TMunicipio;
import java.util.List;

/**
 *
 * @author Genoves
 */
public interface IMunicipioDao {
    
    public void create(TMunicipio municipio);

    public TMunicipio getMunicipio(Integer id);

    public List<TMunicipio> listTMunicipio();
    
    public List<TMunicipio> listCargarMunicipios(Integer id);

    public void delete(Integer id);

    public void update(TMunicipio municipio);
    
    public Boolean municipioRepetido(String nombre, int idDepartamento);
    
    
    
}
