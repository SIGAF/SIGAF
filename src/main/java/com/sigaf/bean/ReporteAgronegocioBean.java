/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IEjercicioBo;
import com.sigaf.pojo.TEjercicio;
import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;

/**
 *
 * @author Eliseo
 */
@ManagedBean
@RequestScoped
public class ReporteAgronegocioBean extends Actividad {

    /**
     * Creates a new instance of ReporteContabilidadBean
     */
    private Integer idReporte;
    private Integer estadoReporte;
    private Integer estadoMensual;
    private Date fechaInicial;
    private Date fechaFin;
    private String msgFechaIncial;
    private String msgFechaFin;

    public String getMsgFechaIncial() {
        return msgFechaIncial;
    }

    public void setMsgFechaIncial(String msgFechaIncial) {
        this.msgFechaIncial = msgFechaIncial;
    }

    public String getMsgFechaFin() {
        return msgFechaFin;
    }

    public void setMsgFechaFin(String msgFechaFin) {
        this.msgFechaFin = msgFechaFin;
    }

    public Integer getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(Integer estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getEstadoMensual() {
        return estadoMensual;
    }

    public void setEstadoMensual(Integer estadoMensual) {
        this.estadoMensual = estadoMensual;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public void init() {

        idReporte = 0;

    }

    public void llamarTipoReporte() {

        if (idReporte == 1) {
            try {
                this.generarProductoresIndividuales();
            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 2) {
            try {
                this.generarProductoresIndividualesSinGrupo();
            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 3) {
            try {
                this.generarCompradorDetallista();

            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 4) {
            try {
                this.generarCompradorMayorista();

            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 5) {
            try {
                this.generarCultivosProductos();

            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 6) {
            try {
                this.generarCultivos();

            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 7) {
            try {

                this.generarAreas();
                this.generarPoliticasAgronegocio();

            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 8) {
            try {

                this.generarPoliticasAgronegocio();

            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 9) {

            if(this.fechaInicial ==null || this.fechaFin==null ){
                
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se requiere la fecha inicial y final", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                
            }else if (this.fechaInicial.compareTo(this.fechaFin) > 0) {

                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                
            }else {

                try {

                    this.generarAgronegocios();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }

    public void llamarTipoReportePDF() {

     
        
        if (idReporte == 1) {
            try {
                this.generarProductoresIndividualesPDF();
            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 2) {
            try {
                this.generarProductoresIndividualesSinGrupoPDF();
            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 3) {
            try {
                this.generarCompradorDetallistaPDF();

            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 4) {
            try {
                this.generarCompradorMayoristaPDF();

            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 5) {
            try {
                this.generarCultivosProductosPDF();

            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 6) {
            try {
                this.generarCultivosPDF();

            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 7) {
            try {

                this.generarAreasPDF();
                this.generarPoliticasAgronegocio();

            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 8) {
            try {

                this.generarPoliticasAgronegocioPDF();

            } catch (Exception ex) {
                Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (idReporte == 9) {

            if(this.fechaInicial ==null || this.fechaFin==null ){
                
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se requiere la fecha inicial y final", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                
            }else if (this.fechaInicial.compareTo(this.fechaFin) > 0) {

                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "La fecha inicial debe ser inferior a la fecha fin", null);
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
                
            }else {

                try {

                    this.generarAgronegociosPDF();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        

    }

    public void generarAgronegocios() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        parametros.put("fechaInicial", this.fechaInicial);

        parametros.put("fechaFin", this.fechaFin);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegociosFinalizados.jasper"));

        if (this.estadoMensual == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegociosFinalizados.jasper"));

        } else if (this.estadoMensual == 2) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegociosPendientes.jasper"));

        } else {

            
        }

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

    public void generarProductoresIndividuales() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado_productor", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado_productor", false);

        } else {

        }
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividuales.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividuales.jasper"));

        } else if (this.estadoReporte == 2) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividuales.jasper"));

        } else {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividualesTodos.jasper"));

        }

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

    public void generarProductoresIndividualesSinGrupo() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado_productor", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado_productor", false);

        } else {

        }
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividualesSinGrupo.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividualesSinGrupo.jasper"));

        } else if (this.estadoReporte == 2) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividualesSinGrupo.jasper"));

        } else {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividualesSinGrupoTodos.jasper"));

        }

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

    public void generarCompradorDetallista() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado_comprador", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado_comprador", false);

        } else {

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresDetallista.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresDetallista.jasper"));

        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresDetallista.jasper"));

        } else {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresDetallistaTodos.jasper"));

        }

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

    public void generarCultivos() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado", false);

        } else {

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/tiposAreas.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/tiposAreas.jasper"));

        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/tiposAreas.jasper"));

        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/tiposAreasTodos.jasper"));

        }

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

    public void generarAreas() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado", false);

        } else {

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/areasTodas.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/areasActivas.jasper"));

        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/areasInactivas.jasper"));

        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/areasTodas.jasper"));

        }

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

    public void generarCompradorMayorista() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado_comprador", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado_comprador", false);

        } else {

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresMayorista.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresMayorista.jasper"));

        } else if (this.estadoReporte == 2) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresMayorista.jasper"));

        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresMayoristaTodos.jasper"));

        }

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

    public void generarCultivosProductos() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado_productor", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado_productor", false);

        } else {

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productorProductor.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productorProductor.jasper"));

        } else if (this.estadoReporte == 2) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productorProductor.jasper"));

        } else {
            
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productorProductorTodos.jasper"));


        }

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

    public void generarPoliticasAgronegocio() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        //parametros.put("estado_productor", this.estadoReporte);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/politicasAgronegocios.jasper"));

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

    public void generarCatalogoPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "rootzor");

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("id_entidad", 1);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/CatalogoCuentas.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), idEntidad, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=jsfReporte.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }
    
    
    

    //generacion de PDF
    public void generarAgronegociosPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        parametros.put("fechaInicial", this.fechaInicial);

        parametros.put("fechaFin", this.fechaFin);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegociosFinalizados.jasper"));

        if (this.estadoMensual == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegociosFinalizados.jasper"));

        } else if (this.estadoMensual == 2) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegociosPendientes.jasper"));

        } else {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegociosTodos.jasper"));

        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Agronegocios.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarProductoresIndividualesPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado_productor", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado_productor", false);

        } else {

        }
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividuales.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividuales.jasper"));

        } else if (this.estadoReporte == 2) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividuales.jasper"));

        } else {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividualesTodos.jasper"));

        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ProductoresIndividuales.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarProductoresIndividualesSinGrupoPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado_productor", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado_productor", false);

        } else {

        }
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividualesSinGrupo.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividualesSinGrupo.jasper"));

        } else if (this.estadoReporte == 2) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividualesSinGrupo.jasper"));

        } else {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productoresIndividualesSinGrupoTodos.jasper"));

        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ProductoresIndividualesSinGrupo.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarCompradorDetallistaPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado_comprador", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado_comprador", false);

        } else {

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresDetallista.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresDetallista.jasper"));

        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresDetallista.jasper"));

        } else {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresDetallistaTodos.jasper"));

        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=CompradoresDetallistas.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarCultivosPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado", false);

        } else {

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/tiposAreas.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/tiposAreas.jasper"));

        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/tiposAreas.jasper"));

        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/tiposAreasTodos.jasper"));

        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=TiposCultivos.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarAreasPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado", false);

        } else {

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/areasTodas.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/areasActivas.jasper"));

        } else if (this.estadoReporte == 2) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/areasInactivas.jasper"));

        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/areasTodas.jasper"));

        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Areas.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarCompradorMayoristaPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado_comprador", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado_comprador", false);

        } else {

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresMayorista.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresMayorista.jasper"));

        } else if (this.estadoReporte == 2) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresMayorista.jasper"));

        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/compradoresMayoristaTodos.jasper"));

        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=CompradoresMayoristas.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarCultivosProductosPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        if (this.estadoReporte == 1) {

            parametros.put("estado_productor", true);

        } else if (this.estadoReporte == 2) {

            parametros.put("estado_productor", false);

        } else {

        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productorProductor.jasper"));

        if (this.estadoReporte == 1) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productorProductorInactivos.jasper"));

        } else if (this.estadoReporte == 2) {

            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productorProductor.jasper"));

        } else {

        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=CultivosProductos.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarPoliticasAgronegocioPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        //parametros.put("estado_productor", this.estadoReporte);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/politicasAgronegocios.jasper"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Politicas.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

}
