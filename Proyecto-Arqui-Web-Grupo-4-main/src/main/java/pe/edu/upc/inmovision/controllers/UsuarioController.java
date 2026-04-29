package pe.edu.upc.inmovision.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.dtos.UsuarioDTO;
import pe.edu.upc.inmovision.dtos.UsuarioResponseDTO;
import pe.edu.upc.inmovision.entities.Rol;
import pe.edu.upc.inmovision.entities.Usuario;
import pe.edu.upc.inmovision.serviceinterfaces.IRolService;
import pe.edu.upc.inmovision.serviceinterfaces.IUsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Inmovision")
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
}
