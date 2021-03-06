package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TAgronegocio generated by hbm2java
 */
public class TAgronegocio  implements java.io.Serializable {


     private int idAgronegocio;
     private TComprador TComprador;
     private TConfiguracionAgronegocio TConfiguracionAgronegocio;
     private TEntidad TEntidad;
     private Date fecha;
     private Integer plazo;
     private String tipo;
     private String estado;
     private String codigo;
     private Date fechapago;
     private Boolean estadoregistro;
     private BigDecimal total;
     private BigDecimal comision;
     private BigDecimal interes;
     private Set TPagoAgronegocios = new HashSet(0);
     private Set TProductoAgronegocios = new HashSet(0);

    public TAgronegocio() {
    }

	
    public TAgronegocio(int idAgronegocio) {
        this.idAgronegocio = idAgronegocio;
    }
    public TAgronegocio(int idAgronegocio, TComprador TComprador, TConfiguracionAgronegocio TConfiguracionAgronegocio, TEntidad TEntidad, Date fecha, Integer plazo, String tipo, String estado, String codigo, Date fechapago, Boolean estadoregistro, BigDecimal total, BigDecimal comision, BigDecimal interes, Set TPagoAgronegocios, Set TProductoAgronegocios) {
       this.idAgronegocio = idAgronegocio;
       this.TComprador = TComprador;
       this.TConfiguracionAgronegocio = TConfiguracionAgronegocio;
       this.TEntidad = TEntidad;
       this.fecha = fecha;
       this.plazo = plazo;
       this.tipo = tipo;
       this.estado = estado;
       this.codigo = codigo;
       this.fechapago = fechapago;
       this.estadoregistro = estadoregistro;
       this.total = total;
       this.comision = comision;
       this.interes = interes;
       this.TPagoAgronegocios = TPagoAgronegocios;
       this.TProductoAgronegocios = TProductoAgronegocios;
    }
   
    public int getIdAgronegocio() {
        return this.idAgronegocio;
    }
    
    public void setIdAgronegocio(int idAgronegocio) {
        this.idAgronegocio = idAgronegocio;
    }
    public TComprador getTComprador() {
        return this.TComprador;
    }
    
    public void setTComprador(TComprador TComprador) {
        this.TComprador = TComprador;
    }
    public TConfiguracionAgronegocio getTConfiguracionAgronegocio() {
        return this.TConfiguracionAgronegocio;
    }
    
    public void setTConfiguracionAgronegocio(TConfiguracionAgronegocio TConfiguracionAgronegocio) {
        this.TConfiguracionAgronegocio = TConfiguracionAgronegocio;
    }
    public TEntidad getTEntidad() {
        return this.TEntidad;
    }
    
    public void setTEntidad(TEntidad TEntidad) {
        this.TEntidad = TEntidad;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Integer getPlazo() {
        return this.plazo;
    }
    
    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Date getFechapago() {
        return this.fechapago;
    }
    
    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }
    public Boolean getEstadoregistro() {
        return this.estadoregistro;
    }
    
    public void setEstadoregistro(Boolean estadoregistro) {
        this.estadoregistro = estadoregistro;
    }
    public BigDecimal getTotal() {
        return this.total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public BigDecimal getComision() {
        return this.comision;
    }
    
    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }
    public BigDecimal getInteres() {
        return this.interes;
    }
    
    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }
    public Set getTPagoAgronegocios() {
        return this.TPagoAgronegocios;
    }
    
    public void setTPagoAgronegocios(Set TPagoAgronegocios) {
        this.TPagoAgronegocios = TPagoAgronegocios;
    }
    public Set getTProductoAgronegocios() {
        return this.TProductoAgronegocios;
    }
    
    public void setTProductoAgronegocios(Set TProductoAgronegocios) {
        this.TProductoAgronegocios = TProductoAgronegocios;
    }




}


