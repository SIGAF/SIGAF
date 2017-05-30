/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TGarantia;
import java.util.List;

/**
 *
 * @author Genovés
 */
public interface IGarantiaProDao {
  
     public void create(TGarantia garantia);
 
   public TGarantia getTGarantia(Integer id);
 
   public List<TGarantia> listTGarantia(Integer id);

   public void delete(Integer id);

   public void update(TGarantia garantia);  
}
