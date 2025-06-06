/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acceso;

import java.util.List;
import javax.ejb.Stateless;
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
public class LimitesCategoriaUsuarioFacade extends AbstractFacade<LimitesCategoriaUsuario> {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LimitesCategoriaUsuarioFacade() {
        super(LimitesCategoriaUsuario.class);
    }
    
    public List<LimitesCategoriaUsuario> findByUsuarioYCategoria(Usuarios usuario, CategoriasGenerales categoria) {
        return em.createQuery("SELECT l FROM LimitesCategoriaUsuario l WHERE l.idUsuario = :usuario AND l.idCategoria = :categoria", LimitesCategoriaUsuario.class)
                 .setParameter("usuario", usuario)
                 .setParameter("categoria", categoria)
                 .getResultList();
    }

    
}
