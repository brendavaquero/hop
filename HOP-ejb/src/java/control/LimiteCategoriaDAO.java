/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package control;

import acceso.CategoriasGeneralesFacade;
import acceso.LimitesCategoriaUsuarioFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.CategoriasGenerales;
import modelo.LimitesCategoriaUsuario;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Stateless
@LocalBean
public class LimiteCategoriaDAO {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    public List<CategoriasGenerales> obtenerCategorias() {
        return em.createQuery("SELECT c FROM CategoriasGenerales c", CategoriasGenerales.class).getResultList();
    }

    public void guardarLimite(LimitesCategoriaUsuario limite) {
        em.persist(limite);
    }

    public boolean existeLimite(Usuarios usuario, CategoriasGenerales categoria) {
        Long count = em.createQuery("SELECT COUNT(l) FROM LimitesCategoriaUsuario l WHERE l.idUsuario = :usuario AND l.idCategoria = :categoria", Long.class)
            .setParameter("usuario", usuario)
            .setParameter("categoria", categoria)
            .getSingleResult();
        return count > 0;
    }
}
