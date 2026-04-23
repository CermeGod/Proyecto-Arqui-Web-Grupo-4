package pe.edu.upc.inmovision.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "propiedades")
public class Propiedades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int propiedadId;

    @Column(name = "titulo",nullable = false,length = 50)
    private String titulo;

    @Column(name = "descripcion",nullable = false,length = 300)
    private String descripcion;

    @Column(name = "precio",nullable = false)
    private Double precio;

    @Column(name = "direccion",nullable = false,length = 150)
    private String direccion;

    @Column(name = "fecha",nullable = false)
    private LocalDate fecha;

    @Column(name = "estado",nullable = false)
    private Boolean estado;

    @Column(name = "area",nullable = false)
    private Double area;

    @Column(name = "habitacion",nullable = false)
    private int habitacion;

    @Column(name = "banios",nullable = false)
    private int banios;

    @Column(name = "url_VR",nullable = false,length = 500)
    private String urlVR;


    public Propiedades() {
    }

    public Propiedades(int propiedadId, String titulo, String descripcion, Double precio, String direccion,
                       LocalDate fecha, Boolean estado, Double area, int habitacion, int banios, String urlVR) {
        this.propiedadId = propiedadId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.direccion = direccion;
        this.fecha = fecha;
        this.estado = estado;
        this.area = area;
        this.habitacion = habitacion;
        this.banios = banios;
        this.urlVR = urlVR;
    }

    public int getPropiedadId() {
        return propiedadId;
    }

    public void setPropiedadId(int propiedadId) {
        this.propiedadId = propiedadId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public int getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(int habitacion) {
        this.habitacion = habitacion;
    }

    public int getBanios() {
        return banios;
    }

    public void setBanios(int banios) {
        this.banios = banios;
    }

    public String getUrlVR() {
        return urlVR;
    }

    public void setUrlVR(String urlVR) {
        this.urlVR = urlVR;
    }
}
