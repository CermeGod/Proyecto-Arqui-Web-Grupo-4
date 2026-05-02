package pe.edu.upc.inmovision.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.entities.Imagenes;
import pe.edu.upc.inmovision.serviceimplements.ImagenesServiceImplement;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Inmovision/imagenes")
public class ImagenesController {

    @Autowired
    private ImagenesServiceImplement iS;

    @PostMapping("/registrar-imagen")
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

    @PutMapping("/actualizar-imagen")
    public ResponseEntity<String> actualizar(@RequestBody Imagenes imagen) {
        Optional<Imagenes> existente = iS.buscarPorId(imagen.getImagenesId());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Imagen no encontrada");
        }

        if (imagen.getUrlImagen() == null || imagen.getDescripcion() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Por favor completar los campos");
        }

        Imagenes i = existente.get();
        i.setUrlImagen(imagen.getUrlImagen());
        i.setDescripcion(imagen.getDescripcion());
        i.setPropiedad(imagen.getPropiedad());

        iS.update(i);

        return ResponseEntity.ok("Datos actualizados con éxito");
    }
    @GetMapping("/cantidad-imagenes-por-propiedad")
    public ResponseEntity<?> cantidadImagenesPorPropiedad() {

        List<Object[]> lista = iS.cantidadImagenesPorPropiedad();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No hay datos");
        }

        return ResponseEntity.ok(lista);
    }
}