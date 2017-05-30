package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TAreaCultivo generated by hbm2java
 */
public class TAreaCultivo  implements java.io.Serializable {


     private int idAreaCultivo;
     private String nombreAreaCultivo;
     private Boolean estadoAreaCultivo;
     private Set TTipoCultivos = new HashSet(0);

    public TAreaCultivo() {
    }

	
    public TAreaCultivo(int idAreaCultivo) {
        this.idAreaCultivo = idAreaCultivo;
    }
    public TAreaCultivo(int idAreaCultivo, String nombreAreaCultivo, Boolean estadoAreaCultivo, Set TTipoCultivos) {
       this.idAreaCultivo = idAreaCultivo;
       this.nombreAreaCultivo = nombreAreaCultivo;
       this.estadoAreaCultivo = estadoAreaCultivo;
       this.TTipoCultivos = TTipoCultivos;
    }
   
    public int getIdAreaCultivo() {
        return this.idAreaCultivo;
    }
    
    public void setIdAreaCultivo(int idAreaCultivo) {
        this.idAreaCultivo = idAreaCultivo;
    }
    public String getNombreAreaCultivo() {
        return this.nombreAreaCultivo;
    }
    
    public void setNombreAreaCultivo(String nombreAreaCultivo) {
        this.nombreAreaCultivo = nombreAreaCultivo;
    }
    public Boolean getEstadoAreaCultivo() {
        return this.estadoAreaCultivo;
    }
    
    public void setEstadoAreaCultivo(Boolean estadoAreaCultivo) {
        this.estadoAreaCultivo = estadoAreaCultivo;
    }
    public Set getTTipoCultivos() {
        return this.TTipoCultivos;
    }
    
    public void setTTipoCultivos(Set TTipoCultivos) {
        this.TTipoCultivos = TTipoCultivos;
    }




}

