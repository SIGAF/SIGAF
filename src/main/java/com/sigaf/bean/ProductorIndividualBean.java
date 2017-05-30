/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IProductorGrupalBo;
import com.sigaf.Ibo.IProductorIndividualBo;
import com.sigaf.pojo.TProductorGrupal;
import com.sigaf.pojo.TProductorIndividual;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@Named(value = "productorIndividualBean")
@RequestScoped
@ManagedBean
public class ProductorIndividualBean extends Actividad {

    private TProductorIndividual productorIndividual;
    private TProductorIndividual ProductorIndividualSeleccionado;
    private TProductorIndividual ProductorIndividualSeleccionadoModificar;

    public TProductorIndividual getProductorIndividualSeleccionadoModificar() {
        return ProductorIndividualSeleccionadoModificar;
    }

    public void setProductorIndividualSeleccionadoModificar(TProductorIndividual ProductorIndividualSeleccionadoModificar) {
        this.ProductorIndividualSeleccionadoModificar = ProductorIndividualSeleccionadoModificar;
    }
    private List<TProductorIndividual> listaProductorIndividual;
    private List<TProductorGrupal> listaProductorGrupal;
    private TProductorGrupal productorGrupalSeleccionado;
    private Boolean representante;

    public Boolean getRepresentante() {
        return representante;
    }

    public void setRepresentante(Boolean representante) {
        this.representante = representante;
    }

    public TProductorGrupal getProductorGrupalSeleccionado() {
        return productorGrupalSeleccionado;
    }

    public void setProductorGrupalSeleccionado(TProductorGrupal productorGrupalSeleccionado) {
        this.productorGrupalSeleccionado = productorGrupalSeleccionado;
    }

    public List<TProductorGrupal> getListaProductorGrupal() {
        return listaProductorGrupal;
    }

    public void setListaProductorGrupal(List<TProductorGrupal> listaProductorGrupal) {
        this.listaProductorGrupal = listaProductorGrupal;
    }
    private Date date = new Date();

    private IProductorIndividualBo productorIndividualBo;
    private IProductorGrupalBo productorGrupalBo;

    public IProductorGrupalBo getProductorGrupalBo() {
        return productorGrupalBo;
    }

    public void setProductorGrupalBo(IProductorGrupalBo productorGrupalBo) {
        this.productorGrupalBo = productorGrupalBo;
    }
    private String edad;

    //propiedades de validacion
    private String msgNombre;
    private String msgApellidos;
    private String msgFecha;
    private String msgDui;
    private String msgNit;
    private String msgEstadoFamiliar;
    private String msgHijosvarones;
    private String msgHijosninas;
    private String msgInstitucion;
    private String msgEstudio;
    private String msgGrupo;
    private Boolean habilitarGrupo;

    public Boolean getHabilitarGrupo() {
        return habilitarGrupo;
    }

    public void setHabilitarGrupo(Boolean habilitarGrupo) {
        this.habilitarGrupo = habilitarGrupo;
    }

    public String getMsgGrupo() {
        return msgGrupo;
    }

    public void setMsgGrupo(String msgGrupo) {
        this.msgGrupo = msgGrupo;
    }

    private boolean experienciaProductorIndividua;

    public boolean isExperienciaProductorIndividua() {
        return experienciaProductorIndividua;
    }

    public void setExperienciaProductorIndividua(boolean experienciaProductorIndividua) {
        this.experienciaProductorIndividua = experienciaProductorIndividua;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgApellidos() {
        return msgApellidos;
    }

    public void setMsgApellidos(String msgApellidos) {
        this.msgApellidos = msgApellidos;
    }

    public String getMsgFecha() {
        return msgFecha;
    }

    public void setMsgFecha(String msgFecha) {
        this.msgFecha = msgFecha;
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

    public String getMsgEstadoFamiliar() {
        return msgEstadoFamiliar;
    }

    public void setMsgEstadoFamiliar(String msgEstadoFamiliar) {
        this.msgEstadoFamiliar = msgEstadoFamiliar;
    }

    public String getMsgHijosvarones() {
        return msgHijosvarones;
    }

    public void setMsgHijosvarones(String msgHijosvarones) {
        this.msgHijosvarones = msgHijosvarones;
    }

    public String getMsgHijosninas() {
        return msgHijosninas;
    }

    public void setMsgHijosninas(String msgHijosninas) {
        this.msgHijosninas = msgHijosninas;
    }

    public String getMsgInstitucion() {
        return msgInstitucion;
    }

    public void setMsgInstitucion(String msgInstitucion) {
        this.msgInstitucion = msgInstitucion;
    }

    public String getMsgEstudio() {
        return msgEstudio;
    }

    public void setMsgEstudio(String msgEstudio) {
        this.msgEstudio = msgEstudio;
    }

    public String getMsgActividades() {
        return msgActividades;
    }

    public void setMsgActividades(String msgActividades) {
        this.msgActividades = msgActividades;
    }

    public String getMsgCorreo() {
        return msgCorreo;
    }

    public void setMsgCorreo(String msgCorreo) {
        this.msgCorreo = msgCorreo;
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

    public String getMsgDireccion() {
        return msgDireccion;
    }

    public void setMsgDireccion(String msgDireccion) {
        this.msgDireccion = msgDireccion;
    }

    public boolean isEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }
    private String msgActividades;

    private String msgCorreo;
    private String msgTelefono;
    private String msgMovil;
    private String msgDireccion;

    private boolean estadoFormulario;

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public TProductorIndividual getProductorIndividual() {
        return productorIndividual;
    }

    public void setProductorIndividual(TProductorIndividual productorIndividual) {
        this.productorIndividual = productorIndividual;
    }

    public TProductorIndividual getProductorIndividualSeleccionado() {
        return ProductorIndividualSeleccionado;
    }

    public void setProductorIndividualSeleccionado(TProductorIndividual ProductorIndividualSeleccionado) {
        this.ProductorIndividualSeleccionado = ProductorIndividualSeleccionado;
    }

    public List<TProductorIndividual> getListaProductorIndividual() {
        this.listaProductorIndividual = productorIndividualBo.listProductorIndividual();
        return listaProductorIndividual;
    }

    public void setListaProductorIndividual(List<TProductorIndividual> listaProductorIndividual) {
        this.listaProductorIndividual = listaProductorIndividual;
    }

    public IProductorIndividualBo getProductorIndividualBo() {
        return productorIndividualBo;
    }

    public void setProductorIndividualBo(IProductorIndividualBo productorIndividualBo) {
        this.productorIndividualBo = productorIndividualBo;
    }

    public void init() {
        this.productorIndividual = new TProductorIndividual();
        this.productorIndividual.setTProductorGrupal(new TProductorGrupal());
        this.productorIndividual.setSexoProductorIndividual("Masculino");
        this.productorIndividual.setRepresentanteGrupal(false);
        this.productorGrupalSeleccionado = new TProductorGrupal();
        this.experienciaProductorIndividua = false;
        this.habilitarGrupo = false;
        this.habilitarRepresentante=false;
        super.enableShowData();
    }

    public void modificar() {

        TProductorGrupal productorGrupal;

        productorGrupal = this.productorGrupalBo.getProductorGrupal(1);

       
        if (this.ProductorIndividualSeleccionado.getRepresentanteGrupal() == true) {

            this.ProductorIndividualSeleccionadoModificar = this.productorIndividualBo.getProdcutorIndividualRepre();
            
            
            if (this.ProductorIndividualSeleccionadoModificar != null) {
                this.ProductorIndividualSeleccionadoModificar.setRepresentanteGrupal(false);
                this.productorIndividualBo.update(this.ProductorIndividualSeleccionadoModificar);

            }

        }
        
        if(this.habilitarGrupo==true){
            
             this.ProductorIndividualSeleccionado.setTProductorGrupal(this.productorGrupalSeleccionado);
            
            
        }else{
            this.ProductorIndividualSeleccionado.setTProductorGrupal(productorGrupal);
            
        }

        productorIndividualBo.update(this.ProductorIndividualSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Productor individual modificado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        limpiar();
        enableShowData();
    }

    public void crear() {

        this.productorIndividual.setEstadoProductorIndividual(true);

        TProductorGrupal productorGrupal;

        productorGrupal = this.productorGrupalBo.getProductorGrupal(1);

        if (this.habilitarGrupo == true) {

            this.productorIndividual.setTProductorGrupal(productorGrupal);

        } else {

            this.productorIndividual.setTProductorGrupal(productorGrupalSeleccionado);
        }

        if (this.productorIndividual.getRepresentanteGrupal() == true) {

            this.ProductorIndividualSeleccionado = this.productorIndividualBo.getProdcutorIndividualRepre();
            if (this.ProductorIndividualSeleccionado != null) {
                this.ProductorIndividualSeleccionado.setRepresentanteGrupal(false);
                this.productorIndividualBo.update(this.ProductorIndividualSeleccionado);

            }

        }

        productorIndividualBo.create(this.productorIndividual);
        // FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro guardado", null);
        //FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.productorIndividual = new TProductorIndividual();
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Productor individual guardado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        limpiar();
    }

    public void darDeBaja() {

        if (!this.ProductorIndividualSeleccionado.getEstadoProductorIndividual()) {
            // FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede dar de baja", null);
            //FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {

            this.ProductorIndividualSeleccionado.setEstadoProductorIndividual(Boolean.FALSE);
            productorIndividualBo.update(this.ProductorIndividualSeleccionado);

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Productor dado de baja correctamente", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            enableShowData();
        }

    }

    public void darDeAlta() {
        if (this.ProductorIndividualSeleccionado.getEstadoProductorIndividual()) {
            //FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede dar de alta", null);
            //FacesContext.getCurrentInstance().addMessage(null, mensaje);
        } else {

            this.ProductorIndividualSeleccionado.setEstadoProductorIndividual(Boolean.TRUE);
            productorIndividualBo.update(this.ProductorIndividualSeleccionado);

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Productor dado de alta correctamente", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            enableShowData();
        }
    }

    public void mostrarGrupos() {

        this.listaProductorGrupal = this.productorGrupalBo.listProductorGrupal();

    }

    public String showEstado(boolean estado) {
        if (estado) {
            return "Activo";

        } else {
            return "Inactivo";
        }
    }

    public String calcularEdad2(String fecha) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;
        try {
            /**
             * Se puede cambiar la mascara por el formato de la fecha que se
             * quiera recibir, por ejemplo año mes día "yyyy-MM-dd" en este caso
             * es día mes año
             */
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            nueva = (formatter.format(fechaNac));

            //Se crea un objeto con la fecha actual
            Calendar fechaActual = Calendar.getInstance();
            //Se asigna la fecha recibida a la fecha de nacimiento.
            fechaNacimiento.setTime(fechaNac);
            //Se restan la fecha actual y la f

            año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
            int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
            int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
            //Se ajusta el año dependiendo el mes y el día
            if (mes < 0 || (mes == 0 && dia < 0)) {
                año--;
            }

        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }

        return "" + año;

    }

    public void validarFormulario() {
        this.estadoFormulario = true;

        if (this.productorIndividual.getNombresProdcutorIndividual().length() < 3) {
            this.msgNombre = "Los nombre debe contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }

        if (this.productorIndividual.getApellidosProductorIndividual().length() < 3) {
            this.msgApellidos = "Los apellidos deben contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgApellidos = "";
        }
        if (this.productorIndividual.getFechanacimientoProductorIndividual() == null) {
            this.msgFecha = "La fecha es requerida";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }

        if (this.productorIndividual.getDuiProductorIndividual().length() < 9) {
            this.msgDui = "El DUI es requerido";
            this.estadoFormulario = false;
        } else if (this.productorIndividualBo.getProdcutorIndividual(this.productorIndividual.getDuiProductorIndividual())) {
            this.estadoFormulario = false;
            this.msgDui = "Ya se registro un productor con ese DUI";
        } else {
            this.msgDui = "";
        }

        if (this.productorIndividual.getNitProductorIndividual().length() < 14) {
            this.msgNit = "El NIT es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgNit = "";
        }
        if (this.productorIndividual.getEstadoFamiliarProductorIndividual() == "") {
            this.msgEstadoFamiliar = "El estado familiar es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgEstadoFamiliar = "";
        }
        if (this.productorIndividual.getVaronesProductorIndividual() == null) {
            this.msgHijosvarones = "El numero de hijos es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgHijosvarones = "";
        }
        if (this.productorIndividual.getNinasProductorIndividual() == null) {
            this.msgHijosninas = "El numero de hijas es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgHijosninas = "";

        }

        if (this.productorIndividual.getExperienciaProductorIndividual() == "true") {

            if (this.productorIndividual.getInstitucionProductorIndividual().length() < 5) {
                this.msgInstitucion = "El nombre de la institucion debe contener como minimo 5 caracteres";
                this.estadoFormulario = false;
            } else {
                this.msgInstitucion = "";

            }

        } else {

            this.msgInstitucion = "";
        }

        if (this.productorIndividual.getEstudioProductorIndividual() == "") {
            this.msgEstudio = "El nivel de estudio es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgEstudio = "";

        }
        if (this.productorIndividual.getActividadesProductorIndividual().length() < 5) {
            this.msgActividades = "Las actividades economicas son requeridas";
            this.estadoFormulario = false;
        } else {
            this.msgActividades = "";

        }
        if (this.productorIndividual.getTelefonoProductorIndividual().length() < 8) {
            this.msgTelefono = "El telefono debe contener como minimo 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgTelefono = "";

        }
        if (this.productorIndividual.getMovilProductorIndividual().length() < 8) {
            this.msgMovil = "El movil debe contener como minimo 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgMovil = "";

        }
        if (this.productorIndividual.getDireccionProductorIndividual().length() < 5) {
            this.msgDireccion = "La direccion es requerida";
            this.estadoFormulario = false;
        } else {
            this.msgDireccion = "";

        }

        if (this.habilitarGrupo == false) {

            if (this.productorGrupalSeleccionado.getNombreProductorGrupal().length() == 0) {
                this.msgGrupo = "Debe de seleccionar un grupo";
                this.estadoFormulario = false;
            } else {
                this.msgGrupo = "";

            }

        }

    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;

        if (this.ProductorIndividualSeleccionado.getNombresProdcutorIndividual().length() < 3) {
            this.msgNombre = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        if (this.ProductorIndividualSeleccionado.getApellidosProductorIndividual().length() < 3) {
            this.msgApellidos = "Los apellidos deben contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgApellidos = "";
        }
        if (this.ProductorIndividualSeleccionado.getFechanacimientoProductorIndividual() == null) {
            this.msgFecha = "La fecha es requerida";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }
        if (this.ProductorIndividualSeleccionado.getDuiProductorIndividual().length() < 9) {
            this.msgDui = "El DUI es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgDui = "";
        }
        if (this.ProductorIndividualSeleccionado.getNitProductorIndividual().length() < 14) {
            this.msgNit = "El NIT es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgNit = "";
        }
        if (this.ProductorIndividualSeleccionado.getEstadoFamiliarProductorIndividual() == "") {
            this.msgEstadoFamiliar = "El estado familiar es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgEstadoFamiliar = "";
        }
        if (this.ProductorIndividualSeleccionado.getVaronesProductorIndividual() == null) {
            this.msgHijosvarones = "El numero de hijos es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgHijosvarones = "";
        }
        if (this.ProductorIndividualSeleccionado.getNinasProductorIndividual() == null) {
            this.msgHijosninas = "El numero de hijas es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgHijosninas = "";

        }

        if (this.ProductorIndividualSeleccionado.getExperienciaProductorIndividual() == "true") {

            if (this.ProductorIndividualSeleccionado.getInstitucionProductorIndividual().length() < 3) {
                this.msgInstitucion = "El nombre de la institucion debe contener como minimo 3 caracteres";
                this.estadoFormulario = false;
            } else {
                this.msgInstitucion = "";

            }
        } else {

            this.msgInstitucion = "";

        }

        if (this.ProductorIndividualSeleccionado.getEstudioProductorIndividual() == "") {
            this.msgEstudio = "El nivel de estudio es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgEstudio = "";

        }
        if (this.ProductorIndividualSeleccionado.getActividadesProductorIndividual().length() < 5) {
            this.msgActividades = "Las actividades economicas son requeridas";
            this.estadoFormulario = false;
        } else {
            this.msgActividades = "";

        }
        if (this.ProductorIndividualSeleccionado.getTelefonoProductorIndividual().length() < 8) {
            this.msgTelefono = "El telefono debe contener como minimo 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgTelefono = "";

        }
        if (this.ProductorIndividualSeleccionado.getMovilProductorIndividual().length() < 8) {
            this.msgMovil = "El movil debe contener como minimo 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgMovil = "";

        }
        if (this.ProductorIndividualSeleccionado.getDireccionProductorIndividual().length() < 5) {
            this.msgDireccion = "La direccion es requerida";
            this.estadoFormulario = false;
        } else {
            this.msgDireccion = "";

        }
        
         if (this.habilitarGrupo == false) {

            if (this.productorGrupalSeleccionado.getNombreProductorGrupal().length() == 0) {
                this.msgGrupo = "Debe de seleccionar un grupo";
                this.estadoFormulario = false;
            } else {
                this.msgGrupo = "";

            }

        }

    }

    public void limpiar() {

        this.estadoFormulario = false;
        this.msgNombre = "";

        this.msgApellidos = "";
        this.msgFecha = "";
        this.msgDui = "";
        this.msgNit = "";
        this.msgEstadoFamiliar = "";
        this.msgHijosvarones = "";
        this.msgHijosninas = "";
        this.msgInstitucion = "";
        this.msgEstudio = "";
        this.msgActividades = "";

        this.msgCorreo = "";
        this.msgTelefono = "";
        this.msgMovil = "";
        this.msgDireccion = "";

        this.productorIndividual = new TProductorIndividual();
        this.productorIndividual.setSexoProductorIndividual("Masculino");
        ;

    }

    public void enableShowDataBean() {
        this.limpiar();
        super.enableShowData();

    }

    public void habilitar() {

        if (this.experienciaProductorIndividua == true) {

            this.experienciaProductorIndividua = true;
        } else {

            this.experienciaProductorIndividua = false;
            this.productorIndividual.setInstitucionProductorIndividual("");
        }

    }

    public void habilitarModificar() {

        if (this.ProductorIndividualSeleccionado.getExperienciaProductorIndividual() == "true") {

            this.ProductorIndividualSeleccionado.setExperienciaProductorIndividual("" + true);
        } else {

            this.ProductorIndividualSeleccionado.setExperienciaProductorIndividual("" + false);
            this.ProductorIndividualSeleccionado.setInstitucionProductorIndividual("");
        }

    } 
    
    private Boolean habilitarRepresentante;

    public Boolean getHabilitarRepresentante() {
        return habilitarRepresentante;
    }

    public void setHabilitarRepresentante(Boolean habilitarRepresentante) {
        this.habilitarRepresentante = habilitarRepresentante;
    }
    
     public void cargarModificar() {
         
         
         if(this.productorGrupalSeleccionado.getNombreProductorGrupal() == "Ninguno"){
             
             this.habilitarGrupo=true;
                         
             
         }else{
                  
             this.habilitarGrupo=true;
         }
         
        

    }
    

    public void habilitatPGrupal() {

    }
    
      public void verReporteProductor() throws SQLException, JRException, IOException{
        
        
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;   
       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("La fecha de hoy es: " + sdf.format(this.ProductorIndividualSeleccionado.getFechanacimientoProductorIndividual()));

        try {
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(""+sdf.format(this.ProductorIndividualSeleccionado.getFechanacimientoProductorIndividual()));
        } catch (ParseException ex) {
            Logger.getLogger(CompradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        nueva = (formatter.format(fechaNac));

        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la f

        año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            año--;
        }
        
        parametros.put("id_productor", this.ProductorIndividualSeleccionado.getIdProductorIndividual());
        
        parametros.put("edad",""+año);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productor.jasper"));

        
               
       

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
      
      
      
      public void verReporteProductorPDF() throws SQLException, JRException, IOException{
        
        
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;   
       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("La fecha de hoy es: " + sdf.format(this.ProductorIndividualSeleccionado.getFechanacimientoProductorIndividual()));

        try {
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(""+sdf.format(this.ProductorIndividualSeleccionado.getFechanacimientoProductorIndividual()));
        } catch (ParseException ex) {
            Logger.getLogger(CompradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        nueva = (formatter.format(fechaNac));

        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la f

        año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            año--;
        }
        
        parametros.put("id_productor", this.ProductorIndividualSeleccionado.getIdProductorIndividual());
        
        parametros.put("edad",""+año);

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/productor.jasper"));

        

        
       
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Productor individual.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
        
    }
    

}
