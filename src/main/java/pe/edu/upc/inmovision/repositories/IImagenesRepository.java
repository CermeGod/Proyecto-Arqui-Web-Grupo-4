package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovision.entities.Imagenes;

import java.util.List;

@Repository
public interface IImagenesRepository extends JpaRepository<Imagenes,Integer> {
    @Query(value =
            "SELECT p.titulo, COUNT(i.imagenes_id) AS total_imagenes " +
                    "FROM imagenes i " +
                    "JOIN propiedades p ON p.propiedad_id = i.propiedad_id " +
                    "GROUP BY p.titulo",
            nativeQuery = true)
    List<Object[]> cantidadImagenesPorPropiedad();
}