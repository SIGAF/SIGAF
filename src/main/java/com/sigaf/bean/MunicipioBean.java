/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IDepartamentoBo;
import com.sigaf.Ibo.IMunicipioBo;
import com.sigaf.pojo.TDepartamento;
import com.sigaf.pojo.TMunicipio;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Genoves
 */
@Named(value = "municipioBean")
@RequestScoped
@ManagedBean
public class MunicipioBean extends Actividad {

    TMunicipio municipios;
    TMunicipio municipioSeleccionado;
    List<TMunicipio> listaMunicipio;
    TDepartamento departamento;
    IMunicipioBo imunicipioBo;
    IDepartamentoBo idepartamentoBo;
    private Boolean estadoFormulario;
    private Integer idDepartamentoAuxiliar;

    private String msgNombre;
    private String msgNombreRepetido;
    private String msgDepartamento;
    private String nombreMunicipio;

    public String getMsgDepartamento() {
        return msgDepartamento;
    }

    public void setMsgDepartamento(String msgDepartamento) {
        this.msgDepartamento = msgDepartamento;
    }

    public String getMsgNombreRepetido() {
        return msgNombreRepetido;
    }

    public void setMsgNombreRepetido(String msgNombreRepetido) {
        this.msgNombreRepetido = msgNombreRepetido;
    }

    public IDepartamentoBo getIdepartamentoBo() {
        return idepartamentoBo;
    }

    public void setIdepartamentoBo(IDepartamentoBo idepartamentoBo) {
        this.idepartamentoBo = idepartamentoBo;
    }

    public IMunicipioBo getImunicipioBo() {
        return imunicipioBo;
    }

    public void setImunicipioBo(IMunicipioBo imunicipioBo) {
        this.imunicipioBo = imunicipioBo;
    }

    /**
     * Creates a new instance of MunicipioBean
     */
    public void Init() {

        this.municipios = new TMunicipio();
        this.municipios.setTDepartamento(new TDepartamento());
        this.estadoFormulario = false;
        super.enableShowCreate();

    }

    public Integer getIdDepartamentoAuxiliar() {
        return idDepartamentoAuxiliar;
    }

    public void setIdDepartamentoAuxiliar(Integer idDepartamentoAuxiliar) {
        this.idDepartamentoAuxiliar = idDepartamentoAuxiliar;
    }

    public TMunicipio getMunicipios() {
        return municipios;
    }

    public void setMunicipios(TMunicipio municipios) {
        this.municipios = municipios;
    }

    public TMunicipio getMunicipioSeleccionado() {
        return municipioSeleccionado;
    }

    public void setMunicipioSeleccionado(TMunicipio municipioSeleccionado) {
        this.municipioSeleccionado = municipioSeleccionado;
    }

    public List<TMunicipio> getListaMunicipio() {
        return this.imunicipioBo.listTMunicipio();
    }

    public void setListaMunicipio(List<TMunicipio> listaMunicipio) {
        this.listaMunicipio = listaMunicipio;
    }

    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public TDepartamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(TDepartamento departamento) {
        this.departamento = departamento;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public void validarFormulario() {
        this.estadoFormulario = true;
        if (this.municipios.getNombreMunicipio().length() < 3) {
            this.msgNombre = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        if (this.imunicipioBo.municipioRepetido(municipios.getNombreMunicipio().toUpperCase(), this.municipios.getTDepartamento().getIdDepartamento())) {
            this.msgNombreRepetido = "Este municipio ya está registrado en este departamento";
            this.estadoFormulario = false;
        } else {
            this.msgNombreRepetido = "";
        }
        if (this.municipios.getTDepartamento().getIdDepartamento() == 0) {
            this.msgDepartamento = "Seleccione un departamento";
            this.estadoFormulario = false;
        } else {
            this.msgDepartamento = "";
        }

    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;
        if (this.municipioSeleccionado.getNombreMunicipio().length() < 3) {
            this.msgNombre = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        if (this.municipioSeleccionado.getNombreMunicipio().toUpperCase().equals(this.nombreMunicipio)) {
        } else if (this.imunicipioBo.municipioRepetido(this.municipioSeleccionado.getNombreMunicipio().toUpperCase(), this.municipioSeleccionado.getTDepartamento().getIdDepartamento()) == true) {
            this.msgNombreRepetido = "Este municipio ya está registrado en este departamento";
            this.estadoFormulario = false;
        } else {
            this.msgNombreRepetido = "";
        }
        if (this.idDepartamentoAuxiliar == 0) {
            this.msgDepartamento = "Seleccione un departamento";
            this.estadoFormulario = false;
        } else {
            this.msgDepartamento = "";
        }

    }

    public void crear() {

        String nombre = this.municipios.getNombreMunicipio().toUpperCase();
        this.municipios.setNombreMunicipio(nombre);
        this.imunicipioBo.create(municipios);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Registro insertado correctamente"));
        limpiar(0);

    }

    public void modificar() {

        this.municipioSeleccionado.getTDepartamento().setIdDepartamento(this.getIdDepartamentoAuxiliar());
        String nombre = this.municipioSeleccionado.getNombreMunicipio().toUpperCase();
        this.municipioSeleccionado.setNombreMunicipio(nombre);
        this.imunicipioBo.update(municipioSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Registro modificado correctamente"));
        super.enableShowData();

    }

    public void darDeBaja() {

        this.municipioSeleccionado.setEstadoMunicipio(false);
        this.imunicipioBo.update(municipioSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Registro dado de baja correctamente"));
        super.enableShowData();

    }

    public void darDeAlta() {

        this.municipioSeleccionado.setEstadoMunicipio(true);
        this.imunicipioBo.update(municipioSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Registro dado de alta correctamente"));
        super.enableShowData();

    }

    public void limpiar(int i) {
        this.setEstadoFormulario(false);
        this.msgDepartamento = "";
        this.msgNombre = "";
        this.msgNombreRepetido = "";

        if (i == 0) {
            this.municipios = new TMunicipio();
            this.municipios.setTDepartamento(new TDepartamento());

        } else {
            super.enableShowData();
        }
   
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }
    
    
    
    public void verReporteMunicipio() throws SQLException, JRException, IOException{
        
        
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

         parametros.put("id_municipio", this.municipioSeleccionado.getIdMunicipio());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/municipio.jasper"));

        
               
       

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, conn);

        System.out.println(bytes.length);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        FacesContext.getCurrentInstance().responseComplete();
        
        
        
        
        
    }
     
     
      public void verReporteMunicipioPDF() throws SQLException, JRException, IOException{
        
        
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

         Map<String, Object> parametros = new HashMap();

        parametros.put("id_municipio", this.municipioSeleccionado.getIdMunicipio());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/municipio.jasper"));

        
               
       
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Municipio.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
        
    }

}
