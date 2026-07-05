package pe.edu.upc.inmovision.serviceinterfaces;

import pe.edu.upc.inmovision.entities.Calificacion;
import java.util.List;
import java.util.Optional;

public interface ICalificacionService {
    public Calificacion insertar(Calificacion calificacion);
    public List<Calificacion> listar();
    public Optional<Calificacion> listById(int id);
    public void delete(int id);
    public List<Calificacion> listarporpuntuacion(int puntuacion);
    public List<Calificacion>listarporfechareciente();
    public List<Calificacion>listarporfechaantigua();
}
