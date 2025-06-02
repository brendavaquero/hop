/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Acceso;

import control.ResumenGastoDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import modelo.ResumenGastoDTO;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Named(value = "resumenGastoCDI")
@SessionScoped
public class ResumenGastoCDI implements Serializable {

    @Inject
    private UsuarioCDI usuarioCDI;

    @EJB
    private ResumenGastoDAO resumenGastoDAO;

    private List<ResumenGastoDTO> resumenMensual;

    private Integer mesSeleccionado;
    private Integer anioSeleccionado;

    @PostConstruct
    public void init() {
        //inicializa con el mes y año actual
        Calendar cal = Calendar.getInstance();
        mesSeleccionado = cal.get(Calendar.MONTH) + 1; //1-12
        anioSeleccionado = cal.get(Calendar.YEAR);
        cargarResumen();
    }

    public void cargarResumen() {
        Usuarios usuario = usuarioCDI.getUsuario();
        if (usuario != null && mesSeleccionado != null && anioSeleccionado != null) {
            resumenMensual = resumenGastoDAO.obtenerResumenPorMes(usuario, mesSeleccionado, anioSeleccionado);
        } else {
            resumenMensual = new ArrayList<>();
        }
    }

    public List<ResumenGastoDTO> getResumenMensual() {
        return resumenMensual;
    }

    public Integer getMesSeleccionado() {
        return mesSeleccionado;
    }

    public void setMesSeleccionado(Integer mesSeleccionado) {
        this.mesSeleccionado = mesSeleccionado;
    }

    public Integer getAnioSeleccionado() {
        return anioSeleccionado;
    }

    public void setAnioSeleccionado(Integer anioSeleccionado) {
        this.anioSeleccionado = anioSeleccionado;
    }
}

