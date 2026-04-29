package pe.edu.upc.inmovision.serviceinterfaces;

import pe.edu.upc.inmovision.entities.Imagenes;

import java.util.List;
import java.util.Optional;

public interface IImagenesService {
    public Imagenes insertar(Imagenes imagen);
    public List<Imagenes> listar();
    public Optional<Imagenes> buscarPorId(int id);
    public void delete(int id);
    public void update(Imagenes imagen);
}
