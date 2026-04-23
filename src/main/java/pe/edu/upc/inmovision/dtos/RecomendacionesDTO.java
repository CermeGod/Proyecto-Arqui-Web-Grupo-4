package pe.edu.upc.inmovision.dtos;

import pe.edu.upc.inmovision.entities.Propiedades;
import pe.edu.upc.inmovision.entities.Usuario;

public class RecomendacionesDTO {
    private int recomendacionId;
    private Propiedades propiedad;
    private Usuario usuario;

    public RecomendacionesDTO() {
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
