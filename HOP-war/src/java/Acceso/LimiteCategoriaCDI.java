/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Acceso;

import control.LimiteCategoriaDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import modelo.CategoriasGenerales;
import modelo.LimitesCategoriaUsuario;

/**
 *
 * @author brend
 */
@Named(value = "limiteCategoriaCDI")
@SessionScoped
public class LimiteCategoriaCDI implements Serializable {

    /**
     * Creates a new instance of LimiteCategoriaCDI
     */
    public LimiteCategoriaCDI() {
    }
    
    @Inject
    private ResumenGastosCDI resumenGastosCDI;

    @Inject
    private LimiteCategoriaDAO limiteCategoriaDAO;

    @Inject
    private UsuarioCDI usuarioCDI;

    private LimitesCategoriaUsuario nuevoLimite;
    private List<CategoriasGenerales> categoriasDisponibles;

    @PostConstruct
    public void init() {
        nuevoLimite = new LimitesCategoriaUsuario();
        nuevoLimite.setIdCategoria(new CategoriasGenerales()); // Clave
        categoriasDisponibles = limiteCategoriaDAO.obtenerCategorias();
    }

    public void guardar() {
        nuevoLimite.setIdUsuario(usuarioCDI.getUsuario());

        System.out.println("→ Entrando a guardar()");
        System.out.println("Usuario: " + nuevoLimite.getIdUsuario());
        System.out.println("Categoría: " + nuevoLimite.getIdCategoria().getIdCategoria());
        System.out.println("Monto: " + nuevoLimite.getMontoAsignado());

        if (limiteCategoriaDAO.existeLimite(nuevoLimite.getIdUsuario(), nuevoLimite.getIdCategoria())) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Ya existe un límite para esta categoría.", ""));
            return;
        }

        limiteCategoriaDAO.guardarLimite(nuevoLimite);

        nuevoLimite = new LimitesCategoriaUsuario();
        nuevoLimite.setIdCategoria(new CategoriasGenerales());

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Límite guardado con éxito.", ""));
        
        resumenGastosCDI.cargarResumen();

    }

    public LimitesCategoriaUsuario getNuevoLimite() { return nuevoLimite; }
    public void setNuevoLimite(LimitesCategoriaUsuario nuevoLimite) { this.nuevoLimite = nuevoLimite; }

    public List<CategoriasGenerales> getCategoriasDisponibles() { return categoriasDisponibles; }
}


