package com.sigaf.pojo;
// Generated 05-07-2017 10:04:17 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * TParametro generated by hbm2java
 */
public class TParametro  implements java.io.Serializable {


     private int idParametro;
     private TParametroseguimiento TParametroseguimiento;
     private TSeguimiento TSeguimiento;
     private BigDecimal valor;
     private String descripcion;
     private Boolean estado;

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public TParametro() {
    }

	
    public TParametro(int idParametro) {
        this.idParametro = idParametro;
    }
    public TParametro(int idParametro,Boolean estado, TParametroseguimiento TParametroseguimiento, TSeguimiento TSeguimiento, BigDecimal valor, String descripcion) {
       this.idParametro = idParametro;
       this.TParametroseguimiento = TParametroseguimiento;
       this.TSeguimiento = TSeguimiento;
       this.valor = valor;
       this.descripcion = descripcion;
       this.estado= estado;
    }
   
    public int getIdParametro() {
        return this.idParametro;
    }
    
    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }
    public TParametroseguimiento getTParametroseguimiento() {
        return this.TParametroseguimiento;
    }
    
    public void setTParametroseguimiento(TParametroseguimiento TParametroseguimiento) {
        this.TParametroseguimiento = TParametroseguimiento;
    }
    public TSeguimiento getTSeguimiento() {
        return this.TSeguimiento;
    }
    
    public void setTSeguimiento(TSeguimiento TSeguimiento) {
        this.TSeguimiento = TSeguimiento;
    }
    public BigDecimal getValor() {
        return this.valor;
    }
    
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }




}


