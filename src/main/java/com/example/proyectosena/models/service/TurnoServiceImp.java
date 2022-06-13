package com.example.proyectosena.models.service;

import com.example.proyectosena.models.dao.TurnoDao;
import com.example.proyectosena.models.entity.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TurnoServiceImp implements TurnoService{

    @Autowired
    private TurnoDao dao;

    @Override
    public List<Turno> listarTurnos() {
        return dao.findAll();
    }
}
