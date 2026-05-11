package pe.edu.upc.inmovision.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "contacto")
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactoId;
    private String nombre;
    private String correo;
    private String telefono;
    private String mensaje;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "propiedadId")
    private Propiedades propiedad;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    public Contacto() {
    }

    public Contacto(int contactoId, String nombre, String correo, String telefono, String mensaje, LocalDate fecha, Propiedades propiedad, Usuario usuario) {
        this.contactoId = contactoId;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.propiedad = propiedad;
        this.usuario = usuario;
    }

    public int getContactoId() {
        return contactoId;
    }

    public void setContactoId(int contactoId) {
        this.contactoId = contactoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
