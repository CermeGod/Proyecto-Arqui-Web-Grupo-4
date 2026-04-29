package pe.edu.upc.inmovision.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inmovision.entities.Recomendaciones;
import pe.edu.upc.inmovision.repositories.IRecomendacionesRepository;
import pe.edu.upc.inmovision.serviceinterfaces.IRecomendacionesService;

import java.util.List;
import java.util.Optional;

@Service
public class RecomendacionesServiceImplement implements IRecomendacionesService {
    @Autowired
    private IRecomendacionesRepository rR;

    @Override
    public Recomendaciones insertar(Recomendaciones recomendaciones) {
        return rR.save(recomendaciones);
    }

    @Override
    public List<Recomendaciones> listar() {
        return rR.findAll();
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }

    @Override
    public Optional<Recomendaciones> listById(int id) {
        return rR.findById(id);
    }
}
