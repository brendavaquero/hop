/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author brend
 */
@Entity
@Table(name = "limites_categoria_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LimitesCategoriaUsuario.findAll", query = "SELECT l FROM LimitesCategoriaUsuario l"),
    @NamedQuery(name = "LimitesCategoriaUsuario.findByIdLimite", query = "SELECT l FROM LimitesCategoriaUsuario l WHERE l.idLimite = :idLimite"),
    @NamedQuery(name = "LimitesCategoriaUsuario.findByMontoAsignado", query = "SELECT l FROM LimitesCategoriaUsuario l WHERE l.montoAsignado = :montoAsignado")})
public class LimitesCategoriaUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_limite")
    private Integer idLimite;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_asignado")
    private BigDecimal montoAsignado;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne(optional = false)
    private CategoriasGenerales idCategoria;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public LimitesCategoriaUsuario() {
    }

    public LimitesCategoriaUsuario(Integer idLimite) {
        this.idLimite = idLimite;
    }

    public LimitesCategoriaUsuario(Integer idLimite, BigDecimal montoAsignado) {
        this.idLimite = idLimite;
        this.montoAsignado = montoAsignado;
    }

    public Integer getIdLimite() {
        return idLimite;
    }

    public void setIdLimite(Integer idLimite) {
        this.idLimite = idLimite;
    }

    public BigDecimal getMontoAsignado() {
        return montoAsignado;
    }

    public void setMontoAsignado(BigDecimal montoAsignado) {
        this.montoAsignado = montoAsignado;
    }

    public CategoriasGenerales getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CategoriasGenerales idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLimite != null ? idLimite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LimitesCategoriaUsuario)) {
            return false;
        }
        LimitesCategoriaUsuario other = (LimitesCategoriaUsuario) object;
        if ((this.idLimite == null && other.idLimite != null) || (this.idLimite != null && !this.idLimite.equals(other.idLimite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.LimitesCategoriaUsuario[ idLimite=" + idLimite + " ]";
    }
    
}
