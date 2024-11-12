package com.upiiz.EquiposDeportivos.Services;

import com.upiiz.EquiposDeportivos.Entities.Jugador;
import com.upiiz.EquiposDeportivos.Repositories.JugadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository JugadorRepository;

    public List<Jugador> getJugadors() {
        return JugadorRepository.findAll();
    }

    public Optional<Jugador> getJugadorById(Long id) {
        return JugadorRepository.findById(id);
    }

    public Jugador createJugador(Jugador Jugador) {
        return JugadorRepository.save(Jugador);
    }

    public Jugador updateJugador(Jugador Jugador) {
        return JugadorRepository.save(Jugador);
    }

    public void deleteJugadorById(Long id) {
        JugadorRepository.deleteById(id);
    }
}
