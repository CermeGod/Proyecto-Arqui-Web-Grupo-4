package pe.edu.upc.inmovision.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inmovision.entities.Propiedades;
import pe.edu.upc.inmovision.repositories.IPropiedadRepository;
import pe.edu.upc.inmovision.serviceinterfaces.IPropiedadService;

import java.util.List;
import java.util.Optional;

@Service
public class PropiedadServiceImplement implements IPropiedadService {
    @Autowired
    private IPropiedadRepository pR;

    @Override
    public Propiedades insertar(Propiedades propiedades) {
        return pR.save(propiedades);
    }

    @Override
    public List<Propiedades> listar() {
        return pR.findAll();
    }

    @Override
    public Optional<Propiedades> listById(int id) {
        return pR.findById(id);
    }

    @Override
    public void delete(int id) {
        pR.deleteById(id);
    }


}
