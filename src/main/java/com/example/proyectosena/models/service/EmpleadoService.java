package com.example.proyectosena.models.service;

import java.util.List;

import com.example.proyectosena.models.entity.Empleado;

// Interface para mantener buenas practicas e inyectar en los controladores
public interface EmpleadoService {
  List<Empleado> listarEmpleados();
  Empleado buscarEmpleado(Long id);
  void guardarEmpleado(Empleado empleado);
  void eliminarEmpleado(Long id);

  String cedulaUnica(String cedula);
  String correoUnico(String correo);

  
}
