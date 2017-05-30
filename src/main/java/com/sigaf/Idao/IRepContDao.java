/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;


import java.math.BigDecimal;



/**
 *
 * @author Eliseo Aguilar
 */
public interface IRepContDao {
    
  
    
  public BigDecimal saldosCuenta(Integer idCuenta, Integer idEjercicio,String tipoSaldo);
    
  
    
}
