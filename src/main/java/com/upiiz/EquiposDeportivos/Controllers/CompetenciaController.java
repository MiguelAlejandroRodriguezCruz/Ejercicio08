package com.upiiz.EquiposDeportivos.Controllers;

import com.upiiz.EquiposDeportivos.Entities.Competencia;
import com.upiiz.EquiposDeportivos.Dto.CustomResponse;
import com.upiiz.EquiposDeportivos.Services.CompetenciaService;
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
@RequestMapping("/competencias")
public class CompetenciaController {

    @Autowired
    private CompetenciaService CompetenciaService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Competencia>>> getCompetencias() {
        List<Competencia> Competencias = new ArrayList<>();
        Link allCompetenciasLink = linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links = List.of(allCompetenciasLink);

        try {
            Competencias = CompetenciaService.getCompetencias();
            if (!Competencias.isEmpty()) {
                CustomResponse<List<Competencia>> response = new CustomResponse<>(
                        1, "Competencias encontrados", Competencias, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(
                        0, "Competencias no encontrados", Competencias, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Competencia>> response = new CustomResponse<>(
                    0, "Error interno de servidor", Competencias, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Get Competencia by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Competencia>> getCompetenciaById(@PathVariable("id") Long id) {
        Optional<Competencia> Competencia = CompetenciaService.getCompetenciaById(id);
        Link allCompetenciasLink = linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links = List.of(allCompetenciasLink);

        if (Competencia.isPresent()) {
            CustomResponse<Competencia> response = new CustomResponse<>(1, "Competencia encontrado", Competencia.get(),
                    links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Competencia> response = new CustomResponse<>(0, "Competencia no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Create Competencia
    @PostMapping
    public ResponseEntity<CustomResponse<Competencia>> createCompetencia(@RequestBody Competencia Competencia) {
        Link allCompetenciasLink = linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links = List.of(allCompetenciasLink);

        try {
            Competencia createdCompetencia = CompetenciaService.createCompetencia(Competencia);
            CustomResponse<Competencia> response = new CustomResponse<>(1, "Competencia creado", createdCompetencia,
                    links);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            CustomResponse<Competencia> response = new CustomResponse<>(0, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Update Competencia
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Competencia>> updateCompetencia(@RequestBody Competencia Competencia,
            @PathVariable Long id) {
        Link allCompetenciasLink = linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links = List.of(allCompetenciasLink);

        Competencia.setId(id);
        if (CompetenciaService.getCompetenciaById(id).isPresent()) {
            Competencia updatedCompetencia = CompetenciaService.updateCompetencia(Competencia);
            CustomResponse<Competencia> response = new CustomResponse<>(1, "Competencia actualizado",
                    updatedCompetencia, links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Competencia> response = new CustomResponse<>(0, "Competencia no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete Competencia by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteCompetenciaById(@PathVariable Long id) {
        Link allCompetenciasLink = linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links = List.of(allCompetenciasLink);

        if (CompetenciaService.getCompetenciaById(id).isPresent()) {
            CompetenciaService.deleteCompetenciaById(id);
            CustomResponse<Void> response = new CustomResponse<>(1, "Competencia eliminado", null, links);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            CustomResponse<Void> response = new CustomResponse<>(0, "Competencia no encontrado", null, links);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
