/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IDepartamentoDao;
import com.sigaf.pojo.TDepartamento;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genoves
 */
public class DepartamentoDao implements IDepartamentoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TDepartamento departamento) {

        departamento.setEstadoDepartamento(true);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(departamento);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<TDepartamento> listTDepartamento() {
        Session session = this.sessionFactory.openSession();
        List<TDepartamento> listaDepartamento = session.createQuery("from TDepartamento d").list();
        session.close();
        return listaDepartamento;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TDepartamento departamento) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(departamento);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TDepartamento getDepartamento(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean departaentoRepetido(String nombre) {
        Session session = this.sessionFactory.openSession();
        List<TDepartamento> listaDepto= session.createQuery("from TDepartamento d where d.nombreDepartamento=:nombre").setParameter("nombre", nombre).list();
        session.close();
        return !listaDepto.isEmpty();
    }

}
