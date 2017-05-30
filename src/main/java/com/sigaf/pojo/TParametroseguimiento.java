package com.sigaf.pojo;
// Generated 05-07-2017 10:04:17 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TParametroseguimiento generated by hbm2java
 */
public class TParametroseguimiento  implements java.io.Serializable {


     private int idparametro;
     private String nombre;
     private String descripcion;
     private String unidad;
     private Integer estado;
     private Set TParametros = new HashSet(0);

    public TParametroseguimiento() {
    }

	
    public TParametroseguimiento(int idparametro) {
        this.idparametro = idparametro;
    }
    public TParametroseguimiento(int idparametro, String nombre, String descripcion, String unidad, Integer estado, Set TParametros) {
       this.idparametro = idparametro;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.unidad = unidad;
       this.estado = estado;
       this.TParametros = TParametros;
    }
   
    public int getIdparametro() {
        return this.idparametro;
    }
    
    public void setIdparametro(int idparametro) {
        this.idparametro = idparametro;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getUnidad() {
        return this.unidad;
    }
    
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    public Integer getEstado() {
        return this.estado;
    }
    
    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    public Set getTParametros() {
        return this.TParametros;
    }
    
    public void setTParametros(Set TParametros) {
        this.TParametros = TParametros;
    }




}


