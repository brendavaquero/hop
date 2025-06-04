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
import modelo.CategoriasGenerales;
import modelo.Gastos;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Stateless
@LocalBean
public class DesgloseGastosDAO {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    public List<Gastos> obtenerPorCategoriaYFecha(Usuarios usuario, CategoriasGenerales categoria, int mes, int anio) {
        return em.createQuery(
            "SELECT g FROM Gastos g WHERE g.idUsuario = :usuario AND g.idCategoria = :categoria " +
            "AND FUNCTION('MONTH', g.fechaGasto) = :mes AND FUNCTION('YEAR', g.fechaGasto) = :anio", Gastos.class)
            .setParameter("usuario", usuario)
            .setParameter("categoria", categoria)
            .setParameter("mes", mes)
            .setParameter("anio", anio)
            .getResultList();
    }
    
}

