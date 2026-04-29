package pe.edu.upc.inmovision.serviceinterfaces;

import pe.edu.upc.inmovision.entities.Departamento;

import java.util.List;
import java.util.Optional;

public interface IDepartamentoService {
    public Departamento insertar(Departamento departamento);
    public List<Departamento> listar();
    public Optional<Departamento> buscarPorId(int id);
    public void delete(int id);
    public void update(Departamento departamento);
}
