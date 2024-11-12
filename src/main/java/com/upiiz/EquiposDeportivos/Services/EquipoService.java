package com.upiiz.EquiposDeportivos.Services;

import com.upiiz.EquiposDeportivos.Entities.Equipo;
import com.upiiz.EquiposDeportivos.Repositories.EquipoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> getEquipos() {
        return equipoRepository.findAll();
    }

    public Optional<Equipo> getEquipoById(Long id) {
        return equipoRepository.findById(id);
    }

    public Equipo createEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public Equipo updateEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public void deleteEquipoById(Long id) {
        equipoRepository.deleteById(id);
    }
}
