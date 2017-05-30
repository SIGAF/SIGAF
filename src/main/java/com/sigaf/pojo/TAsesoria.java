package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * TAsesoria generated by hbm2java
 */
public class TAsesoria  implements java.io.Serializable {


     private int idasesoria;
     private TEmpleado TEmpleado;
     private TProyecto TProyecto;
     private String descripcion;
     private Date fecha;
     private Integer beneficiarios;
     private Boolean estado;
     private BigDecimal precio;

    public TAsesoria() {
    }

	
    public TAsesoria(int idasesoria) {
        this.idasesoria = idasesoria;
    }
    public TAsesoria(int idasesoria, TEmpleado TEmpleado, TProyecto TProyecto, String descripcion, Date fecha, Integer beneficiarios, Boolean estado, BigDecimal precio) {
       this.idasesoria = idasesoria;
       this.TEmpleado = TEmpleado;
       this.TProyecto = TProyecto;
       this.descripcion = descripcion;
       this.fecha = fecha;
       this.beneficiarios = beneficiarios;
       this.estado = estado;
       this.precio = precio;
    }
   
    public int getIdasesoria() {
        return this.idasesoria;
    }
    
    public void setIdasesoria(int idasesoria) {
        this.idasesoria = idasesoria;
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
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Integer getBeneficiarios() {
        return this.beneficiarios;
    }
    
    public void setBeneficiarios(Integer beneficiarios) {
        this.beneficiarios = beneficiarios;
    }
    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }




}

