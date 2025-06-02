/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.math.BigDecimal;

/**
 *
 * @author brend
 */
public class ResumenGastoDTO {

    private String nombreCategoria;
    private BigDecimal montoAsignado;
    private BigDecimal montoGastado;
    private BigDecimal montoDisponible;

    public ResumenGastoDTO(String nombreCategoria, BigDecimal montoAsignado, BigDecimal montoGastado) {
        this.nombreCategoria = nombreCategoria;
        this.montoAsignado = montoAsignado;
        this.montoGastado = montoGastado;
        this.montoDisponible = montoAsignado.subtract(montoGastado);
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public BigDecimal getMontoAsignado() {
        return montoAsignado;
    }

    public BigDecimal getMontoGastado() {
        return montoGastado;
    }

    public BigDecimal getMontoDisponible() {
        return montoDisponible;
    }
}
