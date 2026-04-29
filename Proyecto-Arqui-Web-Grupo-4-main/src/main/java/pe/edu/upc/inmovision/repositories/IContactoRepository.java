package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.inmovision.entities.Contacto;

public interface IContactoRepository extends JpaRepository<Contacto,Integer> {
}
