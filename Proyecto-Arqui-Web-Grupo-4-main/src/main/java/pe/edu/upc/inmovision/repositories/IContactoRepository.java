package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovision.entities.Contacto;

import java.util.List;

@Repository
public interface IContactoRepository extends JpaRepository<Contacto,Integer> {
    @Query(value = "SELECT usuario_id, COUNT(*) " +
            "FROM contacto " +
            "GROUP BY usuario_id", nativeQuery = true)
    public List<Object[]> cantidadContactoPorUsuario();

    @Query(value = "SELECT propiedad_id, COUNT(*) " +
            "FROM contacto " +
            "GROUP BY propiedad_id", nativeQuery = true)
    public List<Object[]> cantidadContactoPorPropiedad();
}
