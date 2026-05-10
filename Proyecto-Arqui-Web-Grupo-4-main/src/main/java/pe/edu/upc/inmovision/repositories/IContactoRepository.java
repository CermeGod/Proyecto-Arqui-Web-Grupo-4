package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovision.entities.Contacto;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IContactoRepository extends JpaRepository<Contacto,Integer> {
    @Query(value = "SELECT * FROM contacto WHERE nombre LIKE %:nombre%", nativeQuery = true)
    List<Contacto> buscarPorNombre(@Param("nombre") String nombre);

    @Query(value = "SELECT * FROM contacto WHERE fecha = :fecha", nativeQuery = true)
    List<Contacto> buscarPorFecha(@Param("fecha") LocalDate fecha);

}
