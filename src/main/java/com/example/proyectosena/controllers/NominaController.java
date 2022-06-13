package com.example.proyectosena.controllers;

import com.example.proyectosena.formats.NominaExcelExporter;
import com.example.proyectosena.formats.NominaPDFExporter;
import com.example.proyectosena.models.entity.Empleado;
import com.example.proyectosena.models.entity.Nomina;
import com.example.proyectosena.models.service.EmpleadoService;
import com.example.proyectosena.models.service.NominaService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/nominas")
public class NominaController {
   @Autowired
   private EmpleadoService empleadoService;

   @Autowired
    private NominaService nominaService;

   @GetMapping("/{id}/liquidar")
    public String liquidar(@PathVariable("id")Long id,  Model model){
       Empleado empleado = empleadoService.buscarEmpleado(id);
       Nomina nomina = new Nomina();
       // Formulas para hallar la liquidación de nomina
      // si empleado.getSueldo() < 1817052 si es verdadero ? 106454, si es falso 0 a la variable
       Float auxilio_transporte = empleado.getSueldo() < 1817052 ?  106454f : 0f;
       Float bono_cumpleanos = empleado.getSueldo() <= 908526 ? (empleado.getSueldo() * 3 / 100) : 0;
       Float valor_operador = empleado.getOperador() != null ? empleado.getOperador().getValor_plan() : 0f;
       // Pensiones y salud
       Float otros = ((empleado.getSueldo() * 4) / 100);
       Float total = (empleado.getSueldo() + auxilio_transporte + bono_cumpleanos) - (valor_operador + (otros * 2));

       model.addAttribute("auxilio_transporte", auxilio_transporte);
       model.addAttribute("bono_cumpleanos", bono_cumpleanos);
       model.addAttribute("otros", otros);
       model.addAttribute("total", total);
       model.addAttribute("empleado", empleado);
       model.addAttribute("nomina", nomina);
       return "nominas/formulario";
   }

   @GetMapping("/{id}/detalle")
   public String encontrarNominaEmpleado(@PathVariable("id") Long id, Model model){
      model.addAttribute("nominas", nominaService.findByEmpleado(id));
      return "nominas/detalle";
   }

   @PostMapping
   public String guardar(Nomina nomina, RedirectAttributes flash){
      nominaService.guardar(nomina);
      flash.addFlashAttribute("info", "Se agrego correctamente la liquidación de nomina");
      return "redirect:/empleados";
   }

   @GetMapping("/{id}/export/pdf")
   public void exportToPDF(@PathVariable("id") Long id, HttpServletResponse response) throws DocumentException, IOException {
      response.setContentType("application/pdf");
      DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
      String currentDateTime = dateFormatter.format(new Date());

      String headerKey = "Content-Disposition";
      String headerValue = "attachment; filename=liquidación_" + currentDateTime + ".pdf";
      response.setHeader(headerKey, headerValue);

      List<Nomina> nominas = nominaService.findByEmpleado(id);

      NominaPDFExporter exporter = new NominaPDFExporter(nominas);
      exporter.export(response);

   }
   @GetMapping("/{id}/export/excel")
   public void exportToExcel(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
      response.setContentType("application/octet-stream");
      DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
      String currentDateTime = dateFormatter.format(new Date());

      String headerKey = "Content-Disposition";
      String headerValue = "attachment; filename=nominas_" + currentDateTime + ".xlsx";
      response.setHeader(headerKey, headerValue);

      List<Nomina> nominas = nominaService. findByEmpleado(id);

      NominaExcelExporter excelExporter = new NominaExcelExporter(nominas);

      excelExporter.export(response);
   }
}
