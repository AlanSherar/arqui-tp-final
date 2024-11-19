package org.example.microgestorviajes.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.microgestorviajes.Entity.Viaje;
import org.example.microgestorviajes.Repository.ViajeRepository;
import org.example.microgestorviajes.Service.ViajeService;
import org.example.microgestorviajes.clienteFeign.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViajeServiceTest {

    @Mock
    private ViajeRepository viajeRepository;
    @Mock
    private MonopatinClient monopatinClient;
    @Mock
    private ParadaClient paradaClient;
    @Mock
    private AdminClient adminClient;

    @InjectMocks
    private ViajeService viajeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearViaje() {
        Viaje viaje = new Viaje();
        viaje.setId(1L);
        viaje.setFecha(LocalDate.now());
        viaje.setHoraInicio(8);
        viaje.setPrecio(20.0);
        viaje.setTiempoDePausa(15);

        when(viajeRepository.save(viaje)).thenReturn(viaje);

        Viaje result = viajeService.crearViaje(viaje);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(viajeRepository, times(1)).save(viaje);
    }

    @Test
    void testGetById() {
        long viajeId = 1L;
        Viaje viaje = new Viaje();
        viaje.setId(viajeId);

        when(viajeRepository.findById(viajeId)).thenReturn(Optional.of(viaje));

        Viaje result = viajeService.getById(viajeId);

        assertNotNull(result);
        assertEquals(viajeId, result.getId());
    }

    @Test
    void testGetById_ViajeNotFound() {
        long viajeId = 1L;

        when(viajeRepository.findById(viajeId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> viajeService.getById(viajeId));
    }

    @Test
    void testFinalizarViaje_ParadaValida() {
        long viajeId = 1L;
        int ubicacionX = 10;
        int ubicacionY = 20;

        Viaje viaje = new Viaje();
        viaje.setId(viajeId);
        viaje.setMonopatinId(100L);
        viaje.setTarifaId(200L);

        Monopatin monopatin = new Monopatin();
        monopatin.setId(100L);
        monopatin.setUbicacionX(10);
        monopatin.setUbicacionY(20);

        Parada parada = new Parada();
        parada.setUbicacionX(ubicacionX);
        parada.setUbicacionY(ubicacionY);

        Tarifa tarifa = new Tarifa();

        ResponseEntity<Parada> responseParada = new ResponseEntity<>(parada, HttpStatus.OK);
        ResponseEntity<Monopatin> responseMonopatin = new ResponseEntity<>(monopatin, HttpStatus.OK);
        ResponseEntity<Tarifa> responseTarifa = new ResponseEntity<>(tarifa, HttpStatus.OK);

        when(viajeRepository.findById(viajeId)).thenReturn(Optional.of(viaje));
        when(paradaClient.getByUbicacion(ubicacionX, ubicacionY)).thenReturn(responseParada);
        when(monopatinClient.getMonopatinById(viaje.getMonopatinId())).thenReturn(responseMonopatin);
        when(monopatinClient.updateById(monopatin.getId(), monopatin)).thenReturn(responseMonopatin);

        when(adminClient.obtenerTarifa(viaje.getTarifaId())).thenReturn(tarifa);

        ResponseEntity<?> response = viajeService.finalizarViaje(viajeId, ubicacionX, ubicacionY);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertTrue(response.getBody().toString().contains("Viaje finalizado correctamente"));

        verify(viajeRepository, times(1)).findById(viajeId);
        verify(paradaClient, times(1)).getByUbicacion(ubicacionX, ubicacionY);
        verify(monopatinClient, times(1)).getMonopatinById(viaje.getMonopatinId());
        verify(monopatinClient, times(1)).updateById(monopatin.getId(), monopatin);
        verify(adminClient, times(1)).obtenerTarifa(viaje.getTarifaId());
    }


    @Test
    void testFinalizarViaje_ParadaNoValida() {
        long viajeId = 1L;
        int ubicacionX = 10;
        int ubicacionY = 20;
        Viaje viaje = new Viaje();
        viaje.setId(viajeId);

        ResponseEntity<Parada> responseParada = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        when(viajeRepository.findById(viajeId)).thenReturn(Optional.of(viaje));
        when(paradaClient.getByUbicacion(ubicacionX, ubicacionY)).thenReturn(responseParada);

        ResponseEntity<?> response = viajeService.finalizarViaje(viajeId, ubicacionX, ubicacionY);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("El monopatín no está en una parada válida"));
    }
}
