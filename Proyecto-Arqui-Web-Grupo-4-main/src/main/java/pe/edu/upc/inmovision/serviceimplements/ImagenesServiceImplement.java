package pe.edu.upc.inmovision.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inmovision.entities.Imagenes;
import pe.edu.upc.inmovision.repositories.IImagenesRepository;
import pe.edu.upc.inmovision.serviceinterfaces.IImagenesService;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenesServiceImplement implements IImagenesService {

    @Autowired
    private IImagenesRepository iR;

    @Override
    public Imagenes insertar(Imagenes imagen) {
        return iR.save(imagen);
    }

    @Override
    public List<Imagenes> listar() {
        return iR.findAll();
    }

    @Override
    public Optional<Imagenes> buscarPorId(int id) {
        return iR.findById(id);
    }

    @Override
    public void delete(int id) {
        iR.deleteById(id);
    }

    @Override
    public void update(Imagenes imagen) {
        iR.save(imagen);
    }
}