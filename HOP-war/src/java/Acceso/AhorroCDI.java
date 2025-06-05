/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Acceso;

import control.ResumenAhorroDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author brend
 */
@Named(value = "ahorroCDI")
@SessionScoped
public class AhorroCDI implements Serializable {

    /**
     * Creates a new instance of AhorroCDI
     */
    public AhorroCDI() {
    }


    @Inject
    private UsuarioCDI usuarioCDI;

    @EJB
    private ResumenAhorroDAO resumenAhorroDAO;

    private int mesSeleccionado;  // valores de 1 a 12
    private int anioSeleccionado; // por ejemplo 2025

    private BigDecimal ingresos;
    private BigDecimal gastos;
    private BigDecimal tasaAhorro;

    public void calcularTasaAhorro() {
        if (usuarioCDI.getUsuario() == null || mesSeleccionado < 1 || mesSeleccionado > 12 || anioSeleccionado < 1900) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Parámetros de periodo inválidos."));
            return;
        }

        ingresos = resumenAhorroDAO.obtenerTotalIngresos(usuarioCDI.getUsuario(), mesSeleccionado, anioSeleccionado);
        gastos = resumenAhorroDAO.obtenerTotalGastos(usuarioCDI.getUsuario(), mesSeleccionado, anioSeleccionado);

        if (ingresos.compareTo(BigDecimal.ZERO) > 0) {
            tasaAhorro = (ingresos.subtract(gastos))
                    .divide(ingresos, 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
        } else {
            tasaAhorro = BigDecimal.ZERO;
        }
    }

    public int getMesSeleccionado() {
        return mesSeleccionado;
    }

    public void setMesSeleccionado(int mesSeleccionado) {
        this.mesSeleccionado = mesSeleccionado;
    }

    public int getAnioSeleccionado() {
        return anioSeleccionado;
    }

    public void setAnioSeleccionado(int anioSeleccionado) {
        this.anioSeleccionado = anioSeleccionado;
    }

    public BigDecimal getIngresos() {
        return ingresos;
    }

    public void setIngresos(BigDecimal ingresos) {
        this.ingresos = ingresos;
    }

    public BigDecimal getGastos() {
        return gastos;
    }

    public void setGastos(BigDecimal gastos) {
        this.gastos = gastos;
    }
    
    public BigDecimal getTasaAhorro() {
        return tasaAhorro;
    }

    public void setTasaAhorro(BigDecimal tasaAhorro) {
        this.tasaAhorro = tasaAhorro;
    }

}
