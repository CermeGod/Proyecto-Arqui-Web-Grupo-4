package pe.edu.upc.inmovision.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "recomendaciones")
public class Recomendaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recomendacionId;

    @ManyToOne
    @JoinColumn(name = "propiedad_id", nullable = false)
    private Propiedades propiedad;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Recomendaciones() {
    }

    public Recomendaciones(int recomendacionId, Propiedades propiedad, Usuario usuario) {
        this.recomendacionId = recomendacionId;
        this.propiedad = propiedad;
        this.usuario = usuario;
    }

    public int getRecomendacionId() {
        return recomendacionId;
    }

    public void setRecomendacionId(int recomendacionId) {
        this.recomendacionId = recomendacionId;
    }

    public Propiedades getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedades propiedad) {
        this.propiedad = propiedad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
