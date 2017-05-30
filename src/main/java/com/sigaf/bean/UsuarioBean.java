/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IEmpleadoAreaBo;
import com.sigaf.Ibo.IUsuarioBo;
import com.sigaf.bo.AreaBo;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEmpleadoArea;
import com.sigaf.pojo.TUsuario;
import java.io.File;
import java.io.IOException;
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

import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class UsuarioBean extends Actividad {

    private IBitacoraBo bitacoraBo;

    private TUsuario usuario;

    private TUsuario usuarioSelecionado;

    private IEmpleadoAreaBo empleadoAreaBo;

    private IUsuarioBo usuarioBo;

    private AreaBo areaBo;

    private List<TArea> listaArea;

    private List<TEmpleadoArea> listaEmpleadoArea;

    private List<TUsuario> listaUsuarios;

    private Integer idEmpleado;

    private Integer idArea;

    private String msgEmpleado;

    private String msgArea;

    private String msgTipo;

    private String msgNombre;

    private String msgClave;

    private String msgClaveConfir;

    private Boolean estadoFormulario;

    private String claveConfir;

    private String clave;

    private String Nombrbe;

    private Boolean editPass;

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public String getNombrbe() {
        return Nombrbe;
    }

    public void setNombrbe(String Nombrbe) {
        this.Nombrbe = Nombrbe;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEditPass() {
        return editPass;
    }

    public void setEditPass(Boolean editPass) {
        this.editPass = editPass;
    }

    public TUsuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(TUsuario usuarioSelecionado) {

        this.editPass = false;

        this.listaArea = this.areaBo.listArea(1);

        this.setIdArea(this.empleadoAreaBo.getTEmpleadoArea(null, usuarioSelecionado.getTEmpleado().getIdEmpleado()).getTArea().getIdArea());

        this.setIdEmpleado(usuarioSelecionado.getTEmpleado().getIdEmpleado());

        this.usuarioSelecionado = usuarioSelecionado;

        this.claveConfir = "";

        this.clave = "";

        this.Nombrbe = usuarioSelecionado.getNombreUsuario();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_usuario");
        auxBitacora.setAccionBitacora("Ver información de usuario");
        auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
        auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

    }

    public List<TUsuario> getListaUsuarios() {
        this.listaUsuarios = this.usuarioBo.listUsuario();
        return listaUsuarios;
    }

    public void setListaUsuarios(List<TUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getMsgTipo() {
        return msgTipo;
    }

    public void setMsgTipo(String msgTipo) {
        this.msgTipo = msgTipo;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgClave() {
        return msgClave;
    }

    public void setMsgClave(String msgClave) {
        this.msgClave = msgClave;
    }

    public String getMsgClaveConfir() {
        return msgClaveConfir;
    }

    public void setMsgClaveConfir(String msgClaveConfir) {
        this.msgClaveConfir = msgClaveConfir;
    }

    public String getClaveConfir() {
        return claveConfir;
    }

    public void setClaveConfir(String claveConfir) {
        this.claveConfir = claveConfir;
    }

    public TUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(TUsuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getMsgEmpleado() {
        return msgEmpleado;
    }

    public void setMsgEmpleado(String msgEmpleado) {
        this.msgEmpleado = msgEmpleado;
    }

    public String getMsgArea() {
        return msgArea;
    }

    public void setMsgArea(String msgArea) {
        this.msgArea = msgArea;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        Boolean estado = true;

        for (TArea tArea : listaArea) {

            if (tArea.getIdArea() == idArea) {
                estado = false;
                this.listaEmpleadoArea = new ArrayList<>(tArea.getTEmpleadoAreas());
            }

        }

        if (estado) {
            this.listaEmpleadoArea = null;
        }

        this.idArea = idArea;
    }

    public AreaBo getAreaBo() {
        return areaBo;
    }

    public void setAreaBo(AreaBo areaBo) {
        this.areaBo = areaBo;
    }

    public List<TArea> getListaArea() {
        listaArea = this.areaBo.listArea(1);
        return listaArea;
    }

    public void setListaArea(List<TArea> listaArea) {
        this.listaArea = listaArea;
    }

    public IUsuarioBo getUsuarioBo() {
        return usuarioBo;
    }

    public void setUsuarioBo(IUsuarioBo usuarioBo) {
        this.usuarioBo = usuarioBo;
    }

    public IEmpleadoAreaBo getEmpleadoAreaBo() {
        return empleadoAreaBo;
    }

    public void setEmpleadoAreaBo(IEmpleadoAreaBo empleadoAreaBo) {
        this.empleadoAreaBo = empleadoAreaBo;
    }

    public List<TEmpleadoArea> getListaEmpleadoArea() {
        return listaEmpleadoArea;
    }

    public void setListaEmpleadoArea(List<TEmpleadoArea> listaEmpleadoArea) {
        this.listaEmpleadoArea = listaEmpleadoArea;
    }

    public void init() {
        super.enableShowData();
        this.usuario = new TUsuario();
    }

    public void crear() {

        try {
            usuario.setEstadoUsuario(true);
            usuario.setClaveUsuario(getHash(claveConfir));
            usuario.setTEmpleado(new TEmpleado(idEmpleado));
            this.usuarioBo.create(usuario);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_usuario");
            auxBitacora.setAccionBitacora("Crear usuario");
            auxBitacora.setDatosBitacora("Usuario:" + usuario.getNombreUsuario()
                    + ", Tipo: " + usuario.getTipoUsuario()
                    + ", Empleado: " + usuario.getTEmpleado().getNombreEmpleado()
                    + " " + usuario.getTEmpleado().getApellidoEmpleado());
            auxBitacora.setIdTableBitacora(usuario.getIdUsuario());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

            bitacoraBo.create(auxBitacora);

            this.limpiar();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuario creado correctamente."));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El  usuario no pudo ser agregado."));
        }

    }

    public void validarFormulario() {
        estadoFormulario = true;

        if (this.idArea == 0) {
            this.msgArea = "El area es requerida";
            estadoFormulario = false;
        } else {
            this.msgArea = "";
        }

        if (this.idEmpleado == 0) {
            this.msgEmpleado = "El empleado es requerido";
            estadoFormulario = false;
        } else {
            this.msgEmpleado = "";
        }

        if (this.usuario.getTipoUsuario() == 0) {
            this.msgTipo = "El tipo es requerido";
            estadoFormulario = false;
        } else {
            this.msgTipo = "";
        }

        if (this.usuario.getNombreUsuario().length() < 6) {
            this.msgNombre = "El nombre de usuario debe contener minimo 6 caracteres";
            estadoFormulario = false;
        } else {
            if (this.usuario.getNombreUsuario().length() > 16) {
                this.msgNombre = "El nombre de usuario debe contener máximo 16 caracteres";
                estadoFormulario = false;
            } else {

                if (this.usuarioBo.getUsuarioValRep(this.usuario.getNombreUsuario()) != null) {
                    this.msgNombre = "El nombre de usuario no esta disponible";
                    estadoFormulario = false;
                } else {

                    this.msgNombre = "";

                }
            }
        }

        if (this.usuario.getClaveUsuario().length() < 8) {
            this.msgClave = "La contraseña debe contener nimimo 8 caracteres";
            estadoFormulario = false;
        } else {
            this.msgClave = "";
        }

        if (!this.usuario.getClaveUsuario().equals(this.claveConfir)) {
            this.msgClaveConfir = "Las contraseña deben ser iguales";
            estadoFormulario = false;
        } else {
            this.msgClaveConfir = "";
        }

    }

    public void darDeBaja() {

        try {
            this.usuarioSelecionado.setEstadoUsuario(false);
            this.usuarioBo.update(usuarioSelecionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_usuario");
            auxBitacora.setAccionBitacora("Dar de baja usuario");
            auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                    + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                    + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                    + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
            auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuario dado de baja correctamente."));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El Usuario no pudo ser dado de baja."));
        }

    }

    public void darDeAlta() {

        try {

            this.usuarioSelecionado.setEstadoUsuario(true);

            this.usuarioBo.update(usuarioSelecionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_usuario");
            auxBitacora.setAccionBitacora("Dar de alta usuario");
            auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                    + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                    + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                    + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
            auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuario dado de alta correctamente."));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El Usuario no pudo ser dodo de alta."));
        }
    }

    public void limpiar() {

        this.usuario = new TUsuario();
        this.idArea = 0;
        this.idEmpleado = 0;
        this.msgArea = "";
        this.msgClave = "";
        this.msgClaveConfir = "";
        this.msgEmpleado = "";
        this.msgNombre = "";
        this.msgTipo = "";
        this.estadoFormulario = false;
        this.clave = "";
        this.claveConfir = "";

    }

    public void enableShowDataBean() {
        this.enableShowData();
        this.limpiar();
    }

    public void actualizar() {

        try {

            if (editPass) {

                usuarioSelecionado.setClaveUsuario(getHash(claveConfir));
            }

            this.usuarioBo.update(usuarioSelecionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_usuario");
            auxBitacora.setAccionBitacora("Modificar usuario");
            auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                    + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                    + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                    + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
            auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

            bitacoraBo.create(auxBitacora);

            this.enableShowData();
         

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuario modificado correctamente."));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El  usuario no pudo ser modificado."));
        }

    }

    public void actualizarPerfil() {

        try {

            if (editPass) {

                usuarioSelecionado.setClaveUsuario(getHash(claveConfir));
            }

            usuarioSelecionado.setEstadoUsuario(this.usuarioBo.getUsuario(usuarioSelecionado.getIdUsuario()).getEstadoUsuario());

            usuarioSelecionado.setNombreUsuario(Nombrbe);

            this.usuarioBo.update(usuarioSelecionado);

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_usuario");
            auxBitacora.setAccionBitacora("Modificar perfil usuario");
            auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                    + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                    + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                    + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
            auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

            bitacoraBo.create(auxBitacora);

            this.limpiar();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuario modificado correctamente."));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El  usuario no pudo ser modificado."));
        }

    }

    public void validarFormularioModificar() {

        estadoFormulario = true;

        if (this.usuarioSelecionado.getTipoUsuario() == 0) {
            this.msgTipo = "El tipo es requerido";
            estadoFormulario = false;
        } else {
            this.msgTipo = "";
        }

        if (this.usuarioSelecionado.getNombreUsuario().length() < 6) {
            this.msgNombre = "El nombre de usuario debe contener minimo 6 caracteres";
            estadoFormulario = false;
        } else {
            if (this.usuarioSelecionado.getNombreUsuario().length() > 16) {
                this.msgNombre = "El nombre de usuario debe contener máximo 16 caracteres";
                estadoFormulario = false;
            } else {
                TUsuario usu = this.usuarioBo.getUsuarioValRep(this.usuarioSelecionado.getNombreUsuario());
                if (usu != null) {
                    if (usu.getIdUsuario() != usuarioSelecionado.getIdUsuario()) {
                        this.msgNombre = "El nombre de usuario no esta disponible";
                        estadoFormulario = false;
                    } else {

                        this.msgNombre = "";

                    }
                } else {

                    this.msgNombre = "";

                }

            }
        }

        if (editPass) {
            if (this.clave.length() < 8) {
                this.msgClave = "La contraseña debe contener nimimo 8 caracteres";
                estadoFormulario = false;
            } else {
                this.msgClave = "";
            }

            if (!this.clave.equals(this.claveConfir)) {
                this.msgClaveConfir = "Las contraseña deben ser iguales";
                estadoFormulario = false;
            } else {
                this.msgClaveConfir = "";
            }
        }

    }

    public void validarFormularioModificarPerfil() {

        estadoFormulario = true;

        if (this.usuarioSelecionado.getTipoUsuario() == 0) {
            this.msgTipo = "El tipo es requerido";
            estadoFormulario = false;
        } else {
            this.msgTipo = "";
        }

        if (this.Nombrbe.length() < 6) {
            this.msgNombre = "El nombre de usuario debe contener minimo 6 caracteres";
            estadoFormulario = false;
        } else {
            if (this.Nombrbe.length() > 16) {
                this.msgNombre = "El nombre de usuario debe contener máximo 16 caracteres";
                estadoFormulario = false;
            } else {
                TUsuario usu = this.usuarioBo.getUsuarioValRep(this.Nombrbe);
                if (usu != null) {
                    if (usu.getIdUsuario() != usuarioSelecionado.getIdUsuario()) {
                        this.msgNombre = "El nombre de usuario no esta disponible";
                        estadoFormulario = false;
                    } else {

                        this.msgNombre = "";

                    }
                } else {

                    this.msgNombre = "";

                }

            }
        }

        if (editPass) {
            if (this.clave.length() < 8) {
                this.msgClave = "La contraseña debe contener nimimo 8 caracteres";
                estadoFormulario = false;
            } else {
                this.msgClave = "";
            }

            if (!this.clave.equals(this.claveConfir)) {
                this.msgClaveConfir = "Las contraseña deben ser iguales";
                estadoFormulario = false;
            } else {
                this.msgClaveConfir = "";
            }
        }

    }

    public String tipoUser(Integer tipo) {

        if (tipo != null) {
            switch (tipo) {
                case 1:
                    return "Contador";
                case 2:
                    return "Asesor de crédito";
                default:
                    return "Administrador";
            }
        } else {
            return "";
        }

    }

    /* Retorna un hash a partir de un tipo y un texto */
    public String getHash(String txt) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("SHA1");
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void redirectPerfil() throws IOException {
      

        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().
                handleNavigation(FacesContext.getCurrentInstance(), null, "/inicio/perfil/adminPerfil.xhtml");

    }

    public void redirectHome() throws IOException {
      

        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().
                handleNavigation(FacesContext.getCurrentInstance(), null, "/inicio/index.xhtml");

    }

    public Connection conec() throws SQLException {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "user_sigaf", "adminsigaf");

        return conn;
    }

    public void generarUsuario() throws Exception {

        Map<String, Object> estadoUsuario = new HashMap();

        estadoUsuario.put("id_usuario", usuarioSelecionado.getIdUsuario());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/seguridad/UsuarioIndividual.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), estadoUsuario, this.conec());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        FacesContext.getCurrentInstance().responseComplete();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_usuario");
        auxBitacora.setAccionBitacora("Generar reporte de usuario");
        auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
        auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

    }

    public void generarUsuarioPDF() throws Exception {

        Map<String, Object> estadoUsuario = new HashMap();

        estadoUsuario.put("id_usuario", usuarioSelecionado.getIdUsuario());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/seguridad/UsuarioIndividual.jasper"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), estadoUsuario, this.conec());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Usuario.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();

        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_usuario");
        auxBitacora.setAccionBitacora("Descargar reporte de usuario");
        auxBitacora.setDatosBitacora("Usuario:" + usuarioSelecionado.getNombreUsuario()
                + ", Tipo: " + usuarioSelecionado.getTipoUsuario()
                + ", Empleado: " + usuarioSelecionado.getTEmpleado().getNombreEmpleado()
                + " " + usuarioSelecionado.getTEmpleado().getApellidoEmpleado());
        auxBitacora.setIdTableBitacora(usuarioSelecionado.getIdUsuario());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

        bitacoraBo.create(auxBitacora);

    }

    public void enableShowCreateBean() {
        
        this.limpiar();
        super.enableShowCreate();

    }

}
