package pe.edu.upc.inmovision.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "distrito")
public class Distrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int distritoId;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "provinciaId")
    private Provincia provincia;

    public Distrito() {
    }

    public Distrito(int distritoId, String nombre, Provincia provincia) {
        this.distritoId = distritoId;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public int getDistritoId() {
        return distritoId;
    }

    public void setDistritoId(int distritoId) {
        this.distritoId = distritoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}
