package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovision.entities.Calificacion;

import java.util.List;

@Repository
public interface ICalificacionRepository extends JpaRepository<Calificacion,Integer> {
    public List<Calificacion>findByPuntuacion(int puntuacion);
    public List<Calificacion>findAllByOrderByFechaDesc();
    public List<Calificacion>findAllByOrderByFechaAsc();
}
