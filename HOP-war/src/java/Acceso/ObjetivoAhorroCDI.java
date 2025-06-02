/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Acceso;

import control.ObjetivoAhorroDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import modelo.ObjetivosAhorro;
import acceso.AportesObjetivoFacade;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Usuarios;
import modelo.AportesObjetivo;

/**
 *
 * @author brend
 */
@Named(value = "objetivoAhorroCDI")
@SessionScoped
public class ObjetivoAhorroCDI implements Serializable {

    @Inject
    private ObjetivoAhorroDAO objetivoAhorroDAO;

    @Inject
    private UsuarioCDI usuarioCDI;

    private ObjetivosAhorro nuevoObjetivo;
    private List<ObjetivosAhorro> listaObjetivos;
    
    //nuevos, para acceder a los aportes por objetivo
    private List<AportesObjetivo> historialAportes;
    private ObjetivosAhorro objetivoParaHistorial;

    @PostConstruct
    public void init() {
        nuevoObjetivo = new ObjetivosAhorro();
        cargarObjetivos();
    }

    /*public void cargarObjetivos() {
        Usuarios usuario = usuarioCDI.getUsuario();
        if (usuario != null) {
            listaObjetivos = objetivoAhorroDAO.obtenerPorUsuario(usuario);
        }
    }*/
    
    public void cargarObjetivos() {
        Usuarios usuario = usuarioCDI.getUsuario(); //usuario log
        if (usuario != null) {
            listaObjetivos = objetivoAhorroDAO.obtenerPorUsuario(usuario);
        } else {
            listaObjetivos = new ArrayList<>();
        }
    }


    public void guardarObjetivo() {
        Usuarios u = usuarioCDI.getUsuario();
        if (u == null) {
            return; //redirigir al login
        }
        //usuario en sesión:
        nuevoObjetivo.setIdUsuario(usuarioCDI.getUsuario());
        nuevoObjetivo.setMontoActual(BigDecimal.ZERO);
        objetivoAhorroDAO.crearObjetivo(nuevoObjetivo);
        //reiniciar para la próxima inserción
        nuevoObjetivo = new ObjetivosAhorro();
        cargarObjetivos();
    }

    public void eliminarObjetivo(ObjetivosAhorro o) {
        objetivoAhorroDAO.eliminarObjetivo(o);
        cargarObjetivos();
    }    

    public ObjetivosAhorro getNuevoObjetivo() {
        return nuevoObjetivo;
    }
    public void setNuevoObjetivo(ObjetivosAhorro nuevoObjetivo) {
        this.nuevoObjetivo = nuevoObjetivo;
    }

    public List<ObjetivosAhorro> getListaObjetivos() {
        return listaObjetivos;
    }
    public void setListaObjetivos(List<ObjetivosAhorro> listaObjetivos) {
        this.listaObjetivos = listaObjetivos;
    }
    
    @Inject
    private AportesObjetivoFacade aportesObjetivoFacade;

    private AportesObjetivo nuevoAporte;
    private ObjetivosAhorro objetivoSeleccionado;

    public void seleccionarObjetivo(ObjetivosAhorro objetivo) {
        this.objetivoSeleccionado = objetivo;
        this.nuevoAporte = new AportesObjetivo();
    }

    public void guardarAporte() {
        if (objetivoSeleccionado == null || nuevoAporte == null || nuevoAporte.getMontoAportado() == null) {
            return;
        }

        BigDecimal montoActual = objetivoSeleccionado.getMontoActual() != null
            ? objetivoSeleccionado.getMontoActual()
            : BigDecimal.ZERO; // si el monto es null lo sustituye por cero

        BigDecimal montoMeta = objetivoSeleccionado.getMontoMeta();
        BigDecimal montoAportado = nuevoAporte.getMontoAportado();

        //no permitir que se exceda la meta
        if (montoActual.add(montoAportado).compareTo(montoMeta) > 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                "El aporte excede la meta de ahorro establecida", null));
            return;
        }

        // Guardar el aporte
        nuevoAporte.setFechaAporte(new Date());
        nuevoAporte.setIdObjetivo(objetivoSeleccionado);
        aportesObjetivoFacade.create(nuevoAporte);

        // Actualizar el monto actual
        objetivoSeleccionado.setMontoActual(montoActual.add(montoAportado));
        objetivoAhorroDAO.actualizarObjetivo(objetivoSeleccionado);

        // Refrescar
        cargarObjetivos();
        nuevoAporte = null;
        objetivoSeleccionado = null;
    }

    public void verHistorial(ObjetivosAhorro objetivo) {
        this.objetivoParaHistorial = objetivo;
        this.historialAportes = aportesObjetivoFacade.buscarPorObjetivo(objetivo);
    }

    public List<AportesObjetivo> getHistorialAportes() {
        return historialAportes;
    }

    public ObjetivosAhorro getObjetivoParaHistorial() {
        return objetivoParaHistorial;
    }

    public ObjetivosAhorro getObjetivoSeleccionado() {
        return objetivoSeleccionado;
    }

    public void setObjetivoSeleccionado(ObjetivosAhorro objetivoSeleccionado) {
        this.objetivoSeleccionado = objetivoSeleccionado;
    }

    public AportesObjetivo getNuevoAporte() {
        return nuevoAporte;
    }

    public void setNuevoAporte(AportesObjetivo nuevoAporte) {
        this.nuevoAporte = nuevoAporte;
    }
    
    
}


