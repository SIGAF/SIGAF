/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IMunicipioEmpleadoDao;
import com.sigaf.pojo.TMunicipioEmpleado;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genoves
 */
public class MunicipioEmpleadoDao implements IMunicipioEmpleadoDao{
    
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TMunicipioEmpleado municipioEmpleado) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(municipioEmpleado);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TMunicipioEmpleado getTMunicipioEmpleado(Integer id) {
         Session session = this.sessionFactory.openSession();
        TMunicipioEmpleado municipioEmpleado = (TMunicipioEmpleado) session.createQuery("from TMunicipioEmpleado e where e.TEmpleado.idEmpleado =:id").setParameter("id", id).uniqueResult() ;
        session.close();
        return municipioEmpleado;
    }

    @Override
    public List<TMunicipioEmpleado> listTMunicipioEmpleado(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TMunicipioEmpleado municipioEmpleado) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(municipioEmpleado);
        session.getTransaction().commit();
        session.clear();
        session.close();
    }


        
}
