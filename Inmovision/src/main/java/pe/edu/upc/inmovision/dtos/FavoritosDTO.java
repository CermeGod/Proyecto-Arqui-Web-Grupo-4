package pe.edu.upc.inmovision.dtos;

public class FavoritosDTO {

    private int favoritoId;
    private int usuarioId;
    private int propiedadId;
    private Integer coleccionId;

    public int getFavoritoId() {
        return favoritoId;
    }

    public void setFavoritoId(int favoritoId) {
        this.favoritoId = favoritoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPropiedadId() {
        return propiedadId;
    }

    public void setPropiedadId(int propiedadId) {
        this.propiedadId = propiedadId;
    }

    public Integer getColeccionId() {
        return coleccionId;
    }

    public void setColeccionId(Integer coleccionId) {
        this.coleccionId = coleccionId;
    }
}
