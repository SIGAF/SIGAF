/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IActivoFijoBo;
import com.sigaf.Ibo.IBajaActivoFijoBo;
import com.sigaf.Ibo.ICuentaBo;
import com.sigaf.Ibo.IDepreciacionBo;
import com.sigaf.Ibo.IDetalleParidaBo;
import com.sigaf.Ibo.IEjercicioBo;
import com.sigaf.Ibo.IEmpleadoAreaBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IPartidaBo;
import com.sigaf.Ibo.IPeriodoBo;
import com.sigaf.Ibo.IValorActivoBo;
import com.sigaf.bo.AreaBo;
import com.sigaf.bo.ConfiguracionBo;
import com.sigaf.bo.ProveedorBo;
import com.sigaf.bo.TipoActivoBo;
import com.sigaf.pojo.TActivoFijo;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TBajaActivoFijo;
import com.sigaf.pojo.TConfiguracion;
import com.sigaf.pojo.TCuenta;
import com.sigaf.pojo.TDepreciacion;
import com.sigaf.pojo.TDetallePartida;
import com.sigaf.pojo.TEjercicio;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEmpleadoArea;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TPartida;
import com.sigaf.pojo.TPeriodo;
import com.sigaf.pojo.TProveedor;
import com.sigaf.pojo.TTipoActivo;
import com.sigaf.pojo.TValorActivo;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Eliseo
 */
@ManagedBean
@RequestScoped
public class ActivoFijoBean extends Actividad {
    
    private Boolean estadoPredeterminado;

    private IBajaActivoFijoBo bajaActivoFijoBo;

    private IDepreciacionBo depreciacionBo;

    private List<TDepreciacion> listaDepreciaciones;

    private BigDecimal valorLibro;

    private BigDecimal valorVenta;

    private BigDecimal valorDepre;

    private BigDecimal valorActivoBaja;

    private List<TEntidad> listaEntidades;

    private IEntidadBo entidadBo;

    private Integer idEntidad;

    private IValorActivoBo valorActivoBo;

    private TValorActivo valorActivo;

    private IPartidaBo partidaBo;

    private IDetalleParidaBo detallePartidaBo;

    private IPeriodoBo periodoBo;

    private IEjercicioBo ejercicioBo;

    private ICuentaBo cuentaBo;

    private IEmpleadoAreaBo empleadoAreaBo;

    private IActivoFijoBo activoFijoBo;

    private ProveedorBo proveedorBo;

    private TipoActivoBo tipoActivoBo;

    private ConfiguracionBo configuracionBo;

    private TPartida partida;

    private TDetallePartida detallePartida;

    private List<TDetallePartida> listaDetallePartida;

    private List<TCuenta> listaCuentas;

    private TPeriodo perido;

    private TEjercicio ejercicio;

    private TCuenta cuenta;

    private BigDecimal totalDebe;

    private BigDecimal totalHaber;

    private Boolean estadoFormulario;

    private String msgConcepto;

    private String msgNo;

    private String msgCantidad;

    private String msgConceptoDetalle;

    private String msgDetallePar;

    private String msgCuadre;

    private Integer numPartida;

    private String codigigoAxu;

    /**
     * Activo datos
     */
    private TEntidad entidad;

    private TConfiguracion configuracion;

    private AreaBo areaBo;

    private List<TArea> listaArea;

    private List<TTipoActivo> ListaTipos;

    private List<TProveedor> listaProveedor;

    private List<TEmpleadoArea> listaEmpleadoArea;

    private TActivoFijo activoFijo;

    private TActivoFijo activoFijoSeleccionado;

    private Integer idArea;

    private Integer idTipoActivo;

    private TArea area;

    private TTipoActivo tipoActivo;

    private String codTip;

    private Boolean estadoValido;

    private String msgArea;

    private String msgEmpleado;

    private String msgTipo;

    private String msgDesc;

    private String msgValor;

    private String msgProv;

    private String msgCod;

    private String msgTipoBaja;

    private Boolean showBaja;

    private Boolean showRegistro;

    private Boolean showRegistroModificar;

    private Integer idTab;

    private String tipoBaja;

    private String msgValorActivoBaja;
    
    
    
    public void cambiarPredeterminado() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        loginBean.setIdEntidad(this.idEntidad);
        loginBean.setPredeterminado(estadoPredeterminado);
    
    }
    
    
    public Boolean getEstadoPredeterminado() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        estadoPredeterminado = loginBean.getPredeterminado();
        if(estadoPredeterminado){
        setIdEntidad(loginBean.getIdEntidad());
        }

        return estadoPredeterminado;

    }
    
     

    public void setEstadoPredeterminado(Boolean estadoPredeterminado) {
        this.estadoPredeterminado = estadoPredeterminado;
    }

    public Integer getIdEntidad() {
             
               return idEntidad;  

    }

    public IBajaActivoFijoBo getBajaActivoFijoBo() {
        return bajaActivoFijoBo;
    }

    public void setBajaActivoFijoBo(IBajaActivoFijoBo bajaActivoFijoBo) {
        this.bajaActivoFijoBo = bajaActivoFijoBo;
    }

    public String getMsgValorActivoBaja() {
        return msgValorActivoBaja;
    }

    public void setMsgValorActivoBaja(String msgValorActivoBaja) {
        this.msgValorActivoBaja = msgValorActivoBaja;
    }

    public String getMsgTipoBaja() {
        return msgTipoBaja;
    }

    public void setMsgTipoBaja(String msgTipoBaja) {
        this.msgTipoBaja = msgTipoBaja;
    }

    public String getTipoBaja() {
        return tipoBaja;
    }

    public void setTipoBaja(String tipoBaja) {
        this.valorVenta = BigDecimal.ZERO;
        this.tipoBaja = tipoBaja;
    }

    public Integer getIdTab() {
        return idTab;
    }

    public void setIdTab(Integer idTab) {
        this.idTab = idTab;
    }

    public BigDecimal getValorActivoBaja() {
        return valorActivoBaja;
    }

    public void setValorActivoBaja(BigDecimal valorActivoBaja) {
        this.valorActivoBaja = valorActivoBaja;
    }

    public BigDecimal getValorDepre() {
        return valorDepre;
    }

    public void setValorDepre(BigDecimal valorDepre) {
        this.valorDepre = valorDepre;
    }

    public BigDecimal getValorLibro() {
        return valorLibro;
    }

    public void setValorLibro(BigDecimal valorLibro) {
        this.valorLibro = valorLibro;
    }

    public BigDecimal getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(BigDecimal valorVenta) {
        this.valorVenta = valorVenta;
    }

    public List<TDepreciacion> getListaDepreciaciones() {
        return listaDepreciaciones;
    }

    public void setListaDepreciaciones(List<TDepreciacion> listaDepreciaciones) {
        this.listaDepreciaciones = listaDepreciaciones;
    }

    public IDepreciacionBo getDepreciacionBo() {
        return depreciacionBo;
    }

    public void setDepreciacionBo(IDepreciacionBo depreciacionBo) {
        this.depreciacionBo = depreciacionBo;
    }

    public Boolean getShowRegistroModificar() {
        return showRegistroModificar;
    }

    public void setShowRegistroModificar(Boolean showRegistroModificar) {
        this.showRegistroModificar = showRegistroModificar;
    }

    public void enableShowCreateBean() {

        super.enableShowCreate();
        this.limpiar();
        this.limpiarRegistro();
    }

    public void enableShowDataBean() {
        this.showBaja = false;
        this.showRegistro = false;
        this.showRegistroModificar = false;
        super.enableShowData();
        this.limpiar();
        this.limpiarRegistro();
    }

    public void enableShowRegistro() {
        this.showRegistroModificar = false;
        this.showRegistro = true;
        this.setShowCreate(false);
        this.setShowData(false);
        this.setShowUpdate(false);
        this.setShowView(false);
        this.showBaja = false;
    }

    public void enableShowRegistroModificar() {
        this.showRegistroModificar = true;
        this.showRegistro = false;
        this.setShowCreate(false);
        this.setShowData(false);
        this.setShowUpdate(false);
        this.setShowView(false);
        this.showBaja = false;
    }

    public void enableShowBaja() {
        this.showRegistroModificar = false;
        this.showRegistro = false;
        this.setShowCreate(false);
        this.setShowData(false);
        this.setShowUpdate(false);
        this.setShowView(false);
        this.showBaja = true;
    }

    public Boolean getShowRegistro() {
        return showRegistro;
    }

    public void setShowRegistro(Boolean showRegistro) {
        this.showRegistro = showRegistro;
    }

    public List<TEntidad> getListaEntidades() {
        listaEntidades = this.entidadBo.listTEndidadTodos();
        return listaEntidades;
    }

    public void setListaEntidades(List<TEntidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }



    public void setIdEntidad(Integer idEntidad) {

        listaArea = this.areaBo.listArea(idEntidad);
        ListaTipos = this.tipoActivoBo.listTipoActivo(idEntidad);
        listaProveedor = this.proveedorBo.listProveedor(idEntidad);

        this.idEntidad = idEntidad;
    }

    public String getCodigigoAxu() {
        return codigigoAxu;
    }

    public void setCodigigoAxu(String codigigoAxu) {
        this.codigigoAxu = codigigoAxu;
    }

    public Boolean getShowBaja() {
        return showBaja;
    }

    public void setShowBaja(Boolean showBaja) {
        this.showBaja = showBaja;
    }

    public IEmpleadoAreaBo getEmpleadoAreaBo() {
        return empleadoAreaBo;
    }

    public void setEmpleadoAreaBo(IEmpleadoAreaBo empleadoAreaBo) {
        this.empleadoAreaBo = empleadoAreaBo;
    }

    public TActivoFijo getActivoFijoSeleccionado() {
        return activoFijoSeleccionado;
    }

    public Boolean estadoRegistro(Integer id) {

        if (null != this.valorActivoBo.getTValorActivo(id).getTPartida()) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean estadoRegistroModificar() {

        if (null != this.valorActivoBo.getTValorActivo(activoFijoSeleccionado.getIdActivoFijo()).getTPartida()) {
            return true;
        } else {
            return false;
        }
    }

    public void setActivoFijoSeleccionado(TActivoFijo activoFijoSeleccionado) {

        this.activoFijoSeleccionado = activoFijoSeleccionado;

        this.listaArea = this.areaBo.listArea(idEntidad);

        this.setIdArea(this.empleadoAreaBo.getTEmpleadoArea(null, activoFijoSeleccionado.getTEmpleado().getIdEmpleado()).getTArea().getIdArea());

        this.setIdTipoActivo(this.activoFijoSeleccionado.getTTipoActivo().getIdTipo());

        this.valorActivo = this.valorActivoBo.getTValorActivo(activoFijoSeleccionado.getIdActivoFijo());

        if (!this.showBaja) {

            if (null != valorActivo.getTPartida()) {

                this.partida = this.partidaBo.getPartida(valorActivo.getTPartida().getIdPartida());

                this.totalDebe = BigDecimal.ZERO;
                this.totalHaber = BigDecimal.ZERO;
                this.listaDetallePartida = detallePartidaBo.listDetallePartida(partida.getIdPartida());

                for (TDetallePartida tDetallePartida : listaDetallePartida) {

                    if (tDetallePartida.getTipoSaldoDetallePartida().equals("Debe")) {
                        this.totalDebe = this.totalDebe.add(tDetallePartida.getSaldoDetallePartida());
                    } else {
                        this.totalHaber = this.totalHaber.add(tDetallePartida.getSaldoDetallePartida());
                    }
                }

            } else {

                this.partida.setConceptoPartida("Registro del activo fijo " + this.cogigoGenerarBaja());

                this.prepararRegistro();

            }

        } else {

            this.listaDepreciaciones = this.depreciacionBo.listDepreciacion(activoFijoSeleccionado.getIdActivoFijo());

            if (null != listaDepreciaciones) {
                this.valorLibro = BigDecimal.ZERO;
                this.valorDepre = BigDecimal.ZERO;
                valorActivoBaja = valorActivo.getValorActivo();
                for (TDepreciacion listaDepreciacione : listaDepreciaciones) {
                    valorDepre = valorDepre.add(listaDepreciacione.getValorDepreciacion());
                }
                valorLibro = valorActivoBaja.subtract(valorDepre);

            }

            this.partida.setConceptoPartida("Por baja del activo " + cogigoGenerarBaja() + " " + this.activoFijoSeleccionado.getDescripcionActivoFijo());

            //this.prepararRegistroBAja();
        }

    }

    public void prepararRegistro() {

        listaDetallePartida.clear();

        TCuenta cue = this.cuentaBo.getCuenta(tipoActivo.getTCuentaByIdCuentaActivoTipo().getIdCuenta());
        TDetallePartida det = new TDetallePartida();

        det.setTCuenta(cue);
        det.setTipoSaldoDetallePartida("Debe");

        det.setSaldoDetallePartida(this.valorActivo.getValorActivo());
        this.totalDebe = totalDebe.add(this.valorActivo.getValorActivo());

        this.listaDetallePartida.add(0, det);

    }

    public void prepararRegistroBAja() {

        listaDetallePartida.clear();
        this.totalDebe = BigDecimal.ZERO;
        this.totalHaber = BigDecimal.ZERO;

        TCuenta cueDep = this.cuentaBo.getCuenta(tipoActivo.getTCuentaByIdCuentaDepreciacionTipo().getIdCuenta());
        TDetallePartida detDep = new TDetallePartida();

        detDep.setTCuenta(cueDep);
        detDep.setTipoSaldoDetallePartida("Debe");

        detDep.setSaldoDetallePartida(this.valorDepre);
        this.totalDebe = totalDebe.add(this.valorDepre);

        this.listaDetallePartida.add(detDep);

        TCuenta cueAc = this.cuentaBo.getCuenta(tipoActivo.getTCuentaByIdCuentaActivoTipo().getIdCuenta());
        TDetallePartida detAc = new TDetallePartida();

        detAc.setTCuenta(cueAc);
        detAc.setTipoSaldoDetallePartida("Haber");

        detAc.setSaldoDetallePartida(this.valorActivoBaja);
        this.totalHaber = totalHaber.add(this.valorActivoBaja);

        this.listaDetallePartida.add(detAc);

    }

    public TValorActivo getValorActivo() {
        return valorActivo;
    }

    public void setValorActivo(TValorActivo valorActivo) {
        this.valorActivo = valorActivo;
    }

    public IValorActivoBo getValorActivoBo() {
        return valorActivoBo;
    }

    public void setValorActivoBo(IValorActivoBo valorActivoBo) {
        this.valorActivoBo = valorActivoBo;
    }

    public TEjercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(TEjercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public void editListaDetallePartida(Integer i) {

        TDetallePartida detParAux = this.listaDetallePartida.get(i);

        if (detParAux.getTipoSaldoDetallePartida().equals("Debe")) {
            this.totalDebe = this.totalDebe.subtract(detParAux.getSaldoDetallePartida());
        } else {
            this.totalHaber = this.totalHaber.subtract(detParAux.getSaldoDetallePartida());
        }

        this.cuenta = detParAux.getTCuenta();

        this.detallePartida = detParAux;

        this.listaDetallePartida.remove(detParAux);

    }

    public void addListaDetallePartida() {

        Boolean estado = true;

        if (detallePartida.getSaldoDetallePartida().equals(BigDecimal.ZERO)) {
            estado = false;
            this.msgCantidad = "Cantidad  invalida";
        } else {
            this.msgCantidad = "";
        }

        if (cuenta.getCodigoCuenta().length() == 0) {
            estado = false;
            this.msgNo = "Código  requerido";
        } else {
            this.msgNo = "";
        }

        if (estado) {
            this.detallePartida.setTCuenta(cuenta);
            this.detallePartida.setSaldoDetallePartida(detallePartida.getSaldoDetallePartida());
            this.listaDetallePartida.add(detallePartida);

            if (detallePartida.getTipoSaldoDetallePartida().equals("Debe")) {
                this.totalDebe = this.totalDebe.add(detallePartida.getSaldoDetallePartida());
            } else {
                this.totalHaber = this.totalHaber.add(detallePartida.getSaldoDetallePartida());
            }

            detallePartida = new TDetallePartida();
            detallePartida.setSaldoDetallePartida(BigDecimal.ZERO);
            detallePartida.setTipoSaldoDetallePartida("Debe");
            cuenta = new TCuenta();
            cuenta.setCodigoCuenta("");

        }

    }

    public void removeDetalle(Integer i) {

        TDetallePartida detParAux = this.listaDetallePartida.get(i);

        if (detParAux.getTipoSaldoDetallePartida().equals("Debe")) {
            this.totalDebe = this.totalDebe.subtract(detParAux.getSaldoDetallePartida());
        } else {
            this.totalHaber = this.totalHaber.subtract(detParAux.getSaldoDetallePartida());
        }

        this.listaDetallePartida.remove(detParAux);

    }

    public List<TCuenta> getListaCuentas() {
        this.listaCuentas = this.cuentaBo.listCuentaEntActTip(idEntidad, true);
        return listaCuentas;
    }

    public void setListaCuentas(List<TCuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public IPartidaBo getPartidaBo() {
        return partidaBo;
    }

    public void setPartidaBo(IPartidaBo partidaBo) {
        this.partidaBo = partidaBo;
    }

    public IDetalleParidaBo getDetallePartidaBo() {
        return detallePartidaBo;
    }

    public void setDetallePartidaBo(IDetalleParidaBo detallePartidaBo) {
        this.detallePartidaBo = detallePartidaBo;
    }

    public IPeriodoBo getPeriodoBo() {
        return periodoBo;
    }

    public void setPeriodoBo(IPeriodoBo periodoBo) {
        this.periodoBo = periodoBo;
    }

    public IEjercicioBo getEjercicioBo() {
        return ejercicioBo;
    }

    public void setEjercicioBo(IEjercicioBo ejercicioBo) {
        this.ejercicioBo = ejercicioBo;
    }

    public ICuentaBo getCuentaBo() {
        return cuentaBo;
    }

    public void setCuentaBo(ICuentaBo cuentaBo) {
        this.cuentaBo = cuentaBo;
    }

    public List<TDetallePartida> getListaDetallePartida() {
        return listaDetallePartida;
    }

    public void setListaDetallePartida(List<TDetallePartida> listaDetallePartida) {
        this.listaDetallePartida = listaDetallePartida;
    }

    public TPeriodo getPerido() {
        return perido;
    }

    public void setPerido(TPeriodo perido) {
        this.perido = perido;
    }

    public TPartida getPartida() {
        return partida;
    }

    public void setPartida(TPartida partida) {
        this.partida = partida;
    }

    public TDetallePartida getDetallePartida() {
        return detallePartida;
    }

    public void setDetallePartida(TDetallePartida detallePartida) {
        this.detallePartida = detallePartida;
    }

    public TCuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(TCuenta cuenta) {
        this.cuenta = cuenta;
    }

    public BigDecimal getTotalDebe() {
        return totalDebe;
    }

    public void setTotalDebe(BigDecimal totalDebe) {
        this.totalDebe = totalDebe;
    }

    public BigDecimal getTotalHaber() {
        return totalHaber;
    }

    public void setTotalHaber(BigDecimal totalHaber) {
        this.totalHaber = totalHaber;
    }

    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getMsgConcepto() {
        return msgConcepto;
    }

    public void setMsgConcepto(String msgConcepto) {
        this.msgConcepto = msgConcepto;
    }

    public String getMsgNo() {
        return msgNo;
    }

    public void setMsgNo(String msgNo) {
        this.msgNo = msgNo;
    }

    public String getMsgCantidad() {
        return msgCantidad;
    }

    public void setMsgCantidad(String msgCantidad) {
        this.msgCantidad = msgCantidad;
    }

    public String getMsgConceptoDetalle() {
        return msgConceptoDetalle;
    }

    public void setMsgConceptoDetalle(String msgConceptoDetalle) {
        this.msgConceptoDetalle = msgConceptoDetalle;
    }

    public String getMsgDetallePar() {
        return msgDetallePar;
    }

    public void setMsgDetallePar(String msgDetallePar) {
        this.msgDetallePar = msgDetallePar;
    }

    public String getMsgCuadre() {
        return msgCuadre;
    }

    public void setMsgCuadre(String msgCuadre) {
        this.msgCuadre = msgCuadre;
    }

    public Integer getNumPartida() {
        this.ejercicio = this.ejercicioBo.getEjercicioAbierto(idEntidad);
        numPartida = this.partidaBo.numeroPartida(ejercicio.getIdEjercicio());
        return numPartida;

    }

    public void setNumPartida(Integer numPartida) {
        this.numPartida = numPartida;
    }

    public List<TEmpleadoArea> getListaEmpleadoArea() {
        return listaEmpleadoArea;
    }

    public void setListaEmpleadoArea(List<TEmpleadoArea> listaEmpleadoArea) {
        this.listaEmpleadoArea = listaEmpleadoArea;
    }

    public String getMsgCod() {
        return msgCod;
    }

    public void setMsgCod(String msgCod) {
        this.msgCod = msgCod;
    }

    public String getMsgArea() {
        return msgArea;
    }

    public void setMsgArea(String msgArea) {
        this.msgArea = msgArea;
    }

    public String getMsgEmpleado() {
        return msgEmpleado;
    }

    public void setMsgEmpleado(String msgEmpleado) {
        this.msgEmpleado = msgEmpleado;
    }

    public String getMsgTipo() {
        return msgTipo;
    }

    public void setMsgTipo(String msgTipo) {
        this.msgTipo = msgTipo;
    }

    public String getMsgDesc() {
        return msgDesc;
    }

    public void setMsgDesc(String msgDesc) {
        this.msgDesc = msgDesc;
    }

    public String getMsgValor() {
        return msgValor;
    }

    public void setMsgValor(String msgValor) {
        this.msgValor = msgValor;
    }

    public String getMsgProv() {
        return msgProv;
    }

    public void setMsgProv(String msgProv) {
        this.msgProv = msgProv;
    }

    public Boolean getEstadoValido() {
        return estadoValido;
    }

    public void setEstadoValido(Boolean estadoValido) {
        this.estadoValido = estadoValido;
    }

    public Integer getIdTipoActivo() {
        return idTipoActivo;
    }

    public void setIdTipoActivo(Integer idTipoActivo) {
        Boolean estado = true;

        for (TTipoActivo tTipo : ListaTipos) {

            if (tTipo.getIdTipo() == idTipoActivo) {
                this.tipoActivo = tTipo;

                estado = false;
            }

        }

        if (estado) {
            this.tipoActivo.setCodigoTipo("");
        }

        this.idTipoActivo = idTipoActivo;
    }

    public TArea getArea() {

        return area;
    }

    public void setArea(TArea area) {

        this.area = area;
    }

    public TTipoActivo getTipoActivo() {
        return tipoActivo;
    }

    public void setTipoActivo(TTipoActivo tipoActivo) {
        this.tipoActivo = tipoActivo;
    }

    public String getCodTip() {
        return codTip;
    }

    public void setCodTip(String codTip) {
        this.codTip = codTip;
    }

    public TEntidad getEntidad() {
        entidad = this.entidadBo.getTEntidad(1);
        return entidad;
    }

    public void setEntidad(TEntidad entidad) {
        this.entidad = entidad;
    }

    public IEntidadBo getEntidadBo() {
        return entidadBo;
    }

    public void setEntidadBo(IEntidadBo entidadBo) {
        this.entidadBo = entidadBo;
    }

    public TConfiguracion getConfiguracion() {
        configuracion = this.configuracionBo.getConfiguracion(idEntidad);
        return configuracion;
    }

    public void setConfiguracion(TConfiguracion configuracion) {
        this.configuracion = configuracion;
    }

    public ConfiguracionBo getConfiguracionBo() {
        return configuracionBo;
    }

    public void setConfiguracionBo(ConfiguracionBo configuracionBo) {
        this.configuracionBo = configuracionBo;
    }

    public List<TProveedor> getListaProveedor() {
        listaProveedor = this.proveedorBo.listProveedor(idEntidad);
        return listaProveedor;
    }

    public void setListaProveedor(List<TProveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public ProveedorBo getProveedorBo() {
        return proveedorBo;
    }

    public void setProveedorBo(ProveedorBo proveedorBo) {
        this.proveedorBo = proveedorBo;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        Boolean estado = true;

        for (TArea tArea : listaArea) {

            if (tArea.getIdArea() == idArea) {
                this.area = tArea;
                estado = false;
                this.listaEmpleadoArea = new ArrayList<>(area.getTEmpleadoAreas());
            }

        }

        if (estado) {
            this.area.setCodigoArea("");
            this.listaEmpleadoArea = null;
        }

        this.idArea = idArea;
    }

    public TActivoFijo getActivoFijo() {
        return activoFijo;
    }

    public void setActivoFijo(TActivoFijo activoFijo) {
        this.activoFijo = activoFijo;
    }

    public List<TArea> getListaArea() {
        listaArea = this.areaBo.listArea(idEntidad);

        return listaArea;
    }

    public void setListaArea(List<TArea> listaArea) {
        this.listaArea = listaArea;
    }

    public List<TTipoActivo> getListaTipos() {
        ListaTipos = this.tipoActivoBo.listTipoActivo(idEntidad);
        return ListaTipos;
    }

    public void setListaTipos(List<TTipoActivo> ListaTipos) {
        this.ListaTipos = ListaTipos;
    }

    private List<TActivoFijo> listaActivoFijo;

    public List<TActivoFijo> getListaActivoFijo() {
        this.listaActivoFijo = this.activoFijoBo.listActivoFijo(idEntidad);
        return listaActivoFijo;
    }

    public void setListaActivoFijo(List<TActivoFijo> listaActivoFijo) {
        this.listaActivoFijo = listaActivoFijo;
    }

    public String cogigoGenerar(TActivoFijo activo) {

        String codigoArea = this.empleadoAreaBo.getTEmpleadoArea(null, activo.getTEmpleado().getIdEmpleado()).getTArea().getCodigoArea();

        return activo.getTTipoActivo().getTEntidad().getCodigoEntidad() + "-" + codigoArea + "-" + activo.getTTipoActivo().getCodigoTipo() + "-" + activo.getCodigoActivoFijo();

    }

    public IActivoFijoBo getActivoFijoBo() {
        return activoFijoBo;
    }

    public void setActivoFijoBo(IActivoFijoBo activoFijoBo) {
        this.activoFijoBo = activoFijoBo;
    }

    public TipoActivoBo getTipoActivoBo() {
        return tipoActivoBo;
    }

    public void setTipoActivoBo(TipoActivoBo tipoActivoBo) {
        this.tipoActivoBo = tipoActivoBo;
    }

    public AreaBo getAreaBo() {
        return areaBo;
    }

    public void setAreaBo(AreaBo areaBo) {
        this.areaBo = areaBo;
    }

    public void init() {

        this.listaDetallePartida = new ArrayList<>();
        this.limpiar();
        this.limpiarRegistro();
        this.idEntidad = 0;
        this.enableShowDataBean();
          this.estadoPredeterminado=false;

    }

    public void registro() {

        try {

            this.partida.setEstadoPartida(true);

            this.perido = this.periodoBo.getPeriodoAbierto(this.ejercicio.getIdEjercicio());

            this.partida.setNumeroPartida(numPartida);
            this.partida.setTPeriodo(perido);
            this.partidaBo.create(this.partida);

            for (TDetallePartida tDetallePartida : listaDetallePartida) {
                tDetallePartida.setTPartida(partida);

                this.detallePartidaBo.create(tDetallePartida);
            }

            valorActivo.setTPartida(partida);
            this.valorActivoBo.update(valorActivo);

            this.enableShowDataBean();

            super.enableShowData();
            this.estadoFormulario = false;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo resgistrado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo fijo no pudo ser resgistrado.", ""));
        }

    }

    public void registroActulizar() {

        try {
            this.partidaBo.update(this.partida);

            this.detallePartidaBo.delete(this.partida.getIdPartida());

            for (TDetallePartida tDetallePartida : listaDetallePartida) {
                tDetallePartida.setTPartida(this.partida);
                this.detallePartidaBo.create(tDetallePartida);

            }

            this.enableShowDataBean();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo modificado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El activo fijo no pudo ser modificado.", ""));
        }

    }

    public void crear() {

        try {

            /**
             * Activo fijo
             */
            this.activoFijo.setEstadoActivoFijo("Activo");
            this.activoFijo.setTTipoActivo(this.tipoActivo);
            this.activoFijoBo.create(this.activoFijo);

            /**
             * Partida activo valor
             */
            this.valorActivo.setTActivoFijo(this.activoFijo);

            this.valorActivoBo.create(valorActivo);

            this.limpiar();

             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo registrado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El  activo fijo no pudo ser registrado.", ""));

        }

    }

    public void actualizar() {

        try {

            /**
             * Activo fijo
             */
            this.activoFijoSeleccionado.setTTipoActivo(this.tipoActivo);
            this.activoFijoBo.update(this.activoFijoSeleccionado);

            System.out.println();

            /**
             * Partida activo valor
             */
            this.valorActivoBo.update(valorActivo);

            this.limpiar();

               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo modificado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El  activo fijo no pudo ser modificado.", ""));

        }

    }

    public void limpiar() {

        this.activoFijo = new TActivoFijo();

        this.activoFijo.setTProveedor(new TProveedor());
        this.activoFijo.setTEmpleado(new TEmpleado());
        this.valorActivo = new TValorActivo();
        this.valorActivo.setValorActivo(BigDecimal.ZERO);

        this.area = new TArea();
        this.tipoActivo = new TTipoActivo();
        this.idArea = 0;
        this.idTipoActivo = 0;

        this.activoFijo.setRegistroActivoFijo(new Date());
        this.activoFijo.setCompraActivoFijo(new Date());

        this.estadoValido = false;

    }

    public void limpiarRegistro() {

        partida = new TPartida();
        partida.setFechaPartida(new Date());

        detallePartida = new TDetallePartida();
        detallePartida.setSaldoDetallePartida(BigDecimal.ZERO);
        detallePartida.setTipoSaldoDetallePartida("Debe");

        cuenta = new TCuenta();
        cuenta.setCodigoCuenta("");

        this.listaDetallePartida.clear();
        this.totalDebe = BigDecimal.ZERO;
        this.totalHaber = BigDecimal.ZERO;

        this.estadoFormulario = false;

        this.idTab = 0;
        this.tipoBaja = "";

    }

    public Boolean validarFormularioActivoModificar() {

        this.estadoValido = true;

        if (this.idArea == 0) {
            msgArea = "El area es requerida";
            this.estadoValido = false;
        } else {
            msgArea = "";
        }

        if (this.activoFijoSeleccionado.getCodigoActivoFijo().length() == 0) {
            msgCod = "El código  del activo es requerido";
            this.estadoValido = false;
        } else {
            msgCod = "";
        }

        if (this.activoFijoSeleccionado.getTEmpleado().getIdEmpleado() == 0) {
            msgEmpleado = "El empleado es requerido";
            this.estadoValido = false;
        } else {
            msgEmpleado = "";
        }

        if (this.idTipoActivo == 0) {
            msgTipo = "El tipo es requerido";
            this.estadoValido = false;
        } else {
            msgTipo = "";
        }

        if (this.activoFijoSeleccionado.getDescripcionActivoFijo().length() <= 3) {
            msgDesc = "La descripción debe contener como minimo 3 caracteres";
            this.estadoValido = false;
        } else {
            msgDesc = "";
        }

        if (this.valorActivo.equals(BigDecimal.ZERO)) {
            msgValor = "El valor del activo  es requerido";
            this.estadoValido = false;
        } else {
            msgValor = "";
        }

        if (this.activoFijoSeleccionado.getTProveedor().getIdProveedor() == 0) {
            msgProv = "El proveedor es requerido";
            this.estadoValido = false;
        } else {
            msgProv = "";
        }

        return this.estadoValido;

    }

    public Boolean validarFormularioActivo() {

        this.estadoValido = true;

        if (this.idArea == 0) {
            msgArea = "El area es requerida";
            this.estadoValido = false;
        } else {
            msgArea = "";
        }

        if (this.activoFijo.getCodigoActivoFijo().length() == 0) {
            msgCod = "El código  del activo es requerido";
            this.estadoValido = false;
        } else {
            msgCod = "";
        }

        if (this.activoFijo.getTEmpleado().getIdEmpleado() == 0) {
            msgEmpleado = "El empleado es requerido";
            this.estadoValido = false;
        } else {
            msgEmpleado = "";
        }

        if (this.idTipoActivo == 0) {
            msgTipo = "El tipo es requerido";
            this.estadoValido = false;
        } else {
            msgTipo = "";
        }

        if (this.activoFijo.getDescripcionActivoFijo().length() <= 3) {
            msgDesc = "La descripción debe contener como minimo 3 caracteres";
            this.estadoValido = false;
        } else {
            msgDesc = "";
        }

        if (this.valorActivo.equals(BigDecimal.ZERO)) {
            msgValor = "El valor del activo  es requerido";
            this.estadoValido = false;
        } else {
            msgValor = "";
        }

        if (this.activoFijo.getTProveedor().getIdProveedor() == 0) {
            msgProv = "El proveedor es requerido";
            this.estadoValido = false;
        } else {
            msgProv = "";
        }

        return this.estadoValido;

    }

    public void validarFormulario() {

        this.estadoFormulario = true;

        if (partida.getConceptoPartida().length() < 5) {
            this.msgConcepto = "El concepto debe contener como minimo 5 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgConcepto = "";
        }

        if (this.listaDetallePartida.size() == 0) {
            this.estadoFormulario = false;
            this.msgDetallePar = "No hay transacciones";
        } else {
            this.msgDetallePar = "";
        }

        if (!this.totalDebe.equals(this.totalHaber)) {
            this.estadoFormulario = false;
            this.msgCuadre = "La transacción no cuadra ";
        } else {
            this.msgCuadre = "";

        }

    }

    public void validarFormularioModificar() {

        this.estadoFormulario = true;

        if (partida.getConceptoPartida().length() < 5) {
            this.msgConcepto = "El concepto debe contener como minimo 5 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgConcepto = "";
        }

        if (this.listaDetallePartida.size() == 0) {
            this.estadoFormulario = false;
            this.msgDetallePar = "No hay transacciones";
        } else {
            this.msgDetallePar = "";
        }

        if (!this.totalDebe.equals(this.totalHaber)) {
            this.estadoFormulario = false;
            this.msgCuadre = "La transacción no cuadra ";
        } else {
            this.msgCuadre = "";

        }

    }

    public String cogigoGenerarBaja() {

        return activoFijoSeleccionado.getTTipoActivo().getTEntidad().getCodigoEntidad() + "-" + area.getCodigoArea() + "-" + activoFijoSeleccionado.getTTipoActivo().getCodigoTipo() + "-" + activoFijoSeleccionado.getCodigoActivoFijo();
    }

    public void darDeAlta() {
        try {

            this.activoFijoSeleccionado.setEstadoActivoFijo("Activo");
            this.activoFijoBo.update(activoFijoSeleccionado);

               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo dado de alta correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El  activo fijo no pudo ser dado de alta.", ""));

        }

    }

    public void darDeBaja() {

        try {
            this.activoFijoSeleccionado.setEstadoActivoFijo("Inactivo");
            this.activoFijoBo.update(activoFijoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo dado de baja correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El  activo fijo no pudo ser dado de baja.", ""));

        }
    }

    public final void siguienteBaja() {

        if (this.tipoBaja.equals("")) {

            this.msgTipoBaja = "El motivo de es requerido";

        } else {

            this.msgTipoBaja = "";

            if (this.tipoBaja.equals("Venta") && this.valorVenta.equals(BigDecimal.ZERO)) {
                this.msgValorActivoBaja = "El valor de venta invalido";
            } else {

                this.msgValorActivoBaja = "";

                if (this.tipoBaja.equals("Venta") && !this.valorVenta.equals(BigDecimal.ZERO)) {

                    this.prepararRegistroBAja();

                    TCuenta cueDep = this.cuentaBo.getCuenta(tipoActivo.getTCuentaByIdCuentaVentaTipo().getIdCuenta());

                    TDetallePartida detVenta = new TDetallePartida();

                    detVenta.setTCuenta(cueDep);

                    detVenta.setTipoSaldoDetallePartida("Debe");

                    detVenta.setSaldoDetallePartida(this.valorVenta);

                    this.totalDebe = totalDebe.add(this.valorVenta);

                    this.listaDetallePartida.add(detVenta);
                } else {

                    this.prepararRegistroBAja();

                }

                this.idTab = 1;
            }
        }

    }

    public void darDeBajaContable() {
        try {

            this.activoFijoSeleccionado.setEstadoActivoFijo("Inactivo");

            this.activoFijoBo.update(activoFijoSeleccionado);

            this.partida.setEstadoPartida(true);

            this.perido = this.periodoBo.getPeriodoAbierto(this.ejercicio.getIdEjercicio());

            this.partida.setNumeroPartida(numPartida);

            this.partida.setTPeriodo(perido);

            this.partidaBo.create(this.partida);

            for (TDetallePartida tDetallePartida : listaDetallePartida) {
                tDetallePartida.setTPartida(partida);

                this.detallePartidaBo.create(tDetallePartida);
            }

            TBajaActivoFijo bajaActivo = new TBajaActivoFijo();

            bajaActivo.setTActivoFijo(activoFijoSeleccionado);

            bajaActivo.setTPartida(partida);

            bajaActivo.setTipoBajaActivoFijo(this.tipoBaja);

            bajaActivo.setValorBajaActivoFijo(valorActivoBaja);

            this.bajaActivoFijoBo.create(bajaActivo);

            this.valorActivoBo.update(valorActivo);

            this.enableShowDataBean();

                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo fijo dado de baja correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El  activo fijo no pudo ser dado de baja.", ""));

        }

    }

    public Connection conec() throws SQLException {
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");
        return conn;
    }

    public void generarReporteActivo() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idActivoFijo", this.activoFijoSeleccionado.getIdActivoFijo());
        estadoUsuario.put("idEntidad",this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/reporteActivoFijoIndividual.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), estadoUsuario, this.conec());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generarReporteActivoPDF() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
             estadoUsuario.put("idActivoFijo", this.activoFijoSeleccionado.getIdActivoFijo());
        estadoUsuario.put("idEntidad",this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/reporteActivoFijoIndividual.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.conec());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Activo-"+activoFijoSeleccionado.getDescripcionActivoFijo()+".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
    
     public void generarReporteActivoDepre() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idActivoFijo", this.activoFijoSeleccionado.getIdActivoFijo());
        estadoUsuario.put("idEntidad",this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/reporteActivoFijoDepreAmort.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), estadoUsuario, this.conec());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void generarReporteActivoDeprePDF() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
             estadoUsuario.put("idActivoFijo", this.activoFijoSeleccionado.getIdActivoFijo());
        estadoUsuario.put("idEntidad",this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/reporteActivoFijoDepreAmort.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.conec());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Activo-Dpre-Amortiz"+activoFijoSeleccionado.getDescripcionActivoFijo()+".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
}
