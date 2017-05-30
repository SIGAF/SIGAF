/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IMunicipioDao;
import com.sigaf.pojo.TMunicipio;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genoves
 */
public class MunicipioDao implements IMunicipioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TMunicipio municipio) {
        municipio.setEstadoMunicipio(true);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(municipio);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TMunicipio municipio) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(municipio);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TMunicipio getMunicipio(Integer id) {
        Session session = this.sessionFactory.openSession();
        TMunicipio departamento = (TMunicipio) session.createQuery("from TMunicipio m inner join fetch m.TDepartamento where m.idMunicipio =:id and m.estadoMunicipio=true").setParameter("id", id).uniqueResult() ;
        session.close();
        return departamento;
    }

    @Override
    public List<TMunicipio> listTMunicipio() {
        Session session = this.sessionFactory.openSession();
        List<TMunicipio> listaMunicipio = session.createQuery("from TMunicipio m inner join fetch m.TDepartamento").list();
        session.close();
        return listaMunicipio;
    }

    @Override
    public List<TMunicipio> listCargarMunicipios(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TMunicipio> listaMunicipioDepartamento = session.createQuery("from TMunicipio m inner join fetch m.TDepartamento "
                + "where m.TDepartamento.idDepartamento =:id and m.estadoMunicipio=true").setParameter("id",id).list();
        session.close();
        return listaMunicipioDepartamento;
    }

    @Override
    public Boolean municipioRepetido(String nombre, int idDepartamento) {
        Session session = this.sessionFactory.openSession();
        List<TMunicipio> municipioRepetido= session.createQuery("from TMunicipio m where m.nombreMunicipio=:nombre and m.TDepartamento.idDepartamento=:idDepartamento").setParameter("nombre", nombre).setParameter("idDepartamento", idDepartamento).list();
        session.close();
        return !municipioRepetido.isEmpty();
    }

   

   

}
