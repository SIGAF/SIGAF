/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IRepContDao;
import java.math.BigDecimal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo Aguilar
 */
public class RepContDao implements IRepContDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public BigDecimal saldosCuenta(Integer idCuenta, Integer idEjercicio, String tipoSaldo) {

        Session session = this.sessionFactory.openSession();
        BigDecimal saldo = (BigDecimal) session.createSQLQuery("WITH RECURSIVE libro_mayor(id_cuenta, id_subcuenta_cuenta ,codigo_cuenta,nombre_cuenta ,naturaleza_cuenta, orden_cuenta , total_debe  ) as"
                + "       (select c.id_cuenta, c.id_subcuenta_cuenta, c.codigo_cuenta,c.nombre_cuenta , c.naturaleza_cuenta,"
                + "       CAST('/' || CAST(id_cuenta AS VARCHAR(15)) || '/' AS varchar(900)) AS  orden_cuenta,"
                + "	(select sum(saldo_detalle_partida) from t_detalle_partida dt  inner join t_partida pt on dt.id_partida_detalle_partida= pt.id_partida"
                + "	inner join t_periodo pr on pr.id_periodo=pt.id_periodo_partida where tipo_saldo_detalle_partida=:tipoSaldo and id_cuenta_detalle_partida=c.id_cuenta "
                + "	and id_ejercicio=:idEjercicio group by id_cuenta_detalle_partida) as total_debe from t_cuenta c where id_cuenta not in "
                + "	(select  distinct  id_subcuenta_cuenta from t_cuenta where id_subcuenta_cuenta!=0 )"
                + "	union all"
                + "	select  c.id_cuenta,c.id_subcuenta_cuenta, c.codigo_cuenta,c.nombre_cuenta , c.naturaleza_cuenta,"
                + "	CAST( '/' ||  CAST(c.id_cuenta AS VARCHAR(15))  || l.orden_cuenta  AS varchar(900) ) AS orden_cuenta, l.total_debe"
                + "	from t_cuenta c inner join libro_mayor l  on  c.id_cuenta=l.id_subcuenta_cuenta)"
                + "	select   sum(total_debe) from libro_mayor lb where  lb.id_cuenta =:idCuenta group by id_cuenta").setParameter("idCuenta", idCuenta).setParameter("idEjercicio", idEjercicio).setParameter("tipoSaldo", tipoSaldo).uniqueResult();
        session.close();

        if (saldo != null) {
            return saldo;
        } else {
            return BigDecimal.ZERO;
        }

    }

}
