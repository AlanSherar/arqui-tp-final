package org.example.microgestordecuentas.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.microgestordecuentas.Entity.CuentasAsociadas;
import org.example.microgestordecuentas.Entity.Usuario;
import org.example.microgestordecuentas.repository.CuentasAsociadasRepository;
import org.example.microgestordecuentas.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GestorCuentasServicesTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CuentasAsociadasRepository cuentasAsociadasRepository;

    @InjectMocks
    private GestorCuentasServices gestorCuentasServices;

    private Usuario usuario;
    private CuentasAsociadas cuentaAsociada;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId_usuario(1L);
        usuario.setUsername("testUser");
        usuario.setEmail("test@user.com");

        cuentaAsociada = new CuentasAsociadas();
        cuentaAsociada.setId(1L);
        cuentaAsociada.setSaldo(1000);
    }

    @Test
    public void testGetAllUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        var usuarios = gestorCuentasServices.getAll();

        assertEquals(1, usuarios.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    public void testGetByIdUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        var foundUser = gestorCuentasServices.getById(1L);

        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetByIdUsuarioNotFound() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> gestorCuentasServices.getById(1L));
    }

    @Test
    public void testSaveOneUsuario() {
        gestorCuentasServices.saveOne(usuario);

        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testUpdateUsuario() {
        Usuario updatedUsuario = new Usuario();
        updatedUsuario.setUsername("updatedUser");
        updatedUsuario.setEmail("updated@user.com");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(updatedUsuario);

        var result = gestorCuentasServices.updateUsuario(1L, updatedUsuario);

        assertEquals("updatedUser", result.getUsername());
        assertEquals("updated@user.com", result.getEmail());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    public void testDeleteUsuarioById() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);

        gestorCuentasServices.deleteUsuarioByID(1L);

        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteUsuarioByIdNotFound() {
        when(usuarioRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> gestorCuentasServices.deleteUsuarioByID(1L));
    }

    @Test
    public void testGetAllCuentasAsociadas() {
        when(cuentasAsociadasRepository.findAll()).thenReturn(List.of(cuentaAsociada));

        var cuentas = gestorCuentasServices.getAllCuentasAsociadas();

        assertEquals(1, cuentas.size());
        verify(cuentasAsociadasRepository, times(1)).findAll();
    }

    @Test
    public void testSaveOneCuentas() {
        gestorCuentasServices.saveOneCuentas(cuentaAsociada);

        verify(cuentasAsociadasRepository, times(1)).save(cuentaAsociada);
    }

    @Test
    public void testGetCuentaById() {
        when(cuentasAsociadasRepository.findById(1L)).thenReturn(Optional.of(cuentaAsociada));

        var foundCuenta = gestorCuentasServices.GetCuentaById(1L);

        assertNotNull(foundCuenta);
        assertEquals(1000.0, foundCuenta.getSaldo());
        verify(cuentasAsociadasRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetCuentaByIdNotFound() {
        when(cuentasAsociadasRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> gestorCuentasServices.GetCuentaById(1L));
    }

    @Test
    public void testUpdateCuenta() {
        CuentasAsociadas updatedCuenta = new CuentasAsociadas();
        updatedCuenta.setSaldo(1500);

        when(cuentasAsociadasRepository.findById(1L)).thenReturn(Optional.of(cuentaAsociada));
        when(cuentasAsociadasRepository.save(any(CuentasAsociadas.class))).thenReturn(updatedCuenta);

        var result = gestorCuentasServices.updateCuenta(1L, updatedCuenta);

        assertEquals(1500.0, result.getSaldo());
        verify(cuentasAsociadasRepository, times(1)).save(any(CuentasAsociadas.class));
    }

    @Test
    public void testDeleteCuentaById() {
        when(cuentasAsociadasRepository.existsById(1L)).thenReturn(true);

        var response = gestorCuentasServices.deleteCuentaById(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(cuentasAsociadasRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteCuentaByIdNotFound() {
        when(cuentasAsociadasRepository.existsById(1L)).thenReturn(false);

        var response = gestorCuentasServices.deleteCuentaById(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testAsociarCuentaUsuarioNotFound() {
        when(usuarioRepository.existsById(1L)).thenReturn(false);
        when(cuentasAsociadasRepository.existsById(1L)).thenReturn(true);

        var response = gestorCuentasServices.asociar(1L, 1L);

        assertEquals(500, response.getStatusCodeValue());
    }

}
