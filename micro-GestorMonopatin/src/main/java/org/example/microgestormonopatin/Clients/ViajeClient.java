package org.example.microgestormonopatin.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="micro-GestorViajes", url="http://localhost:8093/viajes")
public interface ViajeClient {

    @GetMapping("/cantViajes/idMonopatin/{idMonopatin}/year/{anio}")
    int getCantViajesByYear(@PathVariable("idMonopatin") Long idMonopatin, @PathVariable("anio") int anio);

}