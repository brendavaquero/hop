/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "categorias_gasto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriasGasto.findAll", query = "SELECT c FROM CategoriasGasto c"),
    @NamedQuery(name = "CategoriasGasto.findByIdCategoria", query = "SELECT c FROM CategoriasGasto c WHERE c.idCategoria = :idCategoria"),
    @NamedQuery(name = "CategoriasGasto.findByNombre", query = "SELECT c FROM CategoriasGasto c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CategoriasGasto.findByMontoAsignado", query = "SELECT c FROM CategoriasGasto c WHERE c.montoAsignado = :montoAsignado")})
public class CategoriasGasto implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_asignado")
    private BigDecimal montoAsignado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
    private List<Gastos> gastosList;

    public CategoriasGasto() {
    }

    public CategoriasGasto(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CategoriasGasto(Integer idCategoria, String nombre, BigDecimal montoAsignado) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.montoAsignado = montoAsignado;
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

    public BigDecimal getMontoAsignado() {
        return montoAsignado;
    }

    public void setMontoAsignado(BigDecimal montoAsignado) {
        this.montoAsignado = montoAsignado;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof CategoriasGasto)) {
            return false;
        }
        CategoriasGasto other = (CategoriasGasto) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CategoriasGasto[ idCategoria=" + idCategoria + " ]";
    }
    
}
