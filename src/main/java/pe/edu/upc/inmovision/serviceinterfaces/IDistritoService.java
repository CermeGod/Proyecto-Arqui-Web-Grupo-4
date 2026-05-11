package pe.edu.upc.inmovision.serviceinterfaces;

import pe.edu.upc.inmovision.entities.Distrito;
import pe.edu.upc.inmovision.entities.Provincia;

import java.util.List;
import java.util.Optional;

public interface IDistritoService {
    public Optional<Distrito> listID(int id);
    public Distrito insertar(Distrito distrito);
    public List<Distrito> listar();
    public void delete(int id);
    public void update(Distrito distrito);
}
