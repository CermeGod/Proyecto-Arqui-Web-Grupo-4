package pe.edu.upc.inmovision.entities;

import jakarta.persistence.*;

@Entity
@Table(name ="coleccion")
public class Coleccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coleccionId;
    private String name;

    public Long getColeccionId() {
        return coleccionId;
    }

    public void setColeccionId(Long coleccionId) {
        this.coleccionId = coleccionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
