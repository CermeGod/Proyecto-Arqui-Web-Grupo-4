package pe.edu.upc.inmovision.serviceinterfaces;

import pe.edu.upc.inmovision.entities.Departamento;
import pe.edu.upc.inmovision.entities.Provincia;

import java.util.List;
import java.util.Optional;

public interface IProvinciaService {
    public Provincia insertar(Provincia provincia);
    public List<Provincia> listar();
    public Optional<Provincia> buscarPorId(int id);
    public void delete(int id);
    public void update(Provincia provincia);
}
