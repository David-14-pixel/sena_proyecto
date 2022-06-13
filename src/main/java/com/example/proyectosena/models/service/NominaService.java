package com.example.proyectosena.models.service;

import com.example.proyectosena.models.entity.Nomina;

import java.util.List;

public interface NominaService {
    List<Nomina> findByEmpleado(Long id);
    void guardar( Nomina nomina);
    void eliminaPorEmpleadoId(Long id);

}
