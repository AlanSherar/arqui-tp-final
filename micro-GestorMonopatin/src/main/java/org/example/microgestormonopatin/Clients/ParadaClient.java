package org.example.microgestormonopatin.Clients;

import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="micro-GestorParadas")
public interface ParadaClient {

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable Long id);


}
