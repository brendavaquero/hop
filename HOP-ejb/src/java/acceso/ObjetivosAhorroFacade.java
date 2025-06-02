/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acceso;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.ObjetivosAhorro;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Stateless
public class ObjetivosAhorroFacade extends AbstractFacade<ObjetivosAhorro> {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetivosAhorroFacade() {
        super(ObjetivosAhorro.class);
    }
    
    public List<ObjetivosAhorro> findByUsuario(Usuarios u) {
        /*return em.createQuery(
            "SELECT o FROM ObjetivosAhorro o WHERE o.idUsuario = :usuario ORDER BY o.fechaInicio DESC",
            ObjetivosAhorro.class
        )
        .setParameter("usuario", u)
        .getResultList();*/
            return em.createQuery("SELECT o FROM ObjetivosAhorro o WHERE o.idUsuario = :usuario ORDER BY o.fechaInicio DESC", ObjetivosAhorro.class)
             .setParameter("usuario", u)
             .getResultList();
    }
}
