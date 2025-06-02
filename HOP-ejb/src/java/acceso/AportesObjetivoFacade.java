/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acceso;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.AportesObjetivo;
import modelo.ObjetivosAhorro;

/**
 *
 * @author brend
 */
@Stateless
public class AportesObjetivoFacade extends AbstractFacade<AportesObjetivo> {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AportesObjetivoFacade() {
        super(AportesObjetivo.class);
    }
    
    public List<AportesObjetivo> buscarPorObjetivo(ObjetivosAhorro objetivo) {
        return em.createQuery("SELECT a FROM AportesObjetivo a WHERE a.idObjetivo = :objetivo ORDER BY a.fechaAporte DESC", AportesObjetivo.class)
                 .setParameter("objetivo", objetivo)
                 .getResultList();
    }    
}
