/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Acceso;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;
import acceso.IngresosFacade;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Ingresos;

/**
 *
 * @author brend
 */
@Named(value = "ingresosCDI")
@SessionScoped
public class IngresosCDI implements Serializable {    
   @Inject
    private IngresosFacade ingresosFacade;

    @Inject
    private UsuarioCDI usuarioCDI;

    private BigDecimal monto;
    private Date fecha;
    private String descripcion;

    private List<Ingresos> listaIngresos;

    @PostConstruct
    public void init() {
        actualizarListaIngresos();
    }

    public void registrarIngreso() {
        Ingresos ingreso = new Ingresos();
        ingreso.setMonto(monto);
        ingreso.setFecha(fecha);
        ingreso.setDescripcion(descripcion);
        ingreso.setIdUsuario(usuarioCDI.getUsuario());

        ingresosFacade.create(ingreso);
        actualizarListaIngresos();

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso registrado", ""));

        limpiarFormulario();
        
    }

    public void actualizarListaIngresos() {
        listaIngresos = ingresosFacade.obtenerPorUsuario(usuarioCDI.getUsuario());
    }

    public void limpiarFormulario() {
        monto = null;
        fecha = null;
        descripcion = null;
    }    

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Ingresos> getListaIngresos() {
        return listaIngresos;
    }

    public void setListaIngresos(List<Ingresos> listaIngresos) {
        this.listaIngresos = listaIngresos;
    }
    
    
}
