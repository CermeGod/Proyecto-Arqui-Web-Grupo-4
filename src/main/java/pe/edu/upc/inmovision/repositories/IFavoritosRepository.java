package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovision.entities.Favoritos;

import java.util.List;

@Repository
public interface IFavoritosRepository extends JpaRepository<Favoritos,Integer> {
    @Query(value =
            "SELECT u.nombre, COUNT(f.favoritos_id) AS cantidad " +
                    "FROM favoritos f " +
                    "JOIN usuario u ON u.usuario_id = f.usuario_id " +
                    "GROUP BY u.nombre",
            nativeQuery = true)
    List<Object[]> cantidadFavoritosPorUsuario();
}
