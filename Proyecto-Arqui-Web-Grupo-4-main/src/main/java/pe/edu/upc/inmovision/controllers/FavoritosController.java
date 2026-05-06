package pe.edu.upc.inmovision.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.entities.Favoritos;
import pe.edu.upc.inmovision.serviceimplements.FavoritosServiceImplement;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Inmovision/favoritos")
public class FavoritosController {

    @Autowired
    private FavoritosServiceImplement fS;

    @PostMapping("/registrar-favorito")
    public ResponseEntity<Favoritos> registrar(@RequestBody Favoritos favoritos) {
        Favoritos f = fS.insertar(favoritos);
        return ResponseEntity.status(HttpStatus.CREATED).body(f);
    }

    @GetMapping("/listar-favoritos")
    public ResponseEntity<?> listarFavoritos() {
        List<Favoritos> lista = fS.listar();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No hay favoritos registrados");
        }
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/eliminar-favorito/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Favoritos> favorito = fS.buscarPorId(id);

        if (favorito.isPresent()) {
            fS.delete(id);
            return ResponseEntity.ok("Favorito eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Favorito no encontrado");
        }
    }

    @PutMapping("/actualizar-favorito")
    public ResponseEntity<String> actualizar(@RequestBody Favoritos favoritos) {
        Optional<Favoritos> existente = fS.buscarPorId(favoritos.getFavoritosId());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Favorito no encontrado");
        }

        if (favoritos.getUsuario() == null ||
                favoritos.getPropiedad() == null ||
                favoritos.getColeccion() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Por favor completar los campos");
        }

        Favoritos f = existente.get();
        f.setUsuario(favoritos.getUsuario());
        f.setPropiedad(favoritos.getPropiedad());
        f.setColeccion(favoritos.getColeccion());

        fS.update(f);

        return ResponseEntity.ok("Datos actualizados con éxito");
    }
    @GetMapping("/cantidad-favoritos-por-usuario")
    public ResponseEntity<?> cantidadFavoritosPorUsuario() {

        List<Object[]> lista = fS.cantidadFavoritosPorUsuario();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No hay datos");
        }

        return ResponseEntity.ok(lista);
    }
}
