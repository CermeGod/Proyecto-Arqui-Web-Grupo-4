package pe.edu.upc.inmovision.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.entities.Departamento;
import pe.edu.upc.inmovision.serviceimplements.DepartamentoServiceImplement;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Inmovision/departamento")
public class DepartamentoController {
    @Autowired
    private DepartamentoServiceImplement dS;

    @PostMapping("/registrar-departamento")
    public ResponseEntity<Departamento> registrar(@RequestBody Departamento departamento)
    {
        Departamento d=dS.insertar(departamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(d);
    }

    @GetMapping("/listar-departamentos")
    public ResponseEntity<?> listarDepartamentos()
    {
        List<Departamento> lista=dS.listar();
        if(lista.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay departamentos registrados");
        }
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/eliminar-departamento/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id)
    {
        Optional<Departamento> departamento=dS.buscarPorId(id);

        if(departamento.isPresent())
        {
            dS.delete(id);
            return ResponseEntity.ok("Departamento eliminado con éxito");
        }
        else
        {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
        }
    }
    @PutMapping
    public ResponseEntity<String>actualizar(@RequestBody Departamento departamento)
    {
        Optional<Departamento>existente=dS.buscarPorId(departamento.getDepartamentoId());
        if(existente.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
        }
        if(departamento.getName()==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor completar los campos");
        }
        Departamento d= existente.get();
        d.setName(departamento.getName());
        dS.update(d);
        return ResponseEntity.ok("Datos actualizados con éxito");
    }
}
