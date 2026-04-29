package pe.edu.upc.inmovision.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.RecomendacionesDTO;
import pe.edu.upc.inmovision.entities.Recomendaciones;
import pe.edu.upc.inmovision.serviceinterfaces.IRecomendacionesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Inmovision/recomendaciones")
public class RecomendacionesController {
    @Autowired
    private IRecomendacionesService rS;

    @PostMapping("/registrar-recomendacion")
    public ResponseEntity<RecomendacionesDTO> registrar(@RequestBody RecomendacionesDTO dto) {
        ModelMapper m = new ModelMapper();
        Recomendaciones r = m.map(dto, Recomendaciones.class);
        Recomendaciones rec = rS.insertar(r);
        RecomendacionesDTO responseDTO = m.map(rec, RecomendacionesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/listar-recomendaciones")
    public ResponseEntity<?> listar() {
        ModelMapper m = new ModelMapper();
        List<RecomendacionesDTO> lista = rS.listar().stream()
                .map(y -> m.map(y, RecomendacionesDTO.class))
                .collect(Collectors.toList());
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay recomendaciones registradas");
        }
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/eliminar-recomendacion/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Recomendaciones> recomendacion = rS.listById(id);
        if (recomendacion.isPresent()) {
            rS.delete(id);
            return ResponseEntity.ok("Recomendacion eliminada con exito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recomendacion no encontrada");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody RecomendacionesDTO dto) {
        Optional<Recomendaciones> existente = rS.listById(dto.getRecomendacionId());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recomendacion no encontrada");
        }
        if (dto.getPropiedad() == null || dto.getUsuario() == null) {
            return ResponseEntity.badRequest().body("Por favor completar los campos");
        }
        Recomendaciones r = existente.get();
        r.setPropiedad(dto.getPropiedad());
        r.setUsuario(dto.getUsuario());
        rS.insertar(r);
        return ResponseEntity.ok("Datos actualizados con exito");
    }
}
