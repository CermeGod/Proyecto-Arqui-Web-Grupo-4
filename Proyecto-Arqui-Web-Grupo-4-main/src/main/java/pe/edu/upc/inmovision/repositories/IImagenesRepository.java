package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovision.entities.Imagenes;

@Repository
public interface IImagenesRepository extends JpaRepository<Imagenes,Integer> {
}
