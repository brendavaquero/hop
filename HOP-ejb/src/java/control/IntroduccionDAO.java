/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package control;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Stateless
@LocalBean
public class IntroduccionDAO {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    public boolean esUsuarioNuevo(Usuarios usuario) {
        return usuario != null && "1".equals(usuario.getNuevo());
    }

    public void marcarIntroduccionVista(Usuarios usuario) {
        usuario.setNuevo("0");
        em.merge(usuario);
    }
}


