/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IParametroBo;
import com.sigaf.Ibo.IParametroSeguimientoBo;
import com.sigaf.pojo.TParametroseguimiento;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Genovés
 */
@Named(value = "parametroBean")
@RequestScoped
@ManagedBean
public class ParametroBean extends Actividad {

   
    private IParametroSeguimientoBo parametroSeguimientoBo;

    public IParametroSeguimientoBo getParametroSeguimientoBo() {
        return parametroSeguimientoBo;
    }

    public void setParametroSeguimientoBo(IParametroSeguimientoBo parametroSeguimientoBo) {
        this.parametroSeguimientoBo = parametroSeguimientoBo;
    }
    
    private TParametroseguimiento parametro;
    
    private TParametroseguimiento parametroSeleccionado;
    
    private List<TParametroseguimiento> listaParametro;
    
    private String msgNombre;
    private String msgUnidad;
    private String msgDescripcion;
    private Boolean estadoFormulario;
    private String msgNombreRepetido;
    private String nombreCopia;

    public String getNombreCopia() {
        return nombreCopia;
    }

    public void setNombreCopia(String nombreCopia) {
        this.nombreCopia = nombreCopia;
    }
    
    public String getMsgNombreRepetido() {
        return msgNombreRepetido;
    }

    public void setMsgNombreRepetido(String msgNombreRepetido) {
        this.msgNombreRepetido = msgNombreRepetido;
    }
    
    public List<TParametroseguimiento> getListaParametro() {
        this.listaParametro = this.parametroSeguimientoBo.listParametroSeguimiento();
        return listaParametro;
    }
    
    public void setListaParametro(List<TParametroseguimiento> listaParametro) {
        this.listaParametro = listaParametro;
    }
    
    public TParametroseguimiento getParametroSeleccionado() {
        return parametroSeleccionado;
    }
    
    public void setParametroSeleccionado(TParametroseguimiento parametroSeleccionado) {
        this.parametroSeleccionado = parametroSeleccionado;
    }
    
    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }
    
    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }
    
    public TParametroseguimiento getParametro() {
        return parametro;
    }
    
    public void setParametro(TParametroseguimiento parametro) {
        this.parametro = parametro;
    }
    
    public String getMsgNombre() {
        return msgNombre;
    }
    
    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }
    
    public String getMsgUnidad() {
        return msgUnidad;
    }
    
    public void setMsgUnidad(String msgUnidad) {
        this.msgUnidad = msgUnidad;
    }
    
    public String getMsgDescripcion() {
        return msgDescripcion;
    }
    
    public void setMsgDescripcion(String msgDescripcion) {
        this.msgDescripcion = msgDescripcion;
    }
    
    
    public void init() {
        
        super.enableShowCreate();
        this.parametro = new TParametroseguimiento();
        this.estadoFormulario = false;
        this.listaParametro = new ArrayList<>();
        this.parametroSeleccionado = new TParametroseguimiento();
        this.nombreCopia = "";
        
    }
    
 
    public void validarFormulario() {
        this.estadoFormulario = true;
        if (this.parametro.getNombre().length() < 3) {
            this.msgNombre = "El nombre debe contener al menos 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        
        if (this.parametro.getUnidad().length() < 3) {
            this.msgUnidad = "La unidad debe contener al menos 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgUnidad = "";
        }
        
        if (this.parametro.getDescripcion().length() < 3) {
            this.msgDescripcion = "La unidad debe contener al menos 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgDescripcion = "";
        }
        if (this.parametroSeguimientoBo.getParametroRepetido(this.parametro.getNombre())  ) {
            this.msgNombreRepetido = "El nombre ya existe";
            this.estadoFormulario = false;
        } else {
            this.msgNombreRepetido = "";
        }
    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;
        if (this.parametroSeleccionado.getNombre().length() < 3) {
            this.msgNombre = "El nombre debe contener al menos 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        
        if (this.parametroSeleccionado.getUnidad().length() < 3) {
            this.msgUnidad = "La unidad debe contener al menos 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgUnidad = "";
        }
        
        if (this.parametroSeleccionado.getDescripcion().length() < 3) {
            this.msgDescripcion = "La unidad debe contener al menos 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgDescripcion = "";
        }
       
        if (this.parametroSeleccionado.getNombre().equals(this.nombreCopia)) {
        } else if (this.parametroSeguimientoBo.getParametroRepetido(parametroSeleccionado.getNombre()) == true) {
            this.msgNombreRepetido = "Este nombre ya está registrado";
            this.estadoFormulario = false;
        } else {
            this.msgNombreRepetido = "";
        }
         
    }
    
    public void crear() {
        
        try {
            this.parametro.setEstado(1);
           this.parametroSeguimientoBo.create(this.parametro);
            limpiar(1);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro insertado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(e.getMessage()));
        }
        
    }
    
    public void limpiar(int i) {
        
        this.msgNombre = "";
        this.msgDescripcion = "";
        this.msgNombreRepetido = "";
        this.msgUnidad = "";
        this.parametro = new TParametroseguimiento();
        this.estadoFormulario = false;
        
        if (i == 0) {
            
            this.enableShowData();
            
        }
        
    }

    public void modificar() {
        try {
            this.parametroSeguimientoBo.update(parametroSeleccionado);
            limpiar(0);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro modificado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(e.getMessage()));
        }
    }

    public void darDeBaja() {
        try {
            this.parametroSeleccionado.setEstado(0);
            this.parametroSeguimientoBo.update(parametroSeleccionado);
            limpiar(0);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro modificado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(e.getMessage()));
        }
    }
    public void darDeAlta() {
        try {
            this.parametroSeleccionado.setEstado(1); 
            this.parametroSeguimientoBo.update(parametroSeleccionado);
            limpiar(0);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro modificado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(e.getMessage()));
        }
    }
    public void generarParametro() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> parametros = new HashMap();

      
        parametros.put("idParametro", this.parametroSeleccionado.getIdparametro());
        

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/ReporteParametroIndividual.jasper"));

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
    public void generarParametroPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

       Map<String, Object> parametros = new HashMap();

        
        parametros.put("idParametro", this.parametroSeleccionado.getIdparametro());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/ReporteParametroIndividual.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ReporteParametroIndividual.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }
}
