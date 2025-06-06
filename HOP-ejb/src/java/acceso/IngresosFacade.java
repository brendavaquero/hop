/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acceso;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Ingresos;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Stateless
public class IngresosFacade extends AbstractFacade<Ingresos> {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngresosFacade() {
        super(Ingresos.class);
    }
    
    /*public List<Ingresos> findByUsuario(Usuarios usuario) {
        return getEntityManager().createQuery(
            "SELECT i FROM Ingresos i WHERE i.idUsuario = :usuario ORDER BY i.fecha DESC", Ingresos.class)
            .setParameter("usuario", usuario)
            .getResultList();
    }*/
    
    public List<Ingresos> obtenerPorUsuario(Usuarios usuario) {
        return em.createQuery(
                "SELECT i FROM Ingresos i WHERE i.idUsuario = :usuario ORDER BY i.fecha DESC", Ingresos.class)
                .setParameter("usuario", usuario)
                .getResultList();
    }


    
}
