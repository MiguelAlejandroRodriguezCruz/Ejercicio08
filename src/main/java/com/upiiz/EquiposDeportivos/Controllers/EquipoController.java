package com.upiiz.EquiposDeportivos.Controllers;

import com.upiiz.EquiposDeportivos.Entities.Equipo;
import com.upiiz.EquiposDeportivos.Dto.CustomResponse;
import com.upiiz.EquiposDeportivos.Services.EquipoService;
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
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Equipo>>> getEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        Link allEquiposLink = linkTo(EquipoController.class).withSelfRel();
        List<Link> links = List.of(allEquiposLink);

        try {
            equipos = equipoService.getEquipos();
            if (!equipos.isEmpty()) {
                CustomResponse<List<Equipo>> response = new CustomResponse<>(
                        1, "Equipos encontrados", equipos, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(
                        0, "Equipos no encontrados", equipos, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Equipo>> response = new CustomResponse<>(
                    0, "Error interno de servidor", equipos, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Get equipo by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Equipo>> getEquipoById(@PathVariable("id") Long id) {
        Optional<Equipo> equipo = equipoService.getEquipoById(id);
        Link allEquiposLink = linkTo(EquipoController.class).withSelfRel();
        List<Link> links = List.of(allEquiposLink);

        if (equipo.isPresent()) {
            CustomResponse<Equipo> response = new CustomResponse<>(1, "Equipo encontrado", equipo.get(), links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Equipo> response = new CustomResponse<>(0, "Equipo no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Create equipo
    @PostMapping
    public ResponseEntity<CustomResponse<Equipo>> createEquipo(@RequestBody Equipo equipo) {
        Link allEquiposLink = linkTo(EquipoController.class).withSelfRel();
        List<Link> links = List.of(allEquiposLink);

        try {
            Equipo createdEquipo = equipoService.createEquipo(equipo);
            CustomResponse<Equipo> response = new CustomResponse<>(1, "Equipo creado", createdEquipo, links);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            CustomResponse<Equipo> response = new CustomResponse<>(0, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Update equipo
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Equipo>> updateEquipo(@RequestBody Equipo equipo, @PathVariable Long id) {
        Link allEquiposLink = linkTo(EquipoController.class).withSelfRel();
        List<Link> links = List.of(allEquiposLink);

        equipo.setId(id);
        if (equipoService.getEquipoById(id).isPresent()) {
            Equipo updatedEquipo = equipoService.updateEquipo(equipo);
            CustomResponse<Equipo> response = new CustomResponse<>(1, "Equipo actualizado", updatedEquipo, links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Equipo> response = new CustomResponse<>(0, "Equipo no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete equipo by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteEquipoById(@PathVariable Long id) {
        Link allEquiposLink = linkTo(EquipoController.class).withSelfRel();
        List<Link> links = List.of(allEquiposLink);

        if (equipoService.getEquipoById(id).isPresent()) {
            equipoService.deleteEquipoById(id);
            CustomResponse<Void> response = new CustomResponse<>(1, "Equipo eliminado", null, links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Void> response = new CustomResponse<>(0, "Equipo no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
