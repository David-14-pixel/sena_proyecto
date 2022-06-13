package com.example.proyectosena.models.dao;

import com.example.proyectosena.models.entity.Nomina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NominaDao extends JpaRepository<Nomina, Long> {
    @Query("SELECT n from Nomina n where n.empleado.id = ?1")
    List<Nomina> findByEmpleado(Long id);
    @Query("DELETE FROM Nomina n where n.empleado.id = ?1")
    void deleteByEmpleadoId(Long id);
}
