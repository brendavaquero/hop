/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Acceso;

import control.IntroduccionDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import modelo.Usuarios;

/**
 *
 * @author brend
 */


@Named(value = "usuarioCDI")
@SessionScoped
public class UsuarioCDI implements Serializable {

    public UsuarioCDI() {
    }   
    
    private Usuarios usuario;    
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public boolean isAutenticado() {
        return usuario != null;
    }
    
    @Inject
    private IntroduccionDAO introduccionDAO;

    public boolean mostrarGuia() {
        return introduccionDAO.esUsuarioNuevo(usuario);
    }
    public void marcarGuiaVista() {
        introduccionDAO.marcarIntroduccionVista(usuario);
    }

    /*public void verificarGuia() {
        if (mostrarGuia()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,"Bienvenid@", "Te mostraremos una guía rápida para comenzar"));
            marcarGuiaVista();
        }
    }*/
    


}

