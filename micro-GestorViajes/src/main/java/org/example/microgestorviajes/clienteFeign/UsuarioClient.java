package org.example.microgestorviajes.clienteFeign;
import org.springframework.web.bind.annotation.*;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="Micro-GestorDeCuentas")
public interface UsuarioClient {

    @GetMapping("/usuarios/{id}")
    Usuario getUsuarioById(@PathVariable("id") Long id);

}
