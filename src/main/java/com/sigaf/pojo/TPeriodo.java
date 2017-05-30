package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TPeriodo generated by hbm2java
 */
public class TPeriodo  implements java.io.Serializable {


     private int idPeriodo;
     private TEjercicio TEjercicio;
     private Boolean estadoPeriodo;
     private String mesPeriodo;
     private Set TPartidas = new HashSet(0);

    public TPeriodo() {
    }

	
    public TPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }
    public TPeriodo(int idPeriodo, TEjercicio TEjercicio, Boolean estadoPeriodo, String mesPeriodo, Set TPartidas) {
       this.idPeriodo = idPeriodo;
       this.TEjercicio = TEjercicio;
       this.estadoPeriodo = estadoPeriodo;
       this.mesPeriodo = mesPeriodo;
       this.TPartidas = TPartidas;
    }
   
    public int getIdPeriodo() {
        return this.idPeriodo;
    }
    
    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }
    public TEjercicio getTEjercicio() {
        return this.TEjercicio;
    }
    
    public void setTEjercicio(TEjercicio TEjercicio) {
        this.TEjercicio = TEjercicio;
    }
    public Boolean getEstadoPeriodo() {
        return this.estadoPeriodo;
    }
    
    public void setEstadoPeriodo(Boolean estadoPeriodo) {
        this.estadoPeriodo = estadoPeriodo;
    }
    public String getMesPeriodo() {
        return this.mesPeriodo;
    }
    
    public void setMesPeriodo(String mesPeriodo) {
        this.mesPeriodo = mesPeriodo;
    }
    public Set getTPartidas() {
        return this.TPartidas;
    }
    
    public void setTPartidas(Set TPartidas) {
        this.TPartidas = TPartidas;
    }




}

