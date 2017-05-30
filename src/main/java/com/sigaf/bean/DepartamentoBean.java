/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IDepartamentoBo;
import com.sigaf.pojo.TDepartamento;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
@Named(value = "departamentoBean")
@RequestScoped
@ManagedBean
public class DepartamentoBean extends Actividad {

    IDepartamentoBo idepartamentoBo;
    TDepartamento departamentos;
    TDepartamento departamentoSeleccionado;
    List<TDepartamento> listaDepartamento;
    String nombreAux;
    private boolean estadoFormulario;
    private String msgNombre;
    private String msgNombreRepetido;

    public String getNombreAux() {
        return nombreAux;
    }

    public void setNombreAux(String nombreAux) {
        this.nombreAux = nombreAux;
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

    /**
     * Creates a new instance of DepartamentoBean
     */
    public void Init() {
        this.departamentos = new TDepartamento();
        this.departamentoSeleccionado = new TDepartamento();
        super.enableShowCreate();
        this.estadoFormulario = false;
    }

    public TDepartamento getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(TDepartamento departamentos) {
        this.departamentos = departamentos;
    }

    public TDepartamento getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setDepartamentoSeleccionado(TDepartamento departamentoSeleccionado) {
        this.departamentoSeleccionado = departamentoSeleccionado;
    }

    public List<TDepartamento> getListaDepartamento() {

        return listaDepartamento = this.idepartamentoBo.listTDepartamento();
    }

    public void setListaDepartamento(List<TDepartamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public boolean isEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public void crear() {
        try {

            String nombre = this.departamentos.getNombreDepartamento().toUpperCase();
            this.departamentos.setNombreDepartamento(nombre);
            this.idepartamentoBo.create(departamentos);

            this.estadoFormulario = false;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro guardado correctamente: " + departamentos.getNombreDepartamento()));
            this.departamentos = new TDepartamento();
        } catch (Exception e) {

        }
    }

    public void modificar() {
        try {

            this.idepartamentoBo.update(departamentoSeleccionado);

            this.estadoFormulario = false;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro modificado correctamente: " + departamentoSeleccionado.getNombreDepartamento()));

            super.enableShowData();
        } catch (Exception e) {

        }

    }

    public void darDeBaja() {
        try {

            this.departamentoSeleccionado.setEstadoDepartamento(false);
            this.idepartamentoBo.update(departamentoSeleccionado);
            this.estadoFormulario = false;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro dado de baja correctamente: " + departamentoSeleccionado.getNombreDepartamento()));

            super.enableShowData();
        } catch (Exception e) {

        }

    }

    public void darDeAlta() {
        try {

            this.departamentoSeleccionado.setEstadoDepartamento(true);
            this.idepartamentoBo.update(departamentoSeleccionado);
            this.estadoFormulario = false;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro dado de alta correctamente: " + departamentoSeleccionado.getNombreDepartamento()));

            super.enableShowData();
        } catch (Exception e) {

        }

    }

    public void validarFormulario() {
        this.estadoFormulario = true;
        if (this.departamentos.getNombreDepartamento().length() < 3) {
            this.msgNombre = "El nombre debe contener como minimo 5 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";

        }
        if (this.idepartamentoBo.departaentoRepetido(departamentos.getNombreDepartamento().toUpperCase()) == true) {
            this.msgNombreRepetido = "Este departamento ya está registrado";
            this.estadoFormulario = false;
        } else {
            this.msgNombreRepetido = "";
        }
    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;
        if (this.departamentoSeleccionado.getNombreDepartamento().length() < 3) {
            this.msgNombre = "El nombre debe contener como minimo 5 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";

        }
        if (this.departamentoSeleccionado.getNombreDepartamento().equals(this.nombreAux)) {
        } else if (this.idepartamentoBo.departaentoRepetido(departamentoSeleccionado.getNombreDepartamento().toUpperCase()) == true) {
            this.msgNombreRepetido = "Este departamento ya está registrado";
            this.estadoFormulario = false;
        } else {
            this.msgNombreRepetido = "";
        }
    }

    public void limpiar(int i) {

        this.msgNombre = "";
        this.msgNombreRepetido="";

        if (i == 0) {

            this.estadoFormulario = false;
            //System.out.println("limpiando");
            this.departamentos = new TDepartamento();
            
        } else {
            this.departamentos = new TDepartamento();
            this.estadoFormulario = false;
            super.enableShowData();
        }

    }
    
    
     public void verReporteDepartamento() throws SQLException, JRException, IOException{
        
        
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

         parametros.put("id_departamento", this.departamentoSeleccionado.getIdDepartamento());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/departamento.jasper"));

        
               
       

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
     
     
      public void verReporteDepartamentoPDF() throws SQLException, JRException, IOException{
        
        
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

         Map<String, Object> parametros = new HashMap();

        parametros.put("id_departamento", this.departamentoSeleccionado.getIdDepartamento());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/departamento.jasper"));

        
               
       
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Departamento.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
        
    }
}
