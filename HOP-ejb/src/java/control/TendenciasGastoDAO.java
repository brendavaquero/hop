/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package control;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.TendenciaGastoMensualDTO;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Stateless
@LocalBean
public class TendenciasGastoDAO {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    public List<TendenciaGastoMensualDTO> obtenerTendenciasPorAnio(Usuarios usuario, int anio) {
        return em.createQuery(
            "SELECT new modelo.TendenciaGastoMensualDTO(FUNCTION('MONTH', g.fechaGasto), c.nombre, SUM(g.monto)) " +
            "FROM Gastos g JOIN g.idCategoria c " +
            "WHERE g.idUsuario = :usuario AND FUNCTION('YEAR', g.fechaGasto) = :anio " +
            "GROUP BY FUNCTION('MONTH', g.fechaGasto), c.nombre " +
            "ORDER BY FUNCTION('MONTH', g.fechaGasto), c.nombre", 
            TendenciaGastoMensualDTO.class)
            .setParameter("usuario", usuario)
            .setParameter("anio", anio)
            .getResultList();
    }
}

