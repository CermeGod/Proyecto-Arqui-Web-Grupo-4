package pe.edu.upc.inmovision.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.GeneralPropiedadDTO;
import pe.edu.upc.inmovision.entities.Distrito;
import pe.edu.upc.inmovision.entities.Propiedades;
import pe.edu.upc.inmovision.entities.Usuario;
import pe.edu.upc.inmovision.serviceimplements.DistritoServiceImplement;
import pe.edu.upc.inmovision.serviceimplements.IUsuarioServiceImplement;
import pe.edu.upc.inmovision.serviceimplements.PropiedadServiceImplement;
import pe.edu.upc.inmovision.serviceimplements.ProvinciaServiceImplement;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Inmovision/propiedad")
public class PropiedadController {

    @Autowired
    private PropiedadServiceImplement pS;
    @Autowired
    private DistritoServiceImplement dS;
    @Autowired
    private IUsuarioServiceImplement uS;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody GeneralPropiedadDTO dto)
    {
        ModelMapper m = new ModelMapper();
        Optional<Usuario>listado=uS.listById(dto.getUsuarioId());
        if(listado.isPresent())
        {
            Propiedades p=m.map(dto,Propiedades.class);
            Propiedades pr=pS.insertar(p);
            GeneralPropiedadDTO responseDTO=m.map(pr,GeneralPropiedadDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }
        else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el usuario");
        }

    }

    @GetMapping("/listar-propiedades")
    public ResponseEntity<?> listarPropiedades()
    {
        ModelMapper m = new ModelMapper();
        List<GeneralPropiedadDTO> lista=pS.listar().stream()
                .map(y->m.map(y, GeneralPropiedadDTO.class))
                .collect(Collectors.toList());
        if(lista.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay propiedades registradas");
        }
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    public ResponseEntity<String>actualizar(@RequestBody GeneralPropiedadDTO dto) {
        Optional<Propiedades> existente = pS.listById(dto.getPropiedadId());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la propiedad");
        }
        if (dto.getTitulo() == null || dto.getDescripcion() == null || dto.getPrecio() < 0 || dto.getDireccion() == null
                || dto.getEstado() == null || dto.getArea() == null || dto.getHabitacion() < 0 || dto.getBanios() < 0
                || dto.getUrlVR() == null || dto.getDistritoId() < 0)
        {
            return ResponseEntity.badRequest().body("Por favor completar todos los campos");
        }
        Optional<Distrito> existe= dS.listID(dto.getDistritoId());
        if(existe.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Distrito no encontrado");
        }
        Propiedades p=existente.get();
        p.setTitulo(dto.getTitulo());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setDireccion(dto.getDireccion());
        p.setEstado(dto.getEstado());
        p.setArea(dto.getArea());
        p.setHabitacion(dto.getHabitacion());
        p.setBanios(dto.getBanios());
        p.setUrlVR(dto.getUrlVR());
        p.setDistrito(existe.get());
        pS.update(p);
        return ResponseEntity.ok("Propiedad actualizada");

    }
    @DeleteMapping("borrar-propiedad/{id}")
    public ResponseEntity<String> borrarPropiedad(@PathVariable int id)
    {
        Optional<Propiedades> existe=pS.listById(id);
        if(existe.isPresent())
        {
            pS.delete(id);
            return ResponseEntity.ok("Propiedad eliminada");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Propiedad no encontrada");
        }
    }
}
