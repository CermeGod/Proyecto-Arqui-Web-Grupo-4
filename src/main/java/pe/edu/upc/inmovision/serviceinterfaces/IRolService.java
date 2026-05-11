package pe.edu.upc.inmovision.serviceinterfaces;

import pe.edu.upc.inmovision.entities.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    public Rol insertar(Rol rol);
    public List<Rol> listar();
    public void eliminar(int id);
    public Optional<Rol> listById(int id);
    public void update(Rol rol);

}
