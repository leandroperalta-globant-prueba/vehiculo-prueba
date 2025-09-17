package com.abm.vehiculo.vehiculo.repositories;

import com.abm.vehiculo.vehiculo.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    Optional<Vehiculo> findByPatente(String patente);

    void deleteByPatente(String patente);
}
