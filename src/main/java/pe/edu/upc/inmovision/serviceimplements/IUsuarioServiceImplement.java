package pe.edu.upc.inmovision.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inmovision.entities.Usuario;
import pe.edu.upc.inmovision.repositories.IUsuarioRepository;
import pe.edu.upc.inmovision.serviceinterfaces.IUsuarioService;

import java.util.List;
import java.util.Optional;

@Service
public class IUsuarioServiceImplement implements IUsuarioService {
    @Autowired
    private IUsuarioRepository uR;
    @Override
    public Usuario insertar(Usuario usuario) {
        return uR.save(usuario) ;
    }

    @Override
    public List<Usuario> listar() {
        return uR.findAll();
    }

    @Override
    public Optional<Usuario> listById(int id) {
        return uR.findById(id);
    }

    @Override
    public void delete(int id) {
        uR.deleteById(id);
    }

    @Override
    public List<Object[]> obtenerUsuariosConPropiedades() {
        return uR.obtenerUsuariosConPropiedades();
    }

    @Override
    public List<Object[]> contarUsuariosPorRol() {
        return uR.contarUsuariosPorRol();
    }
}
