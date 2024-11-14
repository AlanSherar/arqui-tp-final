package org.example.microgestormonopatin.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "micro-Mantenimiento", url="http://localhost:8097/mantenimiento")
public interface MantenimientoClient {

    @PutMapping("/realizar/{idMonopatin}")
    ResponseEntity<String> realizarMantenimiento(@PathVariable Long idMonopatin);
}
