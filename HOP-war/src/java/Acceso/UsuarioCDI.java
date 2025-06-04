/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Acceso;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import modelo.Usuarios;

/**
 *
 * @author brend
 */


@Named(value = "usuarioCDI")
@SessionScoped
public class UsuarioCDI implements Serializable {

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
}

