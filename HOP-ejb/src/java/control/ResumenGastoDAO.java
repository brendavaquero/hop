/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package control;

import acceso.CategoriasGastoFacade;
import acceso.GastosFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.CategoriasGasto;
import modelo.ResumenGastoDTO;
import modelo.Usuarios;

/**
 *
 * @author brend
 */
@Stateless
@LocalBean
public class ResumenGastoDAO {

    @EJB
    private CategoriasGastoFacade categoriasGastoFacade;

    @EJB
    private GastosFacade gastosFacade;

    public List<ResumenGastoDTO> obtenerResumenPorMes(Usuarios usuario, int mes, int anio) {
        List<ResumenGastoDTO> resumen = new ArrayList<>();

        List<CategoriasGasto> categorias = categoriasGastoFacade.categoriasPorUsuario(usuario);
        for (CategoriasGasto cat : categorias) {
            BigDecimal gastado = gastosFacade.totalGastadoPorCategoriaYMes(usuario, cat, mes, anio);
            BigDecimal asignado = cat.getMontoAsignado();
            resumen.add(new ResumenGastoDTO(cat.getNombre(), asignado, gastado));
        }

        return resumen;
    }
}

