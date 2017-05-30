package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1



/**
 * TSugerencia generated by hbm2java
 */
public class TSugerencia  implements java.io.Serializable {


     private int idsugerencia;
     private TEmpleado TEmpleado;
     private TProyecto TProyecto;
     private String descripcion;
     private Integer estado;

    public TSugerencia() {
    }

	
    public TSugerencia(int idsugerencia) {
        this.idsugerencia = idsugerencia;
    }
    public TSugerencia(int idsugerencia, TEmpleado TEmpleado, TProyecto TProyecto, String descripcion, Integer estado) {
       this.idsugerencia = idsugerencia;
       this.TEmpleado = TEmpleado;
       this.TProyecto = TProyecto;
       this.descripcion = descripcion;
       this.estado = estado;
    }
   
    public int getIdsugerencia() {
        return this.idsugerencia;
    }
    
    public void setIdsugerencia(int idsugerencia) {
        this.idsugerencia = idsugerencia;
    }
    public TEmpleado getTEmpleado() {
        return this.TEmpleado;
    }
    
    public void setTEmpleado(TEmpleado TEmpleado) {
        this.TEmpleado = TEmpleado;
    }
    public TProyecto getTProyecto() {
        return this.TProyecto;
    }
    
    public void setTProyecto(TProyecto TProyecto) {
        this.TProyecto = TProyecto;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getEstado() {
        return this.estado;
    }
    
    public void setEstado(Integer estado) {
        this.estado = estado;
    }




}


