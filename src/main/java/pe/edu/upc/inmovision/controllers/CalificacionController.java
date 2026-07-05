package pe.edu.upc.inmovision.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.CalificacionDTO;
import pe.edu.upc.inmovision.dtos.ColeccionDTO;
import pe.edu.upc.inmovision.dtos.ProvinciaDTO;
import pe.edu.upc.inmovision.entities.Calificacion;
import pe.edu.upc.inmovision.entities.Coleccion;
import pe.edu.upc.inmovision.entities.Propiedades;
import pe.edu.upc.inmovision.serviceinterfaces.ICalificacionService;
import pe.edu.upc.inmovision.serviceinterfaces.IPropiedadService;
import pe.edu.upc.inmovision.serviceinterfaces.IUsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Inmovision/calificacion")
@SecurityRequirement(name = "bearerAuth")

public class CalificacionController {
    @Autowired
    private ICalificacionService caS;

    @Autowired
    private IPropiedadService pS;

    @Autowired
    private IUsuarioService uS;

    @PostMapping("/registrar-calificacion")
    //@PreAuthorize("hasAuthority('ROLE_CLIENTE')")
    public ResponseEntity<?> registrar(@RequestBody CalificacionDTO dto)
    {
        ModelMapper m=new ModelMapper();
        Optional<Propiedades> existente= pS.listById(dto.getPropiedadId());
        if(existente.isPresent())
        {
            Calificacion c=m.map(dto,Calificacion.class);
            Calificacion cr=caS.insertar(c);
            CalificacionDTO responseDTO=m.map(cr,CalificacionDTO.class);
            return  ResponseEntity.ok(responseDTO);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Propiedad no encontrada");
        }
    }

    @GetMapping("/listar-calificacion")
    //@PreAuthorize("hasAuthority('ROLE_PROPIETARIO')")
    public ResponseEntity<?> listarcalificacion()
    {
        ModelMapper m=new ModelMapper();
        List<CalificacionDTO> lista=caS.listar().stream()
                .map(y->m.map(y, CalificacionDTO.class))
                .collect(Collectors.toList());
        if(lista.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay calificaciones registradas");
        }
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/eliminar-calificacion/{id}")
    //@PreAuthorize("hasAuthority('ROLE_CLIENTE')")
    public ResponseEntity<String> eliminar(@PathVariable int id)
    {
        Optional<Calificacion> calificacion=caS.listById(id);

        if(calificacion.isPresent())
        {
            caS.delete(id);
            return ResponseEntity.ok("Calificacion eliminada con éxito");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Calificacion no encontrada");
        }
    }

    @GetMapping("/listar-calificacion-por-puntuacion/{puntuacion}")
    public ResponseEntity<?> listarporpuntuacion(@PathVariable int puntuacion)
    {
        ModelMapper m=new ModelMapper();
        List<CalificacionDTO> lista=  caS.listarporpuntuacion(puntuacion).stream()
            .map(y->m.map(y, CalificacionDTO.class))
                .collect(Collectors.toList());
        if(lista.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay calificaciones registradas");
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/listar-calificacion-por-fecha-reciente")
    public ResponseEntity<?> listarporfechareciente()
    {
        ModelMapper m=new ModelMapper();
        List<CalificacionDTO> lista=  caS.listarporfechareciente().stream()
                .map(y->m.map(y, CalificacionDTO.class))
                .collect(Collectors.toList());
        if(lista.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay calificaciones registradas");
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/listar-calificacion-por-fecha-antigua")
    public ResponseEntity<?> listarporfechaantigua()
    {
        ModelMapper m=new ModelMapper();
        List<CalificacionDTO> lista=  caS.listarporfechaantigua().stream()
                .map(y->m.map(y, CalificacionDTO.class))
                .collect(Collectors.toList());
        if(lista.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay calificaciones registradas");
        }
        return ResponseEntity.ok(lista);
    }
    
}
