/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author brend
 */
@Entity
@Table(name = "aportes_objetivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AportesObjetivo.findAll", query = "SELECT a FROM AportesObjetivo a"),
    @NamedQuery(name = "AportesObjetivo.findByIdAporte", query = "SELECT a FROM AportesObjetivo a WHERE a.idAporte = :idAporte"),
    @NamedQuery(name = "AportesObjetivo.findByFechaAporte", query = "SELECT a FROM AportesObjetivo a WHERE a.fechaAporte = :fechaAporte"),
    @NamedQuery(name = "AportesObjetivo.findByMontoAportado", query = "SELECT a FROM AportesObjetivo a WHERE a.montoAportado = :montoAportado")})
public class AportesObjetivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_aporte")
    private Integer idAporte;
    @Column(name = "fecha_aporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAporte;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_aportado")
    private BigDecimal montoAportado;
    @JoinColumn(name = "id_objetivo", referencedColumnName = "id_objetivo")
    @ManyToOne(optional = false)
    private ObjetivosAhorro idObjetivo;

    public AportesObjetivo() {
    }

    public AportesObjetivo(Integer idAporte) {
        this.idAporte = idAporte;
    }

    public AportesObjetivo(Integer idAporte, BigDecimal montoAportado) {
        this.idAporte = idAporte;
        this.montoAportado = montoAportado;
    }

    public Integer getIdAporte() {
        return idAporte;
    }

    public void setIdAporte(Integer idAporte) {
        this.idAporte = idAporte;
    }

    public Date getFechaAporte() {
        return fechaAporte;
    }

    public void setFechaAporte(Date fechaAporte) {
        this.fechaAporte = fechaAporte;
    }

    public BigDecimal getMontoAportado() {
        return montoAportado;
    }

    public void setMontoAportado(BigDecimal montoAportado) {
        this.montoAportado = montoAportado;
    }

    public ObjetivosAhorro getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(ObjetivosAhorro idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAporte != null ? idAporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AportesObjetivo)) {
            return false;
        }
        AportesObjetivo other = (AportesObjetivo) object;
        if ((this.idAporte == null && other.idAporte != null) || (this.idAporte != null && !this.idAporte.equals(other.idAporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AportesObjetivo[ idAporte=" + idAporte + " ]";
    }
    
}
