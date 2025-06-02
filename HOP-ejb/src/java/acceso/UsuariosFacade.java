/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acceso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "HOP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    public Usuarios encontrarPorCorreoYContrasena(String correo, String contrasena) {
        try {
            return em.createQuery("SELECT u FROM Usuarios u WHERE u.correo = :correo AND u.contrasena = :contrasena", Usuarios.class)
                     .setParameter("correo", correo)
                     .setParameter("contrasena", contrasena)
                     .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
