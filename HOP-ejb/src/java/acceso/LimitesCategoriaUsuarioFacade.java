/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acceso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.LimitesCategoriaUsuario;

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
    
}
