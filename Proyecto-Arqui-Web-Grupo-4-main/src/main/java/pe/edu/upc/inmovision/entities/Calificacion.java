package pe.edu.upc.inmovision.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "calificación")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int calificacionId;
    private int puntuacion;
    private String comentario;
    private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name = "propiedadId")
    private Propiedades propiedad;
    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    public Calificacion() {
    }

    public Calificacion(int calificacionId, int puntuacion, String comentario, LocalDate fecha, Propiedades propiedad, Usuario usuario) {
        this.calificacionId = calificacionId;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = fecha;
        this.propiedad = propiedad;
        this.usuario = usuario;
    }

    public int getCalificacionId() {
        return calificacionId;
    }

    public void setCalificacionId(int calificacionId) {
        this.calificacionId = calificacionId;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
