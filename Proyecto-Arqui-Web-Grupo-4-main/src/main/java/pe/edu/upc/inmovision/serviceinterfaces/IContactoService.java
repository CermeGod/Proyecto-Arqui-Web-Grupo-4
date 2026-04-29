package pe.edu.upc.inmovision.serviceinterfaces;

import pe.edu.upc.inmovision.entities.Contacto;
import pe.edu.upc.inmovision.entities.Provincia;

import java.util.List;
import java.util.Optional;

public interface IContactoService {
    public Contacto insertar(Contacto contacto);
    public List<Contacto> listar();
    public Optional<Contacto> buscarPorId(int id);
    public void delete(int id);
    public void update(Contacto contacto);

}
