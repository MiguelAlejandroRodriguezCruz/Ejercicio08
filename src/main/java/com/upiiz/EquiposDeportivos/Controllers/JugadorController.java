package com.upiiz.EquiposDeportivos.Controllers;

import com.upiiz.EquiposDeportivos.Entities.Jugador;
import com.upiiz.EquiposDeportivos.Dto.CustomResponse;
import com.upiiz.EquiposDeportivos.Services.JugadorService;
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
@RequestMapping("/jugadors")
public class JugadorController {

    @Autowired
    private JugadorService JugadorService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Jugador>>> getJugadors() {
        List<Jugador> Jugadors = new ArrayList<>();
        Link allJugadorsLink = linkTo(JugadorController.class).withSelfRel();
        List<Link> links = List.of(allJugadorsLink);

        try {
            Jugadors = JugadorService.getJugadors();
            if (!Jugadors.isEmpty()) {
                CustomResponse<List<Jugador>> response = new CustomResponse<>(
                        1, "Jugadors encontrados", Jugadors, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(
                        0, "Jugadors no encontrados", Jugadors, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Jugador>> response = new CustomResponse<>(
                    0, "Error interno de servidor", Jugadors, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Get Jugador by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Jugador>> getJugadorById(@PathVariable("id") Long id) {
        Optional<Jugador> Jugador = JugadorService.getJugadorById(id);
        Link allJugadorsLink = linkTo(JugadorController.class).withSelfRel();
        List<Link> links = List.of(allJugadorsLink);

        if (Jugador.isPresent()) {
            CustomResponse<Jugador> response = new CustomResponse<>(1, "Jugador encontrado", Jugador.get(),
                    links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Jugador> response = new CustomResponse<>(0, "Jugador no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Create Jugador
    @PostMapping
    public ResponseEntity<CustomResponse<Jugador>> createJugador(@RequestBody Jugador Jugador) {
        Link allJugadorsLink = linkTo(JugadorController.class).withSelfRel();
        List<Link> links = List.of(allJugadorsLink);

        try {
            Jugador createdJugador = JugadorService.createJugador(Jugador);
            CustomResponse<Jugador> response = new CustomResponse<>(1, "Jugador creado", createdJugador,
                    links);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            CustomResponse<Jugador> response = new CustomResponse<>(0, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Update Jugador
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Jugador>> updateJugador(@RequestBody Jugador Jugador,
            @PathVariable Long id) {
        Link allJugadorsLink = linkTo(JugadorController.class).withSelfRel();
        List<Link> links = List.of(allJugadorsLink);

        Jugador.setId(id);
        if (JugadorService.getJugadorById(id).isPresent()) {
            Jugador updatedJugador = JugadorService.updateJugador(Jugador);
            CustomResponse<Jugador> response = new CustomResponse<>(1, "Jugador actualizado",
                    updatedJugador, links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Jugador> response = new CustomResponse<>(0, "Jugador no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete Jugador by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteJugadorById(@PathVariable Long id) {
        Link allJugadorsLink = linkTo(JugadorController.class).withSelfRel();
        List<Link> links = List.of(allJugadorsLink);

        if (JugadorService.getJugadorById(id).isPresent()) {
            JugadorService.deleteJugadorById(id);
            CustomResponse<Void> response = new CustomResponse<>(1, "Jugador eliminado", null, links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Void> response = new CustomResponse<>(0, "Jugador no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
