package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovision.entities.Propiedades;

import java.util.List;

@Repository
public interface IPropiedadRepository extends JpaRepository<Propiedades,Integer> {
    @Query(value = " select d.nombre, count(p.propiedad_id) as total\n" +
            " from distrito d\n" +
            " join propiedades p on d.distrito_id=p.distrito_id\n" +
            " group by d.nombre", nativeQuery = true)
    List<Object[]> propiedadesPorDistrito();

    @Query(value = " select p.titulo, count(c.contacto_id) as total_contactos\n" +
            " from propiedades p\n" +
            " join contacto c on p.propiedad_id = c.propiedad_id\n" +
            " group by p.titulo\n" +
            " order by total_contactos desc;", nativeQuery = true)
    List<Object[]> propiedadesPorContacto();
}
