package org.example.microgestormonopatin.services;

import org.example.microgestormonopatin.Entity.Monopatin;
import org.example.microgestormonopatin.Repository.MonopatinRepository;
import org.example.microgestormonopatin.Dto.MonopatinKmsDTO;
import org.example.microgestormonopatin.Service.MonopatinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MonopatinServiceTest {

    @Mock
    private MonopatinRepository monopatinRepository;

    @InjectMocks
    private MonopatinService monopatinService;

    private Monopatin monopatin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        monopatin = new Monopatin();
        monopatin.setId(1L);
        monopatin.setKms(100.0);
        monopatin.setDisponible(true);
        monopatin.setTiempo_de_uso(10.5);
        monopatin.setCant_viajes(5);
        monopatin.setTiempo_pausa(2.5);
        monopatin.setMantenimiento_tiempo_uso(15.0);
        monopatin.setMantenimiento_kms(120.0);
        monopatin.setUbicacionX(10);
        monopatin.setUbicacionY(20);
    }

    @Test
    void getAllMonopatines() {
        when(monopatinRepository.findAll()).thenReturn(List.of(monopatin));

        List<Monopatin> result = monopatinService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(monopatinRepository, times(1)).findAll();
    }

    @Test
    void getAllThrowIllegalStateException() {
        when(monopatinRepository.findAll()).thenReturn(List.of());

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> monopatinService.getAll());
        assertEquals("No hay monopatines disponibles.", exception.getMessage());
    }

    @Test
    void findByIdMonopatin() throws Exception {
        when(monopatinRepository.findById(1L)).thenReturn(Optional.of(monopatin));

        Monopatin result = monopatinService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(monopatinRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdNull() {
        when(monopatinRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> monopatinService.findById(1L));
    }

    @Test
    void saveMonopatin() throws Exception {
        when(monopatinRepository.save(any(Monopatin.class))).thenReturn(monopatin);

        Monopatin result = monopatinService.save(monopatin);

        assertNotNull(result);
        assertEquals(monopatin, result);
        verify(monopatinRepository, times(1)).save(monopatin);
    }

    @Test
    void saveThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> monopatinService.save(null));
        assertEquals("El monopatín proporcionado no puede ser nulo.", exception.getMessage());
    }

    @Test
    void saveMonopatinThrowIllegalStateException() throws Exception {
        when(monopatinRepository.existsById(1L)).thenReturn(true);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> monopatinService.save(monopatin));
        assertEquals("El monopatín con ID " + monopatin.getId() + " ya existe.", exception.getMessage());
    }

    @Test
    void deleteMonopatinExists() throws Exception {
        when(monopatinRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<?> response = monopatinService.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Eliminación exitosa", response.getBody());
        verify(monopatinRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteMonopatinDoesNotExist() throws Exception {
        when(monopatinRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<?> response = monopatinService.delete(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("El monopatín con ID " + 1L + " no existe", response.getBody());
    }

    @Test
    void modificarMonopatin() {
        when(monopatinRepository.existsById(1L)).thenReturn(true);
        when(monopatinRepository.save(any(Monopatin.class))).thenReturn(monopatin);

        Monopatin result = monopatinService.modificarMonopatin(1L, monopatin);

        assertNotNull(result);
        assertEquals(monopatin.getId(), result.getId());
        verify(monopatinRepository, times(1)).save(monopatin);
    }

    @Test
    void modificarMonopatinNull() {
        when(monopatinRepository.existsById(1L)).thenReturn(false);

        Monopatin result = monopatinService.modificarMonopatin(1L, monopatin);

        assertNull(result);
    }

    @Test
    void getMonopatinesByKms() {
        when(monopatinRepository.getMonopatinesByKms()).thenReturn(List.of(monopatin));

        List<MonopatinKmsDTO> result = monopatinService.getMonopatinesByKms();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(monopatinRepository, times(1)).getMonopatinesByKms();
    }

    @Test
    void getMonopatinesByKmsThrowIllegalStateException() {
        when(monopatinRepository.getMonopatinesByKms()).thenReturn(List.of());

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> monopatinService.getMonopatinesByKms());
        assertEquals("No hay monopatines disponibles.", exception.getMessage());
    }

}
