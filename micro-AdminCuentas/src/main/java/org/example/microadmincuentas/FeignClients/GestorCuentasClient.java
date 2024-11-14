package org.example.microadmincuentas.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="micro-gestorCuentas" ,url="http://localhost:8091/GestorCuentas")
public interface GestorCuentasClient {


    @DeleteMapping("/CuentasAsociadas/{id}")
    String anularCuenta(@PathVariable Long id);
}

