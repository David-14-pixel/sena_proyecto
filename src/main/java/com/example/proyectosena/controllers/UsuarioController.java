package com.example.proyectosena.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class UsuarioController {
    @GetMapping("/")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("info", "La sesión sigue activa");
            return "redirect:/empleados";
        }
        if (error != null) {
            model.addAttribute("error", "Estas credenciales no coinciden con nuestros registros");
        }
        if (logout != null) {
            model.addAttribute("info", "Ha cerrado sesión con éxito");
        }
        return "index";

    }
}
