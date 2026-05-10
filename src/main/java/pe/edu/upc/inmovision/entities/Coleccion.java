package pe.edu.upc.inmovision.entities;

import jakarta.persistence.*;

@Entity
@Table(name ="coleccion")
public class Coleccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coleccionId;
    private String name;

    public Coleccion() {
    }

    public Coleccion(int coleccionId, String name) {
        this.coleccionId = coleccionId;
        this.name = name;
    }

    public int getColeccionId() {
        return coleccionId;
    }

    public void setColeccionId(int coleccionId) {
        this.coleccionId = coleccionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
