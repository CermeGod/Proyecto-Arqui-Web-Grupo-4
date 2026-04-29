package pe.edu.upc.inmovision.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inmovision.entities.Provincia;
import pe.edu.upc.inmovision.repositories.IProvinciaRepository;
import pe.edu.upc.inmovision.serviceinterfaces.IProvinciaService;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinciaServiceImplement  implements IProvinciaService {
    @Autowired
    private IProvinciaRepository pR;
    @Override
    public Provincia insertar(Provincia provincia) {
        return pR.save(provincia);
    }

    @Override
    public List<Provincia> listar() {
        return pR.findAll();
    }

    @Override
    public Optional<Provincia> buscarPorId(int id) {
        return pR.findById(id);
    }

    @Override
    public void delete(int id) {
        pR.deleteById(id);
    }

    @Override
    public void update(Provincia provincia) {
        pR.save(provincia);
    }
}
