package pe.edu.upc.inmovision.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovision.entities.Favoritos;
import pe.edu.upc.inmovision.serviceimplements.FavoritosServiceImplement;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Inmovision/favoritos")
@SecurityRequirement(name = "bearerAuth")
public class FavoritosController {

    @Autowired
    private FavoritosServiceImplement fS;

    @PostMapping("/registrar-favorito")
    //@PreAuthorize("hasAuthority('ROLE_CLIENTE')")
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
    //@PreAuthorize("hasAuthority('ROLE_CLIENTE')")
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

    @GetMapping("/cantidad-favoritos-por-usuario")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> cantidadFavoritosPorUsuario() {

        List<Object[]> lista = fS.cantidadFavoritosPorUsuario();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No hay datos");
        }

        return ResponseEntity.ok(lista);
    }
}