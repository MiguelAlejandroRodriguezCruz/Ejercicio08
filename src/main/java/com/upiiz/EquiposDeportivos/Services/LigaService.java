package com.upiiz.EquiposDeportivos.Services;

import com.upiiz.EquiposDeportivos.Entities.Liga;
import com.upiiz.EquiposDeportivos.Repositories.LigaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigaService {

    @Autowired
    private LigaRepository LigaRepository;

    public List<Liga> getLigas() {
        return LigaRepository.findAll();
    }

    public Optional<Liga> getLigaById(Long id) {
        return LigaRepository.findById(id);
    }

    public Liga createLiga(Liga Liga) {
        return LigaRepository.save(Liga);
    }

    public Liga updateLiga(Liga Liga) {
        return LigaRepository.save(Liga);
    }

    public void deleteLigaById(Long id) {
        LigaRepository.deleteById(id);
    }
}
