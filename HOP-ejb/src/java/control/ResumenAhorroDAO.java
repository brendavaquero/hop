/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package control;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Stateless
@LocalBean
public class ResumenAhorroDAO {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    public BigDecimal obtenerTotalGastos(Usuarios usuario, int mes, int anio) {
        return em.createQuery(
            "SELECT COALESCE(SUM(g.monto), 0) FROM Gastos g WHERE g.idUsuario = :usuario AND FUNCTION('MONTH', g.fechaGasto) = :mes AND FUNCTION('YEAR', g.fechaGasto) = :anio", BigDecimal.class)
            .setParameter("usuario", usuario)
            .setParameter("mes", mes)
            .setParameter("anio", anio)
            .getSingleResult();
    }

    

    public BigDecimal obtenerTotalIngresos(Usuarios usuario, int mes, int anio) {
        return em.createQuery(
            "SELECT COALESCE(SUM(i.monto), 0) FROM Ingresos i WHERE i.idUsuario = :usuario AND FUNCTION('MONTH', i.fecha) = :mes AND FUNCTION('YEAR', i.fecha) = :anio", BigDecimal.class)
            .setParameter("usuario", usuario)
            .setParameter("mes", mes)
            .setParameter("anio", anio)
            .getSingleResult();
    }
}
