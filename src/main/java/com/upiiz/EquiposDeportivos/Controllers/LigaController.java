package com.upiiz.EquiposDeportivos.Controllers;

import com.upiiz.EquiposDeportivos.Entities.Liga;
import com.upiiz.EquiposDeportivos.Dto.CustomResponse;
import com.upiiz.EquiposDeportivos.Services.LigaService;
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
@RequestMapping("/ligas")
public class LigaController {

    @Autowired
    private LigaService LigaService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Liga>>> getLigas() {
        List<Liga> Ligas = new ArrayList<>();
        Link allLigasLink = linkTo(LigaController.class).withSelfRel();
        List<Link> links = List.of(allLigasLink);

        try {
            Ligas = LigaService.getLigas();
            if (!Ligas.isEmpty()) {
                CustomResponse<List<Liga>> response = new CustomResponse<>(
                        1, "Ligas encontrados", Ligas, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(
                        0, "Ligas no encontrados", Ligas, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Liga>> response = new CustomResponse<>(
                    0, "Error interno de servidor", Ligas, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Get Liga by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Liga>> getLigaById(@PathVariable("id") Long id) {
        Optional<Liga> Liga = LigaService.getLigaById(id);
        Link allLigasLink = linkTo(LigaController.class).withSelfRel();
        List<Link> links = List.of(allLigasLink);

        if (Liga.isPresent()) {
            CustomResponse<Liga> response = new CustomResponse<>(1, "Liga encontrado", Liga.get(),
                    links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Liga> response = new CustomResponse<>(0, "Liga no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Create Liga
    @PostMapping
    public ResponseEntity<CustomResponse<Liga>> createLiga(@RequestBody Liga Liga) {
        Link allLigasLink = linkTo(LigaController.class).withSelfRel();
        List<Link> links = List.of(allLigasLink);

        try {
            Liga createdLiga = LigaService.createLiga(Liga);
            CustomResponse<Liga> response = new CustomResponse<>(1, "Liga creado", createdLiga,
                    links);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            CustomResponse<Liga> response = new CustomResponse<>(0, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Update Liga
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Liga>> updateLiga(@RequestBody Liga Liga,
            @PathVariable Long id) {
        Link allLigasLink = linkTo(LigaController.class).withSelfRel();
        List<Link> links = List.of(allLigasLink);

        Liga.setId(id);
        if (LigaService.getLigaById(id).isPresent()) {
            Liga updatedLiga = LigaService.updateLiga(Liga);
            CustomResponse<Liga> response = new CustomResponse<>(1, "Liga actualizado",
                    updatedLiga, links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Liga> response = new CustomResponse<>(0, "Liga no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete Liga by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteLigaById(@PathVariable Long id) {
        Link allLigasLink = linkTo(LigaController.class).withSelfRel();
        List<Link> links = List.of(allLigasLink);

        if (LigaService.getLigaById(id).isPresent()) {
            LigaService.deleteLigaById(id);
            CustomResponse<Void> response = new CustomResponse<>(1, "Liga eliminado", null, links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Void> response = new CustomResponse<>(0, "Liga no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
