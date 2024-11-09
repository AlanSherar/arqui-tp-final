package org.example.microgestordecuentas.services;

import com.netflix.discovery.converters.Auto;
import org.example.microgestordecuentas.Entity.Usuario;
import org.example.microgestordecuentas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicroGestorCuentasServices {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> getAll(){

        List<Usuario> us = repository.findAll();
        System.out.println("SERVICE");
        System.out.println(us);
        return us;

    }

    public void saveOne(Usuario u){
         repository.save(u);
    }


}
