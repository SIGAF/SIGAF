/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IConfiguracionBo;
import com.sigaf.Ibo.ICuentaBo;
import com.sigaf.Ibo.IEntidadBo;

import com.sigaf.Ibo.ITipoActivoBo;
import com.sigaf.pojo.TConfiguracion;
import com.sigaf.pojo.TCuenta;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TTipoActivo;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
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
@Named(value = "tipoActivoBean")
@RequestScoped
@ManagedBean
public class TipoActivoBean extends Actividad {

    private Boolean estadoPredeterminado;
    
    private List<TEntidad> listaEntidades;

    private IEntidadBo entidadBo;

    private Integer idEntidad;

    private IConfiguracionBo configuracionBo;

    private TConfiguracion configuracion;

    private List<TCuenta> listaCuentas;

    private ICuentaBo cuentaBo;

    private ITipoActivoBo tipoActivoBo;

    private TTipoActivo tipoActivo;

    private TTipoActivo tipoActivoSeleccionado;

    private List< TTipoActivo> listaTipoActivo;

    private TCuenta cuentaActivo;

    private TCuenta cuentaGasto;

    private TCuenta cuentaVenta;

    private TCuenta cuentaDepre;

    private Boolean estadoValido;

    private String msgCodigo;

    private String msgNombre;

    private String msgDescripcion;

    private String msgVidaUtil;

    private String msgGastos;

    private String msgVenta;

    private String msgActivo;

    private String msgDepre;

    private String msgTipo;
    
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

        if (estadoPredeterminado) {
            setIdEntidad(loginBean.getIdEntidad());
        }

        return estadoPredeterminado;

    }

    public void setEstadoPredeterminado(Boolean estadoPredeterminado) {
        this.estadoPredeterminado = estadoPredeterminado;
    }

    public String getMsgTipo() {
        return msgTipo;
    }

    public void setMsgTipo(String msgTipo) {
        this.msgTipo = msgTipo;
    }

    public IEntidadBo getEntidadBo() {
        return entidadBo;
    }

    public void setEntidadBo(IEntidadBo entidadBo) {
        this.entidadBo = entidadBo;
    }

    public List<TEntidad> getListaEntidades() {
        listaEntidades = this.entidadBo.listTEndidadTodos();
        return listaEntidades;
    }

    public void setListaEntidades(List<TEntidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public void init() {

        super.enableShowData();
        this.idEntidad = 0;
        this.limpiar();
    }

    public IConfiguracionBo getConfiguracionBo() {
        return configuracionBo;
    }

    public void setConfiguracionBo(IConfiguracionBo configuracionBo) {
        this.configuracionBo = configuracionBo;
    }

    public TConfiguracion getConfiguracion() {
        this.configuracion = this.configuracionBo.getConfiguracion(idEntidad);
        return configuracion;
    }

    public void setConfiguracion(TConfiguracion configuracion) {
        this.configuracion = configuracion;
    }

    public String getMsgCodigo() {
        return msgCodigo;
    }

    public void setMsgCodigo(String msgCodigo) {
        this.msgCodigo = msgCodigo;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgDescripcion() {
        return msgDescripcion;
    }

    public void setMsgDescripcion(String msgDescripcion) {
        this.msgDescripcion = msgDescripcion;
    }

    public String getMsgVidaUtil() {
        return msgVidaUtil;
    }

    public void setMsgVidaUtil(String msgVidaUtil) {
        this.msgVidaUtil = msgVidaUtil;
    }

    public String getMsgGastos() {
        return msgGastos;
    }

    public void setMsgGastos(String msgGastos) {
        this.msgGastos = msgGastos;
    }

    public String getMsgVenta() {
        return msgVenta;
    }

    public void setMsgVenta(String msgVenta) {
        this.msgVenta = msgVenta;
    }

    public String getMsgActivo() {
        return msgActivo;
    }

    public void setMsgActivo(String msgActivo) {
        this.msgActivo = msgActivo;
    }

    public String getMsgDepre() {
        return msgDepre;
    }

    public void setMsgDepre(String msgDepre) {
        this.msgDepre = msgDepre;
    }

    public Boolean getEstadoValido() {
        return estadoValido;
    }

    public void setEstadoValido(Boolean estadoValido) {
        this.estadoValido = estadoValido;
    }

    public List<TCuenta> getListaCuentas() {
        this.listaCuentas = this.cuentaBo.listCuentaEntActTip(idEntidad, true);
        return listaCuentas;
    }

    public void setListaCuentas(List<TCuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public ICuentaBo getCuentaBo() {
        return cuentaBo;
    }

    public void setCuentaBo(ICuentaBo cuentaBo) {
        this.cuentaBo = cuentaBo;
    }

    public TTipoActivo getTipoActivo() {
        return tipoActivo;
    }

    public void setTipoActivo(TTipoActivo tipoActivo) {
        this.tipoActivo = tipoActivo;
    }

    public TTipoActivo getTipoActivoSeleccionado() {
        return tipoActivoSeleccionado;
    }

    public void setTipoActivoSeleccionado(TTipoActivo tipoActivoSeleccionado) {
        this.tipoActivoSeleccionado = tipoActivoSeleccionado;
    }

    public List<TTipoActivo> getListaTipoActivo() {
        this.listaTipoActivo = this.tipoActivoBo.listTipoActivo(idEntidad);
        return listaTipoActivo;
    }

    public void setListaTipoActivo(List<TTipoActivo> listaTipoActivo) {
        this.listaTipoActivo = listaTipoActivo;
    }

    public ITipoActivoBo getTipoActivoBo() {
        return tipoActivoBo;
    }

    public void setTipoActivoBo(ITipoActivoBo tipoActivoBo) {
        this.tipoActivoBo = tipoActivoBo;
    }

    public TCuenta getCuentaActivo() {
        return cuentaActivo;
    }

    public void setCuentaActivo(TCuenta cuentaActivo) {
        this.cuentaActivo = cuentaActivo;
    }

    public TCuenta getCuentaGasto() {
        return cuentaGasto;
    }

    public void setCuentaGasto(TCuenta cuentaGasto) {
        this.cuentaGasto = cuentaGasto;
    }

    public TCuenta getCuentaVenta() {
        return cuentaVenta;
    }

    public void setCuentaVenta(TCuenta cuentaVenta) {
        this.cuentaVenta = cuentaVenta;
    }

    public TCuenta getCuentaDepre() {
        return cuentaDepre;
    }

    public void setCuentaDepre(TCuenta cuentaDepre) {
        this.cuentaDepre = cuentaDepre;
    }

    public void enableShowDataBean() {
        super.enableShowData();
        this.limpiar();
    }

    public void limpiar() {

        this.tipoActivo = new TTipoActivo();
        this.tipoActivo.setVidaUtilTipo(0);

        this.estadoValido = false;
        this.cuentaActivo = new TCuenta();
        this.cuentaGasto = new TCuenta();
        this.cuentaVenta = new TCuenta();
        this.cuentaDepre = new TCuenta();
        this.cuentaActivo.setCodigoCuenta("");
        this.cuentaGasto.setCodigoCuenta("");
        this.cuentaVenta.setCodigoCuenta("");
        this.cuentaDepre.setCodigoCuenta("");

        this.msgActivo = "";
        this.msgCodigo = "";
        this.msgDepre = "";
        this.msgDescripcion = "";
        this.msgNombre = "";
        this.msgTipo = "";
        this.msgVenta = "";
        this.msgVidaUtil = "";
        this.msgGastos = "";

    }

    public void validarFormulario() {

        this.estadoValido = true;

        if (this.tipoActivo.getCodigoTipo().length() == 0) {
            this.msgCodigo = "El codigo es invalido";
            this.estadoValido = false;
        } else if (this.tipoActivoBo.getActivoRep(1, this.tipoActivo.getCodigoTipo()) != null) {
            this.msgCodigo = "El codigo ya fue asignado a otro tipo";
            this.estadoValido = false;
        } else {
            this.msgCodigo = "";
        }

        if (this.tipoActivo.getNombreTipo().length() < 6) {
            this.msgNombre = "El nombre debe contener como mínimo 6 caracteres";
            this.estadoValido = false;
        } else {
            this.msgNombre = "";
        }

        if (this.tipoActivo.getVidaUtilTipo() < 1) {
            this.msgVidaUtil = "La vida ultil es requerida";
            this.estadoValido = false;
        } else {
            this.msgVidaUtil = "";
        }

        if (this.tipoActivo.getCalculoTipoActivo().equals("")) {
            this.msgTipo = "El tipo es requerido";
            this.estadoValido = false;
        } else {
            this.msgTipo = "";
        }

        /*
        
         */
        if (this.cuentaGasto.getCodigoCuenta().length() == 0) {
            this.msgGastos = "La cuenta de gastos del activo es requerida";
            this.estadoValido = false;
        } else if (this.cuentaGasto.getIdCuenta() == this.cuentaActivo.getIdCuenta() || this.cuentaGasto.getIdCuenta() == this.cuentaVenta.getIdCuenta() || this.cuentaGasto.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgGastos = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgGastos = "";
        }

        if (this.cuentaActivo.getCodigoCuenta().length() == 0) {
            this.msgActivo = "La cuenta del activo es requerida";
            this.estadoValido = false;
        } else if (this.cuentaActivo.getIdCuenta() == this.cuentaGasto.getIdCuenta() || this.cuentaActivo.getIdCuenta() == this.cuentaVenta.getIdCuenta() || this.cuentaActivo.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgActivo = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgActivo = "";
        }

        if (this.cuentaVenta.getCodigoCuenta().length() == 0) {
            this.msgVenta = "La cuenta de venta del activo es requerida";
            this.estadoValido = false;
        } else if (this.cuentaVenta.getIdCuenta() == this.cuentaGasto.getIdCuenta() || this.cuentaVenta.getIdCuenta() == this.cuentaActivo.getIdCuenta() || this.cuentaVenta.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgVenta = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgVenta = "";
        }

        if (this.cuentaDepre.getCodigoCuenta().length() == 0) {
            this.msgDepre = "La cuenta de depreciación  del  activo es requerida";
            this.estadoValido = false;
        } else if (this.cuentaDepre.getIdCuenta() == this.cuentaGasto.getIdCuenta() || this.cuentaDepre.getIdCuenta() == this.cuentaActivo.getIdCuenta() || this.cuentaVenta.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgDepre = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgDepre = "";
        }
    }

    public void validarFormularioModificar() {

        this.estadoValido = true;

        if (this.tipoActivoSeleccionado.getCodigoTipo().length() == 0) {
            this.msgCodigo = "El codigo es requerido";
            this.estadoValido = false;
        } else if (this.tipoActivoBo.getActivoRepAct(1, this.tipoActivoSeleccionado.getIdTipo(), this.tipoActivoSeleccionado.getCodigoTipo()) != null) {
            this.msgCodigo = "El codigo ya fue asignado a otro tipo";
            this.estadoValido = false;
        } else {
            this.msgCodigo = "";
        }

        if (this.tipoActivoSeleccionado.getNombreTipo().length() < 6) {
            this.msgNombre = "El nombre debe contener como mínimo 6 caracteres";
            this.estadoValido = false;
        } else {
            this.msgNombre = "";
        }

        if (this.tipoActivoSeleccionado.getVidaUtilTipo() < 1) {
            this.msgVidaUtil = "La vida ultil es requerida";
            this.estadoValido = false;
        } else {
            this.msgVidaUtil = "";
        }

        if (this.tipoActivoSeleccionado.getCalculoTipoActivo().equals("")) {
            this.msgTipo = "El tipo es requerido";
            this.estadoValido = false;
        } else {
            this.msgTipo = "";
        }

        /*
        
         */
        if (this.cuentaGasto.getCodigoCuenta().length() == 0) {
            this.msgGastos = "La cuenta de gastos del activo es requerida";
            this.estadoValido = false;
        } else if (this.cuentaGasto.getIdCuenta() == this.cuentaActivo.getIdCuenta() || this.cuentaGasto.getIdCuenta() == this.cuentaVenta.getIdCuenta() || this.cuentaGasto.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgGastos = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgGastos = "";
        }

        if (this.cuentaActivo.getCodigoCuenta().length() == 0) {
            this.msgActivo = "La cuenta del activo es requerida";
            this.estadoValido = false;
        } else if (this.cuentaActivo.getIdCuenta() == this.cuentaGasto.getIdCuenta() || this.cuentaActivo.getIdCuenta() == this.cuentaVenta.getIdCuenta() || this.cuentaActivo.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgActivo = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgActivo = "";
        }

        if (this.cuentaVenta.getCodigoCuenta().length() == 0) {
            this.msgVenta = "La cuenta de venta del activo es requerida";
            this.estadoValido = false;
        } else if (this.cuentaVenta.getIdCuenta() == this.cuentaGasto.getIdCuenta() || this.cuentaVenta.getIdCuenta() == this.cuentaActivo.getIdCuenta() || this.cuentaVenta.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgVenta = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgVenta = "";
        }

        if (this.cuentaDepre.getCodigoCuenta().length() == 0) {
            this.msgDepre = "La cuenta de depreciación  del  activo es requerida";
            this.estadoValido = false;
        } else if (this.cuentaDepre.getIdCuenta() == this.cuentaGasto.getIdCuenta() || this.cuentaDepre.getIdCuenta() == this.cuentaActivo.getIdCuenta() || this.cuentaVenta.getIdCuenta() == this.cuentaDepre.getIdCuenta()) {
            this.msgDepre = "La cuenta no puede asignarse  dos veces";
            this.estadoValido = false;
        } else {
            this.msgDepre = "";
        }

    }

    public void crearTipo() {

        try {
            this.tipoActivo.setTEntidad(new TEntidad(idEntidad));
            this.tipoActivo.setTCuentaByIdCuentaVentaTipo(cuentaVenta);
            this.tipoActivo.setTCuentaByIdCuentaActivoTipo(cuentaActivo);
            this.tipoActivo.setTCuentaByIdCuentaDepreciacionTipo(cuentaDepre);
            this.tipoActivo.setTCuentaByIdCuentaGastoTipo(cuentaGasto);
            this.tipoActivoBo.create(tipoActivo);
            this.limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  "Tipo de activo registrado correctamente.",""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,  "El  tipo de activo no pudo ser registrado.",""));
        }
    }

    public void actualizar() {

        try {

            this.tipoActivoSeleccionado.setTCuentaByIdCuentaVentaTipo(cuentaVenta);
            this.tipoActivoSeleccionado.setTCuentaByIdCuentaActivoTipo(cuentaActivo);
            this.tipoActivoSeleccionado.setTCuentaByIdCuentaDepreciacionTipo(cuentaDepre);
            this.tipoActivoSeleccionado.setTCuentaByIdCuentaGastoTipo(cuentaGasto);
            this.tipoActivoBo.update(tipoActivoSeleccionado);
            this.enableShowDataBean();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Tipo de activo  modificado correctamente.","" ));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El  tipo de activo no pudo ser modificado.","" ));
        }
    }

    public void cargarDatosCeuntas() {
        this.cuentaActivo = this.cuentaBo.getCuenta(tipoActivoSeleccionado.getTCuentaByIdCuentaActivoTipo().getIdCuenta());
        this.cuentaDepre = this.cuentaBo.getCuenta(tipoActivoSeleccionado.getTCuentaByIdCuentaDepreciacionTipo().getIdCuenta());
        this.cuentaVenta = this.cuentaBo.getCuenta(tipoActivoSeleccionado.getTCuentaByIdCuentaVentaTipo().getIdCuenta());
        this.cuentaGasto = this.cuentaBo.getCuenta(tipoActivoSeleccionado.getTCuentaByIdCuentaGastoTipo().getIdCuenta());
    }

    public void darDeAlta() {
        try {
            this.tipoActivoSeleccionado.setEstadoTipo(true);
            this.tipoActivoBo.update(tipoActivoSeleccionado);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  "Tipo de activo dado de alta correctamente.",""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  "El tipo de activo no pudo ser dado de alta.",""));
        }

    }

    public void darDeBaja() {

        try {
            this.tipoActivoSeleccionado.setEstadoTipo(false);
            this.tipoActivoBo.update(tipoActivoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de activor dado de baja correctamente.","" ));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tipo de activo no pudo ser dado de baja.", ""));

        }
    }
    
    
     public Connection conec() throws SQLException {
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");
        return conn;
    }

    public void generarReporteTipo() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idTipoActivo", this.tipoActivoSeleccionado.getIdTipo());
        estadoUsuario.put("idEntidad",this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/ReporteTiposActivoIndividual.jasper"));
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

    public void generarReporteTipoPDF() throws Exception {
        Map<String, Object> estadoUsuario = new HashMap();
        estadoUsuario.put("idTipoActivo", this.tipoActivoSeleccionado.getIdTipo());
        estadoUsuario.put("idEntidad",this.idEntidad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/contabilidad/ReporteTiposActivoIndividual.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.conec());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Tipo-Activo-"+this.tipoActivoSeleccionado.getNombreTipo()+".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
}
