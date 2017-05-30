/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IAreaCultivoBo;
import com.sigaf.Ibo.IConfiguracionAgronegocioBo;
import com.sigaf.pojo.TAreaCultivo;
import com.sigaf.pojo.TConfiguracionAgronegocio;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
@Named(value = "configuracionAgronegocioBean")
@RequestScoped
@ManagedBean

public class ConfiguracionAgronegocioBean extends Actividad {

    private TConfiguracionAgronegocio ConfiguracionAgronegocio;
    private TConfiguracionAgronegocio ConfiguracionSeleccionada;
    private List<TConfiguracionAgronegocio> listaConfiguracionAgronegocios;
    private boolean estadoFormulario;
    private String msgInteres;
    private String msgMora;
    private String msgComision;
    private String msgFecha;

    public String getMsgFecha() {
        return msgFecha;
    }

    public void setMsgFecha(String msgFecha) {
        this.msgFecha = msgFecha;
    }

    public boolean isEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getMsgInteres() {
        return msgInteres;
    }

    public void setMsgInteres(String msgInteres) {
        this.msgInteres = msgInteres;
    }

    public String getMsgMora() {
        return msgMora;
    }

    public void setMsgMora(String msgMora) {
        this.msgMora = msgMora;
    }

    public String getMsgComision() {
        return msgComision;
    }

    public void setMsgComision(String msgComision) {
        this.msgComision = msgComision;
    }

    public TConfiguracionAgronegocio getConfiguracionAgronegocio() {
        return ConfiguracionAgronegocio;
    }

    public void setConfiguracionAgronegocio(TConfiguracionAgronegocio ConfiguracionAgronegocio) {
        this.ConfiguracionAgronegocio = ConfiguracionAgronegocio;
    }

    public TConfiguracionAgronegocio getConfiguracionSeleccionada() {
        return ConfiguracionSeleccionada;
    }

    public void setConfiguracionSeleccionada(TConfiguracionAgronegocio ConfiguracionSeleccionada) {
        this.ConfiguracionSeleccionada = ConfiguracionSeleccionada;
    }

    public List<TConfiguracionAgronegocio> getListaConfiguracionAgronegocios() {
        return listaConfiguracionAgronegocios;
    }

    public void setListaConfiguracionAgronegocios(List<TConfiguracionAgronegocio> listaConfiguracionAgronegocios) {
        this.listaConfiguracionAgronegocios = listaConfiguracionAgronegocios;
    }

    private IConfiguracionAgronegocioBo configuracionAgronegocioBo;

    public IConfiguracionAgronegocioBo getConfiguracionAgronegocioBo() {
        return configuracionAgronegocioBo;
    }

    public void setConfiguracionAgronegocioBo(IConfiguracionAgronegocioBo configuracionAgronegocioBo) {
        this.configuracionAgronegocioBo = configuracionAgronegocioBo;
    }

    public void init() {
        
        this.ConfiguracionAgronegocio= new TConfiguracionAgronegocio();
        this.ConfiguracionAgronegocio.setVigenciaAgronegocio(new Date());
        this.listaConfiguracionAgronegocios = this.configuracionAgronegocioBo.listConfiguracion();
        super.setShowCreate(true);
        super.setShowData(false);
        super.setShowUpdate(false);
        
    }

    public void crear() {
                
        TConfiguracionAgronegocio configuracionUpdate= new TConfiguracionAgronegocio();
        configuracionUpdate=this.configuracionAgronegocioBo.getConfiguracion(0);
        
        configuracionUpdate.setEstadoConfiguracion(false);
        
        this.configuracionAgronegocioBo.update(configuracionUpdate);
        this.ConfiguracionAgronegocio.setEstadoConfiguracion(true);
        configuracionAgronegocioBo.create(this.ConfiguracionAgronegocio);
        
        this.listaConfiguracionAgronegocios = configuracionAgronegocioBo.listConfiguracion();
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Politica registrada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        limpiar();

    }

    public void validarFormulario() {
        this.estadoFormulario = true;

       

    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;

      

    }

    public void limpiar() {

        this.estadoFormulario = false;
        this.msgInteres = "";
        this.msgMora = "";
        this.msgComision = "";
        this.msgFecha = "";
        this.ConfiguracionAgronegocio = new TConfiguracionAgronegocio();
        this.ConfiguracionAgronegocio.setVigenciaAgronegocio(new Date());
        this.listaConfiguracionAgronegocios = configuracionAgronegocioBo.listConfiguracion();

    }

    public void enableShowDataBean() {
        limpiar();
        super.enableShowData();

    }
    
    
     public void verReportePolitica() throws SQLException, JRException, IOException{        
        
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        parametros.put("id_politica", this.ConfiguracionSeleccionada.getIdConfiguracionAgronegocio());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/politica.jasper"));
    
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
    
     public void verReportePoliticaPDF() throws SQLException, JRException, IOException{
      
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        parametros.put("id_politica", this.ConfiguracionSeleccionada.getIdConfiguracionAgronegocio());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/politica.jasper"));
    
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Politica agronegocio.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
        
    }
    
    

}
