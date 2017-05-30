/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TPago;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IPagoBo {
    
    public void create(TPago pago);

    public TPago getTPago(Integer id);

    public List<TPago> listTPago(Integer id);
    
    public Integer getCorrelativo();

    public void delete(Integer id);

    public void update(TPago pago);
    
}
