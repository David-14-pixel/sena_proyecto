package com.example.proyectosena.models.service;

import java.util.List;

import com.example.proyectosena.models.dao.OperadorDao;
import com.example.proyectosena.models.entity.Operador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyectosena.models.dao.EmpleadoDao;
import com.example.proyectosena.models.entity.Empleado;

// Anotación para configurar la clase como un servicio, es decir clase que contiene la logica de negocio
@Service
public class EmpleadoServiceImp implements EmpleadoService{
  /*  Anotación Autowired para configurar una inyección de dependencia, es decir Spring simula
      una instancia (EmpleadoDao dao = new EmpleadoDao) por debajo de la interface EmpleadoDao
  */
  @Autowired
  private EmpleadoDao dao;

  @Autowired
  private OperadorDao operadorDao;

  @Override
  public List<Empleado> listarEmpleados() {
    // Metodo de Jpa que realiza la siguiente consulta SQL -> SELECT * FROM empleados where estado = true
    return dao.buscarEmpleadosPorEstado();
  }

  @Override
  public Empleado buscarEmpleado(Long id) {
    // Metodo de Jpa que realiza la siguiente consulta SQL -> SELECT * FROM empleados WHERE id = 1
    /*orElse es de la clase Optional es para evitar el error nullPointerException, es decir si el
    registro llega nulo devuelve un null y no una excepción*/
    return dao.findById(id).orElse(null);
  }

  @Override
  public void guardarEmpleado(Empleado empleado) {
    // Metodo de Jpa que realiza un insert si el id es vacío y un update si el id es diferente de vacío
    // Metodo Long.parseLong es para convertir de String a tipo Long
    // substring extrae los primeros 3 digitos del celular
    Long celular = Long.parseLong(empleado.getCelular().substring(0, 3));
    Operador operador = operadorDao.findById(celular).orElse(null);
    // Le agregar el operador a la propiedad Operador del empleado
    empleado.setOperador(operador);
    // Le agrega el estado activo por defecto al empleado
    empleado.setEstado(true);
    dao.save(empleado);
    
  }

  @Override
  public void eliminarEmpleado(Long id) {
    // Metodo que creamos que realiza un update del estado en la base de datos
    Empleado empleado = dao.findById(id).orElse(null);
    if(empleado.isEstado()){
      dao.actualizarEstado(false, empleado.getId());
    }else{
      dao.actualizarEstado(true, empleado.getId());
    }

    
  }

  @Override
  public String cedulaUnica(String cedula) {
    // Operador ternario ? verdadera la condicion : falsa la condicion
    return (dao.findByCedula(cedula) == null) ? "Unico" : "Existe";
  }

  @Override
  public String correoUnico(String correo) {
    return (dao.findByCorreo(correo) == null) ? "Unico" : "Existe";
  }


}
