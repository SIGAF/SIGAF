/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.ICuentaBo;
import com.sigaf.Idao.ICuentaDao;
import com.sigaf.pojo.TCuenta;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public class CuentaBo implements ICuentaBo{

    private ICuentaDao cuentaDao;

    public ICuentaDao getCuentaDao() {
        return cuentaDao;
    }

    public void setCuentaDao(ICuentaDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }
            
    @Override
    public void create(TCuenta cuenta) {
        
    this.cuentaDao.create(cuenta);
    
    }

    @Override
    public TCuenta getCuenta(Integer id) {
    return this.cuentaDao.getCuenta(id);
   }

    @Override
    public List<TCuenta> listCuentaSub(Integer id) {
    return  this.cuentaDao.listCuentaSub(id);
    }

    @Override
    public List<TCuenta> listCuentaEnt(Integer id) {
        return this.cuentaDao.listCuentaEnt(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TCuenta cuenta) {
    this.cuentaDao.update(cuenta);
    }

    @Override
    public TCuenta getCuentaRep(Integer id,String codigo) {
   return  this.cuentaDao.getCuentaRep(id,codigo);
    }

    @Override
    public TCuenta getCuentaRepAct(Integer idEnt,Integer idCue, String codigo){
        return  this.cuentaDao.getCuentaRepAct(idEnt,idCue, codigo);
    }

    @Override
    public List<TCuenta> listCuentaEntAct(Integer id, Boolean estado) {
        return this.cuentaDao.listCuentaEntAct(id, estado);
    }

    @Override
    public List<TCuenta> listCuentaEntActTip(Integer id, Boolean estado) {
        return this.cuentaDao.listCuentaEntActTip(id, estado);
    }
    
    
   
}
