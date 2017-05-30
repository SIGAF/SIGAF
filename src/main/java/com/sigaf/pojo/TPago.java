package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * TPago generated by hbm2java
 */
public class TPago  implements java.io.Serializable {


     private int idpago;
     private TProyecto TProyecto;
     private BigDecimal mora;
     private BigDecimal cuota;
     private BigDecimal interes;
     private BigDecimal abono;
     private BigDecimal capitalamortizado;
     private Date fecha;
     private BigDecimal saldocapital;
     private BigDecimal saldoadicional;
     private Integer referencia;
     private String destino;

    public Integer getReferencia() {
        return referencia;
    }

    public void setReferencia(Integer referencia) {
        this.referencia = referencia;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public TPago() {
    }

	
    public TPago(int idpago) {
        this.idpago = idpago;
    }
    public TPago(int idpago, TProyecto TProyecto,Integer referencia, String destino, BigDecimal mora, BigDecimal cuota, BigDecimal interes, BigDecimal abono, BigDecimal capitalamortizado, Date fecha, BigDecimal saldocapital, BigDecimal saldoadicional) {
       this.idpago = idpago;
       this.TProyecto = TProyecto;
       this.mora = mora;
       this.cuota = cuota;
       this.interes = interes;
       this.abono = abono;
       this.capitalamortizado = capitalamortizado;
       this.fecha = fecha;
       this.saldocapital = saldocapital;
       this.saldoadicional = saldoadicional;
       this.referencia= referencia;
       this.destino= destino;
    }
   
    public int getIdpago() {
        return this.idpago;
    }
    
    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }
    public TProyecto getTProyecto() {
        return this.TProyecto;
    }
    
    public void setTProyecto(TProyecto TProyecto) {
        this.TProyecto = TProyecto;
    }
    public BigDecimal getMora() {
        return this.mora;
    }
    
    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }
    public BigDecimal getCuota() {
        return this.cuota;
    }
    
    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }
    public BigDecimal getInteres() {
        return this.interes;
    }
    
    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }
    public BigDecimal getAbono() {
        return this.abono;
    }
    
    public void setAbono(BigDecimal abono) {
        this.abono = abono;
    }
    public BigDecimal getCapitalamortizado() {
        return this.capitalamortizado;
    }
    
    public void setCapitalamortizado(BigDecimal capitalamortizado) {
        this.capitalamortizado = capitalamortizado;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public BigDecimal getSaldocapital() {
        return this.saldocapital;
    }
    
    public void setSaldocapital(BigDecimal saldocapital) {
        this.saldocapital = saldocapital;
    }
    public BigDecimal getSaldoadicional() {
        return this.saldoadicional;
    }
    
    public void setSaldoadicional(BigDecimal saldoadicional) {
        this.saldoadicional = saldoadicional;
    }




}


