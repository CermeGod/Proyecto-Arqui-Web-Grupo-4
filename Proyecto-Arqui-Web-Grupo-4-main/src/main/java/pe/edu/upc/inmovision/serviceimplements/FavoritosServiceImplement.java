package pe.edu.upc.inmovision.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inmovision.entities.Favoritos;
import pe.edu.upc.inmovision.repositories.IFavoritosRepository;
import pe.edu.upc.inmovision.serviceinterfaces.IFavoritosService;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritosServiceImplement implements IFavoritosService {

    @Autowired
    private IFavoritosRepository fR;

    @Override
    public Favoritos insertar(Favoritos favoritos) {
        return fR.save(favoritos);
    }

    @Override
    public List<Favoritos> listar() {
        return fR.findAll();
    }

    @Override
    public Optional<Favoritos> buscarPorId(int id) {
        return fR.findById(id);
    }

    @Override
    public void delete(int id) {
        fR.deleteById(id);
    }

    @Override
    public void update(Favoritos favoritos) {
        fR.save(favoritos);
    }

    @Override
    public List<Object[]> cantidadFavoritosPorUsuario() {
        return fR.cantidadFavoritosPorUsuario();
    }
}