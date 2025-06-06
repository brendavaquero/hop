/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Acceso;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;
import acceso.GastosFacade;
import acceso.CategoriasGeneralesFacade;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.CategoriasGenerales;
import modelo.Gastos;

/**
 *
 * @author brend
 */
@Named(value = "registrarGastoCDI")
@SessionScoped
public class RegistrarGastoCDI implements Serializable {

    /**
     * Creates a new instance of RegistrarGastoCDI
     */
    public RegistrarGastoCDI() {
    }
    
    @EJB
    private CategoriasGeneralesFacade categoriasGeneralesFacade;

    @Inject
    private GastosFacade gastosFacade;

    @Inject
    private UsuarioCDI usuarioCDI;

    

    private Gastos nuevoGasto;
    private List<CategoriasGenerales> categoriasDisponibles;

    @PostConstruct
    public void init() {
        nuevoGasto = new Gastos();
        nuevoGasto.setFechaGasto(new Date());
        categoriasDisponibles = categoriasGeneralesFacade.findAll();
    }

    public void guardar() {
        nuevoGasto.setIdUsuario(usuarioCDI.getUsuario());

        gastosFacade.create(nuevoGasto);

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Gasto registrado correctamente.", ""));

        //reiniciar para nuevo gasto
        nuevoGasto = new Gastos();
        nuevoGasto.setFechaGasto(new Date());
    }
    public Gastos getNuevoGasto() {
        return nuevoGasto;
    }

    public void setNuevoGasto(Gastos nuevoGasto) {
        this.nuevoGasto = nuevoGasto;
    }

    public List<CategoriasGenerales> getCategoriasDisponibles() {
        return categoriasDisponibles;
    }

}
