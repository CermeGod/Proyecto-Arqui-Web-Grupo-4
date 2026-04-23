package pe.edu.upc.inmovision.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imagenId;

    @ManyToOne
    @JoinColumn(name = "propiedad_id", nullable = false)
    private Propiedades propiedad;

    @Column(name = "url_imagen", nullable = false, length = 500)
    private String urlImagen;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

    public Imagen(){

    }

    public Imagen(int imagenId, Propiedades propiedad, String urlImagen, String descripcion) {
        this.imagenId = imagenId;
        this.propiedad = propiedad;
        this.urlImagen = urlImagen;
        this.descripcion = descripcion;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

    public Propiedades getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedades propiedad) {
        this.propiedad = propiedad;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
