/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IAreaBo;
import com.sigaf.Ibo.ICargoBo;
import com.sigaf.Ibo.IDepartamentoBo;
import com.sigaf.Ibo.IEmpleadoAreaBo;
import com.sigaf.Ibo.IEmpleadoBo;
import com.sigaf.Ibo.IEmpleadoCargoBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IMunicipioBo;
import com.sigaf.Ibo.IMunicipioEmpleadoBo;
import com.sigaf.Ibo.IOcupacionBo;
import com.sigaf.bo.EmpleadoAreaBo;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TCargo;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEmpleadoArea;
import com.sigaf.pojo.TEmpleadoCargo;
import com.sigaf.pojo.TMunicipio;
import com.sigaf.pojo.TMunicipioEmpleado;
import com.sigaf.pojo.TOcupacion;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static javassist.CtMethod.ConstParameter.string;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import org.hibernate.HibernateException;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Genoves
 */
@Named(value = "empleadoBean")
@RequestScoped
@ManagedBean
public class EmpleadoBean extends Actividad {

    private IEmpleadoBo iempleadoBo;
    private IEntidadBo ientidadBo;
    private IEmpleadoCargoBo iempleadoCargoBo;
    private List<TEmpleado> listaEmpleado;
    private List<TOcupacion> listaOcupacionesAux;
    private TEmpleadoArea empleadoArea;
    private TEmpleado empleados;
    private TEmpleado empleadoSeleccionado;
    private Boolean estadoFormulario;
    private String msgNombre;
    private String msgApellido;
    private String msgDui;
    private String msgNit;
    private String msgSexo;
    private String msgTelefono;
    private String msgMovil;
    private String msgSalario;
    private String msgDireccion;
    private String msgCargo;
    private String msgFechaNacimiento;
    private String msgFechaIngreso;
    private String msgMunicipio;
    private String msgOcupacion;
    private String msgArea;
    private String msgCorreo;
    private String msgDuiRepetido;
    private Integer idAreaModificar;
    private Integer idCargoModificar;
    private Integer idArea;
    private IAreaBo areaBo;
    private ICargoBo icargoBo;
    private IOcupacionBo iocupacionBo;
    private IEmpleadoAreaBo iempleadoAreaBo;
    private boolean mostrarTabla;
    private Integer posicionArea;
    private TArea area;
    private TCargo cargo;
    private TOcupacion ocupacionAuxiliar;
    private String nombreOcupacion;
    private String nombreDepartamento;
    private String nombreMunicipio;
    private Integer idDepartamentoAuxiliar;
    private Integer idMunicipioAuxiliar;
    private TEmpleadoCargo empleadoCargo;
    private TEmpleadoCargo empleadoCargoSeleccionado;
    private ArrayList<TOcupacion> listaOcupacion;
    private List<TCargo> listaCargo;
    private List<TEmpleadoCargo> listaEmpleadoCargos;
    private List<TMunicipio> listaMunicipios;
    private IDepartamentoBo idepartamentoBo;
    private IMunicipioBo imunicipioBo;
    private IMunicipioEmpleadoBo imunicipioEmpleadoBo;
    private TMunicipioEmpleado municipioEmpleado;
    private TMunicipio municipio;
    private String direccion;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean showImagen;
    private Boolean showImagenModificar;
    private String fotoEmpleado;
    private Integer idCargo;
    private String duiCopia;
    private List<TCargo> listaCargoModificar;
    private List<TEmpleadoCargo> listaEmpleadoCargosModificar;
    private List<TEmpleadoCargo> listaEmpleadoCargosModificarCopia;
    private List<TArea> listaArea;
    private Integer idAreaEmpleado;
    private List<TArea> listaAreaFiltrada;
    private String correoCopia;
    private String msgCorreoRepetido;
    private Integer idEmpleadoArea;
    private Boolean emptyImagen;
    private String msgImagen;
    private List<TEmpleadoArea> listaEmpleadoArea;
    private List<TEmpleado> listaEmpleadosFinal;

    public List<TEmpleado> getListaEmpleadosFinal() {
        return listaEmpleadosFinal;
    }

    public void setListaEmpleadosFinal(List<TEmpleado> listaEmpleadosFinal) {
        this.listaEmpleadosFinal = listaEmpleadosFinal;
    }

    public List<TEmpleadoArea> getListaEmpleadoArea() {
        return listaEmpleadoArea;
    }

    public void setListaEmpleadoArea(List<TEmpleadoArea> listaEmpleadoArea) {
        this.listaEmpleadoArea = listaEmpleadoArea;
    }

    private List<TArea> listaEmpleados;

    public List<TArea> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<TArea> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public Boolean getEmptyImagen() {
        return emptyImagen = true;
    }

    public void setEmptyImagen(Boolean emptyImagen) {
        this.emptyImagen = emptyImagen;
    }

    public String getMsgImagen() {
        return msgImagen;
    }

    public void setMsgImagen(String msgImagen) {
        this.msgImagen = msgImagen;
    }

    public Integer getIdEmpleadoArea() {
        return idEmpleadoArea;
    }

    public void setIdEmpleadoArea(Integer idEmpleadoArea) {
        this.idEmpleadoArea = idEmpleadoArea;
    }

    public String getCorreoCopia() {
        return correoCopia;
    }

    public void setCorreoCopia(String correoCopia) {
        this.correoCopia = correoCopia;
    }

    public String getMsgCorreoRepetido() {
        return msgCorreoRepetido;
    }

    public void setMsgCorreoRepetido(String msgCorreoRepetido) {
        this.msgCorreoRepetido = msgCorreoRepetido;
    }

    public TEmpleadoArea getEmpleadoArea() {
        return empleadoArea;
    }

    public void setEmpleadoArea(TEmpleadoArea empleadoArea) {
        this.empleadoArea = empleadoArea;
    }

    public Integer getPosicionArea() {
        return posicionArea;
    }

    public void setPosicionArea(Integer posicionArea) {
        this.posicionArea = posicionArea;
    }

    public boolean isMostrarTabla() {
        return mostrarTabla;
    }

    public void setMostrarTabla(boolean mostrarTabla) {
        this.mostrarTabla = mostrarTabla;
    }

    public IEmpleadoAreaBo getIempleadoAreaBo() {
        return iempleadoAreaBo;
    }

    public void setIempleadoAreaBo(IEmpleadoAreaBo iempleadoAreaBo) {
        this.iempleadoAreaBo = iempleadoAreaBo;
    }

    public List<TArea> getListaAreaFiltrada() {
        return listaAreaFiltrada;
    }

    public void setListaAreaFiltrada(List<TArea> listaAreaFiltrada) {
        this.listaAreaFiltrada = listaAreaFiltrada;
    }

    public Integer getIdAreaEmpleado() {
        return idAreaEmpleado;
    }

    public void setIdAreaEmpleado(Integer idAreaEmpleado) {
        this.idAreaEmpleado = idAreaEmpleado;
    }

    public List<TArea> getListaArea() {
        return listaArea = this.listaArea = this.iempleadoBo.listTAreas(0, 1, 2);
    }

    public void setListaArea(List<TArea> listaArea) {
        this.listaArea = listaArea;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFotoEmpleado() {
        return fotoEmpleado;
    }

    public void setFotoEmpleado(String fotoEmpleado) {
        this.fotoEmpleado = fotoEmpleado;
    }

    public Boolean getShowImagenModificar() {
        return showImagenModificar;
    }

    public void setShowImagenModificar(Boolean showImagenModificar) {
        this.showImagenModificar = showImagenModificar;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getNombreOcupacion() {
        return nombreOcupacion;
    }

    public void setNombreOcupacion(String nombreOcupacion) {
        this.nombreOcupacion = nombreOcupacion;
    }

    public String getDuiCopia() {
        return duiCopia;
    }

    public void setDuiCopia(String duiCopia) {
        this.duiCopia = duiCopia;
    }

    public TEmpleadoCargo getEmpleadoCargoSeleccionado() {
        return empleadoCargoSeleccionado;
    }

    public void setEmpleadoCargoSeleccionado(TEmpleadoCargo empleadoCargoSeleccionado) {
        this.empleadoCargoSeleccionado = empleadoCargoSeleccionado;
    }

    public List<TEmpleadoCargo> getListaEmpleadoCargosModificarCopia() {
        return listaEmpleadoCargosModificarCopia;
    }

    public void setListaEmpleadoCargosModificarCopia(List<TEmpleadoCargo> listaEmpleadoCargosModificarCopia) {
        this.listaEmpleadoCargosModificarCopia = listaEmpleadoCargosModificarCopia;
    }

    public List<TEmpleadoCargo> getListaEmpleadoCargosModificar() {
        return listaEmpleadoCargosModificar;
    }

    public void setListaEmpleadoCargosModificar(List<TEmpleadoCargo> listaEmpleadoCargosModificar) {
        this.listaEmpleadoCargosModificar = listaEmpleadoCargosModificar;
    }

    public List<TCargo> getListaCargoModificar() {
        return listaCargoModificar;
    }

    public void setListaCargoModificar(List<TCargo> listaCargoModificar) {
        this.listaCargoModificar = listaCargoModificar;
    }

    public List<TCargo> getListaCargo() {
        return listaCargo = this.icargoBo.listCargo(1);
    }

    public void setListaCargo(List<TCargo> listaCargo) {
        this.listaCargo = listaCargo;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public List<TOcupacion> getListaOcupacionesAux() {
        return listaOcupacionesAux;
    }

    public void setListaOcupacionesAux(List<TOcupacion> listaOcupacionesAux) {
        this.listaOcupacionesAux = listaOcupacionesAux;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public TMunicipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(TMunicipio municipio) {
        this.municipio = municipio;
    }

    public TMunicipioEmpleado getMunicipioEmpleado() {
        return municipioEmpleado;
    }

    public void setMunicipioEmpleado(TMunicipioEmpleado municipioEmpleado) {
        this.municipioEmpleado = municipioEmpleado;
    }

    public Integer getIdMunicipioAuxiliar() {
        return idMunicipioAuxiliar;
    }

    public void setIdMunicipioAuxiliar(Integer idMunicipioAuxiliar) {
        this.idMunicipioAuxiliar = idMunicipioAuxiliar;
    }

    public Integer getIdDepartamentoAuxiliar() {
        return idDepartamentoAuxiliar;
    }

    public IMunicipioEmpleadoBo getImunicipioEmpleadoBo() {
        return imunicipioEmpleadoBo;
    }

    public void setImunicipioEmpleadoBo(IMunicipioEmpleadoBo imunicipioEmpleadoBo) {
        this.imunicipioEmpleadoBo = imunicipioEmpleadoBo;
    }

    public void setIdDepartamentoAuxiliar(Integer idDepartamentoAuxiliar) {
        this.idDepartamentoAuxiliar = idDepartamentoAuxiliar;
    }

    public List<TEmpleadoCargo> getListaEmpleadoCargos() {
        return listaEmpleadoCargos;
    }

    public void setListaEmpleadoCargos(List<TEmpleadoCargo> listaEmpleadoCargos) {
        this.listaEmpleadoCargos = listaEmpleadoCargos;
    }

    public ArrayList<TOcupacion> getListaOcupacion() {
        return listaOcupacion;
    }

    public void setListaOcupacion(ArrayList<TOcupacion> listaOcupacion) {
        this.listaOcupacion = listaOcupacion;
    }

    public boolean getShowImagen() {
        return showImagen;
    }

    public void setShowImagen(boolean showImagen) {
        this.showImagen = showImagen;
    }

    public TOcupacion getOcupacionAuxiliar() {
        return ocupacionAuxiliar;
    }

    public void setOcupacionAuxiliar(TOcupacion ocupacionAuxiliar) {
        this.ocupacionAuxiliar = ocupacionAuxiliar;
    }

    public Integer getIdCargoModificar() {
        return idCargoModificar;
    }

    public void setIdCargoModificar(Integer idCargoModificar) {
        this.idCargoModificar = idCargoModificar;
    }

    public Integer getIdAreaModificar() {
        return idAreaModificar;
    }

    public void setIdAreaModificar(Integer idAreaModificar) {
        this.idAreaModificar = idAreaModificar;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public IEmpleadoCargoBo getIempleadoCargoBo() {
        return iempleadoCargoBo;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public IEntidadBo getIentidadBo() {
        return ientidadBo;
    }

    public void setIentidadBo(IEntidadBo ientidadBo) {
        this.ientidadBo = ientidadBo;
    }

    public IAreaBo getAreaBo() {
        return areaBo;
    }

    public void setAreaBo(IAreaBo areaBo) {
        this.areaBo = areaBo;
    }

    public IOcupacionBo getIocupacionBo() {
        return iocupacionBo;
    }

    public void setIocupacionBo(IOcupacionBo iocupacionBo) {
        this.iocupacionBo = iocupacionBo;
    }

    public TArea getArea() {
        return area;
    }

    public void setArea(TArea area) {
        this.area = area;
    }

    public IEmpleadoBo getIempleadoBo() {
        return iempleadoBo;
    }

    public void setIempleadoBo(IEmpleadoBo iempleadoBo) {
        this.iempleadoBo = iempleadoBo;
    }

    public List<TEmpleado> getListaEmpleado() {

        mostrarDatosFiltrados();
        return listaEmpleado;
    }

    public void setListaEmpleado(List<TEmpleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
    }

    public TEmpleado getEmpleados() {
        return empleados;
    }

    public void setEmpleados(TEmpleado empleados) {
        this.empleados = empleados;
    }

    public TEmpleado getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(TEmpleado empleadoSeleccionado) {

        this.empleadoSeleccionado = empleadoSeleccionado;
        //cargarEmpleadosCargo(this.empleadoSeleccionado.getIdEmpleado());
        // this.idAreaModificar = this.empleadoSeleccionado.getTArea().getIdArea();
    }

    public Boolean getEstadoFormulario() {
        return estadoFormulario;

    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgApellido() {
        return msgApellido;
    }

    public void setMsgApellido(String msgApellido) {
        this.msgApellido = msgApellido;
    }

    public String getMsgDui() {
        return msgDui;
    }

    public void setMsgDui(String msgDui) {
        this.msgDui = msgDui;
    }

    public String getMsgNit() {
        return msgNit;
    }

    public void setMsgNit(String msgNit) {
        this.msgNit = msgNit;
    }

    public String getMsgSexo() {
        return msgSexo;
    }

    public void setMsgSexo(String msgSexo) {
        this.msgSexo = msgSexo;
    }

    public String getMsgTelefono() {
        return msgTelefono;
    }

    public void setMsgTelefono(String msgTelefono) {
        this.msgTelefono = msgTelefono;
    }

    public String getMsgMovil() {
        return msgMovil;
    }

    public void setMsgMovil(String msgMovil) {
        this.msgMovil = msgMovil;
    }

    public String getMsgSalario() {
        return msgSalario;
    }

///Validar
    public void setMsgSalario(String msgSalario) {
        this.msgSalario = msgSalario;
    }

    public String getMsgDireccion() {
        return msgDireccion;
    }

    public void setMsgDireccion(String msgDireccion) {
        this.msgDireccion = msgDireccion;
    }

    public String getMsgCargo() {
        return msgCargo;
    }

    public void setMsgCargo(String msgCargo) {
        this.msgCargo = msgCargo;
    }

    public String getMsgFechaNacimiento() {
        return msgFechaNacimiento;
    }

    public void setMsgFechaNacimiento(String msgFechaNacimiento) {
        this.msgFechaNacimiento = msgFechaNacimiento;
    }

    public String getMsgFechaIngreso() {
        return msgFechaIngreso;
    }

    public void setMsgFechaIngreso(String msgFechaIngreso) {
        this.msgFechaIngreso = msgFechaIngreso;
    }

    public String getMsgMunicipio() {
        return msgMunicipio;
    }

    public void setMsgMunicipio(String msgMunicipio) {
        this.msgMunicipio = msgMunicipio;
    }

    public String getMsgOcupacion() {
        return msgOcupacion;
    }

    public void setMsgOcupacion(String msgOcupacion) {
        this.msgOcupacion = msgOcupacion;
    }

    public String getMsgArea() {
        return msgArea;
    }

    public void setMsgArea(String msgArea) {
        this.msgArea = msgArea;
    }

    public String getMsgDuiRepetido() {
        return msgDuiRepetido;
    }

    public void setMsgDuiRepetido(String msgDuiRepetido) {
        this.msgDuiRepetido = msgDuiRepetido;
    }

    public String getMsgCorreo() {
        return msgCorreo;
    }

    public void setMsgCorreo(String msgCorreo) {
        this.msgCorreo = msgCorreo;
    }

    public IDepartamentoBo getIdepartamentoBo() {
        return idepartamentoBo;
    }

    public void setIdepartamentoBo(IDepartamentoBo idepartamentoBo) {
        this.idepartamentoBo = idepartamentoBo;
    }

    public IMunicipioBo getImunicipioBo() {
        return imunicipioBo;
    }

    public void setImunicipioBo(IMunicipioBo imunicipioBo) {
        this.imunicipioBo = imunicipioBo;
    }

    public TCargo getCargo() {
        return cargo;
    }

    public void setCargo(TCargo cargo) {
        this.cargo = cargo;
    }

    public TEmpleadoCargo getEmpleadoCargo() {
        return empleadoCargo;
    }

    public void setEmpleadoCargo(TEmpleadoCargo empleadoCargo) {
        this.empleadoCargo = empleadoCargo;
    }

    public ICargoBo getIcargoBo() {
        return icargoBo;
    }

    public void setIcargoBo(ICargoBo icargoBo) {
        this.icargoBo = icargoBo;
    }

    public void setIempleadoCargoBo(IEmpleadoCargoBo iempleadoCargoBo) {
        this.iempleadoCargoBo = iempleadoCargoBo;
    }

    public void Init() {

        float salario = 0;
        this.setEstadoFormulario(false);
        this.setShowImagen(true);
        this.empleadoSeleccionado = new TEmpleado();
        this.empleadoCargo = new TEmpleadoCargo();
        this.ocupacionAuxiliar = new TOcupacion();
        listaOcupacion = new ArrayList<>();
        this.empleados = new TEmpleado();
        this.area = new TArea();
        this.municipio = new TMunicipio();
        this.showImagenModificar = false;
        this.municipioEmpleado = new TMunicipioEmpleado();
        this.municipioEmpleado.setTMunicipio(new TMunicipio());
        this.listaOcupacionesAux = new ArrayList<>();
        this.listaOcupacion = new ArrayList<>();
        this.cargo = new TCargo();
        this.fechaFin = null;
        this.empleados.setSalarioEmpleado(BigDecimal.ZERO);
        this.listaEmpleadoCargos = new ArrayList<>();
        this.listaCargo = this.icargoBo.listCargo(1);
        this.listaEmpleadoCargosModificar = new ArrayList<>();
        this.listaEmpleadoCargosModificarCopia = new ArrayList<>();
        this.empleados.setSexoEmpleado("Másculino");
        this.empleadoArea = new TEmpleadoArea();
        this.emptyImagen = true;
        //mostrarDatosFiltrados();
        super.enableShowData();
    }

    public void create() {

        this.empleados.setEstadoEmpleado(Boolean.TRUE);

        this.area.setIdArea(this.idArea);
        this.iempleadoBo.create(this.empleados);

        this.empleadoArea.setTEmpleado(empleados);
        this.empleadoArea.setTArea(new TArea(this.idArea));

        this.iempleadoAreaBo.create(this.empleadoArea);

        for (int i = 0; i < this.listaEmpleadoCargos.size(); i++) {
            TCargo auxCargo = new TCargo();
            TEmpleadoCargo auxEmpleadoCargo = new TEmpleadoCargo();

            auxCargo.setIdCargo(this.listaEmpleadoCargos.get(i).getTCargo().getIdCargo());
            auxEmpleadoCargo.setTEmpleado(empleados);
            auxEmpleadoCargo.setTCargo(auxCargo);
            auxEmpleadoCargo.setFechainicioEmpleadoCargo(new Date());
            auxEmpleadoCargo.setEstadoEmpleadoCargo(true);
            iempleadoCargoBo.create(auxEmpleadoCargo);
        }
        for (int j = 0; j < this.listaOcupacion.size(); j++) {
            TOcupacion auxOcupacion = new TOcupacion();

            auxOcupacion.setTEmpleado(empleados);
            auxOcupacion.setEstadoOcupacion(true);
            auxOcupacion.setNombreOcupacion(listaOcupacion.get(j).getNombreOcupacion());
            iocupacionBo.create(auxOcupacion);

        }
        this.municipioEmpleado.setTEmpleado(empleados);
        this.municipioEmpleado.setTipodireccionMunicipioEmpleado(0);//para direccion de residencia
        this.municipio.setIdMunicipio(this.idMunicipioAuxiliar);
        this.municipioEmpleado.setDireccionEmpleado(direccion);
        this.municipioEmpleado.setTMunicipio(municipio);
        this.imunicipioEmpleadoBo.create(municipioEmpleado);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Registro insertado correctamente"));
        limpiar(1);
        mostrarEmpleadosFiltrados();
    }

    public void darDeBaja() {
        this.empleadoSeleccionado.setEstadoEmpleado(false);
        try {
            iempleadoBo.update(empleadoSeleccionado);

            super.enableShowData();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro dado de baja correctamente"));
        } catch (HibernateException he) {

        }

    }

    public void darDeAltaOcupacion(int index) {
        this.listaOcupacion.get(index).setEstadoOcupacion(false);

    }

    public void darDeBajaOcupacion(int index) {
        this.listaOcupacion.get(index).setEstadoOcupacion(true);
    }

    public void darDeAlta() {
        this.empleadoSeleccionado.setEstadoEmpleado(Boolean.TRUE);
        try {
            iempleadoBo.update(this.empleadoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro dado de alta correctamente"));
        } catch (HibernateException he) {

        }

        super.enableShowData();

    }

    public void darDeAltaCargo(int id) {

        this.listaEmpleadoCargosModificar.get(id).setFechafinEmpleadoCargo(null);

    }

    public void validarFormulario() {
        this.estadoFormulario = true;
        if (this.empleados.getNombreEmpleado().length() < 3) {
            this.msgNombre = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        if (this.empleados.getApellidoEmpleado().length() < 4) {
            this.msgApellido = "El apellido debe contener como mínimo 4 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgApellido = "";
        }
        if (this.empleados.getDuiEmpleado().length() < 10) {
            this.msgDui = "El DUI debe contener como mínimo 10 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgDui = "";
        }
        if (this.empleados.getNitEmpleado().length() < 15) {
            this.msgNit = "El NIT debe contener como minimo 15 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNit = "";
        }

        if (this.getDireccion().length() < 10) {
            this.msgDireccion = "La dirección debe contener como mínimo 10 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgDireccion = "";
        }
        if (this.empleados.getSalarioEmpleado().compareTo(BigDecimal.ZERO) < 0) {
            this.msgSalario = "Debe introducir un valor";
            this.estadoFormulario = false;
        } else {
            this.msgSalario = "";
        }
        if (this.empleados.getTelefonoEmpleado().length() < 9) {
            this.msgTelefono = "El teléfono debe contener como mínimo 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgTelefono = "";
        }

        if (this.empleados.getCorreoEmpleado().length() < 10) {
            this.msgCorreo = "El correo debe contener como mínimo 10 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgCorreo = "";
        }
        if (this.idMunicipioAuxiliar == 0) {
            this.msgMunicipio = "Seleccione un municipio de residencia";
            this.estadoFormulario = false;
        } else {
            this.msgMunicipio = "";
        }
        if (this.listaEmpleadoCargos.size() == 0) {
            this.msgCargo = "Seleccione al menos un cargo";
            this.estadoFormulario = false;
        } else {
            this.msgCargo = "";
        }
        if (this.listaOcupacion.size() == 0) {
            this.msgOcupacion = "Seleccione al menos una ocupación";
            this.estadoFormulario = false;
        } else {
            this.msgOcupacion = "";
        }
        if (this.empleados.getFechanacimientoEmpleado() == null) {
            this.msgFechaNacimiento = "Seleccione la fecha de nacimieto";
            this.estadoFormulario = false;
        } else {
            this.msgFechaNacimiento = "";
        }
        if (this.empleados.getFechaingresoEmpleado() == null) {
            this.msgFechaIngreso = "Seleccione la fecha de ingreso del empleado";
            this.estadoFormulario = false;
        } else {
            this.msgFechaIngreso = "";
        }
        if (this.empleados.getCelularEmpleado().length() < 9) {
            this.msgMovil = "El móvil debe contener como mínimo 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgMovil = "";
        }
        if (this.iempleadoBo.getDUiTEmpleado(empleados.getDuiEmpleado()) == true) {
            this.msgDuiRepetido = "Este número de DUI ya está registrado";
            this.estadoFormulario = false;
        } else {
            this.msgDuiRepetido = "";
        }
        if (this.iempleadoAreaBo.correoRepetido(empleados.getCorreoEmpleado(), 1) == true) {
            this.msgCorreoRepetido = "Este correo ya está registrado";
            this.estadoFormulario = false;
        } else {
            this.msgCorreoRepetido = "";
        }
        if (this.emptyImagen == true) {
            this.msgImagen = "no has seleccionado una imágen o no ha sigo cargada";
            this.estadoFormulario = false;
        } else {
            this.msgImagen = "";
        }
    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;
        if (this.empleadoSeleccionado.getNombreEmpleado().length() < 3) {
            this.msgNombre = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        if (this.empleadoSeleccionado.getApellidoEmpleado().length() < 4) {
            this.msgApellido = "El apellido debe contener como mínimo 4 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgApellido = "";
        }
        if (this.empleadoSeleccionado.getCorreoEmpleado().length() < 10) {
            this.msgCorreo = "El correo debe contener como mínimo 10 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgCorreo = "";
        }
        if (this.empleadoSeleccionado.getDuiEmpleado().length() < 10) {
            this.msgDui = "El DUI debe contener como mínimo 10 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgDui = "";
        }
        if (this.empleadoSeleccionado.getNitEmpleado().length() < 15) {
            this.msgNit = "El NIT debe contener como minimo 15 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNit = "";
        }

        if (this.getDireccion().length() < 10) {
            this.msgDireccion = "La dirección debe contener como mínimo 10 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgDireccion = "";
        }
        if (this.empleadoSeleccionado.getSalarioEmpleado().compareTo(BigDecimal.ZERO) < 0) {
            this.msgSalario = "Debe ingresar un valor";
            this.estadoFormulario = false;
        } else {
            this.msgSalario = "";
        }
        if (this.empleadoSeleccionado.getTelefonoEmpleado().length() < 9) {
            this.msgTelefono = "El teléfono debe contener como mínimo 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgTelefono = "";
        }

        if (this.idMunicipioAuxiliar == 0) {
            this.msgMunicipio = "Seleccione un municipio de residencia";
            this.estadoFormulario = false;
        } else {
            this.msgMunicipio = "";
        }
        if (this.listaEmpleadoCargosModificar.size() == 0) {
            this.msgCargo = "Seleccione al menos un cargo";
            this.estadoFormulario = false;
        } else {
            this.msgCargo = "";
        }
        if (this.listaOcupacion.size() == 0) {
            this.msgOcupacion = "Seleccione al menos una ocupación";
            this.estadoFormulario = false;
        } else {
            this.msgOcupacion = "";
        }
        if (this.empleadoSeleccionado.getFechanacimientoEmpleado() == null) {
            this.msgFechaNacimiento = "Seleccione la fecha de nacimieto";
            this.estadoFormulario = false;
        } else {
            this.msgFechaNacimiento = "";
        }
        if (this.empleadoSeleccionado.getFechaingresoEmpleado() == null) {
            this.msgFechaIngreso = "Seleccione la fecha de ingreso del empleado";
            this.estadoFormulario = false;
        } else {
            this.msgFechaIngreso = "";
        }
        if (this.empleadoSeleccionado.getCelularEmpleado().length() < 9) {
            this.msgMovil = "El móvil debe contener como mínimo 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgMovil = "";
        }
        if (this.empleadoSeleccionado.getDuiEmpleado().equals(this.duiCopia)) {
        } else if (this.iempleadoBo.getDUiTEmpleado(empleadoSeleccionado.getDuiEmpleado()) == true) {
            this.msgDuiRepetido = "Este número de DUI ya está registrado";
            this.estadoFormulario = false;
        } else {
            this.msgDuiRepetido = "";
        }
        if (this.empleadoSeleccionado.getCorreoEmpleado().equals(this.correoCopia)) {
        } else if (this.iempleadoAreaBo.correoRepetido(empleadoSeleccionado.getCorreoEmpleado(), 1) == true) {
            this.msgCorreoRepetido = "Este correo ya está registrado";
            this.estadoFormulario = false;
        } else {
            this.msgCorreoRepetido = "";
        }
        if (this.emptyImagen == true) {
            this.msgImagen = "no has seleccionado una imágen o no ha sigo cargada";
            this.estadoFormulario = false;
        } else {
            this.msgImagen = "";
        }

    }

    public void limpiar(int i) {
        this.setEstadoFormulario(false);

        //this.listaCargo = new ArrayList<>();
        this.empleados = new TEmpleado();
        this.municipioEmpleado = new TMunicipioEmpleado();
        this.listaOcupacion.clear();
        this.listaOcupacionesAux.clear();
        this.listaEmpleadoCargosModificar.clear();
        this.listaEmpleadoCargos.clear();

        this.empleados.setSalarioEmpleado(BigDecimal.ZERO);
        this.listaCargoModificar = new ArrayList<>();
        this.showImagen = true;
        this.showImagenModificar = false;
        this.fotoEmpleado = "";
        this.direccion = "";
        this.msgApellido = "";
        this.msgArea = "";
        this.msgCargo = "";
        this.msgDireccion = "";
        this.msgDui = "";
        this.msgFechaIngreso = "";
        this.msgFechaNacimiento = "";
        this.msgMovil = "";
        this.msgMunicipio = "";
        this.msgNit = "";
        this.msgNombre = "";
        this.msgOcupacion = "";
        this.msgSalario = "";
        this.msgTelefono = "";
        this.msgDuiRepetido = "";
        this.msgCorreoRepetido = "";
        this.msgCorreo = "";
        this.empleados.setSexoEmpleado("Másculino");
        this.idDepartamentoAuxiliar = 0;
        this.emptyImagen = true;
        this.msgImagen = "";
        this.fechaFin = null;
        if (i == 0) {

            this.enableShowData();
        }

    }

    public void guardarImagen(FileUploadEvent event) throws IOException {

        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.empleados.setFotoEmpleado(guardar);
        this.showImagen = false;
        this.emptyImagen = false;

    }

    public void guardarImagenModificar(FileUploadEvent event) throws IOException {

        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.fotoEmpleado = guardar;
        this.emptyImagen = false;
        this.showImagen = true;
        this.showImagenModificar = true;
        this.emptyImagen = false;

    }

    public void cargarActualizar() {

        this.emptyImagen = false;

        ArrayList<TCargo> borrar = new ArrayList();
        this.duiCopia = this.empleadoSeleccionado.getDuiEmpleado();
        this.listaOcupacionesAux = this.iocupacionBo.listTOcupacion(this.empleadoSeleccionado.getIdEmpleado());
        for (int i = 0; i < this.listaOcupacionesAux.size(); i++) {
            this.listaOcupacion.add(listaOcupacionesAux.get(i));

        }
        this.municipioEmpleado = this.imunicipioEmpleadoBo.getTMunicipioEmpleado(this.empleadoSeleccionado.getIdEmpleado());
        this.idMunicipioAuxiliar = this.municipioEmpleado.getTMunicipio().getIdMunicipio();
        this.direccion = this.municipioEmpleado.getDireccionEmpleado();
        this.idDepartamentoAuxiliar = this.imunicipioBo.getMunicipio(idMunicipioAuxiliar).getTDepartamento().getIdDepartamento();
        this.listaMunicipios = this.imunicipioBo.listCargarMunicipios(idDepartamentoAuxiliar);
        this.listaCargoModificar = icargoBo.listCargo(1);
        this.nombreDepartamento = listaMunicipios.get(0).getTDepartamento().getNombreDepartamento();
        this.nombreMunicipio = listaMunicipios.get(0).getNombreMunicipio();
        this.listaEmpleadoCargosModificar = this.iempleadoCargoBo.listTEmpleadoCargo(this.empleadoSeleccionado.getIdEmpleado());
        this.listaEmpleadoCargosModificarCopia = this.iempleadoCargoBo.listTEmpleadoCargo(this.empleadoSeleccionado.getIdEmpleado());
        this.idAreaModificar = this.iempleadoAreaBo.getTEmpleadoArea(1, this.empleadoSeleccionado.getIdEmpleado()).getTArea().getIdArea();
        this.idEmpleadoArea = this.iempleadoAreaBo.getTEmpleadoArea(1, this.empleadoSeleccionado.getIdEmpleado()).getIdEmpleadoArea();
        for (int i = 0; i < this.listaCargoModificar.size(); i++) {

            for (int y = 0; y < this.listaEmpleadoCargosModificar.size(); y++) {
                if (this.listaCargoModificar.get(i).getIdCargo() == this.listaEmpleadoCargosModificar.get(y).getTCargo().getIdCargo()) {
                    borrar.add(this.listaCargoModificar.get(i));
                }
            }
        }
        for (int y = 0; y < borrar.size(); y++) {

            this.listaCargoModificar.remove(borrar.get(y));

        }

    }

    public void modificar() {

        if (this.showImagenModificar) {
            this.empleadoSeleccionado.setFotoEmpleado(this.fotoEmpleado);
        }

        try {

            this.empleadoArea.setIdEmpleadoArea(this.idEmpleadoArea);
            this.area.setIdArea(this.idAreaModificar);
            this.empleadoArea.setTArea(this.area);
            this.empleadoArea.setTEmpleado(this.empleadoSeleccionado);
            this.iempleadoAreaBo.update(empleadoArea);
            this.iempleadoBo.update(empleadoSeleccionado);
            this.municipioEmpleado.setTMunicipio(new TMunicipio(this.idMunicipioAuxiliar));
            this.municipioEmpleado.setTEmpleado(this.empleadoSeleccionado);

            this.imunicipioEmpleadoBo.update(municipioEmpleado);

            for (int i = 0; i < this.listaEmpleadoCargosModificarCopia.size(); i++) {

                this.listaEmpleadoCargosModificar.get(i).setFechafinEmpleadoCargo(this.fechaFin);
                this.iempleadoCargoBo.update(listaEmpleadoCargosModificar.get(i));

            }

            for (int i = this.listaEmpleadoCargosModificarCopia.size(); i < this.listaEmpleadoCargosModificar.size(); i++) {

                this.listaEmpleadoCargosModificar.get(i).setEstadoEmpleadoCargo(true);

                this.iempleadoCargoBo.create(listaEmpleadoCargosModificar.get(i));

            }
            for (int k = 0; k < this.listaOcupacionesAux.size(); k++) {

                this.listaOcupacion.get(k).setTEmpleado(empleadoSeleccionado);
                this.iocupacionBo.update(listaOcupacion.get(k));

            }
            for (int k = listaOcupacionesAux.size(); k < this.listaOcupacion.size(); k++) {

                this.listaOcupacion.get(k).setTEmpleado(empleadoSeleccionado);
                this.listaOcupacion.get(k).setEstadoOcupacion(true);
                this.iocupacionBo.create(listaOcupacion.get(k));

            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro modificado correctamente"));
            limpiar(0);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Error al modificar al empleado" + e.getMessage()));
        }

    }

    public void enableShowImagen() {
        this.emptyImagen = true;
        this.setShowImagen(!this.getShowImagen());

    }

    public boolean isShowImagen() {
        return showImagen;
    }

    public void agregarOcupaciones() {

        if (this.nombreOcupacion != "") {
            this.ocupacionAuxiliar.setNombreOcupacion(this.nombreOcupacion);
            this.listaOcupacion.add(ocupacionAuxiliar);
            this.ocupacionAuxiliar = new TOcupacion();
            this.nombreOcupacion = "";
        }

    }

    public void eliminarDeLista(int index) {

        listaOcupacion.remove(index);

    }

    public void cargarEmpleadosCargo(int opc) {

        int posicion = 0;
        if (opc == 0 && this.idCargo != 0 && this.fechaInicio != null) {
            this.empleadoCargo.setFechainicioEmpleadoCargo(fechaInicio);
            for (int i = 0; i < this.listaCargo.size(); i++) {

                if (this.idCargo == this.listaCargo.get(i).getIdCargo()) {

                    cargo.setNombreCargo(this.listaCargo.get(i).getNombreCargo());
                    this.empleadoCargo.setTCargo(cargo);

                    this.empleadoCargo.getTCargo().setIdCargo(this.listaCargo.get(i).getIdCargo());

                    this.listaEmpleadoCargos.add(this.empleadoCargo);
                    posicion = i;

                }

            }
            this.listaCargo.remove(posicion);

        } else if (opc == 1 && this.idCargo != 0 && this.fechaInicio != null) {

            for (int i = 0; i < this.listaCargoModificar.size(); i++) {

                if (this.idCargo == this.listaCargoModificar.get(i).getIdCargo()) {
                    //System.out.println("id: " + this.listaCargo.get(i).getIdCargo());

                    //  cargo.setNombreCargo(this.listaCargoModificar.get(i).getNombreCargo());
                    this.empleadoCargo.setTCargo(this.listaCargoModificar.get(i));
                    this.empleadoCargo.setFechainicioEmpleadoCargo(fechaInicio);
                    this.empleadoCargo.setTEmpleado(this.empleadoSeleccionado);
                    this.empleadoCargo.getTCargo().setIdCargo(this.listaCargoModificar.get(i).getIdCargo());

                    this.listaEmpleadoCargosModificar.add(this.empleadoCargo);
                    posicion = i;

                }
            }
            this.listaCargoModificar.remove(posicion);

        }

        empleadoCargo = new TEmpleadoCargo();

        cargo = new TCargo();

    }

    public void eliminarCargo(int index, int opc) {

        if (opc == 0) {
            this.listaCargo.add(this.listaEmpleadoCargos.get(index).getTCargo());
            this.listaEmpleadoCargos.remove(index);
            empleadoCargo = new TEmpleadoCargo();
            cargo = new TCargo();
        } else {

            this.listaCargoModificar.add(this.listaEmpleadoCargosModificar.get(index).getTCargo());
            this.listaEmpleadoCargosModificar.remove(index);

        }

    }

    public List<TMunicipio> getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List<TMunicipio> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public void cargarMunicipios(int id) {

        this.listaMunicipios = this.imunicipioBo.listCargarMunicipios(id);

    }

    public void mostrarDatosFiltrados() {

        this.listaArea = this.iempleadoBo.listTAreas(0, 1, 2);

    }

    public void mostrarEmpleadosFiltrados() {

        this.listaEmpleadosFinal = new ArrayList<>();

     

            this.listaEmpleadoArea = this.iempleadoAreaBo.listTEmpleadoArea(this.idArea);

            for (int y = 0; y < listaEmpleadoArea.size(); y++) {

                this.listaEmpleadosFinal.add(listaEmpleadoArea.get(y).getTEmpleado());

            }

        this.mostrarTabla = true;
    }

    public void generarEmpleadosFundacion() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> parametros = new HashMap();

        parametros.put("id_entidad", 1);
        parametros.put("idEmpleado", this.empleadoSeleccionado.getIdEmpleado());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReporteEmpleadoIndividual.jasper"));

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

    public void generarEmpleadosFundacionPDF() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        Map<String, Object> parametros = new HashMap();

        parametros.put("id_entidad", 1);
        parametros.put("idEmpleado", this.empleadoSeleccionado.getIdEmpleado());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReporteEmpleadoIndividual.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ListadoEmpleadoIndividual.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

}
