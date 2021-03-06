package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * TTipoGarantia generated by hbm2java
 */
public class TTipoGarantia  implements java.io.Serializable {


     private int idtipogarantia;
     private String descripcion;
     private BigDecimal precio;
     private Integer estado;
     private Set TGarantias = new HashSet(0);

    public TTipoGarantia() {
    }

	
    public TTipoGarantia(int idtipogarantia) {
        this.idtipogarantia = idtipogarantia;
    }
    public TTipoGarantia(int idtipogarantia, String descripcion, BigDecimal precio, Integer estado, Set TGarantias) {
       this.idtipogarantia = idtipogarantia;
       this.descripcion = descripcion;
       this.precio = precio;
       this.estado = estado;
       this.TGarantias = TGarantias;
    }
   
    public int getIdtipogarantia() {
        return this.idtipogarantia;
    }
    
    public void setIdtipogarantia(int idtipogarantia) {
        this.idtipogarantia = idtipogarantia;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public Integer getEstado() {
        return this.estado;
    }
    
    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    public Set getTGarantias() {
        return this.TGarantias;
    }
    
    public void setTGarantias(Set TGarantias) {
        this.TGarantias = TGarantias;
    }




}


