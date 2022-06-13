package com.example.proyectosena.controllers;

import com.example.proyectosena.models.entity.Nomina;
import com.example.proyectosena.models.service.NominaService;
import com.example.proyectosena.models.service.TurnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.proyectosena.models.entity.Empleado;
import com.example.proyectosena.models.service.EmpleadoService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

  Logger logger = LoggerFactory.getLogger(EmpleadoController.class);
  
  @Autowired
  private EmpleadoService service;

  @Autowired
  private TurnoService turnoService;

  @Autowired
  private NominaService nominaService;

  @GetMapping
  public String listar(Model model){
    model.addAttribute("empleados", service.listarEmpleados());
    return "empleados/index";
  }
  @GetMapping("/encontrar/{id}")
  @ResponseBody
  public Empleado  encontrar(@PathVariable("id") Long id){
    return service.buscarEmpleado(id);
  }

  @GetMapping("/nuevo")
  public String nuevo(Model model){
    model.addAttribute("empleado", new Empleado());
    model.addAttribute("turnos", turnoService.listarTurnos());
    return "empleados/formulario";
  }

  @PostMapping
  public String guardar(Empleado empleado, RedirectAttributes flash){
    String mensaje = "";
    if(empleado.getId() != null){
        mensaje = "Se edito correctamente el empleado";

    }else{
      mensaje = "Se agrego correctamente el empleado";
    }
    service.guardarEmpleado(empleado);
    flash.addFlashAttribute("info", mensaje);
    return "redirect:/empleados";
  }

  @GetMapping("/{id}/editar")
  public String editar(@PathVariable("id") Long id, Model model){
    Empleado empleado = service.buscarEmpleado(id);
    model.addAttribute("empleado", empleado);
    model.addAttribute("turnos", turnoService.listarTurnos());
    return "empleados/formulario";
  }
  @GetMapping("/{id}/eliminar")
  public String eliminar(@PathVariable("id") Long id){
    service.eliminarEmpleado(id);
    return "redirect:/empleados";
  }

  @GetMapping("/{cedula}/cedula")
  @ResponseBody
  public String buscarCedula(@PathVariable("cedula") String cedula){
    return service.cedulaUnica(cedula);
  }
  @GetMapping("/{correo}/correo")
  @ResponseBody
  public String buscarCorreo(@PathVariable("correo") String correo){
    return service.correoUnico(correo);
  }
}
