/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IActivoFijoBo;
import com.sigaf.Ibo.IDepreciacionBo;
import com.sigaf.Ibo.IDetalleParidaBo;
import com.sigaf.Ibo.IEjercicioBo;
import com.sigaf.Ibo.IEmpleadoAreaBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IPartidaBo;
import com.sigaf.Ibo.IPeriodoBo;
import com.sigaf.Ibo.ITipoActivoBo;
import com.sigaf.Ibo.IValorActivoBo;
import com.sigaf.pojo.TActivoFijo;
import com.sigaf.pojo.TCuenta;
import com.sigaf.pojo.TDepreciacion;
import com.sigaf.pojo.TDetallePartida;
import com.sigaf.pojo.TEjercicio;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TPartida;
import com.sigaf.pojo.TPeriodo;
import com.sigaf.pojo.TTipoActivo;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Eliseo
 */
@SessionScoped
@ManagedBean
public class EjercicioBean extends Actividad {

    /**
     * Creates a new instance of EjercicioBean
     */
    private Boolean estadoPredeterminado;

    private IEmpleadoAreaBo empleadoAreaBo;

    private Integer idEjer;

    private TPeriodo periodoNuevo;

    private TPeriodo periodoViejo;

    private List<TDetallePartida> listaDetallePartida;

    private TPartida partida;

    private BigDecimal totalDebe;

    private BigDecimal totalHaber;

    private IPartidaBo partidaBo;

    private IDetalleParidaBo detallePartidaBo;

    private Integer numPartida;

    private IDepreciacionBo depreciacionBo;

    private IActivoFijoBo activoFijoBo;

    private IValorActivoBo valorActivoBo;

    private ITipoActivoBo tipoActivoBo;

    private List< TTipoActivo> listaTipoActivo;

    private List<TEntidad> listaEntidades;

    private IEntidadBo entidadBo;

    private Integer idEntidad;

    private IEjercicioBo ejercicioBo;

    private List<TEjercicio> listaEjercicios;

    private IPeriodoBo periodoBo;

    private TPeriodo periodoSeleccionado;

    private List<String> mesPeriodo;

    private TEjercicio ejercicio;

    /**
     * Atributos para validar el formulario
     */
    private Boolean estadoValido;

    private String msgPeriodo;

    private String msgEjercicio;

    private String limpiarAux;

    public void cambiarPredeterminado() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        loginBean.setIdEntidad(this.idEntidad);
        loginBean.setPredeterminado(estadoPredeterminado);
    }

    public Integer getIdEntidad() {

        return idEntidad;

    }

    public void setIdEntidad(Integer idEntidad) {

        if (0 != idEntidad) {

            this.listaEjercicios = this.ejercicioBo.listEjercicio(idEntidad);

            if (this.listaEjercicios.isEmpty()) {

                this.limpiar();

                super.enableShowCreate();
            } else {
                super.enableShowData();
            }

        }

        this.idEntidad = idEntidad;
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

    public IEmpleadoAreaBo getEmpleadoAreaBo() {
        return empleadoAreaBo;
    }

    public void setEmpleadoAreaBo(IEmpleadoAreaBo empleadoAreaBo) {
        this.empleadoAreaBo = empleadoAreaBo;
    }

    public String getLimpiarAux() {

        this.listaDetallePartida.clear();
        this.totalDebe = BigDecimal.ZERO;
        this.totalHaber = BigDecimal.ZERO;

        return limpiarAux;
    }

    public void setLimpiarAux(String limpiarAux) {
        this.limpiarAux = limpiarAux;
    }

    public void llenarMesPeriodoAux(String mes) {

        if (mes.equals("Enero")) {

            this.periodoNuevo.setMesPeriodo("Febrero");

        } else if (mes.equals("Febrero")) {

            this.periodoNuevo.setMesPeriodo("Marzo");

        } else if (mes.equals("Marzo")) {

            this.periodoNuevo.setMesPeriodo("Abril");

        } else if (mes.equals("Abril")) {

            this.periodoNuevo.setMesPeriodo("Mayo");

        } else if (mes.equals("Mayo")) {

            this.periodoNuevo.setMesPeriodo("Junio");

        } else if (mes.equals("Junio")) {

            this.periodoNuevo.setMesPeriodo("Julio");

        } else if (mes.equals("Julio")) {

            this.periodoNuevo.setMesPeriodo("Septiembre");

        } else if (mes.equals("Septiembre")) {

            this.periodoNuevo.setMesPeriodo("Octubre");

        } else if (mes.equals("Octubre")) {

            this.periodoNuevo.setMesPeriodo("Noviembre");

        } else if (mes.equals("Noviembre")) {

            this.periodoNuevo.setMesPeriodo("Diciembre");
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Imposible agregar otro periodo a este ejercicio."));

        }

    }

    public Integer getIdEjer() {
        return idEjer;
    }

    public Boolean mesCierre(Integer idEjer) {

        this.periodoViejo = this.periodoBo.getPeriodoAbierto(idEjer);

        if (periodoViejo.getMesPeriodo().equals("Diciembre")) {
            return true;
        } else {
            return false;
        }

    }

    public void setIdEjer(Integer idEjer) {
        this.partida = new TPartida();
        this.partida.setFechaPartida(new Date());
        this.periodoNuevo = new TPeriodo();
        this.llenarMesPeriodoAux(periodoViejo.getMesPeriodo());
        this.partida.setConceptoPartida("Depreciación/Amortización " + periodoViejo.getMesPeriodo());
        this.listaDetallePartida = new ArrayList<>();
        this.totalDebe = BigDecimal.ZERO;
        this.totalHaber = BigDecimal.ZERO;
        this.idEjer = idEjer;
    }

    public Integer getNumPartida() {

        numPartida = this.partidaBo.numeroPartida(idEjer);
        return numPartida;

    }

    public void setNumPartida(Integer numPartida) {
        this.numPartida = numPartida;
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

    public List<TDetallePartida> getListaDetallePartida() {
        return listaDetallePartida;
    }

    public void setListaDetallePartida(List<TDetallePartida> listaDetallePartida) {
        this.listaDetallePartida = listaDetallePartida;
    }

    public TPartida getPartida() {
        return partida;
    }

    public void setPartida(TPartida partida) {
        this.partida = partida;
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

    public IDepreciacionBo getDepreciacionBo() {
        return depreciacionBo;
    }

    public void setDepreciacionBo(IDepreciacionBo depreciacionBo) {
        this.depreciacionBo = depreciacionBo;
    }

    public IActivoFijoBo getActivoFijoBo() {
        return activoFijoBo;
    }

    public void setActivoFijoBo(IActivoFijoBo activoFijoBo) {
        this.activoFijoBo = activoFijoBo;
    }

    public IValorActivoBo getValorActivoBo() {
        return valorActivoBo;
    }

    public void setValorActivoBo(IValorActivoBo valorActivoBo) {
        this.valorActivoBo = valorActivoBo;
    }

    public ITipoActivoBo getTipoActivoBo() {
        return tipoActivoBo;
    }

    public void setTipoActivoBo(ITipoActivoBo tipoActivoBo) {
        this.tipoActivoBo = tipoActivoBo;
    }

    public void setListaTipoActivo(List<TTipoActivo> listaTipoActivo) {
        this.listaTipoActivo = listaTipoActivo;
    }

    public List<TTipoActivo> getListaTipoActivo() {
        this.listaTipoActivo = this.tipoActivoBo.listTipoActivo(idEntidad);
        return listaTipoActivo;
    }

    public BigDecimal valorActivo(TActivoFijo act) {

        BigDecimal valor;

        valor = this.valorActivoBo.getTValorActivo(act.getIdActivoFijo()).getValorActivo();

        BigDecimal valorRedondeado = valor.setScale(2, BigDecimal.ROUND_HALF_UP);

        return valorRedondeado;

    }

    public BigDecimal depreMensualSuma(TActivoFijo act) {

        BigDecimal valor;

        valor = this.valorActivoBo.getTValorActivo(act.getIdActivoFijo()).getValorActivo().divide(new BigDecimal(12 * act.getTTipoActivo().getVidaUtilTipo()), 2, RoundingMode.HALF_UP);
        return valor;

    }

    public List<TActivoFijo> listaActivosDepre(Set activos) {

        List<TActivoFijo> activosLista = new ArrayList<>(activos);

        List<TActivoFijo> listaAux = new ArrayList<>();

        for (TActivoFijo tActivoFijo : activosLista) {

            if (tActivoFijo.getEstadoActivoFijo().equals("Activo") && null != this.valorActivoBo.getTValorActivo(tActivoFijo.getIdActivoFijo()).getTPartida()) {

                if (depreciacionBo.listDepreciacion(tActivoFijo.getIdActivoFijo()).isEmpty()) {

                    String[] aFechaIng = tActivoFijo.getRegistroActivoFijo().toString().split("-");

                    Integer diaInicio = Integer.parseInt(aFechaIng[2]);

                    java.util.Date fecha = new Date();

                    int diferenciaMes = fecha.getMonth() - tActivoFijo.getRegistroActivoFijo().getMonth();

                    if (diferenciaMes >= 1) {

                        listaAux.add(tActivoFijo);

                    } else if (diaInicio <= 15) {

                        listaAux.add(tActivoFijo);

                    }

                } else if (depreciacionBo.listDepreciacion(tActivoFijo.getIdActivoFijo()).size() < tActivoFijo.getTTipoActivo().getVidaUtilTipo() * 12) {
                    listaAux.add(tActivoFijo);
                }

            }
        }

        if (listaAux.size() > 0) {
            this.cargarParida(listaAux);
        }

        return listaAux;
    }

    public void cargarParida(List<TActivoFijo> listaAux) {

        BigDecimal sum = new BigDecimal(0.00);

        TCuenta depre = listaAux.get(0).getTTipoActivo().getTCuentaByIdCuentaDepreciacionTipo();

        TCuenta gasto = listaAux.get(0).getTTipoActivo().getTCuentaByIdCuentaGastoTipo();

        TDetallePartida detallePartidaDepre = new TDetallePartida();

        TDetallePartida detallePartidaGasto = new TDetallePartida();

        detallePartidaDepre.setTCuenta(depre);

        detallePartidaGasto.setTCuenta(gasto);

        for (TActivoFijo tActivoFijo : listaAux) {
            sum = sum.add(depreMensualSuma(tActivoFijo));
        }

        detallePartidaDepre.setSaldoDetallePartida(sum);
        detallePartidaDepre.setTipoSaldoDetallePartida("Haber");
        detallePartidaGasto.setSaldoDetallePartida(sum);
        detallePartidaGasto.setTipoSaldoDetallePartida("Debe");

        this.totalDebe = totalDebe.add(sum);
        this.totalHaber = totalHaber.add(sum);

        this.listaDetallePartida.add(detallePartidaGasto);
        this.listaDetallePartida.add(detallePartidaDepre);

    }

    public List<TEntidad> getListaEntidades() {
        this.listaEntidades = this.entidadBo.listTEndidadTodos();

        return listaEntidades;
    }

    public void setListaEntidades(List<TEntidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public IEntidadBo getEntidadBo() {
        return entidadBo;
    }

    public void setEntidadBo(IEntidadBo entidadBo) {
        this.entidadBo = entidadBo;
    }

    public void init() {

        this.idEntidad = 0;
        this.estadoPredeterminado = false;

    }

    public String getMsgEjercicio() {
        return msgEjercicio;
    }

    public void setMsgEjercicio(String msgEjercicio) {
        this.msgEjercicio = msgEjercicio;
    }

    public Boolean getEstadoValido() {
        return estadoValido;
    }

    public void setEstadoValido(Boolean estadoValido) {
        this.estadoValido = estadoValido;
    }

    public String getMsgPeriodo() {
        return msgPeriodo;
    }

    public void setMsgPeriodo(String msgPeriodo) {
        this.msgPeriodo = msgPeriodo;
    }

    public TEjercicio getEjercicio() {
        return ejercicio;
    }

    public List<TEjercicio> getListaEjercicios() {

        this.listaEjercicios = this.ejercicioBo.listEjercicio(idEntidad);
        return listaEjercicios;

    }

    public void setListaEjercicios(List<TEjercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    public TPeriodo getPeriodoSeleccionado() {
        return periodoSeleccionado;
    }

    public void setPeriodoSeleccionado(TPeriodo periodoSeleccionado) {
        this.periodoSeleccionado = periodoSeleccionado;
    }

    public void setEjercicio(TEjercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public List<String> getMesPeriodo() {
        return mesPeriodo;
    }

    public void setMesPeriodo(List<String> mesPeriodo) {
        this.mesPeriodo = mesPeriodo;
    }

    public TPeriodo getPeriodoNuevo() {
        return periodoNuevo;
    }

    public void setPeriodoNuevo(TPeriodo periodoNuevo) {
        this.periodoNuevo = periodoNuevo;
    }

    public TPeriodo getPeriodoViejo() {
        return periodoViejo;
    }

    public void setPeriodoViejo(TPeriodo periodoViejo) {
        this.periodoViejo = periodoViejo;
    }

    public List<TPeriodo> listaPeriodoEjercicio(Integer id) {

        return periodoBo.listPeriodo(id);

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

    public void crearDespues() {

        try {

            this.periodoNuevo.setTEjercicio(this.ejercicio);
            this.periodoNuevo.setEstadoPeriodo(true);
            this.periodoBo.create(periodoNuevo);

            super.enableShowData();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Periodo registrado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El periodo no pudo ser registrado.", ""));
        }

    }

    public void limpiar() {

        this.estadoValido = false;
        this.msgPeriodo = "";
        this.msgEjercicio = "";
        this.periodoNuevo = new TPeriodo();
        this.ejercicio = new TEjercicio();

    }

    public void validarFormularioInicio() {

        this.estadoValido = true;

        if (this.periodoNuevo.getMesPeriodo().equals("0")) {
            this.msgPeriodo = "Seleccione el periodo";
            this.estadoValido = false;
        } else {
            this.msgPeriodo = "";
        }
        if (this.ejercicio.getAhoEjercicio() == 0) {
            this.msgEjercicio = "Seleccione el ejercicio";
            this.estadoValido = false;
        } else {
            this.msgEjercicio = "";
        }

    }

    public void crearInicio() {

        try {
            TEntidad auxEnt = new TEntidad(idEntidad);
            ejercicio.setEstadoEjercicio(true);
            ejercicio.setTEntidad(auxEnt);
            this.ejercicioBo.create(ejercicio);
            this.periodoNuevo.setTEjercicio(ejercicio);
            this.periodoNuevo.setEstadoPeriodo(true);
            this.periodoBo.create(periodoNuevo);
            super.enableShowData();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Periodo  registrado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El periodo no pudo ser registrado.", ""));
        }

    }

    public void crearCierrePeriodo() {

        try {

            if (listaDetallePartida.size() > 0) {

                this.partida.setEstadoPartida(true);
                this.partida.setNumeroPartida(numPartida);
                this.partida.setTPeriodo(periodoViejo);
                this.partidaBo.create(this.partida);

                listaDetallePartida.clear();

                TDepreciacion depre = new TDepreciacion();

                for (TTipoActivo tipo : listaTipoActivo) {

                    List<TActivoFijo> listaAux = listaActivosDepre(tipo.getTActivoFijos());

                    for (TActivoFijo tActivoFijo : listaAux) {

                        depre.setTPartida(partida);
                        depre.setTActivoFijo(tActivoFijo);
                        depre.setValorDepreciacion(depreMensualSuma(tActivoFijo));
                        depreciacionBo.create(depre);

                    }

                }

                for (TDetallePartida tDetallePartida : listaDetallePartida) {

                    tDetallePartida.setTPartida(partida);

                    this.detallePartidaBo.create(tDetallePartida);
                }

            }

            this.periodoNuevo.setTEjercicio(new TEjercicio(idEjer));
            this.periodoNuevo.setEstadoPeriodo(true);
            this.periodoBo.create(periodoNuevo);

            this.enableShowData();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Periodo  registrado correctamente.", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El periodo no pudo ser registrado.", ""));
        }

    }

    public String cogigoGenerar(TActivoFijo activo) {

        String codigoArea = this.empleadoAreaBo.getTEmpleadoArea(null, activo.getTEmpleado().getIdEmpleado()).getTArea().getCodigoArea();

        return activo.getTTipoActivo().getTEntidad().getCodigoEntidad() + "-" + codigoArea + "-" + activo.getTTipoActivo().getCodigoTipo() + "-" + activo.getCodigoActivoFijo();

    }

    public Integer tipoCancelar() {
        if (estadoPredeterminado) {
            return idEntidad;
        } else {
            return 0;
        }

    }

}
