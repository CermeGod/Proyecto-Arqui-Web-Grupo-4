package pe.edu.upc.inmovision.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inmovision.entities.Coleccion;
import pe.edu.upc.inmovision.repositories.IColeccionRepository;
import pe.edu.upc.inmovision.serviceinterfaces.IColeccionService;

import java.util.List;
import java.util.Optional;

@Service
public class ColeccionServiceImplement implements IColeccionService {
    @Autowired
    private IColeccionRepository coR;

    @Override
    public Coleccion insertar(Coleccion coleccion) {
        return coR.save(coleccion);
    }

    @Override
    public List<Coleccion> listar() {
        return coR.findAll();
    }

    @Override
    public Optional<Coleccion> listById(int id) {
        return coR.findById(id);
    }

    @Override
    public void delete(int id) {
        coR.deleteById(id);
    }
}
