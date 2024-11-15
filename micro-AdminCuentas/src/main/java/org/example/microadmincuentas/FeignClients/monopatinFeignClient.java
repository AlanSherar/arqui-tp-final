package org.example.microadmincuentas.FeignClients;

import org.example.microadmincuentas.Models.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "micro-GestorMonopatin" ,url = "http://localhost:8094/monopatines")
public interface monopatinFeignClient {
    @GetMapping("/cantViajes/{viajes}/fecha/{fecha}")
    public ResponseEntity<List<Monopatin>> getviajesbyCantViajesAndFecha(@PathVariable int cantViajes, @PathVariable  int fecha);

    @GetMapping("/estadisticas")
    public  ResponseEntity<String> getEstadisticas();


}
