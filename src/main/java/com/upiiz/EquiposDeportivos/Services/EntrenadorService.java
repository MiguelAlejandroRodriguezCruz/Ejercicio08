package com.upiiz.EquiposDeportivos.Services;

import com.upiiz.EquiposDeportivos.Entities.Entrenador;
import com.upiiz.EquiposDeportivos.Repositories.EntrenadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository EntrenadorRepository;

    public List<Entrenador> getEntrenadors() {
        return EntrenadorRepository.findAll();
    }

    public Optional<Entrenador> getEntrenadorById(Long id) {
        return EntrenadorRepository.findById(id);
    }

    public Entrenador createEntrenador(Entrenador Entrenador) {
        return EntrenadorRepository.save(Entrenador);
    }

    public Entrenador updateEntrenador(Entrenador Entrenador) {
        return EntrenadorRepository.save(Entrenador);
    }

    public void deleteEntrenadorById(Long id) {
        EntrenadorRepository.deleteById(id);
    }
}
