/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IEjercicioBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IEstructuraBo;
import com.sigaf.Ibo.IRepoContBo;
import com.sigaf.pojo.TEjercicio;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TEstructura;
import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Clase para generar reportes del modulo de contabilidad
 *
 * @author Eliseo
 */
@ManagedBean
@RequestScoped
public class ReporteContabilidadBean {

    //Objeto capa de negocio para Cuentas 
    private IRepoContBo repContBo;

    //Objeto capa de negocio Ejercicios Contables 
    private IEjercicioBo ejercicioBo;

    // Lista  de Ejercicos Contables
    private List<TEjercicio> listaEjercicio;

    //Identificador de Ejercicio Contable
    private Integer idEjercicio;

    //Lista de Entidades
    private List<TEntidad> listaEntidades;

    //Objeto capa de negocio Entidad
    private IEntidadBo entidadBo;

    //Identificador Entidad
    private Integer idEntidad;

    //Identificador Reporte
    private Integer idReporte;

    //Identificador SubReporte
    private Integer idSubReporte;

    //Mensaje error reporte filtro Entidad
    private String msgEntidad;

    //Mensaje error reporte filtro Reporte
    private String msgReporte;

    //Mensaje error reporte filtro SubReporte
    private String msgSubReporte;

    /* Acceso para  datos  estructura*/
    private IEstructuraBo estructuraBo;

    /* objeto para la renta*/
    private TEstructura rentaEstructura;

    /* objeto para  la reserva legal*/
    private TEstructura ReservaEstructura;

    /*Para  Utilidad*/
    private TEstructura utilidadEstructura;

    /*Lista para ingresos */
    private List<TEstructura> listaEstructuraIngresos;

    /*Lista para costos */
    private List<TEstructura> listaEstructuraCostos;

    /*Lista para Gastos */
    private List<TEstructura> listaEstructuraGastos;

    /*Lista para Otros  Gastos */
    private List<TEstructura> listaEstructuraOtrosGastos;

    /*Lista para Otros  Ingresos */
    private List<TEstructura> listaEstructuraOtrosIngresos;
    
    
    /* para estructura  Activos*/
    private TEstructura estructuraActivo;
    
    /* para estructura  Pasivos*/
    private TEstructura estructuraPasivo;

    /* para estructura  Capital*/
    private TEstructura estructuraCapital;

    public TEstructura getEstructuraActivo() {
        return estructuraActivo;
    }

    public void setEstructuraActivo(TEstructura estructuraActivo) {
        this.estructuraActivo = estructuraActivo;
    }

    public TEstructura getEstructuraPasivo() {
        return estructuraPasivo;
    }

    public void setEstructuraPasivo(TEstructura estructuraPasivo) {
        this.estructuraPasivo = estructuraPasivo;
    }

    public TEstructura getEstructuraCapital() {
        return estructuraCapital;
    }

    public void setEstructuraCapital(TEstructura estructuraCapital) {
        this.estructuraCapital = estructuraCapital;
    }
    
    public IEstructuraBo getEstructuraBo() {
        return estructuraBo;
    }

    public void setEstructuraBo(IEstructuraBo estructuraBo) {
        this.estructuraBo = estructuraBo;
    }

    public TEstructura getRentaEstructura() {
        return rentaEstructura;
    }

    public void setRentaEstructura(TEstructura rentaEstructura) {
        this.rentaEstructura = rentaEstructura;
    }

    public TEstructura getReservaEstructura() {
        return ReservaEstructura;
    }

    public void setReservaEstructura(TEstructura ReservaEstructura) {
        this.ReservaEstructura = ReservaEstructura;
    }

    public TEstructura getUtilidadEstructura() {
        return utilidadEstructura;
    }

    public void setUtilidadEstructura(TEstructura utilidadEstructura) {
        this.utilidadEstructura = utilidadEstructura;
    }

    public List<TEstructura> getListaEstructuraIngresos() {
        return listaEstructuraIngresos;
    }

    public void setListaEstructuraIngresos(List<TEstructura> listaEstructuraIngresos) {
        this.listaEstructuraIngresos = listaEstructuraIngresos;
    }

    public List<TEstructura> getListaEstructuraCostos() {
        return listaEstructuraCostos;
    }

    public void setListaEstructuraCostos(List<TEstructura> listaEstructuraCostos) {
        this.listaEstructuraCostos = listaEstructuraCostos;
    }

    public List<TEstructura> getListaEstructuraGastos() {
        return listaEstructuraGastos;
    }

    public void setListaEstructuraGastos(List<TEstructura> listaEstructuraGastos) {
        this.listaEstructuraGastos = listaEstructuraGastos;
    }

    public List<TEstructura> getListaEstructuraOtrosGastos() {
        return listaEstructuraOtrosGastos;
    }

    public void setListaEstructuraOtrosGastos(List<TEstructura> listaEstructuraOtrosGastos) {
        this.listaEstructuraOtrosGastos = listaEstructuraOtrosGastos;
    }

    public List<TEstructura> getListaEstructuraOtrosIngresos() {
        return listaEstructuraOtrosIngresos;
    }

    public void setListaEstructuraOtrosIngresos(List<TEstructura> listaEstructuraOtrosIngresos) {
        this.listaEstructuraOtrosIngresos = listaEstructuraOtrosIngresos;
    }

    /**
     * Retorna Instancia
     *
     * @return IRepoContBo
     */
    public IRepoContBo getRepContBo() {
        return repContBo;
    }

    /**
     * Cambia Instancia
     *
     * @param repContBo
     */
    public void setRepContBo(IRepoContBo repContBo) {
        this.repContBo = repContBo;
    }

    /**
     * Retorna Instancia
     *
     * @return msgEntidad
     */
    public String getMsgEntidad() {
        return msgEntidad;
    }

    /**
     * Cambia Instancia
     *
     * @param msgEntidad
     */
    public void setMsgEntidad(String msgEntidad) {
        this.msgEntidad = msgEntidad;
    }

    /**
     * Retorna Instancia
     *
     * @return msgReporte
     */
    public String getMsgReporte() {
        return msgReporte;
    }

    /**
     * Cambia Instancia
     *
     * @param msgReporte
     */
    public void setMsgReporte(String msgReporte) {
        this.msgReporte = msgReporte;
    }

    /**
     * Retorna Instancia
     *
     * @return msgSubReporte
     */
    public String getMsgSubReporte() {
        return msgSubReporte;
    }

    /**
     * Cambia Instancia
     *
     * @param msgSubReporte
     */
    public void setMsgSubReporte(String msgSubReporte) {
        this.msgSubReporte = msgSubReporte;
    }

    /**
     * Retorna Instancia
     *
     * @return idSubReporte
     */
    public Integer getIdSubReporte() {
        return idSubReporte;
    }

    /**
     * Cambia Instancia
     *
     * @param idSubReporte
     */
    public void setIdSubReporte(Integer idSubReporte) {
        this.idSubReporte = idSubReporte;
    }

    /**
     * Establece valores iniciales a los atributos idReporte = 0 idEntidad = 0
     * idSubReporte = 0
     */
    public void init() {

        idReporte = 0;
        idEntidad = 0;
        idSubReporte = 0;

    }

    /**
     * Retorna Instancia
     *
     * @return listaEntidades
     */
    public List<TEntidad> getListaEntidades() {
        listaEntidades = this.entidadBo.listTEndidadTodos();
        return listaEntidades;
    }

    /**
     * Cambia Instancia
     *
     * @param listaEntidades
     */
    public void setListaEntidades(List<TEntidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    /**
     * Retorna Instancia
     *
     * @return entidadBo
     */
    public IEntidadBo getEntidadBo() {
        return entidadBo;
    }

    /**
     * Cambia Instancia
     *
     * @param entidadBo
     */
    public void setEntidadBo(IEntidadBo entidadBo) {
        this.entidadBo = entidadBo;
    }

    /**
     * Retorna Instancia
     *
     * @return idEntidad
     */
    public Integer getIdEntidad() {
        return idEntidad;
    }

    /**
     * Cambia Instancia
     *
     * @param idEntidad
     */
    public void setIdEntidad(Integer idEntidad) {
        this.idReporte = 0;
        this.idSubReporte = 0;
        this.idEntidad = idEntidad;
    }

    /**
     * Retorna Instancia
     *
     * @return idEjercicio
     */
    public Integer getIdEjercicio() {
        return idEjercicio;
    }

    /**
     * Cambia Instancia
     *
     * @param idEjercicio
     */
    public void setIdEjercicio(Integer idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    /**
     * Retorna Instancia
     *
     * @return listaEjercicio
     */
    public List<TEjercicio> getListaEjercicio() {
        this.listaEjercicio = ejercicioBo.listEjercicio(1);
        return listaEjercicio;
    }

    /**
     * Cambia Instancia
     *
     * @param listaEjercicio
     */
    public void setListaEjercicio(List<TEjercicio> listaEjercicio) {

        this.listaEjercicio = listaEjercicio;
    }

    /**
     * Retorna Instancia
     *
     * @return ejercicioBo
     */
    public IEjercicioBo getEjercicioBo() {
        return ejercicioBo;
    }

    /**
     * Cambia Instancia
     *
     * @param ejercicioBo
     */
    public void setEjercicioBo(IEjercicioBo ejercicioBo) {
        this.ejercicioBo = ejercicioBo;
    }

    /**
     * Retorna Instancia
     *
     * @return idReporte
     */
    public Integer getIdReporte() {
        return idReporte;
    }

    /**
     * Cambia Instancia
     *
     * @param idReporte
     */
    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    /**
     * Crea una conexion con la base de datos para generar reportes
     *
     * @return conn
     * @throws SQLException
     */
    public Connection conec() throws SQLException {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        return conn;
    }

    /**
     *
     */
    public void llamarTipoReporte() {

        Boolean estadoReporte;

        estadoReporte = true;

        if (idEntidad == 0) {
            this.msgEntidad = "La entidad es requerida";
            estadoReporte = false;
        } else {
            this.msgEntidad = "";
        }

        if (idReporte == 0) {
            this.msgReporte = "El tipo de reporte es requerido";
            estadoReporte = false;
        } else {
            this.msgReporte = "";
        }

        if (estadoReporte) {

            if (idReporte == 1) {
                try {
                    if (idSubReporte == 0) {
                        this.generarProveedores();
                    } else {
                        this.generarProveedoresEstado();
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 2) {
                try {
                    if (idSubReporte == 0) {
                        this.generarTipoActivos();
                    } else {
                        this.generarTipoActivosEstado();
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 3) {
                try {
                    if (idSubReporte == 0) {
                        this.generarActivos();
                    } else {
                        this.generarActivosEstado();
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 4) {
                try {
                    if (idSubReporte == 0) {
                        this.generarCatalogo();
                    } else {
                        this.generarCatalogoEstado();
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 5) {
                try {
                    this.generarLibro();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 6) {
                try {
                    this.generarLibroMayor();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 7) {
                try {
                    this.generarBalaza();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (idReporte == 9) {
                try {
                    this.generarEstadoResultado();
                } catch (Exception ex) {
                    Logger.getLogger(ReporteContabilidadBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    /**
     *
     * @throws Exception
     */
    public void generarProveedores() throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.idEntidad);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/ReporteProveedoresGeneral.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, conec());

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

    /**
     *
     * @throws Exception
     */
    public void generarProveedoresEstado() throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.idEntidad);

        if (this.idSubReporte == 1) {
            idEntidad.put("estado", true);
        } else {
            idEntidad.put("estado", false);
        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/ReporteProveedoresGeneralEstado.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, conec());

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

    /**
     *
     * @throws Exception
     */
    public void generarTipoActivos() throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.idEntidad);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/ReporteTiposActivos.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, conec());

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

    /**
     *
     * @throws Exception
     */
    public void generarTipoActivosEstado() throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.idEntidad);

        if (this.idSubReporte == 1) {
            idEntidad.put("estado", true);
        } else {
            idEntidad.put("estado", false);
        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/ReporteTiposActivosEstado.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, conec());

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

    /**
     *
     * @throws Exception
     */
    public void generarActivos() throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.idEntidad);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/reporteActivosFijos.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, conec());

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

    /**
     *
     * @throws Exception
     */
    public void generarActivosEstado() throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.idEntidad);

        if (this.idSubReporte == 1) {
            idEntidad.put("estado", "Activo");
        } else {
            idEntidad.put("estado", "Inactivo");
        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/reporteActivosFijosEstado.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, conec());

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

    /**
     *
     * @throws Exception
     */
    public void generarCatalogo() throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.idEntidad);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/CatalogoCuentas.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, conec());

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

    /**
     *
     * @throws Exception
     */
    public void generarCatalogoEstado() throws Exception {

        Map<String, Object> idEntidad = new HashMap();

        idEntidad.put("idEntidad", this.idEntidad);

        if (this.idSubReporte == 1) {
            idEntidad.put("estado", true);
        } else {
            idEntidad.put("estado", false);
        }

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/CatalogoCuentasEstado.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), idEntidad, conec());

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

    /**
     *
     * @throws Exception
     */
    public void generarLibro() throws Exception {

        Map<String, Object> parametros = new HashMap();

        parametros.put("idEntidad", this.idEntidad);

        parametros.put("idEjercicio", this.idEjercicio);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/LibroDiario.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, conec());

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

    /**
     *
     * @throws Exception
     */
    public void generarLibroMayor() throws Exception {

        Map<String, Object> parametros = new HashMap();

        parametros.put("idEntidad", this.idEntidad);

        parametros.put("idEjercicio", this.idEjercicio);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/LibroMayor.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, conec());

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

    /**
     *
     * @throws Exception
     */
    public void generarBalaza() throws Exception {

        Map<String, Object> parametros = new HashMap();

        parametros.put("idEjercicio", this.idEjercicio);

        parametros.put("idEntidad", this.idEntidad);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/balanzaComprobacion.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, conec());

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

    /**
     *
     * @throws Exception
     */
    public void generarEstadoResultado() {


        /* Accesando a datos de configuracion Estado de Resultado*/
        List<TEstructura> auxEst = this.estructuraBo.listEstructura(this.idEjercicio, 1);

        this.listaEstructuraCostos = new ArrayList<>();
        this.listaEstructuraGastos = new ArrayList<>();
        this.listaEstructuraIngresos = new ArrayList<>();
        this.listaEstructuraOtrosGastos = new ArrayList<>();
        this.listaEstructuraOtrosIngresos = new ArrayList<>();
        this.rentaEstructura = null;
        this.ReservaEstructura = null;
        this.utilidadEstructura = null;

        if (!auxEst.isEmpty()) {

            for (TEstructura tEstructura : auxEst) {
                switch (tEstructura.getGrupoReporte()) {
                    case 1:
                        this.listaEstructuraIngresos.add(tEstructura);
                        break;
                    case 2:
                        this.listaEstructuraCostos.add(tEstructura);
                        break;
                    case 3:
                        this.listaEstructuraGastos.add(tEstructura);
                        break;
                    case 4:
                        this.listaEstructuraOtrosIngresos.add(tEstructura);
                        break;
                    case 5:
                        this.listaEstructuraOtrosGastos.add(tEstructura);
                        break;
                    case 6:
                        this.rentaEstructura = tEstructura;
                        break;
                    case 7:
                        this.ReservaEstructura = tEstructura;
                        break;
                    case 8:
                        this.utilidadEstructura = tEstructura;
                        break;
                }
            }

            List<EstadoResultado> listEstRe = new ArrayList<>();
            BigDecimal Utilidad = BigDecimal.ZERO;
            /* 
            * Datos para el total de Ingresos
             */
            EstadoResultado grupoIngre = new EstadoResultado("INGRESOS", "", BigDecimal.ZERO, BigDecimal.ZERO);
            BigDecimal totalIngre = BigDecimal.ZERO;
            listEstRe.add(grupoIngre);

            for (TEstructura ingre : listaEstructuraIngresos) {
                EstadoResultado auxEstado;
                BigDecimal debe = this.repContBo.saldosCuenta(ingre.getTCuenta().getIdCuenta(), idEjercicio, "Bebe");
                BigDecimal haber = this.repContBo.saldosCuenta(ingre.getTCuenta().getIdCuenta(), idEjercicio, "Haber");
                BigDecimal saldo;
                if (ingre.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                    saldo = debe.subtract(haber);
                } else {
                    saldo = haber.subtract(debe);
                }
                totalIngre = totalIngre.add(saldo);
                auxEstado = new EstadoResultado("", ingre.getTCuenta().getNombreCuenta(), saldo, BigDecimal.ZERO);
                listEstRe.add(auxEstado);
            }
            grupoIngre.setSaldoGrupo(totalIngre);

            /* 
            * Datos para el total de Costos
             */
            EstadoResultado grupoCost = new EstadoResultado("COSTOS", "", BigDecimal.ZERO, BigDecimal.ZERO);
            BigDecimal totalCost = BigDecimal.ZERO;
            listEstRe.add(grupoCost);

            for (TEstructura cost : listaEstructuraIngresos) {
                EstadoResultado auxEstado;
                BigDecimal debe = this.repContBo.saldosCuenta(cost.getTCuenta().getIdCuenta(), idEjercicio, "Bebe");
                BigDecimal haber = this.repContBo.saldosCuenta(cost.getTCuenta().getIdCuenta(), idEjercicio, "Haber");
                BigDecimal saldo;
                if (cost.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                    saldo = debe.subtract(haber);
                } else {
                    saldo = haber.subtract(debe);
                }
                totalCost = totalCost.add(saldo);
                auxEstado = new EstadoResultado("", cost.getTCuenta().getNombreCuenta(), saldo, BigDecimal.ZERO);
                listEstRe.add(auxEstado);
            }
            grupoCost.setSaldoGrupo(totalCost);

            /* 
            * Datos para Utilidad Bruta
             */
            Utilidad = totalIngre.subtract(totalCost);
            EstadoResultado grupoUtiBrut = new EstadoResultado("UTILIDAD BRUTA", "", BigDecimal.ZERO, totalIngre.subtract(totalCost));
            listEstRe.add(grupoUtiBrut);

            /* 
            * Datos para el total de Gastos
             */
            EstadoResultado grupoGast = new EstadoResultado("GASTOS DE OPERACIÓN", "", BigDecimal.ZERO, BigDecimal.ZERO);
            BigDecimal totalGast = BigDecimal.ZERO;
            listEstRe.add(grupoGast);

            for (TEstructura gast : listaEstructuraIngresos) {
                EstadoResultado auxEstado;
                BigDecimal debe = this.repContBo.saldosCuenta(gast.getTCuenta().getIdCuenta(), idEjercicio, "Bebe");
                BigDecimal haber = this.repContBo.saldosCuenta(gast.getTCuenta().getIdCuenta(), idEjercicio, "Haber");
                BigDecimal saldo;
                if (gast.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                    saldo = debe.subtract(haber);
                } else {
                    saldo = haber.subtract(debe);
                }
                totalCost = totalCost.add(saldo);
                auxEstado = new EstadoResultado("", gast.getTCuenta().getNombreCuenta(), saldo, BigDecimal.ZERO);
                listEstRe.add(auxEstado);
            }

            grupoGast.setSaldoGrupo(totalGast);

            /* 
            * Datos para Utilidad de Operacion
             */
            Utilidad = Utilidad.subtract(totalGast);
            EstadoResultado grupoUtiOpe = new EstadoResultado("UTILIDAD DE OPERACIÓN ", "", BigDecimal.ZERO, Utilidad.subtract(totalGast));
            listEstRe.add(grupoUtiOpe);

            /* 
            * Datos para Otros Ingresos
             */
            EstadoResultado grupoOtrIngre = new EstadoResultado("OTROS INGRESOS", "", BigDecimal.ZERO, BigDecimal.ZERO);
            BigDecimal totalOtrIngre = BigDecimal.ZERO;
            listEstRe.add(grupoOtrIngre);

            for (TEstructura otrIngre : listaEstructuraOtrosIngresos) {
                EstadoResultado auxEstado;
                BigDecimal debe = this.repContBo.saldosCuenta(otrIngre.getTCuenta().getIdCuenta(), idEjercicio, "Bebe");
                BigDecimal haber = this.repContBo.saldosCuenta(otrIngre.getTCuenta().getIdCuenta(), idEjercicio, "Haber");
                BigDecimal saldo;
                if (otrIngre.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                    saldo = debe.subtract(haber);
                } else {
                    saldo = haber.subtract(debe);
                }
                totalOtrIngre = totalCost.add(saldo);
                auxEstado = new EstadoResultado("", otrIngre.getTCuenta().getNombreCuenta(), saldo, BigDecimal.ZERO);
                listEstRe.add(auxEstado);
            }

            grupoOtrIngre.setSaldoGrupo(totalOtrIngre);

            /* 
            * Datos para Otros Gastos
             */
            EstadoResultado grupoOtrGast = new EstadoResultado("OTROS GASTOS", "", BigDecimal.ZERO, BigDecimal.ZERO);
            BigDecimal totalOtrGast = BigDecimal.ZERO;
            listEstRe.add(grupoOtrGast);

            for (TEstructura otrGast : listaEstructuraOtrosIngresos) {
                EstadoResultado auxEstado;
                BigDecimal debe = this.repContBo.saldosCuenta(otrGast.getTCuenta().getIdCuenta(), idEjercicio, "Bebe");
                BigDecimal haber = this.repContBo.saldosCuenta(otrGast.getTCuenta().getIdCuenta(), idEjercicio, "Haber");
                BigDecimal saldo;
                if (otrGast.getTCuenta().getNaturalezaCuenta().equals("Deudora")) {
                    saldo = debe.subtract(haber);
                } else {
                    saldo = haber.subtract(debe);
                }
                totalOtrGast = totalCost.add(saldo);
                auxEstado = new EstadoResultado("", otrGast.getTCuenta().getNombreCuenta(), saldo, BigDecimal.ZERO);
                listEstRe.add(auxEstado);
            }

            grupoOtrGast.setSaldoGrupo(totalOtrGast);

            /* 
            * Datos para Utilidad de Ates de Impuestos
             */
            Utilidad = Utilidad.add(totalOtrIngre).subtract(totalOtrGast);
            EstadoResultado grupoUtiAntImp = new EstadoResultado("UTILIDAD ANTES DE IMPUESTOS ", "", BigDecimal.ZERO, Utilidad.add(totalOtrIngre).subtract(totalOtrGast));
            listEstRe.add(grupoUtiAntImp);

            if (this.rentaEstructura != null) {

                BigDecimal porRent;

                porRent = rentaEstructura.getPorMinEstructura().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);

                BigDecimal totalRent = Utilidad.multiply(porRent);

                EstadoResultado grupoImp = new EstadoResultado("IMPUESTO DE RENTA", " ", BigDecimal.ZERO, totalRent);

                listEstRe.add(grupoImp);

                Utilidad = Utilidad.subtract(totalRent);
                EstadoResultado grupoUtiLiq = new EstadoResultado("UTILIDAD LIQUIDA", "", BigDecimal.ZERO, Utilidad.subtract(totalRent));
                listEstRe.add(grupoUtiLiq);

            }

            if (this.ReservaEstructura != null) {

                BigDecimal porRese;

                porRese = ReservaEstructura.getPorMinEstructura().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);

                BigDecimal totalRese = Utilidad.multiply(porRese);

                EstadoResultado grupoRese = new EstadoResultado("RESERVA LEGAL", " ", BigDecimal.ZERO, totalRese);

                listEstRe.add(grupoRese);

                Utilidad = Utilidad.subtract(totalRese);

            }

            EstadoResultado resulEjer = new EstadoResultado("RESULTADO DEL EJERCICIO", "", BigDecimal.ZERO, Utilidad);
            listEstRe.add(resulEjer);

            try {

                TEntidad auxEntidad = entidadBo.getTEntidad(idEntidad);

                TEjercicio auxEjercicio = ejercicioBo.getEjercicio(idEjercicio);

                GregorianCalendar calendar = new GregorianCalendar();

                if (calendar.isLeapYear(auxEjercicio.getAhoEjercicio())) {
                }

                Map<String, Object> parametros = new HashMap();

                parametros.put("nombreEntidad", auxEntidad.getNombreEntidad());

                parametros.put("direccionEntidad", auxEntidad.getDireccionEntidad());

                parametros.put("telEntidad", auxEntidad.getTelefonoEntidad());

                parametros.put("logoEntidad", auxEntidad.getLogoEntidad());

                JRDataSource dataSource = new JRBeanCollectionDataSource(listEstRe);

                File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/estadoResultado.jasper"));

                byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, dataSource);

                System.out.println(bytes.length);

                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream outStream = response.getOutputStream();
                outStream.write(bytes, 0, bytes.length);
                outStream.flush();
                outStream.close();
                FacesContext.getCurrentInstance().responseComplete();

            } catch (Exception e) {

            }

//            
//            for (EstadoResultado esRe : listEstRe) {
//                System.out.println(esRe.getNombreGrupo() + "---" + esRe.getNombreCuenta() + "---" + esRe.getSaldoCuenta() + "---" + esRe.getSaldoGrupo());
//            }
        }

    }

    public void generarBalanceGenral() {


        /* Accesando a datos de configuracion Balance General*/
        List<TEstructura> auxEst = this.estructuraBo.listEstructura(this.idEjercicio, 2);

        this.estructuraActivo = new TEstructura();
        this.estructuraPasivo = new TEstructura();
        this.estructuraCapital = new TEstructura();

        if (!auxEst.isEmpty()) {
            for (TEstructura tauxEstBalace : auxEst) {
                switch (tauxEstBalace.getGrupoReporte()) {
                    case 11:
                        this.estructuraActivo = tauxEstBalace;
                        break;
                    case 12:
                        this.estructuraPasivo = tauxEstBalace;
                        break;
                    case 13:
                        this.estructuraCapital = tauxEstBalace;
                        break;

                }
            }

        /*Codigo Aqui*/
        }

    }

}
