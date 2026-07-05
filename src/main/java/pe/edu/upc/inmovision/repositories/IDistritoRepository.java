package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovision.entities.Distrito;
import pe.edu.upc.inmovision.entities.Provincia;

import java.util.List;

@Repository
public interface IDistritoRepository extends JpaRepository<Distrito,Integer> {
    List<Distrito> findByProvinciaProvinciaId(int idProvincia);
}
