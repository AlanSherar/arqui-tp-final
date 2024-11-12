package org.example.microadmincuentas.services;

import org.example.microadmincuentas.Entities.Tarifa;
import org.example.microadmincuentas.FeignClients.GestorCuentasClient;
import org.example.microadmincuentas.Repository.RepositoryAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminServices {
    @Autowired
    private RepositoryAdmin repository;
    @Autowired
    private GestorCuentasClient feignCuentas;
//    private  GestorMonopatinClient feignMonopatin;
    public ResponseEntity<?> AsignarTarifa (Tarifa t){

            if(t.getIdTarifa()==null || t.getNombreTarifa()==null ||
            t.getPrecioTarifa()<0 || t.getFechaActualizacion()==null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al insertar los datos");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(t));

    }
    public ResponseEntity<?> anularCuenta(Long id){
        ResponseEntity<?> response =feignCuentas.anularCuenta(id);
        if(response.getStatusCode() != HttpStatus.OK ){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al anular una cuenta");

        }
        return  ResponseEntity.status(HttpStatus.OK).body("Cuenta anulada exitosamente");
    }

}
