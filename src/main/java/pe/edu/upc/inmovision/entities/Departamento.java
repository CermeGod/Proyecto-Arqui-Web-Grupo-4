package pe.edu.upc.inmovision.entities;

import jakarta.persistence.*;

@Entity
@Table(name ="departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departamentoId;
    private String name;

    public Departamento() {
    }

    public Departamento(int departamentoId, String name) {
        this.departamentoId = departamentoId;
        this.name = name;
    }

    public int getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(int departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
