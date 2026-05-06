package pe.edu.upc.inmovision.serviceinterfaces;

import pe.edu.upc.inmovision.entities.Recomendaciones;

import java.util.List;
import java.util.Optional;

public interface IRecomendacionesService {
    public Recomendaciones insertar(Recomendaciones recomendaciones);
    public List<Recomendaciones> listar();
    public void delete(int id);
    public Optional<Recomendaciones> listById(int id);
    public Integer contarPorPropiedad(int idPropiedad);
    public List<Recomendaciones> buscarPorUsuario(int idUsuario);

}
