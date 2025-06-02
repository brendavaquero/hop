/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acceso;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.CategoriasGasto;
import modelo.Gastos;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Stateless
public class GastosFacade extends AbstractFacade<Gastos> {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GastosFacade() {
        super(Gastos.class);
    }
    
    public BigDecimal totalGastadoPorCategoriaYMes(Usuarios usuario, CategoriasGasto categoria, int mes, int anio) {
        try {
            return em.createQuery(
                "SELECT COALESCE(SUM(g.monto), 0) FROM Gastos g " +
                "WHERE g.idUsuario = :usuario " +
                "AND g.idCategoria = :categoria " +
                "AND FUNCTION('MONTH', g.fechaGasto) = :mes " +
                "AND FUNCTION('YEAR', g.fechaGasto) = :anio", 
                BigDecimal.class
            )
            .setParameter("usuario", usuario)
            .setParameter("categoria", categoria)
            .setParameter("mes", mes)
            .setParameter("anio", anio)
            .getSingleResult();
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    
}
