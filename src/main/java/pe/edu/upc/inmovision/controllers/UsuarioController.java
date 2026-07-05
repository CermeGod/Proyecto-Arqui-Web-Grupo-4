package pe.edu.upc.inmovision.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.*;
import pe.edu.upc.inmovision.entities.Rol;
import pe.edu.upc.inmovision.entities.Usuario;
import pe.edu.upc.inmovision.serviceinterfaces.IRolService;
import pe.edu.upc.inmovision.serviceinterfaces.IUsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Inmovision/usuario")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {
    @Autowired
    private IUsuarioService uS;
    @Autowired
    private IRolService rS;

    @PostMapping("/registrar-usuario")
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> insertar(@RequestBody UsuarioDTO dto) {
        ModelMapper m=new ModelMapper();
        Optional<Rol> existente= rS.listById(dto.getRolId());
        if(existente.isPresent())
        {
            Usuario u=m.map(dto,Usuario.class);
            Usuario us=uS.insertar(u);
            UsuarioDTO responseDTO=m.map(us,UsuarioDTO.class);
            return  ResponseEntity.ok(responseDTO);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
        }
    }
    @GetMapping("/listar-usuario")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<Usuario> project = uS.listById(id);

        if (project.isPresent()) {
            UsuarioEditDTO dto = m.map(project.get(), UsuarioEditDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proyecto no encontrado");
        }
    }
    @DeleteMapping("/eliminar-usuario/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
    @PutMapping("/modificar-usuario")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String>actualizar(@RequestBody UsuarioEditDTO dto)
    {
        Optional<Usuario>existente=uS.listById(dto.getUsuarioId());
        if(existente.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        if(dto.getNombre()==null || dto.getApellido()==null  || dto.getCorreo()==null
        || dto.getTelefono()==null || dto.getFotoUrl()==null || dto.getRolId()<=0 || dto.getEnabled()== null)
        {
            return ResponseEntity.badRequest().body("Por favor completar los campos");
        }
        Optional<Rol> existe=rS.listById(dto.getRolId());
        if(existe.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
        }
        Usuario u= existente.get();
        u.setNombre(dto.getNombre());
        u.setApellido(dto.getApellido());
        u.setCorreo(dto.getCorreo());
        u.setTelefono(dto.getTelefono());
        u.setFotoUrl(dto.getFotoUrl());
        u.setRol(existe.get());
        u.setEnabled(dto.getEnabled());

        uS.update(u);
        return ResponseEntity.ok("Datos actualizados con éxito");
    }

    @GetMapping("/con-propiedades")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> obtenerUsuariosConPropiedades() {
        List<Object[]>listarCantidad=uS.listarPropietariosConPropiedades();
        if(listarCantidad.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay propiedades registradas");
        }
        List<UsuarioPropiedadDTO> respuesta=new ArrayList<>();
        for(Object[] fila:listarCantidad)
        {
            UsuarioPropiedadDTO dto=new UsuarioPropiedadDTO();
            dto.setUsuarioId(((Number)fila[0]).intValue());
            dto.setNombre(((String)fila[1]));
            dto.setApellido(((String)fila[2]));
            dto.setTotalPropiedades(((Number)fila[3]).intValue());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/listar-cantidad-usuarios-rol")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> obtenerUsuariosPorRol() {
        List<Object[]>listarCantUsuariosRol=uS.contarUsuariosPorRol();
        if(listarCantUsuariosRol.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay usuarios registrados");
        }
        List<RolCountDTO>respuesta=new ArrayList<>();
        for(Object[] fila:listarCantUsuariosRol)
        {
            RolCountDTO dto=new RolCountDTO();
            dto.setNombre(((String)fila[0]));
            dto.setTotalUsuarios(((Number)fila[1]).intValue());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }




}
