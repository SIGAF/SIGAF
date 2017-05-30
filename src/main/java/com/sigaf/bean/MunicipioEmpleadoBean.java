/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IMunicipioEmpleadoBo;
import com.sigaf.bo.MunicipioEmpleadoBo;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Genoves
 */
@Named(value = "municipioempleadoBean")
@RequestScoped
@ManagedBean
public class MunicipioEmpleadoBean extends Actividad {

    private IMunicipioEmpleadoBo imunicipioEmpleadoBo;

    public IMunicipioEmpleadoBo getImunicipioEmpleadoBo() {
        return imunicipioEmpleadoBo;
    }

    public void setImunicipioEmpleadoBo(IMunicipioEmpleadoBo imunicipioEmpleadoBo) {
        this.imunicipioEmpleadoBo = imunicipioEmpleadoBo;
    }

   

   

    public void Init() {
        
    }

}
