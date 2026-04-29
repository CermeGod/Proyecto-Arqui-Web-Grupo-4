package pe.edu.upc.inmovision.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.RecomendacionesDTO;
import pe.edu.upc.inmovision.entities.Propiedades;
import pe.edu.upc.inmovision.entities.Recomendaciones;
import pe.edu.upc.inmovision.entities.Usuario;
import pe.edu.upc.inmovision.serviceimplements.IUsuarioServiceImplement;
import pe.edu.upc.inmovision.serviceimplements.PropiedadServiceImplement;
import pe.edu.upc.inmovision.serviceinterfaces.IRecomendacionesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Inmovision/recomendaciones")
public class RecomendacionesController {
    @Autowired
    private IRecomendacionesService rS;
    @Autowired
    private IUsuarioServiceImplement uS;
    @Autowired
    private PropiedadServiceImplement pS;

    @PostMapping("/registrar-recomendacion")
    public ResponseEntity<?> registrar(@RequestBody RecomendacionesDTO dto) {
        ModelMapper m = new ModelMapper();
        Optional<Usuario>listusuario=uS.listById(dto.getUsuarioId());
        Optional<Propiedades>listpropiedades=pS.listById(dto.getPropiedadId());
        if(listusuario.isPresent()&&listpropiedades.isPresent())
        {
            Recomendaciones r = m.map(dto, Recomendaciones.class);
            Recomendaciones rec = rS.insertar(r);
            RecomendacionesDTO responseDTO = m.map(rec, RecomendacionesDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario o propiedad no encontrada");
        }


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

    
}
