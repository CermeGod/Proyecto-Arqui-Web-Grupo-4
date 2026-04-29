package pe.edu.upc.inmovision.dtos;

public class ImagenesDTO {
    private int imagenesId;
    private String urlImagen;
    private String descripcion;
    private int propiedadId;

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

    public int getPropiedadId() {
        return propiedadId;
    }

    public void setPropiedadId(int propiedadId) {
        this.propiedadId = propiedadId;
    }
}
