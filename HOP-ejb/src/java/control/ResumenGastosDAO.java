/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.CategoriasGenerales;
import modelo.LimitesCategoriaUsuario;
import modelo.ResumenGastoDTO;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Stateless
@LocalBean
public class ResumenGastosDAO {
    

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

     public List<ResumenGastoDTO> obtenerResumenMensual(Usuarios usuario, int mes, int anio) {
        List<ResumenGastoDTO> resumen = new ArrayList<>();

        //límites por categoría del usuario
        List<LimitesCategoriaUsuario> limites = em.createQuery(
            "SELECT l FROM LimitesCategoriaUsuario l WHERE l.idUsuario = :usuario", LimitesCategoriaUsuario.class)
            .setParameter("usuario", usuario)
            .getResultList();

        for (LimitesCategoriaUsuario limite : limites) {
            CategoriasGenerales categoria = limite.getIdCategoria();
            BigDecimal montoAsignado = limite.getMontoAsignado();

            //monto gastado por este usuario, en esta categoría, ese mes/año
            BigDecimal montoGastado = em.createQuery(
                "SELECT COALESCE(SUM(g.monto), 0) FROM Gastos g " +
                "WHERE g.idUsuario = :usuario AND g.idCategoria = :categoria " +
                "AND FUNCTION('MONTH', g.fechaGasto) = :mes " +
                "AND FUNCTION('YEAR', g.fechaGasto) = :anio", BigDecimal.class)
                .setParameter("usuario", usuario)
                .setParameter("categoria", categoria)
                .setParameter("mes", mes)
                .setParameter("anio", anio)
                .getSingleResult();


            ResumenGastoDTO dto = new ResumenGastoDTO(categoria, montoAsignado, montoGastado);
            resumen.add(dto);
        }

        return resumen;
    }
}

