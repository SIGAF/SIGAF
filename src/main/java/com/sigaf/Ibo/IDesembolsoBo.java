/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TDesembolso;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IDesembolsoBo {
    
    public void create(TDesembolso desembolso);

    public TDesembolso getDesembolso(Integer id);

    public List<TDesembolso> listDesembolso();
    
    public List<TDesembolso> listDesembolso(Integer id);

    public void delete(Integer id);

    public void update(TDesembolso desembolso);
    
}
