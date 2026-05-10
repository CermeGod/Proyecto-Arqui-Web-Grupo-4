package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovision.entities.Recomendaciones;

import java.util.List;

@Repository
public interface IRecomendacionesRepository extends JpaRepository<Recomendaciones, Integer> {

    @Query(value = "SELECT COUNT(*) \n" +
            "FROM recomendaciones \n" +
            "WHERE propiedadId = :idPropiedad", nativeQuery = true)
    Integer contarPorPropiedad(@Param("idPropiedad") int idPropiedad);

    @Query(value = "SELECT r.* \n" +
            "FROM recomendaciones r \n" +
            "INNER JOIN propiedades p ON r.propiedadId = p.propiedadId \n" +
            "WHERE r.usuarioId = :idUsuario \n" +
            "ORDER BY p.titulo ASC", nativeQuery = true)
    List<Recomendaciones> buscarPorUsuario(@Param("idUsuario") int idUsuario);

}
