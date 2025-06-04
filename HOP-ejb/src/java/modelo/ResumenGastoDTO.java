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

    private CategoriasGenerales categoria;
    private String nombreCategoria;
    private BigDecimal montoAsignado;
    private BigDecimal montoGastado;
    private BigDecimal montoDisponible;

    public ResumenGastoDTO(CategoriasGenerales categoria, BigDecimal montoAsignado, BigDecimal montoGastado) {
        this.categoria = categoria;
        this.nombreCategoria = categoria.getNombre();
        this.montoAsignado = montoAsignado != null ? montoAsignado : BigDecimal.ZERO;
        this.montoGastado = montoGastado != null ? montoGastado : BigDecimal.ZERO;
        this.montoDisponible = this.montoAsignado.subtract(this.montoGastado);
    }

    public CategoriasGenerales getCategoria() {
        return categoria;
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
