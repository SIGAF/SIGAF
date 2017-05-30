package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1



/**
 * TReferencia generated by hbm2java
 */
public class TReferencia  implements java.io.Serializable {


     private int idreferencia;
     private TProyecto TProyecto;
     private String nombre;
     private String direccion;
     private String correo;
     private String telefono;
     private String celular;
     private String profesion;
     private Integer estado;

    public TReferencia() {
    }

	
    public TReferencia(int idreferencia) {
        this.idreferencia = idreferencia;
    }
    public TReferencia(int idreferencia, TProyecto TProyecto, String nombre, String direccion, String correo, String telefono, String celular, String profesion, Integer estado) {
       this.idreferencia = idreferencia;
       this.TProyecto = TProyecto;
       this.nombre = nombre;
       this.direccion = direccion;
       this.correo = correo;
       this.telefono = telefono;
       this.celular = celular;
       this.profesion = profesion;
       this.estado = estado;
    }
   
    public int getIdreferencia() {
        return this.idreferencia;
    }
    
    public void setIdreferencia(int idreferencia) {
        this.idreferencia = idreferencia;
    }
    public TProyecto getTProyecto() {
        return this.TProyecto;
    }
    
    public void setTProyecto(TProyecto TProyecto) {
        this.TProyecto = TProyecto;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getProfesion() {
        return this.profesion;
    }
    
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    public Integer getEstado() {
        return this.estado;
    }
    
    public void setEstado(Integer estado) {
        this.estado = estado;
    }




}


