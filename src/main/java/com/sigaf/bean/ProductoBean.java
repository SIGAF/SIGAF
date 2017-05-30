/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IProductoBo;
import com.sigaf.Ibo.IProductoCooperativaBo;
import com.sigaf.Ibo.IProductoProductorBo;
import com.sigaf.Ibo.IProductorIndividualBo;
import com.sigaf.pojo.TAreaCultivo;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TProducto;
import com.sigaf.pojo.TProductoCooperativa;
import com.sigaf.pojo.TProductoProductor;
import com.sigaf.pojo.TProductorIndividual;
import com.sigaf.pojo.TTipoCultivo;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Named(value = "productoBean")
@RequestScoped
@ManagedBean
public class ProductoBean extends Actividad {

    private TProducto producto;
    private TProductoProductor productoProductor;
    private TProductoCooperativa productoCooperativa;
    private IProductorIndividualBo iproductorIndividual;

    public IProductorIndividualBo getIproductorIndividual() {
        return iproductorIndividual;
    }

    public void setIproductorIndividual(IProductorIndividualBo iproductorIndividual) {
        this.iproductorIndividual = iproductorIndividual;
    }

    public TProductoCooperativa getProductoCooperativa() {
        return productoCooperativa;
    }

    public void setProductoCooperativa(TProductoCooperativa productoCooperativa) {
        this.productoCooperativa = productoCooperativa;
    }

    public TProductoProductor getProductoProductor() {
        return productoProductor;
    }

    public void setProductoProductor(TProductoProductor productoProductor) {
        this.productoProductor = productoProductor;
    }
    private TProducto ProductoSeleccionado;
    private List<TProducto> listaProducto;
    private List<TProducto> listaProductoFiltrados;
    private TProductorIndividual productorIndividual;
    private TAreaCultivo area;

    public TAreaCultivo getArea() {
        return area;
    }

    public void setArea(TAreaCultivo area) {
        this.area = area;
    }

    public TProductorIndividual getProductorIndividual() {
        return productorIndividual;
    }

    public void setProductorIndividual(TProductorIndividual productorIndividual) {
        this.productorIndividual = productorIndividual;
    }

    public List<TProducto> getListaProductoFiltrados() {
        return listaProductoFiltrados;
    }

    public void setListaProductoFiltrados(List<TProducto> listaProductoFiltrados) {
        this.listaProductoFiltrados = listaProductoFiltrados;
    }

    private IProductoBo productoBo;
    private IProductoProductorBo productoProductorBo;
    private IProductoCooperativaBo productoCooperativaBo;
    private Integer idproductorSeleccionado;
    private TTipoCultivo tipoCultivoSeleccionado;

    public TTipoCultivo getTipoCultivoSeleccionado() {
        return tipoCultivoSeleccionado;
    }

    public void setTipoCultivoSeleccionado(TTipoCultivo tipoCultivoSeleccionado) {
        this.tipoCultivoSeleccionado = tipoCultivoSeleccionado;
    }

    private List<TProductoProductor> listaProductoProductor;

    public List<TProductoProductor> getListaProductoProductor() {
        return listaProductoProductor;
    }

    public void setListaProductoProductor(List<TProductoProductor> listaProductoProductor) {
        this.listaProductoProductor = listaProductoProductor;
    }

    public Integer getIdproductorSeleccionado() {
        return idproductorSeleccionado;
    }

    public void setIdproductorSeleccionado(Integer idproductorSeleccionado) {
        this.idproductorSeleccionado = idproductorSeleccionado;
    }

    public IProductoCooperativaBo getProductoCooperativaBo() {
        return productoCooperativaBo;
    }

    public void setProductoCooperativaBo(IProductoCooperativaBo productoCooperativaBo) {
        this.productoCooperativaBo = productoCooperativaBo;
    }

    public IProductoProductorBo getProductoProductorBo() {
        return productoProductorBo;
    }

    public void setProductoProductorBo(IProductoProductorBo productoProductorBo) {
        this.productoProductorBo = productoProductorBo;
    }

    public IProductoBo getProductoBo() {
        return productoBo;
    }

    public void setProductoBo(IProductoBo productoBo) {
        this.productoBo = productoBo;
    }

    //propiedades de validacion
    private String msgNombre;

    private String msgArea;
    private String msgTipo;
    private String msgSistema;
    private String msgOrganizacion;
    private String msgProductor;
    private String msgAreaCultivo;
    private String msgTipoCultivo;
    private Boolean mostrarTabla;
    private Boolean habilitarAsisten;

    public Boolean getHabilitarAsisten() {
        return habilitarAsisten;
    }

    public void setHabilitarAsisten(Boolean habilitarAsisten) {
        this.habilitarAsisten = habilitarAsisten;
    }

    public Boolean getMostrarTabla() {
        return mostrarTabla;
    }

    public void setMostrarTabla(Boolean mostrarTabla) {
        this.mostrarTabla = mostrarTabla;
    }

    public String getMsgProductor() {
        return msgProductor;
    }

    public void setMsgProductor(String msgProductor) {
        this.msgProductor = msgProductor;
    }

    public String getMsgAreaCultivo() {
        return msgAreaCultivo;
    }

    public void setMsgAreaCultivo(String msgAreaCultivo) {
        this.msgAreaCultivo = msgAreaCultivo;
    }

    public String getMsgTipoCultivo() {
        return msgTipoCultivo;
    }

    public void setMsgTipoCultivo(String msgTipoCultivo) {
        this.msgTipoCultivo = msgTipoCultivo;
    }

    public String getMsgArea() {
        return msgArea;
    }

    public void setMsgArea(String msgArea) {
        this.msgArea = msgArea;
    }

    public String getMsgTipo() {
        return msgTipo;
    }

    public void setMsgTipo(String msgTipo) {
        this.msgTipo = msgTipo;
    }

    public String getMsgSistema() {
        return msgSistema;
    }

    public void setMsgSistema(String msgSistema) {
        this.msgSistema = msgSistema;
    }

    public String getMsgOrganizacion() {
        return msgOrganizacion;
    }

    public void setMsgOrganizacion(String msgOrganizacion) {
        this.msgOrganizacion = msgOrganizacion;
    }

    private List<TProductorIndividual> listaProductoresIndividuales;

    public List<TProductorIndividual> getListaProductoresIndividuales() {
        return listaProductoresIndividuales;
    }

    public void setListaProductoresIndividuales(List<TProductorIndividual> listaProductoresIndividuales) {
        this.listaProductoresIndividuales = listaProductoresIndividuales;
    }

    private boolean estadoFormulario;

    private Integer tipo;
    private Boolean ShowIndi;
    private Boolean ShowCoop;
    private Integer entidadSeleccionada;
    private Integer productorSeleccionado;
    private Integer idAreaSeleccionada;
    private Integer idTipoSeleccionado;

    public Integer getIdAreaSeleccionada() {
        return idAreaSeleccionada;
    }

    public void setIdAreaSeleccionada(Integer idAreaSeleccionada) {
        this.idAreaSeleccionada = idAreaSeleccionada;
    }

    public Integer getIdTipoSeleccionado() {
        return idTipoSeleccionado;
    }

    public void setIdTipoSeleccionado(Integer idTipoSeleccionado) {
        this.idTipoSeleccionado = idTipoSeleccionado;
    }

    public Integer getProductorSeleccionado() {
        return productorSeleccionado;
    }

    public void setProductorSeleccionado(Integer productorSeleccionado) {
        this.productorSeleccionado = productorSeleccionado;
    }

    public Integer getEntidadSeleccionada() {
        return entidadSeleccionada;
    }

    public void setEntidadSeleccionada(Integer entidadSeleccionada) {
        this.entidadSeleccionada = entidadSeleccionada;
    }

    public Boolean getShowIndi() {
        return ShowIndi;
    }

    public void setShowIndi(Boolean ShowIndi) {
        this.ShowIndi = ShowIndi;
    }

    public Boolean getShowCoop() {
        return ShowCoop;
    }

    public void setShowCoop(Boolean ShowCoop) {
        this.ShowCoop = ShowCoop;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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

    public void init() {

        super.setShowCreate(true);
        super.setShowData(false);
        super.setShowUpdate(false);
        this.producto = new TProducto();
        this.ProductoSeleccionado = new TProducto();
        this.ProductoSeleccionado.setTTipoCultivo(new TTipoCultivo());
        this.productoProductor = new TProductoProductor();
        this.productoProductor.setTProducto(new TProducto());
        this.productoProductor.setTProductorIndividual(new TProductorIndividual());
        this.productoCooperativa = new TProductoCooperativa();
        this.productoCooperativa.setTEntidad(new TEntidad());
        this.productoCooperativa.setTProducto(new TProducto());
        this.listaProducto = new ArrayList<TProducto>();
        this.listaProductoFiltrados = new ArrayList<TProducto>();
        this.productorIndividual = new TProductorIndividual();
        this.listaProductoresIndividuales = this.iproductorIndividual.listProductorIndividual();
    }

    private List<SelectItem> selectOneItemIndividual;
    private List<SelectItem> selectOneItemAreas;
    private List<SelectItem> selectOneItemTipos;

    public List<SelectItem> getSelectOneItemTipos() {
        return selectOneItemTipos;
    }

    public void setSelectOneItemTipos(List<SelectItem> selectOneItemTipos) {
        this.selectOneItemTipos = selectOneItemTipos;
    }

    public List<SelectItem> getSelectOneItemAreas() {

        this.selectOneItemAreas = new ArrayList<SelectItem>();
        List<TAreaCultivo> productores = this.productoBo.listTAreaCultivo();
        for (TAreaCultivo individual : productores) {
            if (individual.getEstadoAreaCultivo() == true) {
                SelectItem selectItem = new SelectItem(individual.getIdAreaCultivo(), individual.getNombreAreaCultivo());
                this.selectOneItemAreas.add(selectItem);
            }
        }

        return selectOneItemAreas;
    }

    public void setSelectOneItemAreas(List<SelectItem> selectOneItemAreas) {
        this.selectOneItemAreas = selectOneItemAreas;
    }

    public TProducto getProducto() {
        return producto;
    }

    public void setProducto(TProducto producto) {
        this.producto = producto;
    }

    public TProducto getProductoSeleccionado() {
        return ProductoSeleccionado;
    }

    public void setProductoSeleccionado(TProducto ProductoSeleccionado) {
        this.ProductoSeleccionado = ProductoSeleccionado;
    }

    public List<TProducto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<TProducto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public List<SelectItem> getSelectOneItemIndividual() {

        this.selectOneItemIndividual = new ArrayList<SelectItem>();
        List<TProductorIndividual> productores = this.productoBo.listTProductorIndividual();
        for (TProductorIndividual individual : productores) {
            if (individual.getEstadoProductorIndividual() == true) {
                SelectItem selectItem = new SelectItem(individual.getIdProductorIndividual(), individual.getNombresProdcutorIndividual());
                this.selectOneItemIndividual.add(selectItem);
            }
        }
        return selectOneItemIndividual;
    }

    public void setSelectOneItemIndividual(List<SelectItem> selectOneItemIndividual) {
        this.selectOneItemIndividual = selectOneItemIndividual;
    }

    public void modificar() {

        this.ProductoSeleccionado.setTTipoCultivo(new TTipoCultivo(this.idTipoSeleccionado));
        
        if(this.ProductoSeleccionado.getAsistencia()==false){
            
            this.ProductoSeleccionado.setOrganizacion("No aplica");
            
            
            
        }
        

        this.productoBo.update(this.ProductoSeleccionado);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cultivo modificado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

        super.enableShowData();
        limpiar();
    }

    public void darDeBaja() {

        this.ProductoSeleccionado.setEstado(false);
        this.productoBo.update(this.ProductoSeleccionado);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto dado de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

        super.enableShowData();

    }

    public void darDeAlta() {

        this.ProductoSeleccionado.setEstado(true);
        this.productoBo.update(this.ProductoSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cultivo dado de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

        super.enableShowData();

    }

    public void crear() {

        this.estadoFormulario = false;

        TTipoCultivo tipoCultivo = new TTipoCultivo();
        tipoCultivo.setIdTipoCultivo(this.idTipoSeleccionado);

        for (int i = 0; i < this.listaProducto.size(); i++) {
            this.listaProducto.get(i).setTTipoCultivo(tipoCultivo);
            this.listaProducto.get(i).setEstado(true);
            this.productoBo.create(this.listaProducto.get(i));
            this.productoProductor.setTProducto(this.listaProducto.get(i));
            this.productoProductor.setTProductorIndividual(this.productorIndividual);
            this.productoProductorBo.create(productoProductor);

        }

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cultivos guardados correctamente", null);
        FacesContext.getCurrentInstance().addMessage("" + this.listaProducto.get(0).getNombre(), mensaje);
        limpiar();

    }

    public String verEstado(boolean estado) {
        if (estado) {
            return "Activo";

        } else {
            return "Inactivo";
        }
    }

    private String msgLista;

    public String getMsgLista() {
        return msgLista;
    }

    public void setMsgLista(String msgLista) {
        this.msgLista = msgLista;
    }

    public void validarFormulario() {
        this.estadoFormulario = true;

        if (this.listaProducto.size() == 0) {
            this.msgLista = "Debe introducir al menos un cultivo";
            this.estadoFormulario = false;
        } else {
            this.msgLista = "";
        }

        if (this.productorIndividual.getNombresProdcutorIndividual() == null) {
            this.msgProductor = "Debe seleccionar un productor";
            this.estadoFormulario = false;
        } else {
            this.msgProductor = "";
        }

    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;

        if (this.ProductoSeleccionado.getNombre() == "") {
            this.msgNombre = "Debe introducir el nombre";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }

        if (this.ProductoSeleccionado.getAsistencia() == true) {

            if (this.ProductoSeleccionado.getOrganizacion() == "") {
                this.msgOrganizacion = "Debe introducir la organización";
                this.estadoFormulario = false;
            } else {
                this.msgOrganizacion = "";
            }

        }

        if (this.idAreaSeleccionada == 0) {
            this.msgArea = "Debe seleccionar una área";
            this.estadoFormulario = false;
        } else {
            this.msgArea = "";
        }
        if (this.idTipoSeleccionado == 0) {
            this.msgTipo = "Debe seleccionar un tipo";
            this.estadoFormulario = false;
        } else {
            this.msgTipo = "";
        }

    }

    public void limpiar() {

        this.estadoFormulario = false;

        this.msgNombre = "";
        this.msgArea = "";
        this.msgTipo = "";
        this.msgSistema = "";
        this.msgOrganizacion = "";
        this.listaProducto.clear();
        this.producto = new TProducto();
        mostrarDatosFiltrados();

    }

    public void enableShowDataBean() {
        limpiar();
        super.enableShowData();

    }

    public int sumarParticipantes(int hombres, int mujeres) {

        return hombres + mujeres;

    }

    public void llenar() {

        if (this.tipo == 1) {

            this.ShowIndi = true;
            this.ShowCoop = false;

        } else if (this.tipo == 3) {

            this.ShowCoop = true;
            this.ShowIndi = false;

        } else {

            this.ShowCoop = false;
            this.ShowIndi = false;
        }

    }

    public void mostrarTipos() {

        this.selectOneItemTipos = new ArrayList<SelectItem>();
        List<TTipoCultivo> productores = this.productoBo.listTTipoCultivo(this.idAreaSeleccionada);
        for (TTipoCultivo individual : productores) {
            if (individual.getEstadoTipoCultivo() == true) {
                SelectItem selectItem = new SelectItem(individual.getIdTipoCultivo(), individual.getNombreTipoCultivo());
                this.selectOneItemTipos.add(selectItem);
            }
        }

    }

    public void llenarProductos() {
        if (this.producto.getAsistencia() == false) {

            this.producto.setOrganizacion("No aplica");
        }

        this.listaProducto.add(this.producto);
        this.producto = new TProducto();
    }

    public void removerActivos(int posicion) {

        this.listaProducto.remove(posicion);
    }

    public void mostrarDatosFiltrados() {

        this.listaProductoFiltrados.clear();

        this.listaProductoProductor = this.productoProductorBo.listTProductoProductor(this.productorIndividual.getIdProductorIndividual());

        for (int i = 0; i < this.listaProductoProductor.size(); i++) {

            this.listaProductoFiltrados.add(this.productoBo.getTProducto(this.listaProductoProductor.get(i).getTProducto().getIdproducto()));

        }

        this.mostrarTabla = true;

    }

    public void cargarVista() {

        this.tipoCultivoSeleccionado = this.productoBo.getTCultivo(this.ProductoSeleccionado.getTTipoCultivo().getIdTipoCultivo());
        this.idTipoSeleccionado = this.ProductoSeleccionado.getTTipoCultivo().getIdTipoCultivo();
        this.idAreaSeleccionada = this.tipoCultivoSeleccionado.getTAreaCultivo().getIdAreaCultivo();

        if (this.ProductoSeleccionado.getAsistencia() == true) {

            this.habilitarAsisten = true;

        } else {

            this.habilitarAsisten = false;
        }

        this.selectOneItemTipos = new ArrayList<SelectItem>();
        List<TTipoCultivo> productores = this.productoBo.listTTipoCultivo(this.idAreaSeleccionada);
        for (TTipoCultivo individual : productores) {
            if (individual.getEstadoTipoCultivo() == true) {
                SelectItem selectItem = new SelectItem(individual.getIdTipoCultivo(), individual.getNombreTipoCultivo());
                this.selectOneItemTipos.add(selectItem);
            }
        }

    }

    public void cargarVista3() {

        this.tipoCultivoSeleccionado = this.productoBo.getTCultivo(this.ProductoSeleccionado.getTTipoCultivo().getIdTipoCultivo());
        this.idTipoSeleccionado = this.ProductoSeleccionado.getTTipoCultivo().getIdTipoCultivo();
        this.idAreaSeleccionada = this.tipoCultivoSeleccionado.getTAreaCultivo().getIdAreaCultivo();

        this.area = this.productoBo.getTAreaCultivo(this.tipoCultivoSeleccionado.getTAreaCultivo().getIdAreaCultivo());

    }

    public String mostrarTipo(Integer i) {

        this.tipoCultivoSeleccionado = this.productoBo.getTCultivo(i);
        return this.tipoCultivoSeleccionado.getNombreTipoCultivo();

    }

    public String mostrarArea(Integer i) {

        this.tipoCultivoSeleccionado = this.productoBo.getTCultivo(i);
        TAreaCultivo area = this.productoBo.getTAreaCultivo(this.tipoCultivoSeleccionado.getTAreaCultivo().getIdAreaCultivo());

        return area.getNombreAreaCultivo();
    }

    public void mostrarProductorIndividual() {

        this.listaProductoresIndividuales = this.iproductorIndividual.listProductorIndividual();

    }

    public void mostrarProductorIndividualData() {

    }

    public void habilitar() {

        if (this.producto.getAsistencia() == true) {

            this.habilitarAsisten = true;
        } else {

            this.habilitarAsisten = false;
            this.producto.setOrganizacion("");
        }

    }

    public void habilitarModificar() {

        if (this.ProductoSeleccionado.getAsistencia() == true) {

            this.habilitarAsisten = true;
        } else {

            this.habilitarAsisten = false;
            this.ProductoSeleccionado.setOrganizacion("");
        }

    }

    public String cambiarAsistencia(Boolean asistencia) {

        if (asistencia == true) {

            return "Si";

        } else {
            return "No";

        }

    }

    public void verReporteProducto() throws SQLException, JRException, IOException {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        parametros.put("id_producto", this.ProductoSeleccionado.getIdproducto());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/producto.jasper"));

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

    public void verReporteProductoPDF() throws SQLException, JRException, IOException {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        parametros.put("id_producto", this.ProductoSeleccionado.getIdproducto());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/producto.jasper"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Producto.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

}
