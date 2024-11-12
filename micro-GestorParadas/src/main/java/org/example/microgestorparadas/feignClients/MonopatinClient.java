package org.example.microgestorviajes.clienteFeign;
import org.example.microgestorparadas.feignClients.Monopatin;
import org.springframework.web.bind.annotation.*;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="micro-GestorCuentas")
public interface MonopatinClient {

    @GetMapping("/GestorCuentas/usuarios/{id}")
    Monopatin getMonopatinById(@PathVariable("id") Long id);

}
