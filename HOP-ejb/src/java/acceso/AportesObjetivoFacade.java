/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acceso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.AportesObjetivo;

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
    
}
