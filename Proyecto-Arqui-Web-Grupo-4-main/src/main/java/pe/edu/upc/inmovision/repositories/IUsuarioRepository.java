package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovision.entities.Usuario;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
    Usuario findOneByCorreo(String correo);
    //QUERYS NATIVE STEPHANO
    @Query(value = "SELECT \n" +
            "        u.usuario_id,\n" +
            "        u.nombre,\n" +
            "        u.apellido,\n" +
            "        COUNT(p.propiedad_id) AS total_propiedades\n" +
            "    FROM usuario u\n" +
            "    LEFT JOIN propiedades p\n" +
            "        ON u.usuario_id = p.usuario_id\n" +
            "    GROUP BY u.usuario_id, u.nombre, u.apellido\n" +
            "    ORDER BY total_propiedades DESC", nativeQuery = true)
    List<Object[]> obtenerUsuariosConPropiedades();

    @Query(value ="SELECT \n" +
            "        r.name AS rol,\n" +
            "        COUNT(u.usuario_id) AS totalUsuarios\n" +
            "    FROM usuario u\n" +
            "    INNER JOIN rol r\n" +
            "        ON u.rol_id = r.rol_id\n" +
            "    GROUP BY r.name\n" +
            "    ORDER BY totalUsuarios DESC", nativeQuery = true)
    List<Object[]> contarUsuariosPorRol();
}
