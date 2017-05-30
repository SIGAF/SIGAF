package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TComprador generated by hbm2java
 */
public class TComprador  implements java.io.Serializable {


     private int idComprador;
     private String nombresComprador;
     private String apellidosComprador;
     private String duiComprador;
     private String nitComprador;
     private String sexoComprador;
     private String tipoComprador;
     private String correoComprador;
     private String telefonoComprador;
     private String movilComprador;
     private String direccionComprador;
     private Boolean estadoComprador;
     private Date fechanacimientoComprador;
     private Set TAgronegocios = new HashSet(0);

    public TComprador() {
    }

	
    public TComprador(int idComprador) {
        this.idComprador = idComprador;
    }
    public TComprador(int idComprador, String nombresComprador, String apellidosComprador, String duiComprador, String nitComprador, String sexoComprador, String tipoComprador, String correoComprador, String telefonoComprador, String movilComprador, String direccionComprador, Boolean estadoComprador, Date fechanacimientoComprador, Set TAgronegocios) {
       this.idComprador = idComprador;
       this.nombresComprador = nombresComprador;
       this.apellidosComprador = apellidosComprador;
       this.duiComprador = duiComprador;
       this.nitComprador = nitComprador;
       this.sexoComprador = sexoComprador;
       this.tipoComprador = tipoComprador;
       this.correoComprador = correoComprador;
       this.telefonoComprador = telefonoComprador;
       this.movilComprador = movilComprador;
       this.direccionComprador = direccionComprador;
       this.estadoComprador = estadoComprador;
       this.fechanacimientoComprador = fechanacimientoComprador;
       this.TAgronegocios = TAgronegocios;
    }
   
    public int getIdComprador() {
        return this.idComprador;
    }
    
    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }
    public String getNombresComprador() {
        return this.nombresComprador;
    }
    
    public void setNombresComprador(String nombresComprador) {
        this.nombresComprador = nombresComprador;
    }
    public String getApellidosComprador() {
        return this.apellidosComprador;
    }
    
    public void setApellidosComprador(String apellidosComprador) {
        this.apellidosComprador = apellidosComprador;
    }
    public String getDuiComprador() {
        return this.duiComprador;
    }
    
    public void setDuiComprador(String duiComprador) {
        this.duiComprador = duiComprador;
    }
    public String getNitComprador() {
        return this.nitComprador;
    }
    
    public void setNitComprador(String nitComprador) {
        this.nitComprador = nitComprador;
    }
    public String getSexoComprador() {
        return this.sexoComprador;
    }
    
    public void setSexoComprador(String sexoComprador) {
        this.sexoComprador = sexoComprador;
    }
    public String getTipoComprador() {
        return this.tipoComprador;
    }
    
    public void setTipoComprador(String tipoComprador) {
        this.tipoComprador = tipoComprador;
    }
    public String getCorreoComprador() {
        return this.correoComprador;
    }
    
    public void setCorreoComprador(String correoComprador) {
        this.correoComprador = correoComprador;
    }
    public String getTelefonoComprador() {
        return this.telefonoComprador;
    }
    
    public void setTelefonoComprador(String telefonoComprador) {
        this.telefonoComprador = telefonoComprador;
    }
    public String getMovilComprador() {
        return this.movilComprador;
    }
    
    public void setMovilComprador(String movilComprador) {
        this.movilComprador = movilComprador;
    }
    public String getDireccionComprador() {
        return this.direccionComprador;
    }
    
    public void setDireccionComprador(String direccionComprador) {
        this.direccionComprador = direccionComprador;
    }
    public Boolean getEstadoComprador() {
        return this.estadoComprador;
    }
    
    public void setEstadoComprador(Boolean estadoComprador) {
        this.estadoComprador = estadoComprador;
    }
    public Date getFechanacimientoComprador() {
        return this.fechanacimientoComprador;
    }
    
    public void setFechanacimientoComprador(Date fechanacimientoComprador) {
        this.fechanacimientoComprador = fechanacimientoComprador;
    }
    public Set getTAgronegocios() {
        return this.TAgronegocios;
    }
    
    public void setTAgronegocios(Set TAgronegocios) {
        this.TAgronegocios = TAgronegocios;
    }




}

