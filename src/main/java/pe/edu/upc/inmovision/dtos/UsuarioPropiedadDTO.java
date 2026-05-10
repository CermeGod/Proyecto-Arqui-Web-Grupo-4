package pe.edu.upc.inmovision.dtos;

public class UsuarioPropiedadDTO {
    private int usuarioId;
    private String nombre;
    private String apellido;
    private int totalPropiedades;

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTotalPropiedades() {
        return totalPropiedades;
    }

    public void setTotalPropiedades(int totalPropiedades) {
        this.totalPropiedades = totalPropiedades;
    }
}
