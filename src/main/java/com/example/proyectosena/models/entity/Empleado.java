package com.example.proyectosena.models.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

// Clase que representa una tabla en nuestra base de datos, los ORM trata a los datos como objetos
@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Empleado {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  // Column es para configurar los campos, length tamaño, unique es unico, nullable no puede ser nulo
  @Column(length = 10, unique = true, nullable = false)
  private String cedula;
  @Column(length = 10, unique = true, nullable = false)
  private String celular;
  @Column(nullable = false)
  private String nombre;
  @Column(nullable = false)
  private String correo;
  @Column(nullable = false)
  private Integer estrato;
  private Float sueldo;
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date fecha_nacimiento;
  private Integer edad;
  @ManyToOne
  @JoinColumn(name = "turno_id")
  private Turno turno;
  // Many to one es la relacion muchos a uno en base de datos, @JoinColumn es un inner join en SQL para acceder
  // a los campos del operador
  @ManyToOne
  @JoinColumn(name = "operador_id")
  private Operador operador;
  private boolean estado;



}
