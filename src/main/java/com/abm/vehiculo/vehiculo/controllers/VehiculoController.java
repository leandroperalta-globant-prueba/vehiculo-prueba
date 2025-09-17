package com.abm.vehiculo.vehiculo.controllers;

import com.abm.vehiculo.vehiculo.entities.Vehiculo;
import com.abm.vehiculo.vehiculo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/all")
    public List<Vehiculo> getAll() {
        return vehiculoService.vehiculoList();
    }

    @PostMapping("/create")
    public Vehiculo create(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.createVehiculo(vehiculo);
    }

    @PutMapping("/{patente}/update")
    public Vehiculo update(@PathVariable String patente, @RequestBody Vehiculo vehiculo) {
        return vehiculoService.updateVehiculo(patente, vehiculo);
    }

    @DeleteMapping("/{patente}/delete")
    public void delete(@PathVariable String patente) {
        vehiculoService.deleteVehiculo(patente);
    }
}