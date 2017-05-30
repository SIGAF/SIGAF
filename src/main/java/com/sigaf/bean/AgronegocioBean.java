/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IAgronegocioBo;
import com.sigaf.Ibo.ICompradorBo;
import com.sigaf.Ibo.IConfiguracionAgronegocioBo;
import com.sigaf.Ibo.IPagoAgronegocioBo;
import com.sigaf.Ibo.IPagoBo;
import com.sigaf.Ibo.IProductoAgronegocioBo;
import com.sigaf.Ibo.IProductoBo;
import com.sigaf.Ibo.IProductoCooperativaBo;
import com.sigaf.Ibo.IProductoProductorBo;
import com.sigaf.Ibo.IProductorGrupalBo;
import com.sigaf.Ibo.IProductorIndividualBo;
import com.sigaf.pojo.TAgronegocio;
import com.sigaf.pojo.TAreaCultivo;
import com.sigaf.pojo.TComprador;
import com.sigaf.pojo.TConfiguracionAgronegocio;
import com.sigaf.pojo.TCooperativa;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TPagoAgronegocio;
import com.sigaf.pojo.TProducto;
import com.sigaf.pojo.TProductoAgronegocio;
import com.sigaf.pojo.TProductoCooperativa;
import com.sigaf.pojo.TProductoProductor;
import com.sigaf.pojo.TProductorGrupal;
import com.sigaf.pojo.TProductorIndividual;
import com.sigaf.pojo.TTipoCredito;
import com.sigaf.pojo.TTipoCultivo;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
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
@Named(value = "agronegocioBean")
@RequestScoped
@ManagedBean
public class AgronegocioBean extends Actividad {

    private TProducto producto;
    private TProductoProductor productoProductor;
    private TProducto productoDetalle;

    public TProducto getProductoDetalle() {
        return productoDetalle;
    }

    public void setProductoDetalle(TProducto productoDetalle) {
        this.productoDetalle = productoDetalle;
    }
    private TProductoCooperativa productoCooperativa;
    private TComprador compradorSeleccionado;
    private BigDecimal monto;
    private BigDecimal interes;
    private BigDecimal totales;
    private BigDecimal comisionC;

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public BigDecimal getTotales() {
        return totales;
    }

    public void setTotales(BigDecimal totales) {
        this.totales = totales;
    }

    public BigDecimal getComisionC() {
        return comisionC;
    }

    public void setComisionC(BigDecimal comisionC) {
        this.comisionC = comisionC;
    }

    public TComprador getCompradorSeleccionado() {
        return compradorSeleccionado;
    }

    public void setCompradorSeleccionado(TComprador compradorSeleccionado) {
        this.compradorSeleccionado = compradorSeleccionado;
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
    private TProductorIndividual productorIndividualSeleccionado;
    private TProductorIndividual productorIndividualSeleccionadoVista;

    public TProductorIndividual getProductorIndividualSeleccionadoVista() {
        return productorIndividualSeleccionadoVista;
    }

    public void setProductorIndividualSeleccionadoVista(TProductorIndividual productorIndividualSeleccionadoVista) {
        this.productorIndividualSeleccionadoVista = productorIndividualSeleccionadoVista;
    }

    public TProductorIndividual getProductorIndividualSeleccionado() {
        return productorIndividualSeleccionado;
    }

    public void setProductorIndividualSeleccionado(TProductorIndividual productorIndividualSeleccionado) {
        this.productorIndividualSeleccionado = productorIndividualSeleccionado;
    }
    private List<TProducto> listaProducto;
    private List<TProducto> listaProductoDetalle;

    private List<TProductorIndividual> listaProductoresIndividuales;

    public List<TProductorIndividual> getListaProductoresIndividuales() {
        return listaProductoresIndividuales = this.iproductorindividual.listProductorIndividual();
    }

    public void setListaProductoresIndividuales(List<TProductorIndividual> listaProductoresIndividuales) {
        this.listaProductoresIndividuales = listaProductoresIndividuales;
    }

    private List<TProductoAgronegocio> listaProductoAgronegocio;

    public List<TProductoAgronegocio> getListaProductoAgronegocio() {
        return listaProductoAgronegocio;
    }

    public void setListaProductoAgronegocio(List<TProductoAgronegocio> listaProductoAgronegocio) {
        this.listaProductoAgronegocio = listaProductoAgronegocio;
    }

    public List<TProducto> getListaProductoDetalle() {
        return listaProductoDetalle;
    }

    public void setListaProductoDetalle(List<TProducto> listaProductoDetalle) {
        this.listaProductoDetalle = listaProductoDetalle;
    }

    private IProductoBo productoBo;
    private IProductoProductorBo productoProductorBo;
    private IProductoCooperativaBo productoCooperativaBo;
    private ICompradorBo compradorBo;
    private IConfiguracionAgronegocioBo configuracionAgronegocioBo;
    private IAgronegocioBo agronegocioBo;
    private IProductorIndividualBo iproductorindividual;

    private BigDecimal valorRedondeadoTotal;
    private BigDecimal valorRedondeadoComision;
    private BigDecimal valorRedondeadoComisionCredito;

    public BigDecimal getValorRedondeadoComisionCredito() {
        return valorRedondeadoComisionCredito;
    }

    public void setValorRedondeadoComisionCredito(BigDecimal valorRedondeadoComisionCredito) {
        this.valorRedondeadoComisionCredito = valorRedondeadoComisionCredito;
    }

    public BigDecimal getValorRedondeadoTotal() {
        return valorRedondeadoTotal;
    }

    public void setValorRedondeadoTotal(BigDecimal valorRedondeadoTotal) {
        this.valorRedondeadoTotal = valorRedondeadoTotal;
    }

    public BigDecimal getValorRedondeadoComision() {
        return valorRedondeadoComision;
    }

    public void setValorRedondeadoComision(BigDecimal valorRedondeadoComision) {
        this.valorRedondeadoComision = valorRedondeadoComision;
    }

    public IProductorIndividualBo getIproductorindividual() {
        return iproductorindividual;
    }

    public void setIproductorindividual(IProductorIndividualBo iproductorindividual) {
        this.iproductorindividual = iproductorindividual;
    }

    private TAgronegocio agronegocio;
    private TAgronegocio agronegocioSeleccionado;

    public TAgronegocio getAgronegocioSeleccionado() {
        return agronegocioSeleccionado;
    }

    public void setAgronegocioSeleccionado(TAgronegocio agronegocioSeleccionado) {
        this.agronegocioSeleccionado = agronegocioSeleccionado;
    }

    public TAgronegocio getAgronegocio() {
        return agronegocio;
    }

    public void setAgronegocio(TAgronegocio agronegocio) {
        this.agronegocio = agronegocio;
    }

    public IAgronegocioBo getAgronegocioBo() {
        return agronegocioBo;
    }

    public void setAgronegocioBo(IAgronegocioBo agronegocioBo) {
        this.agronegocioBo = agronegocioBo;
    }

    public IConfiguracionAgronegocioBo getConfiguracionAgronegocioBo() {
        return configuracionAgronegocioBo;
    }

    public void setConfiguracionAgronegocioBo(IConfiguracionAgronegocioBo configuracionAgronegocioBo) {
        this.configuracionAgronegocioBo = configuracionAgronegocioBo;
    }
    private IProductoAgronegocioBo productoAgronegocioBo;
    private IPagoAgronegocioBo pagoAgronegocioBo;
    private TPagoAgronegocio pagoAgro;
    private IPagoBo ipagoBo;

    public IPagoBo getIpagoBo() {
        return ipagoBo;
    }

    public void setIpagoBo(IPagoBo ipagoBo) {
        this.ipagoBo = ipagoBo;
    }

    public TPagoAgronegocio getPagoAgro() {
        return pagoAgro;
    }

    public void setPagoAgro(TPagoAgronegocio pagoAgro) {
        this.pagoAgro = pagoAgro;
    }
    private BigDecimal total;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    private Boolean mostrarTable;
    private TConfiguracionAgronegocio politicaAgronegocio;

    public TConfiguracionAgronegocio getPoliticaAgronegocio() {
        return politicaAgronegocio = this.configuracionAgronegocioBo.getConfiguracion(0);
    }

    public void setPoliticaAgronegocio(TConfiguracionAgronegocio politicaAgronegocio) {
        this.politicaAgronegocio = politicaAgronegocio;
    }

    public Boolean getMostrarTable() {
        return mostrarTable;
    }

    public void setMostrarTable(Boolean mostrarTable) {
        this.mostrarTable = mostrarTable;
    }

    public IPagoAgronegocioBo getPagoAgronegocioBo() {
        return pagoAgronegocioBo;
    }

    public void setPagoAgronegocioBo(IPagoAgronegocioBo pagoAgronegocioBo) {
        this.pagoAgronegocioBo = pagoAgronegocioBo;
    }

    public IProductoAgronegocioBo getProductoAgronegocioBo() {
        return productoAgronegocioBo;
    }

    public void setProductoAgronegocioBo(IProductoAgronegocioBo productoAgronegocioBo) {
        this.productoAgronegocioBo = productoAgronegocioBo;
    }

    public ICompradorBo getCompradorBo() {
        return compradorBo;
    }

    public void setCompradorBo(ICompradorBo compradorBo) {
        this.compradorBo = compradorBo;
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

    private List<TComprador> listaComprador;

    private List<TAgronegocio> listaAgronegocio;

    public List<TAgronegocio> getListaAgronegocio() {
        return listaAgronegocio;
    }

    public void setListaAgronegocio(List<TAgronegocio> listaAgronegocio) {
        this.listaAgronegocio = listaAgronegocio;
    }

    private List<TProductoProductor> listaProductoProductor;

    private List<TProductoProductor> listaProductoProductorCod;

    public List<TProductoProductor> getListaProductoProductorCod() {
        return listaProductoProductorCod;
    }

    public void setListaProductoProductorCod(List<TProductoProductor> listaProductoProductorCod) {
        this.listaProductoProductorCod = listaProductoProductorCod;
    }

    public List<TProductoProductor> getListaProductoProductor() {
        return listaProductoProductor;
    }

    public void setListaProductoProductor(List<TProductoProductor> listaProductoProductor) {
        this.listaProductoProductor = listaProductoProductor;
    }

    public List<TComprador> getListaComprador() {

        this.listaComprador.clear();

        List<TComprador> compradores = this.compradorBo.listComprador();
        for (TComprador comprador : compradores) {
            if (comprador.getEstadoComprador() == true) {
                this.listaComprador.add(comprador);
            }
        }
        return listaComprador;
    }

    public void setListaComprador(List<TComprador> listaComprador) {
        this.listaComprador = listaComprador;
    }

    //propiedades de validacion
    private String msgCodigo;

    private String msgFecha;
    private String msgProductor;
    private String msgComprador;
    private String msgNumeroCheque;
    private String msgPlazo;
    private String msgFechaEstipulada;

    public String getMsgFechaEstipulada() {
        return msgFechaEstipulada;
    }

    public void setMsgFechaEstipulada(String msgFechaEstipulada) {
        this.msgFechaEstipulada = msgFechaEstipulada;
    }
    private String msglistaProductos;
    private String msgCheque;

    public String getMsgCheque() {
        return msgCheque;
    }

    public void setMsgCheque(String msgCheque) {
        this.msgCheque = msgCheque;
    }

    public String getMsgCodigo() {
        return msgCodigo;
    }

    public void setMsgCodigo(String msgCodigo) {
        this.msgCodigo = msgCodigo;
    }

    public String getMsgFecha() {
        return msgFecha;
    }

    public void setMsgFecha(String msgFecha) {
        this.msgFecha = msgFecha;
    }

    public String getMsgComprador() {
        return msgComprador;
    }

    public void setMsgComprador(String msgComprador) {
        this.msgComprador = msgComprador;
    }

    public String getMsgNumeroCheque() {
        return msgNumeroCheque;
    }

    public void setMsgNumeroCheque(String msgNumeroCheque) {
        this.msgNumeroCheque = msgNumeroCheque;
    }

    public String getMsgPlazo() {
        return msgPlazo;
    }

    public void setMsgPlazo(String msgPlazo) {
        this.msgPlazo = msgPlazo;
    }

    public String getMsglistaProductos() {
        return msglistaProductos;
    }

    public void setMsglistaProductos(String msglistaProductos) {
        this.msglistaProductos = msglistaProductos;
    }

    private String msgAreaCultivo;
    private String msgTipoCultivo;
    private Boolean tipocontado;
    private Boolean tipocredito;
    private Boolean PContado;
    private BigDecimal comision;
    private BigDecimal comisionCredito;
    private Integer dias;

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public BigDecimal getComisionCredito() {
        return comisionCredito;
    }

    public void setComisionCredito(BigDecimal comisionCredito) {
        this.comisionCredito = comisionCredito;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }

    private Boolean tipoefectivo;
    private Boolean tipocheque;
    private Boolean Habilitar;

    public Boolean getTipoefectivo() {
        return tipoefectivo;
    }

    public void setTipoefectivo(Boolean tipoefectivo) {
        this.tipoefectivo = tipoefectivo;
    }

    public Boolean getTipocheque() {
        return tipocheque;
    }

    public void setTipocheque(Boolean tipocheque) {
        this.tipocheque = tipocheque;
    }

    public Boolean getHabilitar() {
        return Habilitar;
    }

    public void setHabilitar(Boolean Habilitar) {
        this.Habilitar = Habilitar;
    }

    public Boolean getTipocontado() {
        return tipocontado;
    }

    public void setTipocontado(Boolean tipocontado) {
        this.tipocontado = tipocontado;
    }

    public Boolean getTipocredito() {
        return tipocredito;
    }

    public void setTipocredito(Boolean tipocredito) {
        this.tipocredito = tipocredito;
    }

    public Boolean getPContado() {
        return PContado;
    }

    public void setPContado(Boolean PContado) {
        this.PContado = PContado;
    }

    public Boolean getPCredito() {
        return PCredito;
    }

    public void setPCredito(Boolean PCredito) {
        this.PCredito = PCredito;
    }
    private Boolean PCredito;

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

    private boolean estadoFormulario;

    private Integer tipo;
    private Boolean ShowIndi;
    private Boolean ShowCoop;
    private Integer entidadSeleccionada;
    private Integer productorSeleccionado;
    private Integer idAreaSeleccionada;
    private Integer idTipoSeleccionado;
    private TProductoAgronegocio productoAgronegocio;

    public TProductoAgronegocio getProductoAgronegocio() {
        return productoAgronegocio;
    }

    public void setProductoAgronegocio(TProductoAgronegocio productoAgronegocio) {
        this.productoAgronegocio = productoAgronegocio;
    }

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

    public void init() {

        super.setShowCreate(true);
        super.setShowData(false);
        super.setShowUpdate(false);
        this.tipocontado = true;
        this.PContado = true;
        this.producto = new TProducto();
        this.productoProductor = new TProductoProductor();
        this.productoAgronegocio = new TProductoAgronegocio();
        this.total = BigDecimal.ZERO;
        this.tipoefectivo = true;
        this.Habilitar = true;
        this.pagoAgro = new TPagoAgronegocio();
        this.productoAgronegocio.setTProducto(new TProducto());
        this.productoAgronegocio.setTAgronegocio(new TAgronegocio());
        this.listaAgronegocio = this.agronegocioBo.listAgronegocio();
        this.productorIndividualSeleccionado = new TProductorIndividual();
        this.tipocheque = false;
        this.agronegocio = new TAgronegocio();
        this.agronegocio.setTComprador(new TComprador());
        this.listaProductoAgronegocio = new ArrayList<TProductoAgronegocio>();
        this.productoProductor.setTProducto(new TProducto());
        this.productoProductor.setTProductorIndividual(new TProductorIndividual());
        this.productoCooperativa = new TProductoCooperativa();
        this.productoCooperativa.setTEntidad(new TEntidad());
        this.productoCooperativa.setTProducto(new TProducto());
        this.listaProducto = new ArrayList<TProducto>();
        this.listaProductoDetalle = new ArrayList<TProducto>();
        this.listaComprador = new ArrayList<TComprador>();
        this.compradorSeleccionado = new TComprador();
        this.compradorSeleccionado.setDuiComprador("");
        this.listaProductoProductor = new ArrayList<TProductoProductor>();
       
      
        this.valorRedondeadoTotal = new BigDecimal("0.00");
        this.valorRedondeadoComision = new BigDecimal("0.00");
        cargarNuevoAgronegocio();

        
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

    public void correlativo() {

        Integer correlativo = this.agronegocioBo.getCorrelativo();

        if (correlativo < 10) {

            this.agronegocio.setCodigo("AGRO-0000" + correlativo);

        } else if (correlativo < 100) {

            this.agronegocio.setCodigo("AGRO-000" + correlativo);

        } else if (correlativo < 1000) {

            this.agronegocio.setCodigo("AGRO-00" + correlativo);

        } else if (correlativo < 10000) {

            this.agronegocio.setCodigo("AGRO-0" + correlativo);
        } else {
            this.agronegocio.setCodigo("AGRO" + correlativo);
        }

    }

    public void modificar() {

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupo de productores modificado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

        super.enableShowData();
        limpiar();
    }

    public void darDeBaja() {

        this.agronegocioSeleccionado.setEstadoregistro(false);
        this.agronegocioBo.update(this.agronegocioSeleccionado);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agronegocio dado de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

        super.enableShowData();

    }

    public void darDeAlta() {

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agronegocio dado de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

        this.agronegocioSeleccionado.setEstadoregistro(true);
        this.agronegocioBo.update(this.agronegocioSeleccionado);
        super.enableShowData();

    }

    public void crear() {

        this.agronegocio.setTComprador(this.compradorSeleccionado);
        correlativo();

        if (this.tipoefectivo == true) {
            this.pagoAgro.setForma("Efectivo");
            this.pagoAgro.setCheque("No aplica");

        } else {
            this.pagoAgro.setForma("Cheque");

        }

        if (this.tipocontado == true) {
            this.agronegocio.setTipo("Contado");// 1 = contado
            this.agronegocio.setEstado("Finalizado");

        } else {

            this.agronegocio.setTipo("Credito");// 2 = credito
            this.agronegocio.setEstado("Pendiente");

        }

        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

        System.out.println("Hora: " + hourFormat.format(date));

        this.agronegocio.setTotal(this.total);

        this.agronegocio.setTConfiguracionAgronegocio(this.politicaAgronegocio);
        this.agronegocio.setEstadoregistro(true);

        //Generando el numero de recibo
        Integer numero = this.ipagoBo.getCorrelativo();
        Integer numero2 = this.pagoAgronegocioBo.getCorrelativo();

        if (numero > numero2) {
            numero++;
            this.pagoAgro.setReferencia(numero);
        } else {
            numero2++;
            this.pagoAgro.setReferencia(numero2);
        }

        if (this.tipocontado == true) {

            this.agronegocio.setComision(this.comision);
            this.agronegocio.setInteres(BigDecimal.ZERO);
            this.agronegocio.setFechapago(this.agronegocio.getFecha());
            this.pagoAgro.setHora("" + hourFormat.format(date));
            this.pagoAgro.setInteres(BigDecimal.ZERO);
            this.pagoAgro.setOtrosinteres(BigDecimal.ZERO);
            this.pagoAgro.setDias(0);
            this.pagoAgro.setMonto(this.total);
            this.agronegocio.setPlazo(0);
            this.pagoAgro.setComision(this.comision);
            this.agronegocioBo.create(this.agronegocio);
            this.pagoAgro.setTAgronegocio(this.agronegocio);
            this.pagoAgro.setFecha(this.agronegocio.getFecha());
            this.pagoAgronegocioBo.create(pagoAgro);

        } else {
            this.agronegocio.setComision(this.comisionCredito);
            this.agronegocio.setInteres((this.interes));
            this.agronegocioBo.create(this.agronegocio);

        }

        for (int i = 0; i < this.listaProductoAgronegocio.size(); i++) {

            this.listaProductoAgronegocio.get(i).setTAgronegocio(this.agronegocio);
            this.productoAgronegocioBo.create(this.listaProductoAgronegocio.get(i));

        }

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agronegocio guardado correctamente", null);
        FacesContext.getCurrentInstance().addMessage("", mensaje);
        correlativo();
        limpiarAgronegocio();
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

        if (this.agronegocio.getCodigo().length() == 0) {
            this.msgCodigo = "El codigo es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgCodigo = "";
        }

        if (this.agronegocio.getFecha() == null) {
            this.msgFecha = "La fecha es requeria";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }

        if (this.tipocredito == true) {

            if (this.agronegocio.getPlazo() == null || this.agronegocio.getPlazo() < 1) {
                this.msgPlazo = "El plazo es requerido";
                this.estadoFormulario = false;
            } else {
                this.msgPlazo = "";
            }
            if (this.agronegocio.getFechapago() == null) {
                this.msgFechaEstipulada = "La fecha estipulada de pago es requerida";
                this.estadoFormulario = false;
            } else {
                this.msgFechaEstipulada = "";
            }

        }

        if (this.tipocheque == true) {

            if ("".equals(this.pagoAgro.getCheque())) {
                this.msgCheque = "El numero de cheque es requerido";
                this.estadoFormulario = false;
            } else {
                this.msgCheque = "";
            }

        }

        if (this.productorIndividualSeleccionado.getNombresProdcutorIndividual() == "") {
            this.msgProductor = "El productor es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgProductor = "";
        }

        if (this.compradorSeleccionado.getDuiComprador() == "") {
            this.msgComprador = "El comprador es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgComprador = "";
        }

        if (this.listaProductoAgronegocio.size() == 0) {
            this.msglistaProductos = "Debe de seleccionar por lo menos un productor";
            this.estadoFormulario = false;
        } else {
            this.msglistaProductos = "";
        }

    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;

    }

    public void limpiar() {

        this.estadoFormulario = false;

        this.listaProducto.clear();
        this.producto = new TProducto();

    }

    public void enableShowDataBean() {
        limpiarAgronegocio();
        limpiar();
        correlativo();
        super.setShowData(true);
        super.setShowCreate(false);
        this.listaAgronegocio = this.agronegocioBo.listAgronegocio();

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
        this.listaProducto.add(this.producto);
        this.producto = new TProducto();
    }

    public void removerActivos(int posicion) {

        this.listaProducto.remove(posicion);
    }

    public void cambiarTipo1() {

        if (this.tipocontado == false) {
            this.tipocredito = true;
            this.tipocontado = false;
            this.PContado = false;
            this.PCredito = true;

        }
        if (this.tipocontado == true) {
            this.tipocredito = false;
            this.tipocontado = true;
            this.PContado = true;
            this.PCredito = false;

        }

    }

    public void cambiarTipo2() {

        if (this.tipocredito == false) {
            this.tipocredito = false;
            this.tipocontado = true;
            this.PContado = true;
            this.PCredito = false;

        }
        if (this.tipocredito == true) {
            this.tipocredito = true;
            this.tipocontado = false;
            this.PContado = false;
            this.PCredito = true;

        }

    }

    public void cambiarForma2() {

        if (this.tipocheque == false) {

            this.tipoefectivo = true;
            this.Habilitar = true;
            this.msgCheque = "";
        }

        if (this.tipocheque == true) {

            this.tipoefectivo = false;
            this.Habilitar = false;
        }
    }

    public void cambiarForma1() {

        if (this.tipoefectivo == false) {

            this.tipocheque = true;
            this.Habilitar = false;
        }

        if (this.tipoefectivo == true) {

            this.tipocheque = false;
            this.Habilitar = true;
            this.msgCheque = "";
        }
    }

    public void productos() {

        this.listaProducto.clear();

        this.listaProductoProductor = this.productoProductorBo.listTProductoProductor(this.productorIndividualSeleccionado.getIdProductorIndividual());

        for (int i = 0; i < this.listaProductoProductor.size(); i++) {

            this.listaProducto.add(this.productoBo.getTProducto(this.listaProductoProductor.get(i).getTProducto().getIdproducto()));

        }

    }

    public void pasar(Integer i) {

        if (this.productoAgronegocio.getUnidad() != "" && this.productoAgronegocio.getCantidad() != null && this.productoAgronegocio.getPrecio() != null) {

            this.productoAgronegocio.setNombre(this.listaProducto.get(i).getNombre());
            this.productoAgronegocio.setTProducto(this.listaProducto.get(i));

            this.listaProductoAgronegocio.add(this.productoAgronegocio);

            this.total = this.total.add(this.productoAgronegocio.getPrecio().multiply(new BigDecimal(this.productoAgronegocio.getCantidad())));

            this.comision = this.total.multiply(this.politicaAgronegocio.getTasaComisionAgronegocio().divide(new BigDecimal(100)));

            this.productoAgronegocio = new TProductoAgronegocio();

            this.mostrarTable = false;

        } else {

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debes introducir los datos de Unidad, cantidad y precio", null);
            FacesContext.getCurrentInstance().addMessage("", mensaje);

        }

    }

    public void quitar(int i) {

        this.total = this.total.subtract(this.listaProductoAgronegocio.get(i).getPrecio().multiply(new BigDecimal(this.listaProductoAgronegocio.get(i).getCantidad())));

        this.comision = this.total.multiply(this.politicaAgronegocio.getTasaComisionAgronegocio().divide(new BigDecimal(100)));

        this.listaProductoAgronegocio.remove(i);

        this.productoAgronegocio = new TProductoAgronegocio();

        this.mostrarTable = false;

    }

    public void mostrarCalculos() {

        if (this.agronegocio.getFecha() == null) {
            this.msgFecha = "La fecha es requeria";

        } else {

            this.msgFecha = "";

        }

        if (this.agronegocio.getPlazo() == null || this.agronegocio.getPlazo() < 1) {
            this.msgPlazo = "El plazo es requerido";

        } else {
            this.msgPlazo = "";

        }
        if (this.agronegocio.getFecha() != null && (this.agronegocio.getPlazo() != null)) {

            this.mostrarTable = true;

            this.monto = this.total;

            this.interes = (this.monto.multiply(this.politicaAgronegocio.getTasaInteresAgronegocio().divide(new BigDecimal(100)).multiply(new BigDecimal(this.agronegocio.getPlazo()).divide(new BigDecimal(12)))));

            this.totales = this.monto.add(this.interes);

            this.comisionCredito = this.totales.multiply(this.politicaAgronegocio.getTasaComisionAgronegocio().divide(new BigDecimal(100)));

            int dias = 30;
            Calendar calendar = Calendar.getInstance();

            for (int i = 0; i < this.agronegocio.getPlazo(); i++) {

                calendar.setTime(this.agronegocio.getFecha()); // Configuramos la fecha que se recibe

                calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0

                dias = dias + 30;

            }
            this.agronegocio.setFechapago(calendar.getTime());

        }
    }

    public void mostrarProductorIndividual() {

        this.listaProductoresIndividuales = this.iproductorindividual.listProductorIndividual();

    }

    public void cargarVistaAgronegocio() {
        
        this.totales= new BigDecimal("0");
        this.comision= new BigDecimal("0");
        
        this.pagoAgro.setComision(new BigDecimal("0"));
        this.pagoAgro.setInteres(new BigDecimal("0"));
         this.pagoAgro.setOtrosinteres(new BigDecimal("0"));
          this.pagoAgro.setDias(0);
        

        this.listaProductoAgronegocio = this.productoAgronegocioBo.listProductoAgronegocio(this.agronegocioSeleccionado.getIdAgronegocio());

        this.listaProductoProductorCod = this.productoProductorBo.listTProductoProductorCod(this.listaProductoAgronegocio.get(0).getTProducto().getIdproducto());

        this.productorIndividualSeleccionadoVista = this.iproductorindividual.getProdcutorIndividual(this.listaProductoProductorCod.get(0).getTProductorIndividual().getIdProductorIndividual());

        this.politicaAgronegocio = this.configuracionAgronegocioBo.getConfiguracion(this.agronegocioSeleccionado.getTConfiguracionAgronegocio().getIdConfiguracionAgronegocio());

        if ("Contado".equals(this.agronegocioSeleccionado.getTipo())) {

            this.pagoAgro = this.pagoAgronegocioBo.getPagoAgronegocio(this.agronegocioSeleccionado.getIdAgronegocio());

            this.monto = this.agronegocioSeleccionado.getTotal();

            this.interes = (this.monto.multiply(this.politicaAgronegocio.getTasaInteresAgronegocio().divide(new BigDecimal(100))).multiply(new BigDecimal(this.agronegocioSeleccionado.getPlazo()).divide(new BigDecimal(12))));

            this.totales = this.monto.add(this.interes);

        }

        if ("Credito".equals(this.agronegocioSeleccionado.getTipo())) {
            
            
            if("Pendiente".equals(this.agronegocioSeleccionado.getEstado())){
                
            this.monto = this.agronegocioSeleccionado.getTotal();

            this.interes = (this.monto.multiply(this.politicaAgronegocio.getTasaInteresAgronegocio().divide(new BigDecimal(100))).multiply(new BigDecimal(this.agronegocioSeleccionado.getPlazo()).divide(new BigDecimal(12))));

                          
                
            }else{
                
                if ("Finalizado".equals(this.agronegocioSeleccionado.getEstado())) {

                this.pagoAgro = this.pagoAgronegocioBo.getPagoAgronegocio(this.agronegocioSeleccionado.getIdAgronegocio());

                this.interes = (this.monto.multiply(this.politicaAgronegocio.getTasaInteresAgronegocio().divide(new BigDecimal(100))).multiply(new BigDecimal(this.agronegocioSeleccionado.getPlazo()).divide(new BigDecimal(12))));

                this.totales = this.monto.add(this.interes).add(this.pagoAgro.getOtrosinteres());

                this.comision = this.totales.multiply(this.politicaAgronegocio.getTasaComisionAgronegocio().divide(new BigDecimal(100)));

            }

                
                
            }

           
           }
            
            
        

    }

    public String cambiarAsistencia(Boolean asistencia) {

        if (asistencia == true) {

            return "Si";

        } else {
            return "No";

        }

    }

    public void verReporteAgronegocio() throws SQLException, JRException, IOException {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        parametros.put("id_agronegocio", this.agronegocioSeleccionado.getIdAgronegocio());

        parametros.put("nombreProductor", this.productorIndividualSeleccionadoVista.getNombresProdcutorIndividual() + " " + this.productorIndividualSeleccionadoVista.getApellidosProductorIndividual());
        parametros.put("duiProductor", this.productorIndividualSeleccionadoVista.getDuiProductorIndividual());
        parametros.put("nitProductor", this.productorIndividualSeleccionadoVista.getNitProductorIndividual());
        parametros.put("telefonoProductor", this.productorIndividualSeleccionadoVista.getTelefonoProductorIndividual());
        parametros.put("direccionProductor", this.productorIndividualSeleccionadoVista.getDireccionProductorIndividual());
        parametros.put("celularTelefono", this.productorIndividualSeleccionadoVista.getMovilProductorIndividual());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegocioIndividualPendiente.jasper"));

        if (this.agronegocioSeleccionado.getEstado().equals("Pendiente")) {

            System.out.println(this.agronegocioSeleccionado.getEstado());
            System.out.println(this.agronegocioSeleccionado.getIdAgronegocio());
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegocioIndividualPendiente.jasper"));

        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegocioIndividual.jasper"));

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
    
    
    public void verReporteAgronegocioPDF() throws SQLException, JRException, IOException{
        
        
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        parametros.put("id_agronegocio", this.agronegocioSeleccionado.getIdAgronegocio());

        parametros.put("nombreProductor", this.productorIndividualSeleccionadoVista.getNombresProdcutorIndividual() + " " + this.productorIndividualSeleccionadoVista.getApellidosProductorIndividual());
        parametros.put("duiProductor", this.productorIndividualSeleccionadoVista.getDuiProductorIndividual());
        parametros.put("nitProductor", this.productorIndividualSeleccionadoVista.getNitProductorIndividual());
        parametros.put("telefonoProductor", this.productorIndividualSeleccionadoVista.getTelefonoProductorIndividual());
        parametros.put("direccionProductor", this.productorIndividualSeleccionadoVista.getDireccionProductorIndividual());
        parametros.put("celularTelefono", this.productorIndividualSeleccionadoVista.getMovilProductorIndividual());
       
        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegocioIndividualPendiente.jasper"));

        if (this.agronegocioSeleccionado.getEstado().equals("Pendiente")) {

            System.out.println(this.agronegocioSeleccionado.getEstado());
            System.out.println(this.agronegocioSeleccionado.getIdAgronegocio());
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegocioIndividualPendiente.jasper"));

        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegocioIndividual.jasper"));

        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Agronegocio.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
        
    }
    
    
    public void limpiarAgroVista()
    {
        
        
        this.pagoAgro = new TPagoAgronegocio();
        this.comision= new BigDecimal("0");
        this.totales= new BigDecimal("0");
        
        
    }
    
    
    
    public void cargarNuevoAgronegocio()
    {
        
       
        this.listaProducto = new ArrayList<TProducto>();
        this.productorIndividualSeleccionado= new TProductorIndividual();
        this.compradorSeleccionado= new TComprador();
        this.listaProductoAgronegocio= new ArrayList<TProductoAgronegocio>();
        
        this.mostrarTable=false;
        this.agronegocio= new TAgronegocio();
        this.total= new BigDecimal("0");
                this.comision= new BigDecimal("0");
        //obteniniedo el numero de desembolso
        DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
        inFormat.setTimeZone(TimeZone.getTimeZone("America/Guatemala"));
        Date purchaseDate = new Date();

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        try {
            this.agronegocio.setFecha(formatoDelTexto.parse(inFormat.format(purchaseDate)));
        } catch (ParseException ex) {
            Logger.getLogger(AgronegocioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        correlativo();
        
        
        
    }

    
    
     public void limpiarAgronegocio()
    {
        
        
        
        this.listaProducto = new ArrayList<TProducto>();
        this.productorIndividualSeleccionado= new TProductorIndividual();
        this.compradorSeleccionado= new TComprador();
        this.listaProductoAgronegocio= new ArrayList<TProductoAgronegocio>();
        
        this.mostrarTable=false;
        this.agronegocio= new TAgronegocio();
        
        
        
        
    }

}
