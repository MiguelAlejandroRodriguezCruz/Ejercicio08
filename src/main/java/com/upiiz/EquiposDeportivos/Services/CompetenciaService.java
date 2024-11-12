package com.upiiz.EquiposDeportivos.Services;

import com.upiiz.EquiposDeportivos.Entities.Competencia;
import com.upiiz.EquiposDeportivos.Repositories.CompetenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenciaService {

    @Autowired
    private CompetenciaRepository CompetenciaRepository;

    public List<Competencia> getCompetencias() {
        return CompetenciaRepository.findAll();
    }

    public Optional<Competencia> getCompetenciaById(Long id) {
        return CompetenciaRepository.findById(id);
    }

    public Competencia createCompetencia(Competencia Competencia) {
        return CompetenciaRepository.save(Competencia);
    }

    public Competencia updateCompetencia(Competencia Competencia) {
        return CompetenciaRepository.save(Competencia);
    }

    public void deleteCompetenciaById(Long id) {
        CompetenciaRepository.deleteById(id);
    }
}
