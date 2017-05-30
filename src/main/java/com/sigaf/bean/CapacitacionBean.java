package com.sigaf.bean;

import com.sigaf.Ibo.IAreaBo;
import com.sigaf.Ibo.IAsesoriaBo;
import com.sigaf.Ibo.ICapacitacionBo;
import com.sigaf.Ibo.IClienteProyectoBo;
import com.sigaf.Ibo.ICompradorBo;
import com.sigaf.Ibo.IEmpleadoAreaBo;
import com.sigaf.Ibo.IEmpleadoBo;
import com.sigaf.Ibo.IEntidadProyectoBo;
import com.sigaf.Ibo.IProyectoBo;
import com.sigaf.bean.Actividad;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TAsesoria;
import com.sigaf.pojo.TCapacitacion;
import com.sigaf.pojo.TClienteProyecto;
import com.sigaf.pojo.TComprador;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEmpleadoArea;
import com.sigaf.pojo.TEntidadProyecto;
import com.sigaf.pojo.TProyecto;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

;

/**
 *
 * @author yonat
 */
@Named(value = "capacitacionBean")
@RequestScoped
@ManagedBean

public class CapacitacionBean extends Actividad {

    private TComprador Comprador;
    private TComprador CompradorSeleccionado;
    private List<TComprador> listaComprador;
    private List<TProyecto> listaProyecto;

    public Integer getIdArea() {
        return IdArea;
    }

    public void setIdArea(Integer IdArea) {
        this.IdArea = IdArea;
    }
    private Integer IdArea;

    public List<TProyecto> getListaProyecto() {
        return listaProyecto;
    }

    public void setListaProyecto(List<TProyecto> listaProyecto) {
        this.listaProyecto = listaProyecto;
    }
    private ICompradorBo compradorBo;
    private IProyectoBo proyectoBo;
    private TProyecto proyectoSeleccionado;
    private ICapacitacionBo capacitacionBo;
    private IClienteProyectoBo iclienteProyectoBo;
    private IEntidadProyectoBo ientidadProyectoBo;

    public ICapacitacionBo getCapacitacionBo() {
        return capacitacionBo;
    }

    public void setCapacitacionBo(ICapacitacionBo capacitacionBo) {
        this.capacitacionBo = capacitacionBo;
    }

    public IProyectoBo getProyectoBo() {
        return proyectoBo;
    }

    public void setProyectoBo(IProyectoBo proyectoBo) {
        this.proyectoBo = proyectoBo;
    }

    public TProyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(TProyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }
    private IAreaBo areaBo;

    private List<TArea> listaAreas;
    private List<TEmpleadoArea> listaEmpleadoAreas;
    private List<TEmpleado> listaEmpleados;
    private TEmpleado empleadoSelecionada;
    private Boolean proyectoPersona;
    private Boolean proyectoCooperativa;
    private List<TArea> listaArea;

    private IEmpleadoBo iempleadoBo;

    public IEmpleadoBo getIempleadoBo() {
        return iempleadoBo;
    }

    public void setIempleadoBo(IEmpleadoBo iempleadoBo) {
        this.iempleadoBo = iempleadoBo;
    }

    public List<TArea> getListaArea() {
        return this.listaArea = this.iempleadoBo.listTAreas(0, 1, 2);
    }

    public void setListaArea(List<TArea> listaArea) {
        this.listaArea = listaArea;
    }

    public Boolean getProyectoPersona() {
        return proyectoPersona;
    }

    public void setProyectoPersona(Boolean proyectoPersona) {
        this.proyectoPersona = proyectoPersona;
    }

    public Boolean getProyectoCooperativa() {
        return proyectoCooperativa;
    }

    public void setProyectoCooperativa(Boolean proyectoCooperativa) {
        this.proyectoCooperativa = proyectoCooperativa;
    }

    private TCapacitacion capacitacion;
    private TCapacitacion capacitacionSeleccionada;

    public TCapacitacion getCapacitacionSeleccionada() {
        return capacitacionSeleccionada;
    }

    public void setCapacitacionSeleccionada(TCapacitacion capacitacionSeleccionada) {
        this.capacitacionSeleccionada = capacitacionSeleccionada;
    }

    public TCapacitacion getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(TCapacitacion capacitacion) {
        this.capacitacion = capacitacion;
    }

    private List<TEntidadProyecto> listaEntidadProyecto;
    private List<TClienteProyecto> listaClienteProyecto;

    public IClienteProyectoBo getIclienteProyectoBo() {
        return iclienteProyectoBo;
    }

    public void setIclienteProyectoBo(IClienteProyectoBo iclienteProyectoBo) {
        this.iclienteProyectoBo = iclienteProyectoBo;
    }

    public IEntidadProyectoBo getIentidadProyectoBo() {
        return ientidadProyectoBo;
    }

    public void setIentidadProyectoBo(IEntidadProyectoBo ientidadProyectoBo) {
        this.ientidadProyectoBo = ientidadProyectoBo;
    }

    public List<TEntidadProyecto> getListaEntidadProyecto() {
        return listaEntidadProyecto;
    }

    public void setListaEntidadProyecto(List<TEntidadProyecto> listaEntidadProyecto) {
        this.listaEntidadProyecto = listaEntidadProyecto;
    }

    public List<TClienteProyecto> getListaClienteProyecto() {
        return listaClienteProyecto;
    }

    public void setListaClienteProyecto(List<TClienteProyecto> listaClienteProyecto) {
        this.listaClienteProyecto = listaClienteProyecto;
    }

    private List<TCapacitacion> listaCapacitacion;

    public List<TCapacitacion> getListaCapacitacion() {
        return listaCapacitacion;
    }

    public void setListaCapacitacion(List<TCapacitacion> listaCapacitacion) {
        this.listaCapacitacion = listaCapacitacion;
    }

    public TEmpleado getEmpleadoSelecionada() {
        return empleadoSelecionada;
    }

    public void setEmpleadoSelecionada(TEmpleado empleadoSelecionada) {
        this.empleadoSelecionada = empleadoSelecionada;
    }

    public List<TArea> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<TArea> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public List<TEmpleadoArea> getListaEmpleadoAreas() {
        return listaEmpleadoAreas;
    }

    public void setListaEmpleadoAreas(List<TEmpleadoArea> listaEmpleadoAreas) {
        this.listaEmpleadoAreas = listaEmpleadoAreas;
    }

    public List<TEmpleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<TEmpleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public IAreaBo getAreaBo() {
        return areaBo;
    }

    public void setAreaBo(IAreaBo areaBo) {
        this.areaBo = areaBo;
    }

    private IEmpleadoAreaBo empleadoAreaBo;

    public IEmpleadoAreaBo getEmpleadoAreaBo() {
        return empleadoAreaBo;
    }

    public void setEmpleadoAreaBo(IEmpleadoAreaBo empleadoAreaBo) {
        this.empleadoAreaBo = empleadoAreaBo;
    }

    private IEmpleadoBo empleadoBo;

    public IEmpleadoBo getEmpleadoBo() {
        return empleadoBo;
    }

    public void setEmpleadoBo(IEmpleadoBo empleadoBo) {
        this.empleadoBo = empleadoBo;
    }

    private boolean estadoFormulario;

    private String msgDui;
    private String msgProyecto;
    private String msgFecha;
    private String msgDescripcion;
    private String msgBeneficiarios;
    private String msgPrecio;

    public String getMsgDui() {
        return msgDui;
    }

    public void setMsgDui(String msgDui) {
        this.msgDui = msgDui;
    }

    public String getMsgProyecto() {
        return msgProyecto;
    }

    public void setMsgProyecto(String msgProyecto) {
        this.msgProyecto = msgProyecto;
    }

    public String getMsgFecha() {
        return msgFecha;
    }

    public void setMsgFecha(String msgFecha) {
        this.msgFecha = msgFecha;
    }

    public String getMsgDescripcion() {
        return msgDescripcion;
    }

    public void setMsgDescripcion(String msgDescripcion) {
        this.msgDescripcion = msgDescripcion;
    }

    public String getMsgBeneficiarios() {
        return msgBeneficiarios;
    }

    public void setMsgBeneficiarios(String msgBeneficiarios) {
        this.msgBeneficiarios = msgBeneficiarios;
    }

    public String getMsgPrecio() {
        return msgPrecio;
    }

    public void setMsgPrecio(String msgPrecio) {
        this.msgPrecio = msgPrecio;
    }

    public boolean isEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public void init() {

        this.empleadoSelecionada = new TEmpleado();
        this.proyectoSeleccionado = new TProyecto();
        this.proyectoSeleccionado.setCodigoProyecto("");
        this.empleadoSelecionada.setDuiEmpleado("");
        this.capacitacionSeleccionada = new TCapacitacion();
        this.capacitacionSeleccionada.setTEmpleado(new TEmpleado());
        this.capacitacionSeleccionada.setTProyecto(new TProyecto());
        this.capacitacion = new TCapacitacion();
        this.capacitacion.setTEmpleado(new TEmpleado());
        this.capacitacion.setTProyecto(new TProyecto());
        this.listaComprador = compradorBo.listComprador();

        super.setShowCreate(true);
        super.setShowData(false);
        super.setShowUpdate(false);
    }

    public TComprador getComprador() {
        return Comprador;
    }

    public void setComprador(TComprador Comprador) {
        this.Comprador = Comprador;
    }

    public TComprador getCompradorSeleccionado() {
        return CompradorSeleccionado;
    }

    public void setCompradorSeleccionado(TComprador CompradorSeleccionado) {
        this.CompradorSeleccionado = CompradorSeleccionado;
    }

    public List<TComprador> getListaComprador() {

        return listaComprador;
    }

    public void setListaComprador(List<TComprador> listaComprador) {
        this.listaComprador = listaComprador;
    }

    public ICompradorBo getCompradorBo() {
        return compradorBo;
    }

    public void setCompradorBo(ICompradorBo compradorBo) {
        this.compradorBo = compradorBo;
    }

    public void modificar() {

        this.capacitacionSeleccionada.setTEmpleado(empleadoSelecionada);
        this.capacitacionSeleccionada.setTProyecto(proyectoSeleccionado);

        this.capacitacionBo.update(this.capacitacionSeleccionada);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "capcitacion modificada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

        this.listaCapacitacion = this.capacitacionBo.listCapacitacion();

        super.enableShowData();
        limpiar();
    }

    public void darDeBaja() {
        this.capacitacionSeleccionada.setEstado(false);
        this.capacitacionBo.update(this.capacitacionSeleccionada);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "capacitacion dada de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        super.enableShowData();
    }

    public void darDeAlta() {
        this.capacitacionSeleccionada.setEstado(true);
        this.capacitacionBo.update(this.capacitacionSeleccionada);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Capacitacion dada de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        super.enableShowData();
    }

    public void crear() {

        this.capacitacion.setTEmpleado(empleadoSelecionada);
        this.capacitacion.setTProyecto(proyectoSeleccionado);
        this.capacitacion.setEstado(true);
        this.capacitacionBo.create(capacitacion);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Capacitacion guardada correctamente", null);
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

        if (this.proyectoSeleccionado.getCodigoProyecto() == "") {
            this.msgProyecto = "Debe seleccionar un proyecto";
            this.estadoFormulario = false;
        } else {
            this.msgProyecto = "";
        }

        if (this.capacitacion.getDescripcion() == "") {
            this.msgDescripcion = "La descripción es requerida";
            this.estadoFormulario = false;
        } else {
            this.msgDescripcion = "";
        }

        if (this.capacitacion.getPrecio() == null || this.capacitacion.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
            this.msgPrecio = "El precio es requerido";
            this.estadoFormulario = false;

        } else {
            this.msgPrecio = "";
        }

        if (this.capacitacion.getBeneficiarios() == null || this.capacitacion.getBeneficiarios() <= 0) {
            this.msgBeneficiarios = "El numero de beneficiarios es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgBeneficiarios = "";
        }

        if (this.empleadoSelecionada.getDuiEmpleado() == "") {
            this.msgDui = "Debe de seleccionar un encargado";
            this.estadoFormulario = false;
        } else {
            this.msgDui = "";
        }
        if (this.capacitacion.getFecha() == null) {
            this.msgFecha = "La fecha es requerida";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }

    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;

        if (this.proyectoSeleccionado.getCodigoProyecto() == "") {
            this.msgProyecto = "Debe seleccionar un proyecto";
            this.estadoFormulario = false;
        } else {
            this.msgProyecto = "";
        }

        if (this.capacitacionSeleccionada.getDescripcion() == "") {
            this.msgDescripcion = "La descripción es requerida";
            this.estadoFormulario = false;
        } else {
            this.msgDescripcion = "";
        }

        if (this.capacitacionSeleccionada.getPrecio() == null || this.capacitacionSeleccionada.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
            this.msgPrecio = "El precio es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgPrecio = "";
        }

        if (this.capacitacionSeleccionada.getBeneficiarios() == null || this.capacitacionSeleccionada.getBeneficiarios() <= 0) {
            this.msgBeneficiarios = "El numero de beneficiarios es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgBeneficiarios = "";
        }

        if (this.empleadoSelecionada.getDuiEmpleado() == "") {
            this.msgDui = "Debe de seleccionar un encargado";
            this.estadoFormulario = false;
        } else {
            this.msgDui = "";
        }
        if (this.capacitacionSeleccionada.getFecha() == null) {
            this.msgFecha = "La fecha es requerida";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }

    }

    public void limpiar() {

        this.estadoFormulario = false;

        this.msgDui = "";
        this.msgBeneficiarios = "";
        this.msgPrecio = "";
        this.msgFecha = "";
        this.msgDescripcion = "";
        this.msgProyecto = "";

        this.proyectoSeleccionado = new TProyecto();
        this.empleadoSelecionada = new TEmpleado();
        this.capacitacion = new TCapacitacion();

        this.listaCapacitacion = this.capacitacionBo.listCapacitacion();

    }

    public void enableShowDataBean() {
        limpiar();
        super.enableShowData();

    }

    public void mostrarEmpleados() {

        this.listaEmpleados = new ArrayList<TEmpleado>();

        this.listaAreas = this.areaBo.listArea(1);

        this.listaEmpleadoAreas = this.empleadoAreaBo.listTEmpleadoArea(this.IdArea);

        for (int i = 0; i < this.listaEmpleadoAreas.size(); i++) {

            if (this.listaEmpleadoAreas.get(i).getTEmpleado().getEstadoEmpleado() == true) {
                this.listaEmpleados.add(this.empleadoBo.getTEmpleado(this.listaEmpleadoAreas.get(i).getTEmpleado().getIdEmpleado()));

            }

        }
    }

    public void mostrarProyectos() {

        this.listaEntidadProyecto = this.ientidadProyectoBo.listTEndidadProyectosAprovados(0);

        this.listaClienteProyecto = this.iclienteProyectoBo.listTClienteProyectoAprovados(0);

    }

    public void mostrarAsesorias() {

        this.listaCapacitacion = this.capacitacionBo.listCapacitacion();
        this.enableShowData();

    }

    public void seleccionProyecto(Integer opc) {

        switch (opc) {
            case 0:
                this.proyectoCooperativa = true;
                this.proyectoPersona = false;

                break;

            case 1:
                this.proyectoCooperativa = false;
                this.proyectoPersona = true;
                break;

            default:
                break;
        }

    }

}
