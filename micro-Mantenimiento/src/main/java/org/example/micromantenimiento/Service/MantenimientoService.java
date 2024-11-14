package org.example.micromantenimiento.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.example.micromantenimiento.Entity.MantenimientoMonopatin;
import org.example.micromantenimiento.Repository.MantenimientoRepository;
import org.example.micromantenimiento.models.Monopatin;
import org.example.micromantenimiento.feignClients.MonopatinFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.DataInput;

@Service
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private MonopatinFeignClient monopatinFeignClient;
    private final int kmsMantenimiento = 50;

    public Monopatin obtenerMonopatinPorId(Long monopatinId) {
        return monopatinFeignClient.getMonopatinById(monopatinId);
    }

    public void realizarMantenimiento(Long idMonopatin) {
        try {

            Monopatin monopatin = monopatinFeignClient.getMonopatinById(idMonopatin);
            System.out.println(monopatin);

            if (monopatin==null) {
                return ;
            }

                    System.out.println( "KM MONOPATIN"+monopatin.getKms());
                     monopatin.setKms(monopatin.getKms() + kmsMantenimiento);
                    monopatin.setDisponible(true);

                    ResponseEntity<?> updateResponse = monopatinFeignClient.UpdateMonopatin(idMonopatin,monopatin);

                    if (updateResponse.getStatusCode().is2xxSuccessful()) {
                        System.out.println(monopatinFeignClient.getMonopatinById(idMonopatin));
                        System.out.println("Monopatín actualizado con éxito.");
                    } else {
                        System.out.println("Error al actualizar el monopatín: " + updateResponse.getStatusCode());
                    }


        } catch (Exception e) {
            System.err.println("Error al realizar mantenimiento: " + e.getMessage());

        }
    }

}