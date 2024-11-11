package org.example.microgestormonopatin.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="micro-GestorMantenimiento")
public interface MantenimientoClient {

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id);
}
