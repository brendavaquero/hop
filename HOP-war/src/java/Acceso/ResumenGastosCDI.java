/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Acceso;

import control.DesgloseGastosDAO;
import control.ResumenGastosDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import modelo.CategoriasGenerales;
import modelo.Gastos;
import modelo.ResumenGastoDTO;
import acceso.GastosFacade;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author brend
 */
@Named(value = "resumenGastosCDI")
@SessionScoped
public class ResumenGastosCDI implements Serializable {

    /**
     * Creates a new instance of ResumenGastosCDI
     */
    public ResumenGastosCDI() {
    }
    
    private Gastos nuevoGasto;

    
    //detalle individual de gastos
    @EJB
    private DesgloseGastosDAO desgloseGastosDAO;
    private List<Gastos> desgloseActual;
    private CategoriasGenerales categoriaSeleccionada;

    //resumen mensual
    @EJB
    private ResumenGastosDAO resumenGastosDAO;
    @Inject
    private UsuarioCDI usuarioCDI;
    private int mes;  //1-12
    private int anio;
    private List<ResumenGastoDTO> resumenMensual;

    @PostConstruct
    public void init() {
        LocalDate ahora = LocalDate.now();
        mes = ahora.getMonthValue();
        anio = ahora.getYear();

        if (usuarioCDI != null && usuarioCDI.getUsuario() != null) {
            cargarResumen();
        } else {
            resumenMensual = new ArrayList<>();
        }
    }


    public void cargarResumen() {
    try {
        resumenMensual = resumenGastosDAO.obtenerResumenMensual(usuarioCDI.getUsuario(), mes, anio);
    } catch (Exception e) {
        resumenMensual = new ArrayList<>();
        System.err.println("######Error: " + e.getMessage());
        e.printStackTrace();
    }
}


    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public List<ResumenGastoDTO> getResumenMensual() {
        return resumenMensual;
    }
    
    public void verDesglose(CategoriasGenerales categoria) {
        this.categoriaSeleccionada = categoria;
        desgloseActual = desgloseGastosDAO.obtenerPorCategoriaYFecha(
            usuarioCDI.getUsuario(), categoria, mes, anio);
    }
    
    public List<Gastos> getDesgloseActual() {
        return desgloseActual;
    }

    public CategoriasGenerales getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    public Gastos getNuevoGasto() {
        if (nuevoGasto == null) {
            nuevoGasto = new Gastos();
        }
        return nuevoGasto;
    }
    
    /*public void prepararNuevoGasto() {
        nuevoGasto = new Gastos();
        nuevoGasto.setIdUsuario(usuarioCDI.getUsuario());
        nuevoGasto.setIdCategoria(categoriaSeleccionada);
        //cambiar
        nuevoGasto.setFechaGasto(new Date());
    }*/

    public void prepararNuevoGasto(CategoriasGenerales categoria) {
        this.categoriaSeleccionada = categoria;

        nuevoGasto = new Gastos();
        nuevoGasto.setIdUsuario(usuarioCDI.getUsuario());
        nuevoGasto.setIdCategoria(categoria);
        nuevoGasto.setFechaGasto(new Date());
    }
    @Inject
    private GastosFacade gastosFacade; 

    public void guardarGasto() {
    if (nuevoGasto != null) {
        gastosFacade.create(nuevoGasto);
        //recargar desglose y resumen para reflejar el gasto recién registrado:
        cargarResumen();
        verDesglose(categoriaSeleccionada);
        nuevoGasto = null;

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Gasto registrado correctamente", ""));
    }
}

    
}
