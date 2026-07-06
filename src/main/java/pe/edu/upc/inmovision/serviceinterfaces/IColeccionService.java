package pe.edu.upc.inmovision.serviceinterfaces;

import pe.edu.upc.inmovision.entities.Coleccion;
import java.util.List;
import java.util.Optional;

public interface IColeccionService {
    public Coleccion insertar(Coleccion coleccion);
    public List<Coleccion> listar();
    public Optional<Coleccion> listById(int id);
    public void delete(int id);
}
