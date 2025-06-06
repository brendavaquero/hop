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
public class BalanceDAO {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    public BigDecimal obtenerTotalIngresos(Usuarios usuario) {
        return em.createQuery("SELECT COALESCE(SUM(i.monto), 0) FROM Ingresos i WHERE i.idUsuario = :usuario", BigDecimal.class)
                 .setParameter("usuario", usuario)
                 .getSingleResult();
    }

    public BigDecimal obtenerTotalGastos(Usuarios usuario) {
        return em.createQuery("SELECT COALESCE(SUM(g.monto), 0) FROM Gastos g WHERE g.idUsuario = :usuario", BigDecimal.class)
                 .setParameter("usuario", usuario)
                 .getSingleResult();
    }
}
