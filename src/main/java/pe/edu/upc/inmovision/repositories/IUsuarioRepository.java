package pe.edu.upc.inmovision.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovision.entities.Usuario;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
    public Usuario findOneByCorreo(String correo);

    //QUERYS NATIVE STEPHANO
    /*@Query(value = "SELECT \n" +
            "        u.usuario_id,\n" +
            "        u.nombre,\n" +
            "        u.apellido,\n" +
            "        COUNT(p.propiedad_id) AS total_propiedades\n" +
            "    FROM usuario u\n" +
            "    LEFT JOIN propiedades p\n" +
            "        ON u.usuario_id = p.usuario_id\n" +
            "    GROUP BY u.usuario_id, u.nombre, u.apellido\n" +
            "    ORDER BY total_propiedades DESC", nativeQuery = true)
    List<Object[]> obtenerUsuariosConPropiedades();*/

    @Query(value =
            "SELECT r.name AS rol, COUNT(u.usuario_id) AS totalUsuarios " +
                    "FROM usuario u " +
                    "INNER JOIN usuario_rol ur ON u.usuario_id = ur.user_id " +
                    "INNER JOIN rol r ON ur.rol_id = r.rol_id " +
                    "GROUP BY r.name " +
                    "ORDER BY totalUsuarios DESC",
            nativeQuery = true)
    List<Object[]> contarUsuariosPorRol();

    @Query(value = "SELECT u.usuario_id, u.nombre, u.apellido, " +
            "COUNT(p.propiedad_id) AS total_propiedades " +
            "FROM usuario u " +
            "JOIN usuario_rol ur ON u.usuario_id = ur.user_id " +
            "JOIN rol r ON ur.rol_id = r.rol_id " +
            "LEFT JOIN propiedades p ON u.usuario_id = p.usuario_id " +
            "WHERE r.name = 'ROLE_PROPIETARIO' " +
            "GROUP BY u.usuario_id, u.nombre, u.apellido " +
            "ORDER BY total_propiedades DESC",
            nativeQuery = true)
    List<Object[]> listarPropietariosConPropiedades();
}
