package com.sigaf.pojo;
// Generated 05-mar-2017 20:43:44 by Hibernate Tools 4.3.1



/**
 * TClienteProyecto generated by hbm2java
 */
public class TClienteProyecto  implements java.io.Serializable {


     private int idClienteProyecto;
     private TCliente TCliente;
     private TProyecto TProyecto;

    public TClienteProyecto() {
    }

	
    public TClienteProyecto(int idClienteProyecto) {
        this.idClienteProyecto = idClienteProyecto;
    }
    public TClienteProyecto(int idClienteProyecto, TCliente TCliente, TProyecto TProyecto) {
       this.idClienteProyecto = idClienteProyecto;
       this.TCliente = TCliente;
       this.TProyecto = TProyecto;
    }
   
    public int getIdClienteProyecto() {
        return this.idClienteProyecto;
    }
    
    public void setIdClienteProyecto(int idClienteProyecto) {
        this.idClienteProyecto = idClienteProyecto;
    }
    public TCliente getTCliente() {
        return this.TCliente;
    }
    
    public void setTCliente(TCliente TCliente) {
        this.TCliente = TCliente;
    }
    public TProyecto getTProyecto() {
        return this.TProyecto;
    }
    
    public void setTProyecto(TProyecto TProyecto) {
        this.TProyecto = TProyecto;
    }




}

