package com.upiiz.EquiposDeportivos.Repositories;

import com.upiiz.EquiposDeportivos.Entities.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
}