/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.ICuentaDao;
import com.sigaf.pojo.TCuenta;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class CuentaDao implements ICuentaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TCuenta cuenta) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(cuenta);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TCuenta getCuenta(Integer id) {

        Session session = this.sessionFactory.openSession();
        TCuenta cuenta = (TCuenta) session.createQuery("from TCuenta where idCuenta  =:id").setParameter("id", id).uniqueResult();
        session.close();
        return cuenta;
    }

    /**
     * metodo para verificar si una cuenta dispone de sub, esto es para
     * validadr.
     */
    @Override
    public List<TCuenta> listCuentaSub(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TCuenta> listCuenta = session.createQuery("from TCuenta as a  where  a.TCuenta.idCuenta=:id order by codigoCuenta").setParameter("id", id).list();
        session.close();
        return listCuenta;
    }

    /**
     * Metodo para listar todas la cuentas de una entidad especifica.
     */
    @Override
    public List<TCuenta> listCuentaEnt(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TCuenta> listCuenta = session.createQuery("from TCuenta where TEntidad.idEntidad  =:id order by codigoCuenta").setParameter("id", id).list();
        session.close();
        return listCuenta;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TCuenta cuenta) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(cuenta);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TCuenta getCuentaRep(Integer id, String codigo) {
        Session session = this.sessionFactory.openSession();
        TCuenta cuenta = (TCuenta) session.createQuery("from TCuenta where codigoCuenta=:codigo and TEntidad.idEntidad  =:id ").setParameter("codigo", codigo).setParameter("id", id).uniqueResult();
        session.close();
        return cuenta;
    }

    @Override
    public TCuenta getCuentaRepAct(Integer idEnt, Integer idCue, String codigo) {
        Session session = this.sessionFactory.openSession();
        TCuenta cuenta = (TCuenta) session.createQuery("from TCuenta where codigoCuenta=:codigo and idCuenta!=:idCue and TEntidad.idEntidad  =:idEnt ").setParameter("codigo", codigo).setParameter("idCue", idCue).setParameter("idEnt", idEnt).uniqueResult();
        session.close();
        return cuenta;
    }

    /*
    *Retorna todas las cuentas segun el estado (activo, inactivo) 
    *y excluye las de detalle(cuentas que an sido usadas en el libro diario o en tipos de activos)
     */
    @Override
    public List<TCuenta> listCuentaEntAct(Integer id, Boolean estado) {
        Session session = this.sessionFactory.openSession();
        List<TCuenta> listCuenta = session.createQuery("from TCuenta c  where  c.estadoCuenta=:estado and   c.TEntidad.idEntidad =:id and c.idCuenta "
                + "not in (select c.idCuenta from TDetallePartida d  inner join  d.TCuenta  c where c.TEntidad.idEntidad =:id )"
                + "and c.idCuenta not in ( select c.idCuenta from TTipoActivo t inner join  t.TCuentaByIdCuentaVentaTipo c where c.TEntidad.idEntidad =:id ) "
                + "and c.idCuenta not in ( select c.idCuenta from TTipoActivo t inner join  t.TCuentaByIdCuentaDepreciacionTipo c where c.TEntidad.idEntidad =:id )"
                + "and c.idCuenta not in ( select c.idCuenta from TTipoActivo t inner join  t.TCuentaByIdCuentaGastoTipo c where c.TEntidad.idEntidad =:id )"
                + "and c.idCuenta not in ( select c.idCuenta from TTipoActivo t inner join  t.TCuentaByIdCuentaActivoTipo c where c.TEntidad.idEntidad =:id ) order by c.codigoCuenta"
        ).setParameter("estado", estado).setParameter("id", id).list();
        session.close();
        return listCuenta;
    }

    /*
    * Retorna todas la cuentas de detalles
     */
    @Override
    public List<TCuenta> listCuentaEntActTip(Integer id, Boolean estado) {
        Session session = this.sessionFactory.openSession();
        List<TCuenta> listCuenta = session.createQuery("from TCuenta where estadoCuenta=:estado and TEntidad.idEntidad  =:id and idCuenta not in( select TCuenta.idCuenta from TCuenta where TEntidad.idEntidad  =:id and TCuenta.idCuenta!=0 ) order by codigoCuenta").setParameter("estado", estado).setParameter("id", id).list();
        session.close();
        return listCuenta;
    }

}
