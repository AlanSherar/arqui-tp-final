package org.example.microgestormonopatin.Clients;

import org.example.microgestormonopatin.Entity.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="micro-GestorViajes", url="http://localhost:8093/viajes")
public interface ViajeClient {

    @GetMapping("/cantViajes/{cantViajes}/year/{anio}")
    List<Monopatin> getCantViajesByYear(@PathVariable  int cantViajes, @RequestBody List<Monopatin> monopatines, @PathVariable("anio") int anio);

}