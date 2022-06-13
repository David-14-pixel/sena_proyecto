package com.example.proyectosena.models.service;

import com.example.proyectosena.models.dao.EmpleadoDao;
import com.example.proyectosena.models.dao.NominaDao;
import com.example.proyectosena.models.entity.Empleado;
import com.example.proyectosena.models.entity.Nomina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NominaServiceImp implements NominaService{
    @Autowired
    private NominaDao nominaDao;

    @Autowired
    private EmpleadoDao empleadoDao;

    @Override
    public List<Nomina> findByEmpleado(Long id) {
        return nominaDao.findByEmpleado(id);
    }

    @Override
    public void guardar(Nomina nomina) {
        nominaDao.save(nomina);

    }

    @Override
    public void eliminaPorEmpleadoId(Long id) {
        nominaDao.deleteByEmpleadoId(id);
    }
}
