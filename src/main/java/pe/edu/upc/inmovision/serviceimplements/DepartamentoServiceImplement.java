package pe.edu.upc.inmovision.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inmovision.entities.Departamento;
import pe.edu.upc.inmovision.repositories.IDepartamentoRepository;
import pe.edu.upc.inmovision.repositories.IDistritoRepository;
import pe.edu.upc.inmovision.serviceinterfaces.IDepartamentoService;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServiceImplement implements IDepartamentoService {
    @Autowired
    private IDepartamentoRepository dR;

    @Override
    public Departamento insertar(Departamento departamento) {
        return dR.save(departamento);
    }

    @Override
    public List<Departamento> listar() {
        return dR.findAll();
    }

    @Override
    public Optional<Departamento> buscarPorId(int id) {
        return dR.findById(id);
    }

    @Override
    public void delete(int id) {
        dR.deleteById(id);
    }

    @Override
    public void update(Departamento departamento) {
        dR.save(departamento);
    }
}
