/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Acceso;

import acceso.UsuariosFacade;
import modelo.Usuarios;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author brend
 */
@Named(value = "loginCDI")
@SessionScoped
public class LoginCDI implements Serializable {

    public LoginCDI() {
    }
    
    @EJB
    private UsuariosFacade usuariosFacade;

    @Inject
    private UsuarioCDI usuarioCDI;

    //precargar la lista
    @Inject
    private ObjetivoAhorroCDI objetivoAhorroCDI;

    private String correo;
    private String contrasena;

    public String iniciarSesion() {
        Usuarios u = usuariosFacade.encontrarPorCorreoYContrasena(correo, contrasena);
        if (u != null) {
            usuarioCDI.setUsuario(u);
            // aquí se mostrará la guía introductoria
            if (usuarioCDI.mostrarGuia()) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getFlash().setKeepMessages(true); //conservar mensajesa través de la redirección
                context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Bienvenid@", "Te mostraremos una guía rápida para comenzar"));
                usuarioCDI.marcarGuiaVista();
            }
            /*if (usuarioCDI.mostrarGuia()) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", "Te mostraremos una guía rápida para comenzar"));
                usuarioCDI.marcarGuiaVista();
            }*/

            return "menu.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales inválidas", ""));
            return null;
        }
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }

    public String getCorreo() { 
        return correo; 
    }
    public void setCorreo(String correo) { 
        this.correo = correo;
    }
    public String getContrasena() { 
        return contrasena; 
    }
    public void setContrasena(String contrasena) { 
        this.contrasena = contrasena; 
    }
}
