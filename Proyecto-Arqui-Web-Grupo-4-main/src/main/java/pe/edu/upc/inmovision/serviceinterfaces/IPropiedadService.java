package pe.edu.upc.inmovision.serviceinterfaces;

import pe.edu.upc.inmovision.entities.Propiedades;

import java.util.List;
import java.util.Optional;

public interface IPropiedadService {
    public Propiedades insertar(Propiedades propiedades);
    public List<Propiedades> listar();
    public Optional<Propiedades> listById(int id);
    public void delete(int id);
}
