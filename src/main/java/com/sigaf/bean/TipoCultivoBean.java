/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.ITipoCultivoBo;
import com.sigaf.pojo.TAreaCultivo;
import com.sigaf.pojo.TTipoCultivo;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;

import javax.inject.Named;
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
 * @author yonat
 */
@Named(value = "tipoCultivoBean")
@RequestScoped
@ManagedBean
public class TipoCultivoBean extends Actividad {

    private TTipoCultivo TipoCultivo;
    private TTipoCultivo TipoCultivoSeleccionado;
    private List<TTipoCultivo> listaTipoCultivo;
    private ITipoCultivoBo tipoCultivoBo;
    private boolean estadoFormulario;
    private String msgNombre;
    private String msgArea;

    public TTipoCultivo getTipoCultivo() {
        return TipoCultivo;
    }

    public void setTipoCultivo(TTipoCultivo TipoCultivo) {
        this.TipoCultivo = TipoCultivo;
    }

    public TTipoCultivo getTipoCultivoSeleccionado() {
        return TipoCultivoSeleccionado;
    }

    public void setTipoCultivoSeleccionado(TTipoCultivo TipoCultivoSeleccionado) {
        this.TipoCultivoSeleccionado = TipoCultivoSeleccionado;
    }

    public List<TTipoCultivo> getListaTipoCultivo() {
        return listaTipoCultivo;
    }

    public void setListaTipoCultivo(List<TTipoCultivo> listaTipoCultivo) {
        this.listaTipoCultivo = listaTipoCultivo;
    }

    public ITipoCultivoBo getTipoCultivoBo() {
        return tipoCultivoBo;
    }

    public void setTipoCultivoBo(ITipoCultivoBo tipoCultivoBo) {
        this.tipoCultivoBo = tipoCultivoBo;
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

    public String getMsgArea() {
        return msgArea;
    }

    public void setMsgArea(String msgArea) {
        this.msgArea = msgArea;
    }

    public void init() {
        this.TipoCultivo = new TTipoCultivo();
        this.TipoCultivo.setTAreaCultivo(new TAreaCultivo());
        this.TipoCultivoSeleccionado = new TTipoCultivo();
        this.TipoCultivoSeleccionado.setTAreaCultivo(new TAreaCultivo());

        this.listaTipoCultivo = tipoCultivoBo.listTipoCultivo();

        super.setShowCreate(true);
        super.setShowData(false);
        super.setShowUpdate(false);
    }

    public void modificar() {
        tipoCultivoBo.update(this.TipoCultivoSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de cultivo modificado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaTipoCultivo = tipoCultivoBo.listTipoCultivo();
        limpiar();
        super.enableShowData();
    }

    public void darDeBaja() {
        this.TipoCultivoSeleccionado.setEstadoTipoCultivo(false);
        tipoCultivoBo.update(this.TipoCultivoSeleccionado);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de cultivo dado de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaTipoCultivo = tipoCultivoBo.listTipoCultivo();
        super.enableShowData();
    }

    public void darDeAlta() {
        this.TipoCultivoSeleccionado.setEstadoTipoCultivo(true);
        tipoCultivoBo.update(this.TipoCultivoSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de cultivo dado de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaTipoCultivo = tipoCultivoBo.listTipoCultivo();
        super.enableShowData();
    }

    public void crear() {
        this.TipoCultivo.setEstadoTipoCultivo(true);
        tipoCultivoBo.create(this.TipoCultivo);
        this.listaTipoCultivo = tipoCultivoBo.listTipoCultivo();
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de cultivo guardado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        limpiar();

    }

    public void validarFormulario() {
        this.estadoFormulario = true;

        if (this.TipoCultivo.getNombreTipoCultivo().length() < 3) {
            this.msgNombre = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else if (this.tipoCultivoBo.getTTipoCultivoNombre(this.TipoCultivo.getNombreTipoCultivo())) {
            this.estadoFormulario = false;
            this.msgNombre = "Ya se registro una tipo con ese nombre";
        } else {

            this.msgNombre = "";
        }

    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;

        if (this.TipoCultivoSeleccionado.getNombreTipoCultivo().length() < 3) {
            this.msgNombre = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }

    }

    public void limpiar() {

        this.estadoFormulario = false;
        this.msgNombre = "";

        this.TipoCultivo = new TTipoCultivo();
        this.TipoCultivo.setTAreaCultivo(new TAreaCultivo());

        this.listaTipoCultivo = tipoCultivoBo.listTipoCultivo();

    }

    public void enableShowDataBean() {
        limpiar();
        super.enableShowData();

    }
    
     public void verReporteTipo() throws SQLException, JRException, IOException{        
        
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        parametros.put("id_tipo", this.TipoCultivoSeleccionado.getIdTipoCultivo());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/tipo.jasper"));
    
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
     
     
      public void verReporteTipoPDF() throws SQLException, JRException, IOException{
      
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

         Map<String, Object> parametros = new HashMap();

        parametros.put("id_tipo", this.TipoCultivoSeleccionado.getIdTipoCultivo());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/tipo.jasper"));
    
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Area.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
        
    }
    
  
}
