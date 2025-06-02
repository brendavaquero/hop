/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author brend
 */
@Entity
@Table(name = "objetivos_ahorro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetivosAhorro.findAll", query = "SELECT o FROM ObjetivosAhorro o"),
    @NamedQuery(name = "ObjetivosAhorro.findByIdObjetivo", query = "SELECT o FROM ObjetivosAhorro o WHERE o.idObjetivo = :idObjetivo"),
    @NamedQuery(name = "ObjetivosAhorro.findByNombre", query = "SELECT o FROM ObjetivosAhorro o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "ObjetivosAhorro.findByMontoMeta", query = "SELECT o FROM ObjetivosAhorro o WHERE o.montoMeta = :montoMeta"),
    @NamedQuery(name = "ObjetivosAhorro.findByMontoActual", query = "SELECT o FROM ObjetivosAhorro o WHERE o.montoActual = :montoActual"),
    @NamedQuery(name = "ObjetivosAhorro.findByFechaInicio", query = "SELECT o FROM ObjetivosAhorro o WHERE o.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "ObjetivosAhorro.findByFechaFin", query = "SELECT o FROM ObjetivosAhorro o WHERE o.fechaFin = :fechaFin")})
public class ObjetivosAhorro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_objetivo")
    private Integer idObjetivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_meta")
    private BigDecimal montoMeta;
    @Column(name = "monto_actual")
    private BigDecimal montoActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjetivo")
    private List<AportesObjetivo> aportesObjetivoList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public ObjetivosAhorro() {
    }

    public ObjetivosAhorro(Integer idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public ObjetivosAhorro(Integer idObjetivo, String nombre, BigDecimal montoMeta, Date fechaInicio, Date fechaFin) {
        this.idObjetivo = idObjetivo;
        this.nombre = nombre;
        this.montoMeta = montoMeta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(Integer idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMontoMeta() {
        return montoMeta;
    }

    public void setMontoMeta(BigDecimal montoMeta) {
        this.montoMeta = montoMeta;
    }

    public BigDecimal getMontoActual() {
        return montoActual;
    }

    public void setMontoActual(BigDecimal montoActual) {
        this.montoActual = montoActual;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public List<AportesObjetivo> getAportesObjetivoList() {
        return aportesObjetivoList;
    }

    public void setAportesObjetivoList(List<AportesObjetivo> aportesObjetivoList) {
        this.aportesObjetivoList = aportesObjetivoList;
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
        hash += (idObjetivo != null ? idObjetivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivosAhorro)) {
            return false;
        }
        ObjetivosAhorro other = (ObjetivosAhorro) object;
        if ((this.idObjetivo == null && other.idObjetivo != null) || (this.idObjetivo != null && !this.idObjetivo.equals(other.idObjetivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ObjetivosAhorro[ idObjetivo=" + idObjetivo + " ]";
    }
    
}
