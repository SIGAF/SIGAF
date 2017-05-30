/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IDepartamentoBo;
import com.sigaf.Idao.IDepartamentoDao;
import com.sigaf.pojo.TDepartamento;
import java.util.List;

/**
 *
 * @author Genoves
 */
public class DepartamentoBo implements IDepartamentoBo {
    
    IDepartamentoDao idepartamentoDao;
    
    public IDepartamentoDao getIdepartamentoDao() {
        return idepartamentoDao;
    }
    
    public void setIdepartamentoDao(IDepartamentoDao idepartamentoDao) {
        this.idepartamentoDao = idepartamentoDao;
    }
    
    @Override
    public void create(TDepartamento departamento) {
        this.idepartamentoDao.create(departamento);
    }
    
    @Override
    public TDepartamento getTDepartamento(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TDepartamento> listTDepartamento() {
        
        return this.idepartamentoDao.listTDepartamento();
    }
    
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(TDepartamento departamento) {
        
        this.idepartamentoDao.update(departamento);
    }

    @Override
    public Boolean departaentoRepetido(String nombre) {
       return this.idepartamentoDao.departaentoRepetido(nombre);
    }
    
}
