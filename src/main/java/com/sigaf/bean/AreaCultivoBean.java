/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IAreaCultivoBo;
import com.sigaf.pojo.TAreaCultivo;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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
@Named(value = "areaCultivoBean")
@RequestScoped
@ManagedBean

public class AreaCultivoBean extends Actividad {

    private TAreaCultivo AreaCultivo;
    private TAreaCultivo AreaCultivoSeleccionado;
    private List<TAreaCultivo> listaAreaCultivo;
    private List<SelectItem> selecteOneItemAreas;

    public List<SelectItem> getSelecteOneItemAreas() {

        this.selecteOneItemAreas = new ArrayList<SelectItem>();
        List<TAreaCultivo> cultivos = areaCultivoBo.listAreaCultivoActivos();

        for (TAreaCultivo cultivo : cultivos) {
            SelectItem selectItem = new SelectItem(cultivo.getIdAreaCultivo(), cultivo.getNombreAreaCultivo());
            this.selecteOneItemAreas.add(selectItem);

        }
        return selecteOneItemAreas;
    }

    public void setSelecteOneItemAreas(List<SelectItem> selecteOneItemAreas) {
        this.selecteOneItemAreas = selecteOneItemAreas;
    }
    private IAreaCultivoBo areaCultivoBo;
    private boolean estadoFormulario;
    private String msgNombre;

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

    public void init() {
        this.AreaCultivo = new TAreaCultivo();
        this.listaAreaCultivo = areaCultivoBo.listAreaCultivo();
        super.setShowCreate(true);
        super.setShowData(false);
        super.setShowUpdate(false);
    }

    public TAreaCultivo getAreaCultivo() {
        return AreaCultivo;
    }

    public void setAreaCultivo(TAreaCultivo AreaCultivo) {
        this.AreaCultivo = AreaCultivo;
    }

    public TAreaCultivo getAreaCultivoSeleccionado() {
        return AreaCultivoSeleccionado;
    }

    public void setAreaCultivoSeleccionado(TAreaCultivo AreaCultivoSeleccionado) {
        this.AreaCultivoSeleccionado = AreaCultivoSeleccionado;
    }

    public List<TAreaCultivo> getListaAreaCultivo() {
        return listaAreaCultivo;
    }

    public void setListaAreaCultivo(List<TAreaCultivo> listaAreaCultivo) {
        this.listaAreaCultivo = listaAreaCultivo;
    }

    public IAreaCultivoBo getAreaCultivoBo() {
        return areaCultivoBo;
    }

    public void setAreaCultivoBo(IAreaCultivoBo areaCultivoBo) {
        this.areaCultivoBo = areaCultivoBo;
    }

    public void modificar() {
        areaCultivoBo.update(this.AreaCultivoSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area de cultivo modificada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaAreaCultivo = areaCultivoBo.listAreaCultivo();
        limpiar();
        super.enableShowData();
    }

    public void darDeBaja() {
        this.AreaCultivoSeleccionado.setEstadoAreaCultivo(false);
        areaCultivoBo.update(this.AreaCultivoSeleccionado);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area de cultivo dada de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaAreaCultivo = areaCultivoBo.listAreaCultivo();
        super.enableShowData();
    }

    public void darDeAlta() {
        this.AreaCultivoSeleccionado.setEstadoAreaCultivo(true);
        areaCultivoBo.update(this.AreaCultivoSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area de cultivo dada de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaAreaCultivo = areaCultivoBo.listAreaCultivo();
        super.enableShowData();
    }

    public void crear() {
        this.AreaCultivo.setEstadoAreaCultivo(true);
        areaCultivoBo.create(this.AreaCultivo);
        this.listaAreaCultivo = areaCultivoBo.listAreaCultivo();
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area de cultivo guardada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        limpiar();

    }

    public void validarFormulario() {
        this.estadoFormulario = true;

      
        if (this.AreaCultivo.getNombreAreaCultivo().length() < 3) {
            this.msgNombre = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else if (this.areaCultivoBo.getTAreaCultivoNombre(this.AreaCultivo.getNombreAreaCultivo())) {
            this.estadoFormulario = false;
           this.msgNombre = "Ya se registro una area con ese nombre";
           
        } else {

            this.msgNombre = "";
        }

    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;

        if (this.AreaCultivoSeleccionado.getNombreAreaCultivo().length() < 3) {
            this.msgNombre = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        
     
        

    }

    public void limpiar() {

        this.estadoFormulario = false;
        this.msgNombre = "";

        this.AreaCultivo = new TAreaCultivo();

        this.listaAreaCultivo = areaCultivoBo.listAreaCultivo();

    }

    public void enableShowDataBean() {
        limpiar();
        super.enableShowData();

    }
    
    
     public void verReporteArea() throws SQLException, JRException, IOException{        
        
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        parametros.put("id_tipo", this.AreaCultivoSeleccionado.getIdAreaCultivo());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/Area.jasper"));
    
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
     
     
      public void verReporteAreaPDF() throws SQLException, JRException, IOException{
      
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        parametros.put("id_tipo", this.AreaCultivoSeleccionado.getIdAreaCultivo());
      
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/Area.jasper"));
    
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Area de cultivo.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
        
    }

}
