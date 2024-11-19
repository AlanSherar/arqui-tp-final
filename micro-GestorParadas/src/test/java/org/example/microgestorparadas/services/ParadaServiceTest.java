package org.example.microgestorparadas.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.microgestorparadas.Entity.Parada;
import org.example.microgestorparadas.Repository.ParadasRepository;
import org.example.microgestorparadas.Service.ParadasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParadasServiceTest {

    @Mock
    private ParadasRepository repository;

    @InjectMocks
    private ParadasService paradasService;

    private Parada parada;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        parada = new Parada();
        parada.setId(1L);
        parada.setUbicacionX(10);
        parada.setUbicacionY(20);
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(parada));
        var result = paradasService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(parada, result.get(0));
    }

    @Test
    void findById() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.of(parada));
        var result = paradasService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(parada, result.get());
    }

    @Test
    void findByIdNotFound() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        var result = paradasService.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    void save() throws Exception {
        when(repository.save(parada)).thenReturn(parada);
        var result = paradasService.save(parada);
        assertNotNull(result);
        assertEquals(parada, result);
    }

    @Test
    void deleteExists() throws Exception {
        when(repository.existsById(1L)).thenReturn(true);
        var response = paradasService.delete(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Eliminación exitosa", response.getBody());
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deleteNotFound() throws Exception {
        when(repository.existsById(1L)).thenReturn(false);
        var response = paradasService.delete(1L);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("La entidad con ID 1 no existe", response.getBody());
        verify(repository, times(0)).deleteById(1L);
    }

    @Test
    void updateParada() {
        Parada updatedParada = new Parada();
        updatedParada.setUbicacionX(30);
        updatedParada.setUbicacionY(40);
        when(repository.findById(1L)).thenReturn(Optional.of(parada));
        when(repository.save(any(Parada.class))).thenReturn(updatedParada);

        Parada result = paradasService.updateParada(1L, updatedParada);

        assertNotNull(result);
        assertEquals(30, result.getUbicacionX());
        assertEquals(40, result.getUbicacionY());
        verify(repository, times(1)).save(any(Parada.class));
    }

    @Test
    void updateParadaNotFound() {
        Parada updatedParada = new Parada();
        updatedParada.setUbicacionX(30);
        updatedParada.setUbicacionY(40);
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> paradasService.updateParada(1L, updatedParada));
    }

    @Test
    void findByUbicacion() {
        when(repository.getByUbicacion(10, 20)).thenReturn(ResponseEntity.ok(parada));
        ResponseEntity<?> response = paradasService.findByUbicacion(10, 20);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(parada, response.getBody());
    }
}
