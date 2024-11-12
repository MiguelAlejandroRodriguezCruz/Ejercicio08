package com.upiiz.EquiposDeportivos.Controllers;

import com.upiiz.EquiposDeportivos.Entities.Entrenador;
import com.upiiz.EquiposDeportivos.Dto.CustomResponse;
import com.upiiz.EquiposDeportivos.Services.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/entrenadors")
public class EntrenadorController {

    @Autowired
    private EntrenadorService EntrenadorService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Entrenador>>> getEntrenadors() {
        List<Entrenador> Entrenadors = new ArrayList<>();
        Link allEntrenadorsLink = linkTo(EntrenadorController.class).withSelfRel();
        List<Link> links = List.of(allEntrenadorsLink);

        try {
            Entrenadors = EntrenadorService.getEntrenadors();
            if (!Entrenadors.isEmpty()) {
                CustomResponse<List<Entrenador>> response = new CustomResponse<>(
                        1, "Entrenadors encontrados", Entrenadors, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(
                        0, "Entrenadors no encontrados", Entrenadors, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Entrenador>> response = new CustomResponse<>(
                    0, "Error interno de servidor", Entrenadors, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Get Entrenador by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Entrenador>> getEntrenadorById(@PathVariable("id") Long id) {
        Optional<Entrenador> Entrenador = EntrenadorService.getEntrenadorById(id);
        Link allEntrenadorsLink = linkTo(EntrenadorController.class).withSelfRel();
        List<Link> links = List.of(allEntrenadorsLink);

        if (Entrenador.isPresent()) {
            CustomResponse<Entrenador> response = new CustomResponse<>(1, "Entrenador encontrado", Entrenador.get(),
                    links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Entrenador> response = new CustomResponse<>(0, "Entrenador no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Create Entrenador
    @PostMapping
    public ResponseEntity<CustomResponse<Entrenador>> createEntrenador(@RequestBody Entrenador Entrenador) {
        Link allEntrenadorsLink = linkTo(EntrenadorController.class).withSelfRel();
        List<Link> links = List.of(allEntrenadorsLink);

        try {
            Entrenador createdEntrenador = EntrenadorService.createEntrenador(Entrenador);
            CustomResponse<Entrenador> response = new CustomResponse<>(1, "Entrenador creado", createdEntrenador,
                    links);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            CustomResponse<Entrenador> response = new CustomResponse<>(0, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Update Entrenador
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Entrenador>> updateEntrenador(@RequestBody Entrenador Entrenador,
            @PathVariable Long id) {
        Link allEntrenadorsLink = linkTo(EntrenadorController.class).withSelfRel();
        List<Link> links = List.of(allEntrenadorsLink);

        Entrenador.setId(id);
        if (EntrenadorService.getEntrenadorById(id).isPresent()) {
            Entrenador updatedEntrenador = EntrenadorService.updateEntrenador(Entrenador);
            CustomResponse<Entrenador> response = new CustomResponse<>(1, "Entrenador actualizado",
                    updatedEntrenador, links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Entrenador> response = new CustomResponse<>(0, "Entrenador no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete Entrenador by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteEntrenadorById(@PathVariable Long id) {
        Link allEntrenadorsLink = linkTo(EntrenadorController.class).withSelfRel();
        List<Link> links = List.of(allEntrenadorsLink);

        if (EntrenadorService.getEntrenadorById(id).isPresent()) {
            EntrenadorService.deleteEntrenadorById(id);
            CustomResponse<Void> response = new CustomResponse<>(1, "Entrenador eliminado", null, links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Void> response = new CustomResponse<>(0, "Entrenador no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
