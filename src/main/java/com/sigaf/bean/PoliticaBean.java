/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IPoliticaBo;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TPolitica;
import com.sigaf.pojo.TTipoCredito;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Genovés
 */
@Named(value = "politicaBean")
@ManagedBean
@RequestScoped
public class PoliticaBean extends Actividad {

    /**
     * Creates a new instance of PoliticaBean
     */
    IPoliticaBo ipoliticaBo;
    private TPolitica politicas;
    private List<TPolitica> listaPoliticas;
    private TPolitica politicaSeleccionada;
    private TEntidad entidad;
    private TTipoCredito credito;
    private Integer tipoDeCredito;
    private Integer empleado;
    private Integer inversion;
    private Integer produccion;
    private Integer lisiados;
    private Integer personal;
    private Integer comercio;
    private Integer capital;
    private Integer inversionAgro;
    private Integer produccionAgro;
    private Integer idEntidad;
    private Boolean estadoFormulario;
    private String msgEdadMinima;
    private String msgEdadMaxima;
    private String msgTasaInteres;
    private String msgTasaInteresMora;
    private String msgMontoMaximo;
    private String msgMontoMinimo;
    private String msgPagoMinimo;
    private String msgComision;
    private String msgSeguro;
    private String msgEndeudamientoMinimo;
    private String msgMenor;
    private String msgMayor;

    public void Init() {

        super.enableShowData();
        this.politicas = new TPolitica();
        this.credito = new TTipoCredito();
        this.entidad = new TEntidad();
        this.tipoDeCredito = 0;

        this.empleado = 1;
        this.inversion = 2;
        this.produccion = 3;
        this.produccionAgro = 4;
        this.lisiados = 5;
        this.personal = 6;
        this.comercio = 7;
        this.capital = 8;
        this.inversionAgro = 9;
        this.idEntidad = 1;
    }

    public Integer getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Integer empleado) {
        this.empleado = empleado;
    }

    public Integer getInversion() {
        return inversion;
    }

    public void setInversion(Integer inversion) {
        this.inversion = inversion;
    }

    public Integer getProduccion() {
        return produccion;
    }

    public void setProduccion(Integer produccion) {
        this.produccion = produccion;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public TEntidad getEntidad() {
        return entidad;
    }

    public void setEntidad(TEntidad entidad) {
        this.entidad = entidad;
    }

    public TTipoCredito getCredito() {
        return credito;
    }

    public void setCredito(TTipoCredito credito) {
        this.credito = credito;
    }

    public Integer getTipoDeCredito() {
        return tipoDeCredito;
    }

    public void setTipoDeCredito(Integer tipoDeCredito) {
        this.tipoDeCredito = tipoDeCredito;
    }

    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public IPoliticaBo getIpoliticaBo() {
        return ipoliticaBo;
    }

    public void setIpoliticaBo(IPoliticaBo ipoliticaBo) {
        this.ipoliticaBo = ipoliticaBo;
    }

    public TPolitica getPoliticas() {
        return politicas;
    }

    public void setPoliticas(TPolitica politicas) {
        this.politicas = politicas;
    }

    public List<TPolitica> getListaPoliticas(Integer id) {
        return this.listaPoliticas = ipoliticaBo.listPolitica(id);
    }

    public void setListaPoliticas(List<TPolitica> listaPoliticas) {
        this.listaPoliticas = listaPoliticas;
    }

    public TPolitica getPoliticaSeleccionada() {
        return politicaSeleccionada;
    }

    public void setPoliticaSeleccionada(TPolitica politicaSeleccionada) {
        this.politicaSeleccionada = politicaSeleccionada;
    }

    public String getMsgEdadMinima() {
        return msgEdadMinima;
    }

    public void setMsgEdadMinima(String msgEdadMinima) {
        this.msgEdadMinima = msgEdadMinima;
    }

    public String getMsgEdadMaxima() {
        return msgEdadMaxima;
    }

    public void setMsgEdadMaxima(String msgEdadMaxima) {
        this.msgEdadMaxima = msgEdadMaxima;
    }

    public String getMsgTasaInteres() {
        return msgTasaInteres;
    }

    public void setMsgTasaInteres(String msgTasaInteres) {
        this.msgTasaInteres = msgTasaInteres;
    }

    public String getMsgTasaInteresMora() {
        return msgTasaInteresMora;
    }

    public void setMsgTasaInteresMora(String msgTasaInteresMora) {
        this.msgTasaInteresMora = msgTasaInteresMora;
    }

    public String getMsgMontoMaximo() {
        return msgMontoMaximo;
    }

    public void setMsgMontoMaximo(String msgMontoMaximo) {
        this.msgMontoMaximo = msgMontoMaximo;
    }

    public String getMsgMontoMinimo() {
        return msgMontoMinimo;
    }

    public void setMsgMontoMinimo(String msgMontoMinimo) {
        this.msgMontoMinimo = msgMontoMinimo;
    }

    public String getMsgPagoMinimo() {
        return msgPagoMinimo;
    }

    public void setMsgPagoMinimo(String msgPagoMinimo) {
        this.msgPagoMinimo = msgPagoMinimo;
    }

    public String getMsgComision() {
        return msgComision;
    }

    public void setMsgComision(String msgComision) {
        this.msgComision = msgComision;
    }

    public String getMsgSeguro() {
        return msgSeguro;
    }

    public void setMsgSeguro(String msgSeguro) {
        this.msgSeguro = msgSeguro;
    }

    public String getMsgEndeudamientoMinimo() {
        return msgEndeudamientoMinimo;
    }

    public void setMsgEndeudamientoMinimo(String msgEndeudamientoMinimo) {
        this.msgEndeudamientoMinimo = msgEndeudamientoMinimo;
    }

    public String getMsgMenor() {
        return msgMenor;
    }

    public void setMsgMenor(String msgMenor) {
        this.msgMenor = msgMenor;
    }

    public String getMsgMayor() {
        return msgMayor;
    }

    public void setMsgMayor(String msgMayor) {
        this.msgMayor = msgMayor;
    }

    public Integer getLisiados() {
        return lisiados;
    }

    public void setLisiados(Integer lisiados) {
        this.lisiados = lisiados;
    }

    public Integer getPersonal() {
        return personal;
    }

    public void setPersonal(Integer personal) {
        this.personal = personal;
    }

    public Integer getComercio() {
        return comercio;
    }

    public void setComercio(Integer comercio) {
        this.comercio = comercio;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public Integer getInversionAgro() {
        return inversionAgro;
    }

    public void setInversionAgro(Integer inversionAgro) {
        this.inversionAgro = inversionAgro;
    }

    public Integer getProduccionAgro() {
        return produccionAgro;
    }

    public void setProduccionAgro(Integer produccionAgro) {
        this.produccionAgro = produccionAgro;
    }

    public void crear() {

        try {
            if (this.listaPoliticas.isEmpty()) {
                this.politicas.setEstado(true);
                //this.credito.setIdTipoCredito(this.getTipoDeCredito());
                this.politicas.setTTipoCredito(new TTipoCredito(this.tipoDeCredito));

              
                this.ipoliticaBo.create(politicas);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Política ingresada correctamente"));
                limpiar(1);
            } else {

                this.ipoliticaBo.update(this.tipoDeCredito);
                this.politicas.setEstado(true);
                //this.credito.setIdTipoCredito(this.getTipoDeCredito());
                this.politicas.setTTipoCredito(new TTipoCredito(this.tipoDeCredito));

              
                this.ipoliticaBo.create(politicas);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Política ingresada correctamente"));
                limpiar(1);
            }

        } catch (Exception e) {
        }
    }

    public void limpiar(int opc) {

        this.estadoFormulario = false;
        this.politicas = new TPolitica();
        this.msgComision = "";
        this.msgEdadMaxima = "";
        this.msgEdadMinima = "";
        this.msgEndeudamientoMinimo = "";
        this.msgMontoMaximo = "";
        this.msgMontoMinimo = "";
        this.msgPagoMinimo = "";
        this.msgSeguro = "";
        this.msgTasaInteres = "";
        this.msgTasaInteresMora = "";
        if (opc == 1) {
            super.enableShowData();
        }
    }

    public void validarFormulario() {

        this.estadoFormulario = true;
        if (this.politicas.getCapacidadEndeudamientoMinimo() == null) {
            this.msgEndeudamientoMinimo = "Si este campo no se tomará como política deberá ser 0.00";
            this.estadoFormulario = false;
        } else {
            this.msgEndeudamientoMinimo = "";
        }
        if (this.politicas.getCapacidadPagoMinimo() == null) {
            this.msgPagoMinimo = "Si este campo no se tomará como política deberá ser 0.00";
            this.estadoFormulario = false;
        } else {
            this.msgPagoMinimo = "";
        }
        if (this.politicas.getComision() == null) {
            this.msgComision = "Si este campo no se tomará como política deberá ser 0.00";
            this.estadoFormulario = false;
        } else {
            this.msgComision = "";
        }
        if (this.politicas.getEdadMaxima() == null) {
            this.msgEdadMaxima = "Si este campo no se tomará como política deberá ser 0.00";
            this.estadoFormulario = false;
        } else {
            this.msgEdadMaxima = "";
        }
        if (this.politicas.getEdadMaxima() != null) {
        if (this.politicas.getEdadMaxima() < 18) {
            this.msgEdadMaxima = "La edad debe ser mayor que 18 años";
            this.estadoFormulario = false;
        } else {
            this.msgEdadMaxima = "";
        }
        }
        if (this.politicas.getEdadMinima() == null) {
            this.msgEdadMinima = "Si este campo no se tomará como política deberá ser 0.00";
            this.estadoFormulario = false;
        } else {
            this.msgEdadMinima = "";
        }
        if (this.politicas.getEdadMinima() != null) {
        if (this.politicas.getEdadMinima() < 18) {
            this.msgEdadMinima = "La edad mínima debe ser mayor que 18 años";
            this.estadoFormulario = false;
        } else {
            this.msgEdadMinima = "";
        }
        }
        if(this.politicas.getEdadMaxima()!=null){
        if (this.politicas.getEdadMinima() > this.politicas.getEdadMaxima()) {
            this.msgMayor = "La edad mínima debe ser menor que la edad máxima";
            this.estadoFormulario = false;
        } else {
            this.msgMayor = "";
        }
        }
        
        if (this.politicas.getMontoMaximo() == null) {
            this.msgMontoMaximo = "Si este campo no se tomará como política deberá ser 0.00";
            this.estadoFormulario = false;
        } else {
            this.msgMontoMaximo = "";
        }
        if (this.politicas.getMontoMinimo() == null) {
            this.msgMontoMinimo = "Si este campo no se tomará como política deberá ser 0.00";
            this.estadoFormulario = false;
        } else {
            this.msgMontoMinimo = "";
        }
        
        if(this.politicas.getMontoMaximo()!=null){
        if (this.politicas.getMontoMinimo().compareTo(this.politicas.getMontoMaximo())> 0 ) {
            this.msgMenor = "El monto mínimo debe ser menor que el monto máximo";
            this.estadoFormulario = false;
        } else {
            this.msgMenor = "";
        }
        }
        
        if (this.politicas.getSeguroDeuda() == null) {
            this.msgSeguro = "Si este campo no se tomará como política deberá ser 0.00";
            this.estadoFormulario = false;
        } else {
            this.msgSeguro = "";
        }
        if (this.politicas.getTasaInteres() == null) {
            this.msgTasaInteres = "Si este campo no se tomará como política deberá ser 0.00";
            this.estadoFormulario = false;
        } else {
            this.msgTasaInteres = "";
        }
        if (this.politicas.getTasaInteresMora() == null) {
            this.msgTasaInteresMora = "Si este campo no se tomará como política deberá ser 0.00";
            this.estadoFormulario = false;
        } else {
            this.msgTasaInteresMora = "";
        }

    }

    public void asignar(Integer valor) {

        this.tipoDeCredito = valor;

    }
}
