package pe.edu.upc.inmovision.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.ContactoDTO;
import pe.edu.upc.inmovision.dtos.GeneralPropiedadDTO;
import pe.edu.upc.inmovision.dtos.ProvinciaDTO;
import pe.edu.upc.inmovision.dtos.UsuarioDTO;
import pe.edu.upc.inmovision.entities.Contacto;
import pe.edu.upc.inmovision.entities.Propiedades;
import pe.edu.upc.inmovision.entities.Provincia;
import pe.edu.upc.inmovision.entities.Usuario;
import pe.edu.upc.inmovision.serviceimplements.ContactoServicesImplement;
import pe.edu.upc.inmovision.serviceimplements.DepartamentoServiceImplement;
import pe.edu.upc.inmovision.serviceimplements.PropiedadServiceImplement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Inmovision/contacto")
public class ContactoController {
    @Autowired
    private ContactoServicesImplement cS;
    @Autowired
    private PropiedadServiceImplement pS;

    @PostMapping("/registrar-contacto")
    public ResponseEntity<?> registrar(@RequestBody ContactoDTO dto)
    {
        ModelMapper m = new ModelMapper();
        Optional<Contacto> listado=cS.buscarPorId(dto.getContactoId());
        if(listado.isPresent())
        {
            Propiedades p=m.map(dto,Propiedades.class);
            Propiedades pr=pS.insertar(p);
            GeneralPropiedadDTO responseDTO=m.map(pr,GeneralPropiedadDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }
        else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el Contacto");
        }

    }

    @GetMapping("/listar-contacto")
    public ResponseEntity<?> listarcontacto() {
        ModelMapper m = new ModelMapper();
        List<ContactoDTO> lista = cS.listar().stream()
                .map(y -> m.map(y, ContactoDTO.class))
                .collect(Collectors.toList());
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay contacto registradas");
        }
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/eliminar-provincia/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id)
    {
        Optional<Contacto> contacto=cS.buscarPorId(id);

        if(contacto.isPresent())
        {
            cS.delete(id);
            return ResponseEntity.ok("Contacto eliminada con éxito");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contacto no encontrada");
        }
    }

    @PutMapping
    public ResponseEntity<String>actualizar(@RequestBody ContactoDTO dto)
    {
        Optional<Contacto>existente=cS.buscarPorId(dto.getContactoId());
        if(existente.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contacto no encontrado");
        }
        if(dto.getNombre()==null || dto.getMensaje()==null || dto.getCorreo()==null
                || dto.getTelefono()==null || dto.getFecha()==null)
        {
            return ResponseEntity.badRequest().body("Por favor completar los campos");
        }
        Contacto c= existente.get();
        c.setNombre(dto.getNombre());
        c.setMensaje(dto.getMensaje());
        c.setCorreo(dto.getCorreo());
        c.setTelefono(dto.getTelefono());
        c.setFecha(dto.getFecha());

        cS.insertar(c);
        return ResponseEntity.ok("Datos actualizados con éxito");
    }

}
