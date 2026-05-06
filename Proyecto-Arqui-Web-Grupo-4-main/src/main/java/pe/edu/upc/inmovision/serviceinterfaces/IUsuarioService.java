package pe.edu.upc.inmovision.serviceinterfaces;

import pe.edu.upc.inmovision.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public Usuario insertar(Usuario usuario);
    public List<Usuario> listar();
    public Optional<Usuario>listById(int id);
    public void delete(int id);
    public List<Object[]> obtenerUsuariosConPropiedades();
    public List<Object[]> contarUsuariosPorRol();
}
