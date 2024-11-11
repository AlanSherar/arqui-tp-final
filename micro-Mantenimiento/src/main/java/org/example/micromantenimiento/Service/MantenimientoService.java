package org.example.micromantenimiento.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.micromantenimiento.Entity.MantenimientoMonopatin;
import org.example.micromantenimiento.Repository.MantenimientoRepository;
import org.example.micromantenimiento.models.Monopatin;
import org.example.micromantenimiento.feignClients.MonopatinFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private MonopatinFeignClient monopatinFeignClient;
    private  final int kmsMantenimiento = 50;

    public Monopatin obtenerMonopatinPorId(Long monopatinId) {
        return monopatinFeignClient.getMonopatinById(monopatinId).getBody();
    }

    public void realizarMantenimiento(Long idMonopatin) {
        ResponseEntity<Monopatin> response= monopatinFeignClient.getMonopatinById(idMonopatin);
        if(response.getStatusCode() == HttpStatus.OK){
            Monopatin MonopatinFeign = response.getBody();
            MonopatinFeign.setKilometros(MonopatinFeign.getKilometros()+kmsMantenimiento);
            MonopatinFeign.setdisponible(true);
            monopatinFeignClient.ActualizarMonopatin(idMonopatin,MonopatinFeign);
        }

    }
}