package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovision.entities.Favoritos;

import java.util.List;

@Repository
public interface IFavoritosRepository extends JpaRepository<Favoritos,Integer> {
    @Query(value = "SELECT usuario_id, COUNT(*) " +
            "FROM favoritos " +
            "GROUP BY usuario_id", nativeQuery = true)
    public List<Object[]> cantidadFavoritosPorUsuario();
}
