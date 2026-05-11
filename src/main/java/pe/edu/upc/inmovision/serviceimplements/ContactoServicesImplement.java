package pe.edu.upc.inmovision.serviceimplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inmovision.entities.Contacto;
import pe.edu.upc.inmovision.repositories.IContactoRepository;
import pe.edu.upc.inmovision.serviceinterfaces.IContactoService;

import java.util.List;
import java.util.Optional;
@Service
public class ContactoServicesImplement implements IContactoService {
    @Autowired
    private IContactoRepository cR;

    @Override
    public Contacto insertar(Contacto contacto) {
        return cR.save(contacto);
    }

    @Override
    public List<Contacto> listar() {
        return cR.findAll();
    }

    @Override
    public Optional<Contacto> buscarPorId(int id) {
        return cR.findById(id);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);

    }

    @Override
    public void update(Contacto contacto) {
        cR.save(contacto);

    }
}
