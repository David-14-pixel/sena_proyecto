package com.example.proyectosena.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nominas")
@Data @NoArgsConstructor @AllArgsConstructor
public class Nomina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float auxilio_transporte;
    private Float bono_cumpleanos;
    private Float val_plan_celular;
    private Float descuento_salud;
    private Float descuento_pension;
    private Float total_devegado;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @PrePersist
    public void prePersist(){
        fecha = new Date();
    }
}

