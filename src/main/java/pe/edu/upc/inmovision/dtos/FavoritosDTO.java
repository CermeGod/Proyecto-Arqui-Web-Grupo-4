package pe.edu.upc.inmovision.dtos;

public class FavoritosDTO {
    private int favoritosId;
    private int usuarioId;
    private int propiedadId;
    private int coleccionId;

    public int getFavoritosId() {
        return favoritosId;
    }

    public void setFavoritosId(int favoritosId) {
        this.favoritosId = favoritosId;
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

    public int getColeccionId() {
        return coleccionId;
    }

    public void setColeccionId(int coleccionId) {
        this.coleccionId = coleccionId;
    }
}
