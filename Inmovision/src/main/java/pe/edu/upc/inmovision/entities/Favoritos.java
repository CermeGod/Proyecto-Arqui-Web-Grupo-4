package pe.edu.upc.inmovision.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "favoritos")
public class Favoritos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favoritoId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "propiedad_id", nullable = false)
    private Propiedades propiedad;

    @ManyToOne
    @JoinColumn(name = "coleccion_id")
    private Coleccion coleccion;

    public Favoritos(){

    }

    public Favoritos(int favoritoId, Usuario usuario, Propiedades propiedad, Coleccion coleccion) {
        this.favoritoId = favoritoId;
        this.usuario = usuario;
        this.propiedad = propiedad;
        this.coleccion = coleccion;
    }

    public Coleccion getColeccion() {
        return coleccion;
    }

    public void setColeccion(Coleccion coleccion) {
        this.coleccion = coleccion;
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

    public int getFavoritoId() {
        return favoritoId;
    }

    public void setFavoritoId(int favoritoId) {
        this.favoritoId = favoritoId;
    }
}