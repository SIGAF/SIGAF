/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.ICompradorBo;
import com.sigaf.pojo.TComprador;
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

;

/**
 *
 * @author yonat
 */
@Named(value = "compradorBean")
@RequestScoped
@ManagedBean

public class CompradorBean extends Actividad {

    private TComprador Comprador;
    private TComprador CompradorSeleccionado;
    private List<TComprador> listaComprador;
    private ICompradorBo compradorBo;

    private boolean estadoFormulario;
    private String msgNombres;
    private String msgApellidos;
    private String msgFecha;
    private String msgDui;
    private String msgNit;
    private String msgTipo;
    private String msgTelefono;
    private String msgMovil;

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
    private String msgDireccion;

    public boolean isEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getMsgNombres() {
        return msgNombres;
    }

    public void setMsgNombres(String msgNombres) {
        this.msgNombres = msgNombres;
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

    public String getMsgTipo() {
        return msgTipo;
    }

    public void setMsgTipo(String msgTipo) {
        this.msgTipo = msgTipo;
    }

    public void init() {
        this.Comprador = new TComprador();
        this.Comprador.setSexoComprador("Masculino");
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
        compradorBo.update(this.CompradorSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprador modificado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaComprador = compradorBo.listComprador();

        super.enableShowData();
        limpiar();
    }

    public void darDeBaja() {
        this.CompradorSeleccionado.setEstadoComprador(false);
        compradorBo.update(this.CompradorSeleccionado);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprador dado de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaComprador = compradorBo.listComprador();
        super.enableShowData();
    }

    public void darDeAlta() {
        this.CompradorSeleccionado.setEstadoComprador(true);
        compradorBo.update(this.CompradorSeleccionado);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprador dado de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        this.listaComprador = compradorBo.listComprador();
        super.enableShowData();
    }

    public void crear() {
        this.Comprador.setEstadoComprador(true);
        compradorBo.create(this.Comprador);
        this.listaComprador = compradorBo.listComprador();
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprador guardado correctamente", null);
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

        if (this.Comprador.getNombresComprador().length() < 3) {
            this.msgNombres = "Los nombres deben contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombres = "";
        }
        if (this.Comprador.getApellidosComprador().length() < 3) {
            this.msgApellidos = "Los apellidos deben contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgApellidos = "";
        }
        if (this.Comprador.getFechanacimientoComprador() == null) {
            this.msgFecha = "La fecha es requerida";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }

        if (this.Comprador.getDuiComprador().length() < 3) {
            this.msgDui = "El DUI es requerido";
            this.estadoFormulario = false;
        } else if (this.compradorBo.getTCompradorCodigo(this.Comprador.getDuiComprador())) {
            this.estadoFormulario = false;
            this.msgDui = "Ya se registro un comprador con ese DUI";
        } else {

            this.msgDui = "";
        }

        if (this.Comprador.getNitComprador().length() < 14) {
            this.msgNit = "El NIT es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgNit = "";
        }

        if (this.Comprador.getTipoComprador() == "") {
            this.msgTipo = "El tipo es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgTipo = "";
        }
        if (this.Comprador.getTelefonoComprador().length() < 8) {
            this.msgTelefono = "El telefono debe contener 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgTelefono = "";
        }
        if (this.Comprador.getMovilComprador().length() < 8) {
            this.msgMovil = "El telefono debe contener 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgMovil = "";
        }
        if (this.Comprador.getDireccionComprador().length() < 5) {
            this.msgDireccion = "La direccion es requerida";
            this.estadoFormulario = false;
        } else {
            this.msgDireccion = "";
        }
    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;

        if (this.CompradorSeleccionado.getNombresComprador().length() < 3) {
            this.msgNombres = "Los nombre deben contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombres = "";
        }
        if (this.CompradorSeleccionado.getApellidosComprador().length() < 3) {
            this.msgApellidos = "Los apellidos deben contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgApellidos = "";
        }
        if (this.CompradorSeleccionado.getFechanacimientoComprador() == null) {
            this.msgFecha = "La fecha es requerida";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }
        if (this.CompradorSeleccionado.getDuiComprador().length() < 8) {
            this.msgDui = "El DUI es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgDui = "";
        }
        if (this.CompradorSeleccionado.getNitComprador().length() < 14) {
            this.msgNit = "El NIT es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgNit = "";
        }

        if (this.CompradorSeleccionado.getTipoComprador() == "") {
            this.msgTipo = "El tipo es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgTipo = "";
        }
        if (this.CompradorSeleccionado.getTelefonoComprador().length() < 8) {
            this.msgTelefono = "El telefono debe contener 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgTelefono = "";
        }
        if (this.CompradorSeleccionado.getMovilComprador().length() < 8) {
            this.msgMovil = "El telefono debe contener 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgMovil = "";
        }
        if (this.CompradorSeleccionado.getDireccionComprador().length() < 5) {
            this.msgDireccion = "La direccion es requerida";
            this.estadoFormulario = false;
        } else {
            this.msgDireccion = "";
        }

    }

    public void limpiar() {

        this.estadoFormulario = false;
        this.msgNombres = "";
        this.msgApellidos = "";
        this.msgDireccion = "";
        this.msgDui = "";
        this.msgNit = "";
        this.msgTelefono = "";
        this.msgMovil = "";
        this.msgDireccion = "";
        this.msgFecha = "";
        this.msgDireccion = "";
        this.msgTipo = "";

        this.Comprador = new TComprador();
        this.Comprador.setSexoComprador("Masculino");
        this.listaComprador = compradorBo.listComprador();

    }

    public void enableShowDataBean() {
        limpiar();
        super.enableShowData();

    }

    public String calcularEdad(String fecha) throws ParseException {
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

    public void verReporteComprador() throws SQLException, JRException, IOException {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;   
       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("La fecha de hoy es: " + sdf.format(this.CompradorSeleccionado.getFechanacimientoComprador()));

        try {
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(""+sdf.format(this.CompradorSeleccionado.getFechanacimientoComprador()));
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
        
        System.out.println(año);

        parametros.put("id_comprador", this.CompradorSeleccionado.getIdComprador());
        
        parametros.put("edad", año );        
        

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/comprador.jasper"));

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
    
    
    
    public void verReporteCompradorPDF() throws SQLException, JRException, IOException{
        
        
        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");

        Map<String, Object> parametros = new HashMap();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;   
       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("La fecha de hoy es: " + sdf.format(this.CompradorSeleccionado.getFechanacimientoComprador()));

        try {
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(""+sdf.format(this.CompradorSeleccionado.getFechanacimientoComprador()));
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
        
        System.out.println(año);

        parametros.put("id_comprador", this.CompradorSeleccionado.getIdComprador());
        
        parametros.put("edad", año );        
        

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/comprador.jasper"));

       
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, conn);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Comprador.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
        
    }
    
    
    

}
