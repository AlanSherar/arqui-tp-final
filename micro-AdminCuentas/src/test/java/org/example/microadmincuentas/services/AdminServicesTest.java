package org.example.microadmincuentas.services;

import org.example.microadmincuentas.Entities.Tarifa;
import org.example.microadmincuentas.FeignClients.GestorCuentasClient;
import org.example.microadmincuentas.FeignClients.monopatinFeignClient;
import org.example.microadmincuentas.Repository.RepositoryAdmin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminServicesTest {

    @Mock
    private RepositoryAdmin repository;

    @Mock
    private GestorCuentasClient feignCuentas;

    @InjectMocks
    private AdminServices adminServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAsignarTarifa() {
        Tarifa tarifa = new Tarifa();
        tarifa.setIdTarifa(1L);
        tarifa.setNombreTarifa("Tarifa Test");
        tarifa.setPrecio_tarifa(100);
        tarifa.setFecha_actualizacion(LocalDate.now());

        when(repository.save(tarifa)).thenReturn(tarifa);

        ResponseEntity<?> response = adminServices.AsignarTarifa(tarifa);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tarifa, response.getBody());
        verify(repository, times(1)).save(tarifa);
    }

    @Test
    void testAsignarTarifa_tarifaInvalida() {
        Tarifa tarifa = new Tarifa();
        tarifa.setPrecio_tarifa(-100);

        ResponseEntity<?> response = adminServices.AsignarTarifa(tarifa);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("error al insertar los datos", response.getBody());
        verify(repository, never()).save(any());
    }

    @Test
    void testAnularCuenta() {
        Long id = 1L;
        when(feignCuentas.anularCuenta(id)).thenReturn("Cuenta anulada");

        ResponseEntity<?> response = adminServices.anularCuenta(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cuenta anulada exitosamente", response.getBody());
        verify(feignCuentas, times(1)).anularCuenta(id);
    }

    @Test
    void testAnularCuenta_error() {
        Long id = 1L;
        when(feignCuentas.anularCuenta(id)).thenReturn("error");

        ResponseEntity<?> response = adminServices.anularCuenta(id);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al anular una cuenta", response.getBody());
        verify(feignCuentas, times(1)).anularCuenta(id);
    }

    @Test
    void testEliminarTarifa_existeTarifa() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);

        HttpStatus response = adminServices.eliminarTarifa(id);

        assertEquals(HttpStatus.OK, response);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void testEliminarTarifa_noExisteTarifa() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);

        HttpStatus response = adminServices.eliminarTarifa(id);

        assertEquals(HttpStatus.NOT_FOUND, response);
        verify(repository, never()).deleteById(any());
    }
}
