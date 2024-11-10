package org.example.microgestordecuentas.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.microgestordecuentas.Entity.CuentasAsociadas;
import org.example.microgestordecuentas.Entity.Usuario;
import org.example.microgestordecuentas.repository.CuentasAsociadasRepository;
import org.example.microgestordecuentas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestorCuentasServices {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private CuentasAsociadasRepository cuentasRepository;

    public List<Usuario> getAll(){

        List<Usuario> us = repository.findAll();
        return us;

    }

    public void saveOne(Usuario u){
         repository.save(u);
    }


    public Usuario getById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
    }

    public Usuario updateUsuario(long id, Usuario u) {

     Usuario usuarioBd = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
      usuarioBd.setUsername(u.getUsername());
      usuarioBd.setNumero_celular(u.getNumero_celular());
      usuarioBd.setEmail(u.getEmail());
      usuarioBd.setNombre(u.getNombre());
      usuarioBd.setApellido(u.getApellido());
      usuarioBd.setRol(u.getRol());
      return  repository.save(usuarioBd);


    }

    public void deleteUsuarioByID(long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Entidad no encontrada: " + id);
        }
        repository.deleteById(id);
    }

    public List<CuentasAsociadas> getAllCuentasAsociadas() {
        return cuentasRepository.findAll();
    }
    public void saveOneCuentas( CuentasAsociadas c){
        cuentasRepository.save(c);
    }

    public CuentasAsociadas GetCuentaById(long id) {

            return cuentasRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada con ID: " + id));

    }
    public CuentasAsociadas updateCuenta (Long id,CuentasAsociadas c){
        CuentasAsociadas cuentaBd = cuentasRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        cuentaBd.setFechaAlta(c.getFechaAlta());
        cuentaBd.setSaldo(c.getSaldo());
        cuentaBd.setHabilitada(c.isHabilitada());
        return  cuentasRepository.save(cuentaBd);
    }

    public void deleteCuentaById(long id) {
        if (!cuentasRepository.existsById(id)) {
                throw new EntityNotFoundException("Entidad no encontrada: " + id);
            }
        cuentasRepository.deleteById(id);

    }
}
