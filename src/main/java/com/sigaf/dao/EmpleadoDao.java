/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IEmpleadoDao;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEmpleadoCargo;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SQLQuery;

/**
 *
 * @author Genoves
 */
public class EmpleadoDao implements IEmpleadoDao{

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TEmpleado empleado) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(empleado);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TEmpleado getTEmpleado(Integer id) {
        Session session = this.sessionFactory.openSession();
        TEmpleado empleado = (TEmpleado) session.createQuery("from TEmpleado where idEmpleado=:id").setParameter("id", id).uniqueResult();
        session.close();
        return empleado;

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TEmpleado empleado) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(empleado);
        session.getTransaction().commit();
        session.clear();
    }

    @Override
    public List<TEmpleadoCargo> listTEmpleadoCargo(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TEmpleadoCargo> listaEmpleadoCargo = session.createQuery("from TEmpleadoCargo c where c.TEmpleado.idEmpleado=:id and c.estadoEmpleadoCargo=TRUE").setParameter("id", id).list();
        session.close();
        return listaEmpleadoCargo;

    }

    @Override
    public Boolean getDUiTEmpleado(String dui) {
        Session session = this.sessionFactory.openSession();
        List<TEmpleado> listaDui = session.createQuery("from TEmpleado e where e.duiEmpleado=:dui").setParameter("dui", dui).list();
        session.close();
        return !listaDui.isEmpty();
    }

    @Override
    public List<TArea> listTAreas(Integer area, Integer entidad, Integer tipo) {

        if (tipo == 2) {
            Session session = this.sessionFactory.openSession();
            List<TArea> listaArea = session.createQuery("from TArea where TEntidad.idEntidad=:id and nombreArea != 'Socios'").setParameter("id", entidad).list();
            session.close();
            return listaArea;
        } else if (tipo == 3) {
            Session session = this.sessionFactory.openSession();
            List<TArea> listaArea = session.createQuery("from TArea where TEntidad.idEntidad=:id and nombreArea = 'Socios'").setParameter("id", entidad).list();
            session.close();
            return listaArea;
        } else {
            Session session = this.sessionFactory.openSession();
            List<TArea> listaArea = session.createQuery("from TArea where TEntidad.idEntidad=:id and nombreArea != 'Socios'").setParameter("id", entidad).list();
            session.close();
            return listaArea;
        }
    }

    @Override
    public List<TEmpleado> listTEmpleado() {
          
        List<TEmpleado> listaEmpleados = null;
        Session session = sessionFactory.openSession();
              listaEmpleados =  session.createQuery("from TEmpleadoArea a inner join a.TEmpleado inner join fetch a.TArea t where t.TEntidad.idEntidad=1").list();
        session.close(); 
        return listaEmpleados;
    }

   
 

}
