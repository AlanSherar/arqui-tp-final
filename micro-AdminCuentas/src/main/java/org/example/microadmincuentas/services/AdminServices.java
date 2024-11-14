package org.example.microadmincuentas.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.microadmincuentas.Entities.Tarifa;
import org.example.microadmincuentas.FeignClients.GestorCuentasClient;
import org.example.microadmincuentas.Repository.RepositoryAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServices {
    @Autowired
    private RepositoryAdmin repository;
    @Autowired
    private GestorCuentasClient feignCuentas;
//    private  GestorMonopatinClient feignMonopatin;
    public ResponseEntity<?> AsignarTarifa (Tarifa t){

            if(t.getIdTarifa()==null || t.getNombreTarifa()==null ||
            t.getPrecio_tarifa()<0 || t.getFecha_actualizacion()==null){

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al insertar los datos");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(t));

    }
    public ResponseEntity<?> anularCuenta(Long id){
        String response =feignCuentas.anularCuenta(id);
        System.out.println( "res "+ response);
        if ("error".equalsIgnoreCase(response)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al anular una cuenta");
        }
        return  ResponseEntity.status(HttpStatus.OK).body("Cuenta anulada exitosamente");
    }

    public  HttpStatus eliminarTarifa(Long id) {
        try{

            // Verifica si la tarifa existe
            if (!repository.existsById(id)) {

                return HttpStatus.NOT_FOUND;
            }
            repository.deleteById(id);
            return HttpStatus.OK;
        }catch (Exception e){

            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public HttpStatus modificarTarifa(Long id, Tarifa t) {
        System.out.print(t);
//        if(t.getFecha_actualizacion()==null || t.getPrecio_tarifa()>0 || t.getNombreTarifa()==null){
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
        if(!repository.existsById(id)){
            return HttpStatus.NOT_FOUND;
        }
        Tarifa tarifaUpdate = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con ID: " + id));
        tarifaUpdate.setNombreTarifa(t.getNombreTarifa());
        tarifaUpdate.setPrecio_tarifa(t.getPrecio_tarifa());
        tarifaUpdate.setFecha_actualizacion(t.getFecha_actualizacion());
        repository.save(tarifaUpdate);
        return HttpStatus.OK;


    }

    public List<Tarifa> getAll() {
        return repository.findAll();
    }

    public Tarifa getTarifaById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarifa no encontrada con el id: " + id));
    }
}
