package pe.edu.upc.inmovision.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.entities.Rol;
import pe.edu.upc.inmovision.serviceimplements.IRolServiceImplement;

import java.util.List;

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
}
