package pe.edu.upc.inmovision.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.RolDTO;
import pe.edu.upc.inmovision.entities.Rol;
import pe.edu.upc.inmovision.serviceimplements.IRolServiceImplement;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Inmovision/rol")
public class RolController {
    @Autowired
    private IRolServiceImplement rS;

    @PostMapping("/registrar-roles")
    public ResponseEntity<Rol>registrar(@RequestBody Rol rol)
    {
        Rol r=rS.insertar(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @GetMapping("/listar-roles")
    public ResponseEntity<List<Rol>> listar()
    {
        List<Rol> r=rS.listar();
        return ResponseEntity.ok(r);
    }

    @PutMapping("/modificar-rol")
    public ResponseEntity<String> actualizar(@RequestBody RolDTO dto)
    {
        Optional<Rol> existente=rS.listById(dto.getRolId());
        if(existente.isEmpty())
        {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
        }
        if(dto.getNombre()==null)
        {
            return ResponseEntity.badRequest().body("El nombre no puede estar vacio");
        }
        Rol r=existente.get();
        r.setName(dto.getNombre());
        rS.update(r);
        return ResponseEntity.ok("Rol actualizado");
    }

    @DeleteMapping("/eliminar-rol/{id}")
    public ResponseEntity<String> delete(@PathVariable int id)
    {
        Optional<Rol> existente=rS.listById(id);
        if (existente.isPresent())
        {
            rS.eliminar(id);
            return ResponseEntity.ok("Rol eliminado");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
        }

    }
}
