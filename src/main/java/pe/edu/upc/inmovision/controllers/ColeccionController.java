package pe.edu.upc.inmovision.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.ColeccionDTO;
import pe.edu.upc.inmovision.entities.*;
import pe.edu.upc.inmovision.serviceinterfaces.IColeccionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Inmovision/coleccion")
@SecurityRequirement(name = "bearerAuth")

public class ColeccionController {
    @Autowired
    private IColeccionService coS;

    @PostMapping("/registrar-coleccion")
    //@PreAuthorize("hasAuthority('ROLE_CLIENTE')")
    public ResponseEntity<Coleccion> registrar(@RequestBody Coleccion coleccion)
    {
        Coleccion c=coS.insertar(coleccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @GetMapping("/listar-coleccion")
    //@PreAuthorize("hasAuthority('ROLE_CLIENTE')")
    public ResponseEntity<?> listarcoleccion()
    {
        ModelMapper m=new ModelMapper();
        List<ColeccionDTO> lista=coS.listar().stream()
                .map(y->m.map(y, ColeccionDTO.class))
                .collect(Collectors.toList());
        if(lista.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay colecciones registradas");
        }
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/eliminar-coleccion/{id}")
    //@PreAuthorize("hasAuthority('ROLE_CLIENTE')")
    public ResponseEntity<String> eliminar(@PathVariable int id)
    {
        Optional<Coleccion> coleccion=coS.listById(id);

        if(coleccion.isPresent())
        {
            coS.delete(id);
            return ResponseEntity.ok("Coleccion eliminada con éxito");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coleccion no encontrada");
        }
    }
}
