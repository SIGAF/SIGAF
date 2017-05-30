/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IMunicipioBo;
import com.sigaf.Idao.IMunicipioDao;
import com.sigaf.pojo.TMunicipio;
import java.util.List;

/**
 *
 * @author Genoves
 */
public class MunicipioBo implements IMunicipioBo{

    IMunicipioDao imunicipioDao;

    public IMunicipioDao getImunicipioDao() {
        return imunicipioDao;
    }

    public void setImunicipioDao(IMunicipioDao imunicipioDao) {
        this.imunicipioDao = imunicipioDao;
    }
    
    
    @Override
    public void create(TMunicipio municipio) {
        this.imunicipioDao.create(municipio);
    }


    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TMunicipio municipio) {
        this.imunicipioDao.update(municipio);
    }

    @Override
    public List<TMunicipio> listTMunicipio() {
        return this.imunicipioDao.listTMunicipio();
    }

    @Override
    public TMunicipio getMunicipio(Integer id) {
       return this.imunicipioDao.getMunicipio(id);
    }

    @Override
    public List<TMunicipio> listCargarMunicipios(Integer id) {
        return this.imunicipioDao.listCargarMunicipios(id);
    }

    @Override
    public Boolean municipioRepetido(String nombre, int idDepartamento) {
        return this.imunicipioDao.municipioRepetido(nombre, idDepartamento);
    }

    

   
    
}
