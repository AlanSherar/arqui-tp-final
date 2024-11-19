package org.example.micromantenimiento.services;

import org.example.micromantenimiento.Service.MantenimientoService;
import org.example.micromantenimiento.feignClients.MonopatinFeignClient;
import org.example.micromantenimiento.models.Monopatin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MantenimientoServiceTest {

    @InjectMocks
    private MantenimientoService mantenimientoService;

    @Mock
    private MonopatinFeignClient monopatinClient;

    private Monopatin monopatin;
    private Long monopatinId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        monopatin = new Monopatin();
        monopatin.setId(monopatinId);
        monopatin.setKms(400); // Inicializamos con 400 kms
        monopatin.setTiempo_de_uso(150); // Inicializamos con 150 horas de uso
        monopatin.setMantenimiento_kms(500); // El mantenimiento es cada 500 kms
        monopatin.setMantenimiento_tiempo_uso(200); // El mantenimiento es cada 200 horas
        monopatin.setDisponible(true);
    }

    @Test
    void testRealizarMantenimiento_MonopatinNoEncontrado() {
        when(monopatinClient.getMonopatinById(monopatinId))
                .thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        HttpStatus result = mantenimientoService.realizarMantenimiento(monopatinId);
        assertEquals(HttpStatus.NOT_FOUND, result);
    }

    @Test
    void testRealizarMantenimiento_ErrorDeServidor() {
        when(monopatinClient.getMonopatinById(monopatinId))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        HttpStatus result = mantenimientoService.realizarMantenimiento(monopatinId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result);
    }

    @Test
    void testRealizarMantenimiento_NoNecesitaMantenimiento() {
        monopatin.setKms(100);
        monopatin.setTiempo_de_uso(50);

        when(monopatinClient.getMonopatinById(monopatinId))
                .thenReturn(new ResponseEntity<>(monopatin, HttpStatus.OK));

        HttpStatus result = mantenimientoService.realizarMantenimiento(monopatinId);
        assertEquals(HttpStatus.BAD_REQUEST, result);
    }

    @Test
    void testRealizarMantenimiento_NecesitaMantenimiento_Exito() {
        monopatin.setKms(500);
        monopatin.setTiempo_de_uso(200);

        when(monopatinClient.getMonopatinById(monopatinId))
                .thenReturn(new ResponseEntity<>(monopatin, HttpStatus.OK));

        when(monopatinClient.updateMonopatin(eq(monopatinId), any(Monopatin.class)))
                .thenReturn(new ResponseEntity<>(monopatin, HttpStatus.OK));

        HttpStatus result = mantenimientoService.realizarMantenimiento(monopatinId);
        assertEquals(HttpStatus.OK, result);
    }

    @Test
    void testRealizarMantenimiento_ErrorDeActualizacion() {
        monopatin.setKms(500);
        monopatin.setTiempo_de_uso(200);

        when(monopatinClient.getMonopatinById(monopatinId))
                .thenReturn(new ResponseEntity<>(monopatin, HttpStatus.OK));

        when(monopatinClient.updateMonopatin(eq(monopatinId), any(Monopatin.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        HttpStatus result = mantenimientoService.realizarMantenimiento(monopatinId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result);
    }

    @Test
    void testRealizarMantenimiento_Exception() {
        when(monopatinClient.getMonopatinById(monopatinId))
                .thenThrow(new RuntimeException("Error inesperado"));

        HttpStatus result = mantenimientoService.realizarMantenimiento(monopatinId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result);
    }
}
