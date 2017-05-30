/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IEmpleadoBo;
import com.sigaf.Ibo.IPoliticaBo;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TPolitica;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
@Named(value = "reporteConfiguracionBean")
@RequestScoped
@ManagedBean
public class ReporteConfiguracionBean extends Actividad {

    /**
     * Creates a new instance of ReporteConfiguracion
     */
    private Integer idReporte;

    private Integer idTipoCredito;

    private Integer tipoCredito;

    private Integer idEmpleadoAux;

    private IPoliticaBo ipoliticaBo;

    private List<TPolitica> listaPolitica;

    private IEmpleadoBo iempleadoBo;

    private List<TEmpleado> listaEmpleado;

    private Boolean estado;

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(Integer tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public IPoliticaBo getIpoliticaBo() {
        return ipoliticaBo;
    }

    public void setIpoliticaBo(IPoliticaBo ipoliticaBo) {
        this.ipoliticaBo = ipoliticaBo;
    }

    public List<TPolitica> getListaPolitica() {
        return listaPolitica;
    }

    public void setListaPolitica(List<TPolitica> listaPolitica) {
        this.listaPolitica = listaPolitica;
    }

    public Integer getIdEmpleadoAux() {
        return idEmpleadoAux;
    }

    public void setIdEmpleadoAux(Integer idEmpleadoAux) {
        this.idEmpleadoAux = idEmpleadoAux;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public Integer getIdTipoCredito() {
        return idTipoCredito;
    }

    public void setIdTipoCredito(Integer idTipoCredito) {
        this.idTipoCredito = idTipoCredito;
    }

    public IEmpleadoBo getIempleadoBo() {
        return iempleadoBo;
    }

    public void setIempleadoBo(IEmpleadoBo iempleadoBo) {
        this.iempleadoBo = iempleadoBo;
    }

    public List<TEmpleado> getListaEmpleado() {
        this.listaEmpleado = this.iempleadoBo.listTEmpleado();
        return listaEmpleado;
    }

    public void setListaEmpleado(List<TEmpleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
    }

    public void init() {

        idReporte = 0;
        this.idEmpleadoAux = 0;

        this.listaEmpleado = new ArrayList<>();

    }

    public void llamarTipoReporte() {
        if (this.idReporte == 1) {

            try {

                this.generarDepartamentos();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 2) {

            try {

                if (null != this.idEmpleadoAux) {
                    switch (this.idEmpleadoAux) {

                        case 0:
                            this.generarEmpleadosFundacion();
                            System.out.println("Empleados: " + this.idEmpleadoAux);
                            break;
                        case 1:
                            this.estado = true;
                            this.generarEmpleadosFundacionEstado();
                            break;
                        case 2:
                            this.estado = false;
                            this.generarEmpleadosFundacionEstado();
                            break;
                        default:
                            break;
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 3) {

            try {
                if (this.idTipoCredito != 0) {
                    this.generarReportePolitica();
                } else {
                    this.generarReportePoliticaActiva();
                }
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 4) {

            try {
                this.generarReporteAreas();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 5) {

            try {
                this.generarReportePerfil();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void llamarTipoReportePDF() {
        if (this.idReporte == 1) {
            try {
                this.generarDepartamentosPDF();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 2) {
            try {
                if (null != this.idEmpleadoAux) {
                    switch (this.idEmpleadoAux) {
                        case 0:
                            System.out.println("tipo: " + this.idEmpleadoAux);
                            this.generarEmpleadosFundacionPDF();
                            break;
                        case 1:
                            this.estado = true;
                            this.generarEmpleadosFundacionEstadoPDF();
                            break;
                        case 2:
                            this.estado = false;
                            this.generarEmpleadosFundacionEstadoPDF();
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 3) {
            try {
                if (this.idTipoCredito != 0) {
                    this.generarReportePoliticaPDF();
                } else {
                    this.generarReportePoliticaActivaPDF();
                }
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 4) {

            try {
                this.generarReporteAreasPDF();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.idReporte == 5) {

            try {
                this.generarReportePerfilPDF();
            } catch (Exception ex) {
                Logger.getLogger(ReporteConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void generarDepartamentos() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("id_entidad", 1);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/departamentos.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, conn);

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

    public void generarDepartamentosPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> idEntidad = new HashMap();

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/departamentos.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), idEntidad, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ListadoDepartamentos.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarEmpleadosFundacion() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> paramentros = new HashMap();

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/EmpleadoGeneral.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), paramentros, conn);

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

    public void generarEmpleadosFundacionPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> idEntidad = new HashMap();

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/EmpleadoGeneral.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), idEntidad, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ListadoEmpleado.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarEmpleadosFundacionEstado() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> paramentros = new HashMap();

        paramentros.put("id_entidad", 1);
        paramentros.put("estado", this.estado);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/EmpleadoGeneralEstado.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), paramentros, conn);

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

    public void generarEmpleadosFundacionEstadoPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> paramentros = new HashMap();

        paramentros.put("id_entidad", 1);
        paramentros.put("estado", this.estado);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/EmpleadoGeneralEstado.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), paramentros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ListadoEmpleadoEstado.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarReportePolitica() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> parametro = new HashMap();

        parametro.put("idTipoCredito", this.idTipoCredito);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReportePolitica.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametro, conn);

        System.out.println("idtipo: " + this.idTipoCredito);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generarReportePoliticaPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> parametros = new HashMap();
        parametros.put("idTipoCredito", this.idTipoCredito);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReportePolitica.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Politicas.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarReportePoliticaActiva() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> parametro = new HashMap();

        parametro.put("idTipoCredito", this.idTipoCredito);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReportePoliticasActivas.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametro, conn);

        System.out.println("idtipo: " + this.idTipoCredito);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generarReportePoliticaActivaPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> parametros = new HashMap();
        parametros.put("idTipoCredito", this.idTipoCredito);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReportePoliticasActivas.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=PoliticasActivas.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarReporteAreas() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> parametro = new HashMap();

        parametro.put("idTipoCredito", this.idTipoCredito);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReporteAreas.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametro, conn);

        System.out.println("idtipo: " + this.idTipoCredito);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generarReporteAreasPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> parametros = new HashMap();
        parametros.put("idTipoCredito", this.idTipoCredito);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReporteAreas.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Areas.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }
     public void generarReportePerfil() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> parametro = new HashMap();

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReportePerfil.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametro, conn);

        

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generarReportePerfilPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> parametros = new HashMap();
        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReportePerfil.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Perfil.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

}
