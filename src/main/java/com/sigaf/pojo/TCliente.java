package com.sigaf.pojo;
// Generated 04-03-2017 03:09:46 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TCliente generated by hbm2java
 */
public class TCliente  implements java.io.Serializable {


     private int idCliente;
     private String nombreCliente;
     private String apellidoCliente;
     private String duiCliente;
     private String nitCliente;
     private String direccionCliente;
     private Date fechaNacimiento;
     private String estadoFamiliar;
     private String telefonoCliente;
     private String correoCliente;
     private String celularCliente;
     private String calificacionCliente;
     private String codigoCliente;
     private String duiCopiaCliente;
     private String nitCopiaCliente;
     private String sexoCliente;
     private Integer tipoCliente1;
     private Integer tipoCliente2;
     private Integer tipoCliente3;

    public Integer getTipoCliente1() {
        return tipoCliente1;
    }

    public void setTipoCliente1(Integer tipoCliente1) {
        this.tipoCliente1 = tipoCliente1;
    }

    public Integer getTipoCliente3() {
        return tipoCliente3;
    }

    public void setTipoCliente3(Integer tipoCliente3) {
        this.tipoCliente3 = tipoCliente3;
    }

    public Integer getTipoCliente2() {
        return tipoCliente2;
    }

    public void setTipoCliente2(Integer tipoCliente2) {
        this.tipoCliente2 = tipoCliente2;
    }
     private Set TLisiados = new HashSet(0);
     private Set TTrabajos = new HashSet(0);
     private Set TClienteProyectos = new HashSet(0);

    public TCliente() {
    }

	
    public TCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public TCliente(int idCliente, String nombreCliente, String apellidoCliente, String duiCliente, String nitCliente, String direccionCliente, Date fechaNacimiento, String estadoFamiliar, String telefonoCliente, String correoCliente, String celularCliente, String calificacionCliente, String codigoCliente, String duiCopiaCliente, String nitCopiaCliente, String sexoCliente, Integer tipoCliente1, Integer tipoCliente2,Integer tipoCliente3,Set TLisiados, Set TTrabajos, Set TClienteProyectos) {
       this.idCliente = idCliente;
       this.nombreCliente = nombreCliente;
       this.apellidoCliente = apellidoCliente;
       this.duiCliente = duiCliente;
       this.nitCliente = nitCliente;
       this.direccionCliente = direccionCliente;
       this.fechaNacimiento = fechaNacimiento;
       this.estadoFamiliar = estadoFamiliar;
       this.telefonoCliente = telefonoCliente;
       this.correoCliente = correoCliente;
       this.celularCliente = celularCliente;
       this.calificacionCliente = calificacionCliente;
       this.codigoCliente = codigoCliente;
       this.duiCopiaCliente = duiCopiaCliente;
       this.nitCopiaCliente = nitCopiaCliente;
       this.sexoCliente = sexoCliente;
       this.tipoCliente1 = tipoCliente1;
       this.tipoCliente2 = tipoCliente2;
       this.tipoCliente3 = tipoCliente3;
       this.TLisiados = TLisiados;
       this.TTrabajos = TTrabajos;
       this.TClienteProyectos = TClienteProyectos;
    }
   
    public int getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public String getNombreCliente() {
        return this.nombreCliente;
    }
    
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public String getApellidoCliente() {
        return this.apellidoCliente;
    }
    
    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }
    public String getDuiCliente() {
        return this.duiCliente;
    }
    
    public void setDuiCliente(String duiCliente) {
        this.duiCliente = duiCliente;
    }
    public String getNitCliente() {
        return this.nitCliente;
    }
    
    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }
    public String getDireccionCliente() {
        return this.direccionCliente;
    }
    
    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getEstadoFamiliar() {
        return this.estadoFamiliar;
    }
    
    public void setEstadoFamiliar(String estadoFamiliar) {
        this.estadoFamiliar = estadoFamiliar;
    }
    public String getTelefonoCliente() {
        return this.telefonoCliente;
    }
    
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
    public String getCorreoCliente() {
        return this.correoCliente;
    }
    
    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }
    public String getCelularCliente() {
        return this.celularCliente;
    }
    
    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }
    public String getCalificacionCliente() {
        return this.calificacionCliente;
    }
    
    public void setCalificacionCliente(String calificacionCliente) {
        this.calificacionCliente = calificacionCliente;
    }
    public String getCodigoCliente() {
        return this.codigoCliente;
    }
    
    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    public String getDuiCopiaCliente() {
        return this.duiCopiaCliente;
    }
    
    public void setDuiCopiaCliente(String duiCopiaCliente) {
        this.duiCopiaCliente = duiCopiaCliente;
    }
    public String getNitCopiaCliente() {
        return this.nitCopiaCliente;
    }
    
    public void setNitCopiaCliente(String nitCopiaCliente) {
        this.nitCopiaCliente = nitCopiaCliente;
    }
    public String getSexoCliente() {
        return this.sexoCliente;
    }
    
    public void setSexoCliente(String sexoCliente) {
        this.sexoCliente = sexoCliente;
    }
   
    public Set getTLisiados() {
        return this.TLisiados;
    }
    
    public void setTLisiados(Set TLisiados) {
        this.TLisiados = TLisiados;
    }
    public Set getTTrabajos() {
        return this.TTrabajos;
    }
    
    public void setTTrabajos(Set TTrabajos) {
        this.TTrabajos = TTrabajos;
    }
    public Set getTClienteProyectos() {
        return this.TClienteProyectos;
    }
    
    public void setTClienteProyectos(Set TClienteProyectos) {
        this.TClienteProyectos = TClienteProyectos;
    }




}


