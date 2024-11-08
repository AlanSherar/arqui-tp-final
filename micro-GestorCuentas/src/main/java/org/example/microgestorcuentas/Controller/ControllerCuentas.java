package org.example.microgestorcuentas.Controller;

import org.example.microgestorcuentas.Service.GestorCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gestorCuentas")
public class ControllerCuentas {

    @Autowired
     private GestorCuentaService service;

    @GetMapping("/{login}")
    public void getLogin(@PathVariable("login") String login){
        proxy.get(login);
    }

}
