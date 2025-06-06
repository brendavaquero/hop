/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Acceso;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import modelo.Usuarios;
import acceso.UsuariosFacade;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author brend
 */
@Named(value = "registroCDI")
@SessionScoped
public class RegistroCDI implements Serializable {

    public RegistroCDI() {
    }
    

    private Usuarios usuario = new Usuarios();

    @EJB
    private UsuariosFacade usuariosFacade;

    public String registrarUsuario() {
        try {
            usuario.setNuevo("1");
            //usuario.setFechaRegistro(new Date());
            usuariosFacade.create(usuario);

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "Ya puedes iniciar sesión"));

            return "login.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar"));
            return null;
        }
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}

