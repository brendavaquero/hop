/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Acceso;

import control.BalanceDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author brend
 */
@Named(value = "balanceCDI")
@SessionScoped
public class BalanceCDI implements Serializable {

    public BalanceCDI() {
    }   

    @Inject
    private UsuarioCDI usuarioCDI;

    @EJB
    private BalanceDAO balanceDAO;

    private BigDecimal ingresos;
    private BigDecimal gastos;
    private BigDecimal balance;
    private String mensaje;
    private BigDecimal aportes;

    @PostConstruct
    public void init() {
        if (usuarioCDI.getUsuario() != null) {
            calcularBalance();
        }
    }

    public void calcularBalance() {
        ingresos = balanceDAO.obtenerTotalIngresos(usuarioCDI.getUsuario());
        gastos = balanceDAO.obtenerTotalGastos(usuarioCDI.getUsuario());
        aportes = balanceDAO.obtenerTotalAportes(usuarioCDI.getUsuario());
        balance = ingresos.subtract(gastos.add(aportes));

        BigDecimal porcentajeGastado = ingresos.compareTo(BigDecimal.ZERO) > 0
            ? gastos.multiply(BigDecimal.valueOf(100)).divide(ingresos, 2, RoundingMode.HALF_UP)
            : BigDecimal.ZERO;

        if (porcentajeGastado.compareTo(BigDecimal.valueOf(90)) >= 0) {
            mensaje = "Estás por terminar tus ingresos";
        } else if (porcentajeGastado.compareTo(BigDecimal.valueOf(70)) >= 0) {
            mensaje = "Has utilizado más del 70% de tus ingresos";
        } else {
            mensaje = "Tu gasto está bajo control";
        }
    }
    public BigDecimal getIngresos() { return ingresos; }
    public BigDecimal getGastos() { return gastos; }
    public BigDecimal getBalance() { return balance; }
    public String getMensaje() { return mensaje; }

    public BigDecimal getAportes() {
        return aportes;
    }
    
}
