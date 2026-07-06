package pe.edu.upc.inmovision.entities;

import jakarta.persistence.*;

@Entity
@Table(name ="favoritos")
public class Favoritos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favoritosId;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "propiedadId")
    private Propiedades propiedad;

    @ManyToOne
    @JoinColumn(name = "coleccionId")
    private Coleccion coleccion;

    public Favoritos() {
    }

    public Favoritos(int favoritosId, Usuario usuario, Propiedades propiedad, Coleccion coleccion) {
        this.favoritosId = favoritosId;
        this.usuario = usuario;
        this.propiedad = propiedad;
        this.coleccion = coleccion;
    }

    public int getFavoritosId() {
        return favoritosId;
    }

    public void setFavoritosId(int favoritosId) {
        this.favoritosId = favoritosId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Propiedades getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedades propiedad) {
        this.propiedad = propiedad;
    }

    public Coleccion getColeccion() {
        return coleccion;
    }

    public void setColeccion(Coleccion coleccion) {
        this.coleccion = coleccion;
    }
}
