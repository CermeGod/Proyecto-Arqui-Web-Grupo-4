package pe.edu.upc.inmovision.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inmovision.entities.Rol;
import pe.edu.upc.inmovision.repositories.IRolRepository;
import pe.edu.upc.inmovision.serviceinterfaces.IRolService;

import java.util.List;
import java.util.Optional;

@Service
public class IRolServiceImplement  implements IRolService {
    @Autowired
    private IRolRepository rR;

    @Override
    public Rol insertar(Rol rol) {
        return rR.save(rol);
    }

    @Override
    public List<Rol> listar() {
        return rR.findAll();
    }

    @Override
    public void eliminar(int id) {
        rR.deleteById(id);
    }

    @Override
    public Optional<Rol> listById(int id) {
        return rR.findById(id);
    }
}
