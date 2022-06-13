package com.example.proyectosena.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proyectosena.models.entity.Empleado;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Interface que hereda de Jpa el cual es una implementación de hibernate (ORM) 
//y nos ayuda facilitandonos metodos para realizar acciones sobre la base de datos sin necesidad de escribir SQL 
public interface EmpleadoDao extends JpaRepository<Empleado, Long> {
    @Query("SELECT e FROM Empleado e where e.estado = true")
    List<Empleado> buscarEmpleadosPorEstado();
    Empleado findByCedula(String cedula);
    Empleado findByCorreo(String correo);

    @Transactional
    @Modifying
    @Query("UPDATE Empleado e SET e.estado = ?1 where e.id = ?2")
    public void actualizarEstado(boolean estado, Long id);
  
}
