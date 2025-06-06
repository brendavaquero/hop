/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acceso;

import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import modelo.CategoriasGenerales;
import acceso.CategoriasGeneralesFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;

/**
 *
 * @author brend
 */

@FacesConverter(value = "categoriasGeneralesConverter", managed = true)
public class CategoriasGeneralesConverter implements Converter<CategoriasGenerales> {

    @Override
    public CategoriasGenerales getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) return null;
        try {
            CategoriasGeneralesFacade categoriasFacade = (CategoriasGeneralesFacade) new InitialContext()
                .lookup("java:global/HOP/HOP-ejb/CategoriasGeneralesFacade");
            return categoriasFacade.find(Integer.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, CategoriasGenerales object) {
        if (object == null) return "";
        return String.valueOf(object.getIdCategoria());
    }
}

