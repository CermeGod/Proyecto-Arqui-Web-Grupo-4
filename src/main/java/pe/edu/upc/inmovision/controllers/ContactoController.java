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
import pe.edu.upc.inmovision.serviceimplements.IUsuarioServiceImplement;
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
    @Autowired
    private IUsuarioServiceImplement uS;

    @PostMapping("/registrar-contacto")
    public ResponseEntity<?> registrar(@RequestBody ContactoDTO dto)
    {
        ModelMapper m = new ModelMapper();
        Optional<Propiedades> listpropiedad=pS.listById(dto.getPropiedadId());
        Optional<Usuario>listusuario=uS.listById(dto.getUsuarioId());
        if(listpropiedad.isPresent() && listusuario.isPresent())
        {
            Contacto c=m.map(dto,Contacto.class);
            Contacto co=cS.insertar(c);
            ContactoDTO responseDTO=m.map(co,ContactoDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }
        else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el usuario o propiedad");
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

    @DeleteMapping("/eliminar-contacto/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id)
    {
        Optional<Contacto> contacto=cS.buscarPorId(id);

        if(contacto.isPresent())
        {
            cS.delete(id);
            return ResponseEntity.ok("Contacto eliminado con éxito");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contacto no encontrad0");
        }
    }



}
