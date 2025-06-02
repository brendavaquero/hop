/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acceso;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.CategoriasGasto;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Stateless
public class CategoriasGastoFacade extends AbstractFacade<CategoriasGasto> {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriasGastoFacade() {
        super(CategoriasGasto.class);
    }
    
    public List<CategoriasGasto> categoriasPorUsuario(Usuarios usuario) {
        return em.createQuery(
            "SELECT c FROM CategoriasGasto c WHERE c.idUsuario = :usuario", 
            CategoriasGasto.class
        )
        .setParameter("usuario", usuario)
        .getResultList();
    }

}
