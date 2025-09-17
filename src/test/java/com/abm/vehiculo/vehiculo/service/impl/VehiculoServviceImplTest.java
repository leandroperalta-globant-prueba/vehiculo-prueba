package com.abm.vehiculo.vehiculo.service.impl;

import com.abm.vehiculo.vehiculo.entities.Vehiculo;
import com.abm.vehiculo.vehiculo.repositories.VehiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class VehiculoServviceImplTest {

    private VehiculoRepository vehiculoRepository;
    private VehiculoServiceImpl vehiculoService;

    @BeforeEach
    void setUp() {
        vehiculoRepository = mock(VehiculoRepository.class);
        vehiculoService = new VehiculoServiceImpl(vehiculoRepository);
    }

    @Test
    void testCreateVehiculo() {
        Vehiculo vehiculo = Vehiculo.builder()
                .id(1L)
                .Color("Azul")
                .marca("Renault")
                .duenio("Julio")
                .patente("asd123")
                .fechaDeEntrega(LocalDate.now())
                .modelo("Clio")
                .build();

        when(vehiculoRepository.save(vehiculo)).thenReturn(Vehiculo.builder()
                .id(1L)
                .Color("Azul")
                .marca("Renault")
                .duenio("Julio")
                .patente("asd123")
                .fechaDeEntrega(LocalDate.now())
                .modelo("Clio")
                .build());

        Vehiculo result = vehiculoService.createVehiculo(vehiculo);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(vehiculoRepository, times(1)).save(vehiculo);
    }

    @Test
    void testAllVehiculos() {
        List<Vehiculo> lista = Arrays.asList(
                Vehiculo.builder()
                        .id(1L)
                        .Color("Rojo")
                        .marca("Ford")
                        .duenio("Juan")
                        .patente("qwe123")
                        .fechaDeEntrega(LocalDate.now())
                        .modelo("Ranger")
                        .build(),
                Vehiculo.builder()
                        .id(2L)
                        .Color("Azul")
                        .marca("Renault")
                        .duenio("Julio")
                        .patente("asd123")
                        .fechaDeEntrega(LocalDate.now())
                        .modelo("Clio")
                        .build());
        when(vehiculoRepository.findAll()).thenReturn(lista);

        List<Vehiculo> result = vehiculoService.vehiculoList();

        assertEquals(2, result.size());
        assertEquals("Ranger", result.get(0).getModelo());
        assertEquals("Renault", result.get(1).getMarca());
        verify(vehiculoRepository, times(1)).findAll();
    }

    @Test
    void testUpdateVehiculo() {
        Vehiculo existing = Vehiculo.builder()
                .id(1L)
                .Color("Rojo")
                .marca("Ford")
                .duenio("Juan")
                .patente("qwe123")
                .fechaDeEntrega(LocalDate.now())
                .modelo("Ranger")
                .build();
        Vehiculo updated = Vehiculo.builder()
                .Color("Azul")
                .marca("Renault")
                .duenio("Julio")
                .patente("asd123")
                .fechaDeEntrega(LocalDate.now())
                .modelo("Clio")
                .build();

        when(vehiculoRepository.findByPatente("qwe123")).thenReturn(Optional.of(existing));
        when(vehiculoRepository.save(existing)).thenReturn(Vehiculo.builder()
                .Color("Azul")
                .marca("Renault")
                .duenio("Julio")
                .patente("asd123")
                .fechaDeEntrega(LocalDate.of(1990,9,28))
                .modelo("Clio")
                .build());

        Vehiculo result = vehiculoService.updateVehiculo("qwe123", updated);

        assertEquals("Renault", result.getMarca());
        assertEquals("Clio", result.getModelo());
        assertEquals("Azul", result.getColor());
        assertEquals("Julio", result.getDuenio());
        assertEquals("asd123", result.getPatente());
        assertEquals(LocalDate.of(1990,9,28), result.getFechaDeEntrega());
        verify(vehiculoRepository, times(1)).findByPatente("qwe123");
        verify(vehiculoRepository, times(1)).save(existing);
    }

    @Test
    void testDeleteByPatente() {
        Vehiculo vehiculo = Vehiculo.builder()
                .id(1L)
                .Color("Rojo")
                .marca("Ford")
                .duenio("Juan")
                .patente("qwe123")
                .fechaDeEntrega(LocalDate.now())
                .modelo("Ranger")
                .build();

        when(vehiculoRepository.findByPatente("qwe123")).thenReturn(Optional.of(vehiculo));
        doNothing().when(vehiculoRepository).delete(vehiculo);

        vehiculoService.deleteVehiculo("qwe123");

        verify(vehiculoRepository, times(1)).findByPatente("qwe123");
        verify(vehiculoRepository, times(1)).delete(vehiculo);
    }

}
