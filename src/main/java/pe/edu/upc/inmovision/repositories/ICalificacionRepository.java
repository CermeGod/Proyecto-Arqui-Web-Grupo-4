package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.inmovision.entities.Calificacion;

public interface ICalificacionRepository extends JpaRepository<Calificacion,Integer> {
}
