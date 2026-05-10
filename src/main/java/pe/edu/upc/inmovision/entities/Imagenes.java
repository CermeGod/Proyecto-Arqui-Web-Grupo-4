package pe.edu.upc.inmovision.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "imagenes")
public class Imagenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imagenesId;
    private String urlImagen;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "propiedadId")
    private Propiedades propiedad;

    public Imagenes() {
    }

    public Imagenes(int imagenesId, String urlImagen, String descripcion, Propiedades propiedad) {
        this.imagenesId = imagenesId;
        this.urlImagen = urlImagen;
        this.descripcion = descripcion;
        this.propiedad = propiedad;
    }

    public int getImagenesId() {
        return imagenesId;
    }

    public void setImagenesId(int imagenesId) {
        this.imagenesId = imagenesId;
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

    public Propiedades getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedades propiedad) {
        this.propiedad = propiedad;
    }
}
