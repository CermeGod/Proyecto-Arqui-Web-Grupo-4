package pe.edu.upc.inmovision.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.ProvinciaDTO;
import pe.edu.upc.inmovision.entities.Departamento;
import pe.edu.upc.inmovision.entities.Provincia;
import pe.edu.upc.inmovision.serviceimplements.DepartamentoServiceImplement;
import pe.edu.upc.inmovision.serviceimplements.ProvinciaServiceImplement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Inmovision/provincia")
public class ProvinciaController {
    @Autowired
    private ProvinciaServiceImplement pS;
    @Autowired
    private DepartamentoServiceImplement dS;

    @PostMapping("/registrar-provincia")
    public ResponseEntity<?> registrar(@RequestBody ProvinciaDTO dto)
    {
        ModelMapper m=new ModelMapper();
        Optional<Departamento> existente= dS.buscarPorId(dto.getProvinciaId());
        if(existente.isPresent())
        {
            Provincia p=m.map(dto,Provincia.class);
            Provincia pr=pS.insertar(p);
            ProvinciaDTO responseDTO=m.map(pr,ProvinciaDTO.class);
            return  ResponseEntity.ok(responseDTO);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
        }
    }

    @GetMapping("/listar-provincias")
    public ResponseEntity<?> listarprovincias()
    {
        ModelMapper m=new ModelMapper();
        List<ProvinciaDTO> lista=pS.listar().stream()
                .map(y->m.map(y, ProvinciaDTO.class))
                .collect(Collectors.toList());
        if(lista.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay provincias registradas");
        }
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/eliminar-provincia/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id)
    {
        Optional<Provincia> provincia=pS.buscarPorId(id);

        if(provincia.isPresent())
        {
            pS.delete(id);
            return ResponseEntity.ok("Provincia eliminada con éxito");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provincia no encontrada");
        }
    }
    @PutMapping
    public ResponseEntity<String>actualizar(@RequestBody ProvinciaDTO dto)
    {
        Optional<Provincia>existente=pS.buscarPorId(dto.getProvinciaId());
        if(existente.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provincia no encontrada");
        }
        if(dto.getNombre()==null || dto.getDepartamentoId()<0)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor completar los campos");
        }
        Optional<Departamento> existe=dS.buscarPorId(dto.getDepartamentoId());
        if(existe.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento no encontrado");
        }
        Provincia p= existente.get();
        p.setNombre(dto.getNombre());
        p.setDepartamento(existe.get());
        pS.update(p);
        return ResponseEntity.ok("Datos actualizados con éxito");
    }
}
