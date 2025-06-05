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
public class TendenciaGastoMensualDTO {
    private int mes;
    private String nombreCategoria;
    private BigDecimal totalGastado;

    public TendenciaGastoMensualDTO(int mes, String nombreCategoria, BigDecimal totalGastado) {
        this.mes = mes;
        this.nombreCategoria = nombreCategoria;
        this.totalGastado = totalGastado;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public BigDecimal getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(BigDecimal totalGastado) {
        this.totalGastado = totalGastado;
    }

    
}
