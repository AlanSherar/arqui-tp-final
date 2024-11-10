package org.example.microgestorviajes.Service;
import jakarta.persistence.EntityNotFoundException;
import org.example.microgestorviajes.Entity.Viaje;
import org.example.microgestorviajes.Repository.ViajeRepository;
import org.example.microgestorviajes.clienteFeign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViajeService {

    @Autowired
    private MonopatinClient monopatinClient;
    @Autowired
    private UsuarioClient usuarioClient;
    @Autowired
    private ParadaClient paradaClient;
    @Autowired
    private ViajeRepository viajeRepository;

    public List<Viaje> getAll(){
        List<Viaje> viajes = viajeRepository.findAll();
        return viajes;
    }

    public Viaje crearViaje(Viaje v ) {
        return viajeRepository.save(v);
    }

    public Viaje getById(long id) {
        return viajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con ID: " + id));
    }

    public Viaje updateViaje(long id, Viaje v) {
        Viaje viajeBd = viajeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con ID: " + id));
        viajeBd.setFecha(v.getFecha());
        viajeBd.setHoraInicio(v.getHoraInicio());
        viajeBd.setPrecio(v.getPrecio());
        viajeBd.setTiempoDePausa(v.getTiempoDePausa());
        return  viajeRepository.save(viajeBd);
    }

    public void deleteViajeByID(long id) {
        if (!viajeRepository.existsById(id)) {
            throw new EntityNotFoundException("Entidad no encontrada: " + id);
        }
        viajeRepository.deleteById(id);
    }

}

