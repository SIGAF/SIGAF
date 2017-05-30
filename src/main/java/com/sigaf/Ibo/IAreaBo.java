/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TArea;
import java.util.List;

/**
 *
 * @author Genoves
 */
public interface IAreaBo {
    
   public void create(TArea area);
 
   public TArea getTArea(Integer id);
   
   public TArea getTAreaRepAct(Integer idEnt,Integer idAre, String codigo);
 
   public TArea getTAreaRep(Integer idEnt,String codigo);
   
   public List<TArea> listArea(Integer id);
   
   public List<TArea> listArea2(Integer id);

   public void delete(Integer id);

   public void update(TArea area);

    
}
