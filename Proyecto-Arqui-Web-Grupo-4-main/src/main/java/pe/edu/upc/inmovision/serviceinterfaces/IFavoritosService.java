package pe.edu.upc.inmovision.serviceinterfaces;

import pe.edu.upc.inmovision.entities.Favoritos;

import java.util.List;
import java.util.Optional;

public interface IFavoritosService {
    public Favoritos insertar(Favoritos favoritos);
    public List<Favoritos> listar();
    public Optional<Favoritos> buscarPorId(int id);
    public void delete(int id);
    public void update(Favoritos favoritos);
    public List<Object[]> cantidadFavoritosPorUsuario();
}
