package pe.edu.upc.inmovision.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inmovision.entities.Calificacion;
import pe.edu.upc.inmovision.repositories.ICalificacionRepository;
import pe.edu.upc.inmovision.serviceinterfaces.ICalificacionService;

import java.util.List;
import java.util.Optional;

@Service
public class CalificacionServiceImplement implements ICalificacionService {
    @Autowired
    private ICalificacionRepository caR;

    @Override
    public Calificacion insertar(Calificacion calificacion) {
        return caR.save(calificacion);
    }

    @Override
    public List<Calificacion> listar() {
        return caR.findAll();
    }

    @Override
    public Optional<Calificacion> listById(int id) {
        return caR.findById(id);
    }

    @Override
    public void delete(int id) {
        caR.deleteById(id);
    }

    @Override
    public List<Calificacion> listarporpuntuacion(int puntuacion) {
        return caR.findByPuntuacion(puntuacion);
    }

    @Override
    public List<Calificacion> listarporfechareciente() {
        return caR.findAllByOrderByFechaDesc();
    }

    @Override
    public List<Calificacion> listarporfechaantigua() {
        return caR.findAllByOrderByFechaAsc();
    }
}
