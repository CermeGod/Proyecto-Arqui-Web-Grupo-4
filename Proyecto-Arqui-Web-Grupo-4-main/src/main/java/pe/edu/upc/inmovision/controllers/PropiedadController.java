package pe.edu.upc.inmovision.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.GeneralPropiedadDTO;
import pe.edu.upc.inmovision.entities.Propiedades;
import pe.edu.upc.inmovision.serviceimplements.PropiedadServiceImplement;
import pe.edu.upc.inmovision.serviceinterfaces.IUsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Inmovision")
public class PropiedadController {

    @Autowired
    private PropiedadServiceImplement pS;
    @Autowired
    private IUsuarioService uS;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody GeneralPropiedadDTO dto)
    {
        ModelMapper m = new ModelMapper();
        Optional<Propiedades>listado=pS.listById(dto.getUsuarioId());
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
}
