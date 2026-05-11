package pe.edu.upc.inmovision.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inmovision.entities.Distrito;
import pe.edu.upc.inmovision.repositories.IDistritoRepository;
import pe.edu.upc.inmovision.serviceinterfaces.IDistritoService;

import java.util.List;
import java.util.Optional;

@Service
public class DistritoServiceImplement implements IDistritoService {
    @Autowired
    private IDistritoRepository dR;

    @Override
    public Optional<Distrito> listID(int id) {
        return dR.findById(id);
    }

    @Override
    public Distrito insertar(Distrito distrito) {
        return dR.save(distrito);
    }

    @Override
    public List<Distrito> listar() {
        return dR.findAll();
    }

    @Override
    public void delete(int id) {
        dR.deleteById(id);
    }

    @Override
    public void update(Distrito distrito) {
    dR.save(distrito);
    }
}
