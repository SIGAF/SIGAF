/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.ICargoBo;
import com.sigaf.pojo.TCargo;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Genoves
 */
@Named(value = "cargoBean")
@RequestScoped
@ManagedBean
public class CargoBean {

    
    private List<TCargo> listaCargos;
    private TCargo cargos;
    private TCargo cargoSeleccionado;
    private ICargoBo icargoBo;
    /**
     * Creates a new instance of CargoBean
     */
    public void Init(){
    
        this.listaCargos = this.icargoBo.listCargo(1);
    
    }

    public ICargoBo getIcargoBo() {
        return icargoBo;
    }

    public void setIcargoBo(ICargoBo icargoBo) {
        this.icargoBo = icargoBo;
    }

    public List<TCargo> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<TCargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public TCargo getCargos() {
        return cargos;
    }

    public void setCargos(TCargo cargos) {
        this.cargos = cargos;
    }

    public TCargo getCargoSeleccionado() {
        return cargoSeleccionado;
    }

    public void setCargoSeleccionado(TCargo cargoSeleccionado) {
        this.cargoSeleccionado = cargoSeleccionado;
    }
    
    
}
