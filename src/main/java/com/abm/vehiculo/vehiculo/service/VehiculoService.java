package com.abm.vehiculo.vehiculo.service;

import com.abm.vehiculo.vehiculo.entities.Vehiculo;

import java.util.List;

public interface VehiculoService {

    Vehiculo createVehiculo(Vehiculo vehiculo);

    void deleteVehiculo (String patente);

    Vehiculo updateVehiculo(String patente, Vehiculo vehiculo);

    List<Vehiculo> vehiculoList();
}
