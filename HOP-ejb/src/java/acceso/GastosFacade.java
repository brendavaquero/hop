/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acceso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Gastos;

/**
 *
 * @author brend
 */
@Stateless
public class GastosFacade extends AbstractFacade<Gastos> {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GastosFacade() {
        super(Gastos.class);
    }
    
}
