/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IRepoContBo;
import com.sigaf.Idao.IRepContDao;
import java.math.BigDecimal;


/**
 *
 * @author Eliseo Aguilar
 */
public class RepContBo implements IRepoContBo{
    
    IRepContDao repContDao;

    public IRepContDao getRepContDao() {
        return repContDao;
    }

    public void setRepContDao(IRepContDao repContDao) {
        this.repContDao = repContDao;
    }
    
    

    @Override
    public BigDecimal saldosCuenta(Integer idCuenta, Integer idEjercicio,String tipoSaldo){
    return this.repContDao.saldosCuenta(idCuenta, idEjercicio,tipoSaldo);
    }

  
    
}
