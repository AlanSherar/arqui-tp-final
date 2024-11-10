package org.example.microgestorviajes.Service;
import org.example.microgestorviajes.Entity.Viaje;
import org.example.microgestorviajes.Repository.ViajeRepository;
import org.example.microgestorviajes.clienteFeign.*;
import org.springframework.stereotype.Service;

@Service
public class ViajeService {

    private final MonopatinClient monopatinClient;
    private final UsuarioClient usuarioClient;
    private final ParadaClient paradaClient;
    private final ViajeRepository viajeRepository;

    public ViajeService(MonopatinClient monopatinClient, UsuarioClient usuarioClient, ParadaClient paradaClient, ViajeRepository viajeRepository) {
        this.monopatinClient = monopatinClient;
        this.usuarioClient = usuarioClient;
        this.paradaClient = paradaClient;
        this.viajeRepository = viajeRepository;
    }


    public Viaje crearViaje(Long monopatinId, Long usuarioId, Long paradaId) {
        Monopatin monopatin = monopatinClient.getMonopatinById(monopatinId);
        Usuario usuario = usuarioClient.getUsuarioById(usuarioId);
        Parada parada = paradaClient.getParadaById(paradaId);

        if (monopatin == null || usuario == null || parada == null) {
            throw new RuntimeException("Error");
        }
        // IMPLEMENTAR
        Viaje viaje = new Viaje();
        return viajeRepository.save(viaje);
    }

}

