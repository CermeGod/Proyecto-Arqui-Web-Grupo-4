package pe.edu.upc.inmovision.dtos;

import pe.edu.upc.inmovision.entities.Propiedades;
import pe.edu.upc.inmovision.entities.Usuario;

public class RecomendacionesDTO {
    private int recomendacionId;
    private int propiedadId;
    private int usuarioId;

    public int getRecomendacionId() {
        return recomendacionId;
    }

    public void setRecomendacionId(int recomendacionId) {
        this.recomendacionId = recomendacionId;
    }

    public int getPropiedadId() {
        return propiedadId;
    }

    public void setPropiedadId(int propiedadId) {
        this.propiedadId = propiedadId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
