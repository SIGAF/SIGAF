package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * TDesembolso generated by hbm2java
 */
public class TDesembolso  implements java.io.Serializable {


     private int idDesembolso;
     private TProyecto TProyecto;
     private Date fechaDesembolso;
     private BigDecimal montoDesembolso;

    public TDesembolso() {
    }

	
    public TDesembolso(int idDesembolso) {
        this.idDesembolso = idDesembolso;
    }
    public TDesembolso(int idDesembolso, TProyecto TProyecto, Date fechaDesembolso, BigDecimal montoDesembolso) {
       this.idDesembolso = idDesembolso;
       this.TProyecto = TProyecto;
       this.fechaDesembolso = fechaDesembolso;
       this.montoDesembolso = montoDesembolso;
    }
   
    public int getIdDesembolso() {
        return this.idDesembolso;
    }
    
    public void setIdDesembolso(int idDesembolso) {
        this.idDesembolso = idDesembolso;
    }
    public TProyecto getTProyecto() {
        return this.TProyecto;
    }
    
    public void setTProyecto(TProyecto TProyecto) {
        this.TProyecto = TProyecto;
    }
    public Date getFechaDesembolso() {
        return this.fechaDesembolso;
    }
    
    public void setFechaDesembolso(Date fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }
    public BigDecimal getMontoDesembolso() {
        return this.montoDesembolso;
    }
    
    public void setMontoDesembolso(BigDecimal montoDesembolso) {
        this.montoDesembolso = montoDesembolso;
    }




}


