/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Acceso;

import control.TendenciasGastoDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
/*import java.math.BigDecimal;
import java.text.DateFormatSymbols;*/
import java.time.Year;
//import java.util.HashMap;
import java.util.List;
/*import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;*/
import javax.ejb.EJB;
import javax.inject.Inject;
//import modelo.ResumenGastoDTO;
import modelo.TendenciaGastoMensualDTO;
/*import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;*/

/**
 *
 * @author brend
 */
@Named(value = "tendenciasGastoCDI")
@SessionScoped
public class TendenciasGastoCDI implements Serializable {

    //private LineChartModel model;

    @EJB
    private TendenciasGastoDAO tendenciasDAO;

    @Inject
    private UsuarioCDI usuarioCDI;

    private int anioSeleccionado = Year.now().getValue();
    private List<TendenciaGastoMensualDTO> tendencias;
    
    /*public LineChartModel getModel() {
        if (model == null) {
            cargarTendencias();
        }
        return model;
    }*/

    public void cargarTendencias() {
        tendencias = tendenciasDAO.obtenerTendenciasPorAnio(usuarioCDI.getUsuario(), anioSeleccionado);
        /*model = new LineChartModel();

        Map<String, ChartSeries> seriesPorCategoria = new HashMap<>();

        for (TendenciaGastoMensualDTO t : tendencias) {
            String categoria = t.getNombreCategoria();
            String mes = getNombreMes(t.getMes());
            BigDecimal monto = t.getTotalGastado();

            ChartSeries serie = seriesPorCategoria.computeIfAbsent(categoria, c -> {
                ChartSeries s = new ChartSeries();
                s.setLabel(c);
                model.addSeries(s);
                return s;
            });

            serie.set(mes, monto);
        }

        model.setTitle("Tendencia de gastos por categoría");
        model.setLegendPosition("e");
        model.setShowPointLabels(true);
        model.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Monto gastado");
        yAxis.setMin(0);*/
    }

    /*private String getNombreMes(int mes) {
        return new DateFormatSymbols(new Locale("es")).getMonths()[mes - 1];
    }*/

    public int getAnioSeleccionado() {
        return anioSeleccionado;
    }

    public void setAnioSeleccionado(int anioSeleccionado) {
        this.anioSeleccionado = anioSeleccionado;
    }

    public TendenciasGastoDAO getTendenciasDAO() {
        return tendenciasDAO;
    }

    public void setTendenciasDAO(TendenciasGastoDAO tendenciasDAO) {
        this.tendenciasDAO = tendenciasDAO;
    }

    public List<TendenciaGastoMensualDTO> getTendencias() {
        return tendencias;
    }

    public void setTendencias(List<TendenciaGastoMensualDTO> tendencias) {
        this.tendencias = tendencias;
    }
    
    
}
