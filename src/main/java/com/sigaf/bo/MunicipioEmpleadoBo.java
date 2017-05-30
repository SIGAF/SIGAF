/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;


import com.sigaf.Ibo.IMunicipioEmpleadoBo;
import com.sigaf.Idao.IMunicipioEmpleadoDao;

import com.sigaf.dao.MunicipioEmpleadoDao;

import com.sigaf.pojo.TMunicipioEmpleado;
import java.util.List;

/**
 *
 * @author Genoves
 */
public class MunicipioEmpleadoBo implements IMunicipioEmpleadoBo{
    
    IMunicipioEmpleadoDao imunicipioEmpleadoDao;

    public IMunicipioEmpleadoDao getImunicipioEmpleadoDao() {
        return imunicipioEmpleadoDao;
    }

    public void setImunicipioEmpleadoDao(IMunicipioEmpleadoDao imunicipioEmpleadoDao) {
        this.imunicipioEmpleadoDao = imunicipioEmpleadoDao;
    }



   
    @Override
    public void create(TMunicipioEmpleado municipioEmpleado) {
        this.imunicipioEmpleadoDao.create(municipioEmpleado);
    }

    @Override
    public TMunicipioEmpleado getTMunicipioEmpleado(Integer id) {
      return this.imunicipioEmpleadoDao.getTMunicipioEmpleado(id);
    }

    @Override
    public List<TMunicipioEmpleado> listTMunicipioEmpleado(Integer id) {
        return this.imunicipioEmpleadoDao.listTMunicipioEmpleado(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TMunicipioEmpleado municipioEmpleado) {
       this.imunicipioEmpleadoDao.update(municipioEmpleado);
    }
    

    
    
}
