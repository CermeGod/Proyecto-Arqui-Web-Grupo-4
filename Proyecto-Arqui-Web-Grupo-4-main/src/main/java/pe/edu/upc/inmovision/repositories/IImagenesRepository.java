package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovision.entities.Imagenes;

import java.util.List;

@Repository
public interface IImagenesRepository extends JpaRepository<Imagenes,Integer> {
    @Query(value = "SELECT propiedad_id, COUNT(*) " +
            "FROM imagenes " +
            "GROUP BY propiedad_id", nativeQuery = true)
    public List<Object[]> cantidadImagenesPorPropiedad();
}
