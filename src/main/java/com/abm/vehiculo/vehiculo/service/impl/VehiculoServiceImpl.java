package com.abm.vehiculo.vehiculo.service.impl;

import com.abm.vehiculo.vehiculo.entities.Vehiculo;
import com.abm.vehiculo.vehiculo.repositories.VehiculoRepository;
import com.abm.vehiculo.vehiculo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public Vehiculo createVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public void deleteVehiculo(String patente) {
        Vehiculo vehiculo = vehiculoRepository.findByPatente(patente).orElseThrow(() ->
                new RuntimeException("Vehiculo con patente "+ patente+ " no encontado."));
        vehiculoRepository.delete(vehiculo);
    }

    @Override
    public Vehiculo updateVehiculo(String patente, Vehiculo vehiculo) {
        Vehiculo vehiculobd = vehiculoRepository.findByPatente(patente).orElseThrow(() ->
                new RuntimeException("Vehiculo con patente "+ patente + " no encontado."));

        if(vehiculobd != null){
            vehiculobd.setPatente(vehiculo.getPatente());
            vehiculobd.setColor(vehiculo.getColor());
            vehiculobd.setMarca(vehiculo.getMarca());
            vehiculobd.setDuenio(vehiculo.getDuenio());
            vehiculobd.setModelo(vehiculo.getModelo());
            vehiculobd.setFechaDeEntrega(vehiculo.getFechaDeEntrega());
            return vehiculoRepository.save(vehiculobd);
        }

        return null;
    }

    @Override
    public List<Vehiculo> vehiculoList() {
        return vehiculoRepository.findAll();
    }
}
