package pe.edu.upc.inmovision.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.DistritoDTO;
import pe.edu.upc.inmovision.entities.Distrito;
import pe.edu.upc.inmovision.entities.Provincia;
import pe.edu.upc.inmovision.serviceimplements.DistritoServiceImplement;
import pe.edu.upc.inmovision.serviceimplements.ProvinciaServiceImplement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Inmovision/distrito")
public class DistritoController {
    @Autowired
    private DistritoServiceImplement dS;
    @Autowired
    private ProvinciaServiceImplement pS;

    @PostMapping("/registrar-provincia")
    public ResponseEntity<?> registrar(@RequestBody DistritoDTO dto)
    {
        ModelMapper m=new ModelMapper();
        Optional<Provincia> existente= pS.buscarPorId(dto.getProvinciaId());
        if(existente.isPresent())
        {
            Distrito d=m.map(dto,Distrito.class);
            Distrito di=dS.insertar(d);
            DistritoDTO responseDTO=m.map(di,DistritoDTO.class);
            return  ResponseEntity.ok(responseDTO);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provincia no encontrada");
        }
    }

    @GetMapping("/listar-distritos")
    public ResponseEntity<?> listarDistritos()
    {
        ModelMapper m=new ModelMapper();
        List<DistritoDTO> lista=dS.listar().stream()
                .map(y->m.map(y,DistritoDTO.class))
                .collect(Collectors.toList());
        if(lista.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay distritos registrados");
        }
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/eliminar-distrito/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id)
    {
        Optional<Distrito> distrito=dS.listID(id);

        if(distrito.isPresent())
        {
            dS.delete(id);
            return ResponseEntity.ok("Distrito eliminada con éxito");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Distrito no encontrado");
        }
    }
    @PutMapping
    public ResponseEntity<String>actualizar(@RequestBody DistritoDTO dto)
    {
        Optional<Distrito>existente=dS.listID(dto.getDistritoId());
        if(existente.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Distrito no encontrado");
        }
        if(dto.getNombre()==null || dto.getProvinciaId()<0)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor completar los campos");
        }
        Optional<Provincia> existe=pS.buscarPorId(dto.getProvinciaId());
        if(existe.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provincia no encontrada");
        }
        Distrito d= existente.get();
        d.setNombre(dto.getNombre());
        d.setProvincia(existe.get());
        dS.update(d);
        return ResponseEntity.ok("Datos actualizados con éxito");
    }
}
