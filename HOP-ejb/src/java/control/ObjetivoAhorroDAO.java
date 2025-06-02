/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package control;

import acceso.ObjetivosAhorroFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.ObjetivosAhorro;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Stateless
@LocalBean
public class ObjetivoAhorroDAO {

    @EJB
    private ObjetivosAhorroFacade objetivosAhorroFacade;

    public List<ObjetivosAhorro> objetivosAhorro() {
        return objetivosAhorroFacade.findAll();
    }

    public void crearObjetivo(ObjetivosAhorro objetivo) {
        objetivosAhorroFacade.create(objetivo);
    }

    public ObjetivosAhorro buscarPorId(Long id) {
        return objetivosAhorroFacade.find(id);
    }

    public void actualizarObjetivo(ObjetivosAhorro objetivo) {
        objetivosAhorroFacade.edit(objetivo);
    }

    public void eliminarObjetivo(ObjetivosAhorro objetivo) {
        objetivosAhorroFacade.remove(objetivo);
    }
    
    public List<ObjetivosAhorro> obtenerPorUsuario(Usuarios usuario) {
        return objetivosAhorroFacade.findByUsuario(usuario);
    }
    
    
}


