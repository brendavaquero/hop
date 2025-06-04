/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author brend
 */
@Entity
@Table(name = "categorias_generales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriasGenerales.findAll", query = "SELECT c FROM CategoriasGenerales c"),
    @NamedQuery(name = "CategoriasGenerales.findByIdCategoria", query = "SELECT c FROM CategoriasGenerales c WHERE c.idCategoria = :idCategoria"),
    @NamedQuery(name = "CategoriasGenerales.findByNombre", query = "SELECT c FROM CategoriasGenerales c WHERE c.nombre = :nombre")})
public class CategoriasGenerales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria")
    private Integer idCategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
    private List<LimitesCategoriaUsuario> limitesCategoriaUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
    private List<Gastos> gastosList;

    public CategoriasGenerales() {
    }

    public CategoriasGenerales(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CategoriasGenerales(Integer idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<LimitesCategoriaUsuario> getLimitesCategoriaUsuarioList() {
        return limitesCategoriaUsuarioList;
    }

    public void setLimitesCategoriaUsuarioList(List<LimitesCategoriaUsuario> limitesCategoriaUsuarioList) {
        this.limitesCategoriaUsuarioList = limitesCategoriaUsuarioList;
    }

    @XmlTransient
    public List<Gastos> getGastosList() {
        return gastosList;
    }

    public void setGastosList(List<Gastos> gastosList) {
        this.gastosList = gastosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriasGenerales)) {
            return false;
        }
        CategoriasGenerales other = (CategoriasGenerales) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CategoriasGenerales[ idCategoria=" + idCategoria + " ]";
    }
    
}
