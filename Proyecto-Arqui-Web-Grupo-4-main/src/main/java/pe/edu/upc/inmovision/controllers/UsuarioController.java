package pe.edu.upc.inmovision.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.ProvinciaDTO;
import pe.edu.upc.inmovision.dtos.UsuarioDTO;
import pe.edu.upc.inmovision.dtos.UsuarioResponseDTO;
import pe.edu.upc.inmovision.entities.Departamento;
import pe.edu.upc.inmovision.entities.Provincia;
import pe.edu.upc.inmovision.entities.Rol;
import pe.edu.upc.inmovision.entities.Usuario;
import pe.edu.upc.inmovision.serviceinterfaces.IRolService;
import pe.edu.upc.inmovision.serviceinterfaces.IUsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Inmovision/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService uS;
    @Autowired
    private IRolService rS;

    @PostMapping("/registrar-usuario")
    public ResponseEntity<?> insertar(@RequestBody UsuarioDTO dto)
    {
        ModelMapper m= new ModelMapper();
        Optional<Rol>listado=rS.listById(dto.getRolId());
        if(listado.isPresent())
        {
            Usuario u =m.map(dto,Usuario.class);
            Usuario usuario=uS.insertar(u);
            UsuarioDTO responseDTO=m.map(usuario,UsuarioDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }
        else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
        }


    }
    @GetMapping("/listar-usuario")
    public ResponseEntity<?> listar()
    {
        ModelMapper m= new ModelMapper();
        List<UsuarioResponseDTO> listado=uS.listar().stream()
                .map(y->m.map(y, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
        if(listado.isEmpty())
        {
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay usuarios registrados");
        }
        return ResponseEntity.ok(listado);
    }
    @DeleteMapping("/eliminar-usuario/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id)
    {
        Optional<Usuario> usuario=uS.listById(id);

        if(usuario.isPresent())
        {
            uS.delete(id);
            return ResponseEntity.ok("Usuario eliminado con éxito");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
    @PutMapping
    public ResponseEntity<String>actualizar(@RequestBody UsuarioDTO dto)
    {
        Optional<Usuario>existente=uS.listById(dto.getUsuarioId());
        if(existente.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        if(dto.getNombre()==null || dto.getApellido()==null || dto.getContrasena()==null || dto.getCorreo()==null
        || dto.getTelefono()==null || dto.getFotoUrl()==null)
        {
            return ResponseEntity.badRequest().body("Por favor completar los campos");
        }
        Usuario u= existente.get();
        u.setNombre(dto.getNombre());
        u.setApellido(dto.getApellido());
        u.setContrasena(dto.getContrasena());
        u.setCorreo(dto.getCorreo());
        u.setTelefono(dto.getTelefono());
        u.setFotoUrl(dto.getFotoUrl());

        uS.insertar(u);
        return ResponseEntity.ok("Datos actualizados con éxito");
    }
}
