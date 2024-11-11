package org.example.microgestorviajes.clienteFeign;
import org.example.microgestorparadas.feignClients.Monopatin;
import org.springframework.web.bind.annotation.*;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="micro-GestorMonopatin")
public interface MonopatinClient {

    @GetMapping("/monopatines/{id}")
    Monopatin getMonopatinById(@PathVariable("id") Long id);

}
