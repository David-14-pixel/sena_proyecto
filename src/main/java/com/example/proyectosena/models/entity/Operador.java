package com.example.proyectosena.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operadores")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Operador {
    @Id
    private Long id;
    private String operador;
    private Float valor_plan;
}
