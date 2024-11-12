package com.upiiz.EquiposDeportivos.Entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne(targetEntity = Liga.class)
    private Liga liga;

    @ManyToMany(targetEntity = Competencia.class, fetch = FetchType.LAZY)
    private List<Competencia> competencias;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equipo")
    private List<Jugador> jugadores;
}
