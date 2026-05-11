package pe.edu.upc.inmovision.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.RecomendacionPropiedadDTO;
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
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/registrar-recomendacion")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> registrar(@RequestBody RecomendacionesDTO dto) {
        Optional<Usuario>listusuario=uS.listById(dto.getUsuarioId());
        Optional<Propiedades>listpropiedades=pS.listById(dto.getPropiedadId());
        if(listusuario.isPresent()&&listpropiedades.isPresent())
        {
            Recomendaciones r = new Recomendaciones();

            r.setUsuario(listusuario.get());
            r.setPropiedad(listpropiedades.get());

            Recomendaciones rec = rS.insertar(r);

            RecomendacionesDTO responseDTO = new RecomendacionesDTO();
            responseDTO.setRecomendacionId(rec.getRecomendacionId());
            responseDTO.setUsuarioId(rec.getUsuario().getUsuarioId());
            responseDTO.setPropiedadId(rec.getPropiedad().getPropiedadId());
            RecomendacionPropiedadDTO pDTO = new RecomendacionPropiedadDTO();
            pDTO.setTitulo(rec.getPropiedad().getTitulo());
            pDTO.setPrecio(rec.getPropiedad().getPrecio());
            pDTO.setDireccion(rec.getPropiedad().getDireccion());

            responseDTO.setPropiedad(pDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario o propiedad no encontrada");
        }


    }
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/listar-recomendaciones")
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENTE','ROLE_ADMIN')")
    public ResponseEntity<?> listar() {
        ModelMapper m = new ModelMapper();
        List<RecomendacionesDTO> lista = rS.listar().stream()
                .map(r ->{
                    RecomendacionesDTO dto = new RecomendacionesDTO();

                    dto.setRecomendacionId(r.getRecomendacionId());
                    dto.setUsuarioId(r.getUsuario().getUsuarioId());
                    dto.setPropiedadId(r.getPropiedad().getPropiedadId());

                    RecomendacionPropiedadDTO pDTO = new RecomendacionPropiedadDTO();
                    pDTO.setTitulo(r.getPropiedad().getTitulo());
                    pDTO.setPrecio(r.getPropiedad().getPrecio());
                    pDTO.setDireccion(r.getPropiedad().getDireccion());

                    dto.setPropiedad(pDTO);

                    return dto;
                })
                .collect(Collectors.toList());
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay recomendaciones registradas");
        }
        return ResponseEntity.ok(lista);
    }
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/eliminar-recomendacion/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENTE','ROLE_ADMIN')")
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