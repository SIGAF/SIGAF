/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IProductorGrupalBo;
import com.sigaf.pojo.TProductorGrupal;
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
@Named(value = "productorGrupalBean")
@RequestScoped
@ManagedBean
public class ProductorGrupalBean extends Actividad {

    private TProductorGrupal productorGrupal;
    private TProductorGrupal ProductorGrupalSeleccionado;
    private List<TProductorGrupal> listaProductorGrupal;
    private IProductorGrupalBo productorGrupalBo;

    //propiedades de validacion
    private String msgNombre;
    private String msgParticipantesHombres;
    private String msgParticipantesMujeres;
    private String msgContacto;
    private String msgDui;
    private String msgNit;
    private String msgCorreo;
    private String msgTelefono;
    private String msgMovil;
    private String msgUbicacion;
    private boolean estadoFormulario;
    private int contador;

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
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

    public String getMsgParticipantesHombres() {
        return msgParticipantesHombres;
    }

    public void setMsgParticipantesHombres(String msgParticipantesHombres) {
        this.msgParticipantesHombres = msgParticipantesHombres;
    }

    public String getMsgParticipantesMujeres() {
        return msgParticipantesMujeres;
    }

    public void setMsgParticipantesMujeres(String msgParticipantesMujeres) {
        this.msgParticipantesMujeres = msgParticipantesMujeres;
    }

    public String getMsgContacto() {
        return msgContacto;
    }

    public void setMsgContacto(String msgContacto) {
        this.msgContacto = msgContacto;
    }

    public String getMsgDui() {
        return msgDui;
    }

    public void setMsgDui(String msgDui) {
        this.msgDui = msgDui;
    }

    public String getMsgNit() {
        return msgNit;
    }

    public void setMsgNit(String msgNit) {
        this.msgNit = msgNit;
    }

    public String getMsgCorreo() {
        return msgCorreo;
    }

    public void setMsgCorreo(String msgCorreo) {
        this.msgCorreo = msgCorreo;
    }

    public String getMsgTelefono() {
        return msgTelefono;
    }

    public void setMsgTelefono(String msgTelefono) {
        this.msgTelefono = msgTelefono;
    }

    public String getMsgMovil() {
        return msgMovil;
    }

    public void setMsgMovil(String msgMovil) {
        this.msgMovil = msgMovil;
    }

    public String getMsgUbicacion() {
        return msgUbicacion;
    }

    public void setMsgUbicacion(String msgUbicacion) {
        this.msgUbicacion = msgUbicacion;
    }

    public void init() {
        this.productorGrupal = new TProductorGrupal();
        this.listaProductorGrupal = productorGrupalBo.listProductorGrupal();
       
        super.setShowCreate(true);
        super.setShowData(false);
        super.setShowUpdate(false);
    }

    public TProductorGrupal getProductorGrupal() {
        return productorGrupal;
    }

    public void setProductorGrupal(TProductorGrupal productorGrupal) {
        this.productorGrupal = productorGrupal;
    }

    public TProductorGrupal getProductorGrupalSeleccionado() {
        return ProductorGrupalSeleccionado;
    }

    public void setProductorGrupalSeleccionado(TProductorGrupal productorGrupalSeleccionado) {
        this.ProductorGrupalSeleccionado = productorGrupalSeleccionado;
    }

    public List<TProductorGrupal> getListaProductorGrupal() {

        return listaProductorGrupal;

    }

    public void setListaProductorGrupal(List<TProductorGrupal> listaProductorGrupal) {
        this.listaProductorGrupal = listaProductorGrupal;
    }

    public IProductorGrupalBo getProductorGrupalBo() {
        return productorGrupalBo;
    }

    public void setProductorGrupalBo(IProductorGrupalBo productorGrupalBo) {
        this.productorGrupalBo = productorGrupalBo;
    }

    public void modificar() {
        productorGrupalBo.update(this.ProductorGrupalSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupo de productores modificado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaProductorGrupal = productorGrupalBo.listProductorGrupal();
        super.enableShowData();
        limpiar();
    }

    public void darDeBaja() {

        this.ProductorGrupalSeleccionado.setEstadoProductorGrupal(false);
        productorGrupalBo.update(this.ProductorGrupalSeleccionado);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupo de productores dado de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaProductorGrupal = productorGrupalBo.listProductorGrupal();
        super.enableShowData();

    }

    public void darDeAlta() {

        this.ProductorGrupalSeleccionado.setEstadoProductorGrupal(true);
        productorGrupalBo.update(this.ProductorGrupalSeleccionado);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupo de productores dado de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaProductorGrupal = productorGrupalBo.listProductorGrupal();
        super.enableShowData();

    }

    public void crear() {
        this.productorGrupal.setEstadoProductorGrupal(true);
        productorGrupalBo.create(this.productorGrupal);
        this.listaProductorGrupal = productorGrupalBo.listProductorGrupal();
        this.estadoFormulario = false;
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupo de productores guardado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        limpiar();

    }

    public String verEstado(boolean estado) {
        if (estado) {
            return "Activo";

        } else {
            return "Inactivo";
        }
    }

    public void validarFormulario() {
        this.estadoFormulario = true;

               
         if (this.productorGrupal.getNombreProductorGrupal().length() < 3) {
           this.msgNombre = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else if (this.productorGrupalBo.getTProductorGrupalNombre(this.productorGrupal.getNombreProductorGrupal())) {
            this.estadoFormulario = false;
            this.msgNombre = "Ya se registro un grupo con ese nombre";
        } else {
            this.msgNombre = "";
        }
        
        
        
        
        
        if (this.productorGrupal.getUbicacionProductorGrupal().length() < 5) {
            this.msgUbicacion = "La ubicacion es requerida";
            this.estadoFormulario = false;
        } else { 
            this.msgUbicacion = "";
        }
        

    }
    
    public void validarFormularioModificar() {
        this.estadoFormulario = true;

        if (this.ProductorGrupalSeleccionado.getNombreProductorGrupal().length() < 3) {
            this.msgNombre = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        
        if (this.ProductorGrupalSeleccionado.getUbicacionProductorGrupal().length() < 5) {
            this.msgUbicacion = "La ubicacion es requerida";
            this.estadoFormulario = false;
        } else { 
            this.msgUbicacion = "";
        }
        

    }
    
    public void limpiar(){
        
        this.estadoFormulario = false;
        this.msgNombre = "";
        this.msgParticipantesHombres = "";
        this.msgParticipantesMujeres = "";
        this.msgContacto = "";
        this.msgDui = "";
        this.msgNit = "";
        this.msgTelefono = "";
        this.msgMovil = "";
        this.msgUbicacion = ""; 
        this.productorGrupal = new TProductorGrupal();
        this.listaProductorGrupal = productorGrupalBo.listProductorGrupal();       
        
    }
    public void enableShowDataBean(){
        limpiar();
        super.enableShowData();            
        
        
    }
    
    public int sumarParticipantes(int hombres,int mujeres){
        
        return hombres+mujeres;
    
    }
    
    public void verReporteGrupo() throws SQLException, JRException, IOException{
        
        
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

           parametros.put("id_productor", this.ProductorGrupalSeleccionado.getIdProductorGrupal());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productorGrupal.jasper"));

        
               
       

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
     
     
      public void verReporteGrupoPDF() throws SQLException, JRException, IOException{
        
        
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

         Map<String, Object> parametros = new HashMap();

        parametros.put("id_productor", this.ProductorGrupalSeleccionado.getIdProductorGrupal());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productorGrupal.jasper"));

        
               
       
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Grupo de productores.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
        
    }
    
    
    

}
