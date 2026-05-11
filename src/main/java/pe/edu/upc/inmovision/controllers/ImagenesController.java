package pe.edu.upc.inmovision.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.entities.Imagenes;
import pe.edu.upc.inmovision.serviceimplements.ImagenesServiceImplement;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Inmovision/imagenes")
@SecurityRequirement(name = "bearerAuth")
public class ImagenesController {

    @Autowired
    private ImagenesServiceImplement iS;

    @PostMapping("/registrar-imagen")
    @PreAuthorize("hasAuthority('ROLE_PROPIETARIO')")
    public ResponseEntity<Imagenes> registrar(@RequestBody Imagenes imagen) {
        Imagenes i = iS.insertar(imagen);
        return ResponseEntity.status(HttpStatus.CREATED).body(i);
    }

    @GetMapping("/listar-imagenes")
    public ResponseEntity<?> listarImagenes() {
        List<Imagenes> lista = iS.listar();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No hay imágenes registradas");
        }
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/eliminar-imagen/{id}")
    @PreAuthorize("hasAuthority('ROLE_PROPIETARIO')")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Imagenes> imagen = iS.buscarPorId(id);

        if (imagen.isPresent()) {
            iS.delete(id);
            return ResponseEntity.ok("Imagen eliminada con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Imagen no encontrada");
        }
    }

    @GetMapping("/cantidad-imagenes-por-propiedad")
    @PreAuthorize("hasAuthority('ROLE_PROPIETARIO')")
    public ResponseEntity<?> cantidadImagenesPorPropiedad() {

        List<Object[]> lista = iS.cantidadImagenesPorPropiedad();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No hay datos");
        }

        return ResponseEntity.ok(lista);
    }
}