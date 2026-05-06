package pe.edu.upc.inmovision.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "provincia")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int provinciaId;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "departamentoId")
    private Departamento departamento;

    public Provincia() {
    }

    public Provincia(int provinciaId, String nombre, Departamento departamento) {
        this.provinciaId = provinciaId;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public int getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(int provinciaId) {
        this.provinciaId = provinciaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
